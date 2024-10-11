package io.github.tuanthhtq.trialswiftbillsb.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tuanthhtq.trialswiftbillsb.configs.implement.UserDetailsImpl;
import io.github.tuanthhtq.trialswiftbillsb.dtos.PagedResponse;
import io.github.tuanthhtq.trialswiftbillsb.dtos.Response;
import io.github.tuanthhtq.trialswiftbillsb.dtos.bill.BillDetailResponse;
import io.github.tuanthhtq.trialswiftbillsb.dtos.bill.BillInfoResponse;
import io.github.tuanthhtq.trialswiftbillsb.dtos.bill.BillSearchRequest;
import io.github.tuanthhtq.trialswiftbillsb.dtos.payment.PaymentCreationResponse;
import io.github.tuanthhtq.trialswiftbillsb.dtos.payment.PaymentRequestDetail;
import io.github.tuanthhtq.trialswiftbillsb.dtos.product.ProductInfoResponse;
import io.github.tuanthhtq.trialswiftbillsb.dtos.product.ProductSearchRequest;
import io.github.tuanthhtq.trialswiftbillsb.entities.Bills;
import io.github.tuanthhtq.trialswiftbillsb.entities.Images;
import io.github.tuanthhtq.trialswiftbillsb.entities.Products;
import io.github.tuanthhtq.trialswiftbillsb.entities.Users;
import io.github.tuanthhtq.trialswiftbillsb.repositories.BillsRepository;
import io.github.tuanthhtq.trialswiftbillsb.repositories.ProductsRepository;
import io.github.tuanthhtq.trialswiftbillsb.repositories.UsersRepository;
import io.github.tuanthhtq.trialswiftbillsb.services.CashierServices;
import io.github.tuanthhtq.trialswiftbillsb.utils.HMacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author io.github.tuanthhtq
 */

@Service
public class CashierServicesImpl extends CommonConcrete implements CashierServices {

	@Value("${payOs.clientId}")
	private String clientId;

	@Value("${payOs.apiKey}")
	private String apiKey;

	@Value("${payOs.endpoint}")
	private String endpoint;


	private final RestTemplate restTemplate;
	private final UsersRepository usersRepo;
	private final ProductsRepository productsRepo;
	private final BillsRepository billsRepo;

	@Autowired
	public CashierServicesImpl(
			RestTemplate restTemplate,
			UsersRepository usersRepo,
			ProductsRepository productsRepo, BillsRepository billsRepo
	) {
		this.restTemplate = restTemplate;
		this.usersRepo = usersRepo;
		this.productsRepo = productsRepo;
		this.billsRepo = billsRepo;
	}

	/**
	 * Process payment creation
	 *
	 * @param requestDetail {@link PaymentRequestDetail}
	 * @return {@link PaymentCreationResponse}
	 */
	private PaymentCreationResponse doCreate(PaymentRequestDetail requestDetail) {
		int orderCode = (int) (System.currentTimeMillis() + requestDetail.orderCode());
		String returnUrl = "http://192.168.31.211:8080/api/v1/payment/test-payment-success";

		//payment data
		String data = String.format("amount=%d&cancelUrl=%s&description=%s&orderCode=%d&returnUrl=%s",
				requestDetail.amount(),
				requestDetail.cancelUrl(),
				requestDetail.description(),
				orderCode, returnUrl
		);
		long expired = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10);

		PaymentRequestDetail newRequest = new PaymentRequestDetail(
				orderCode,
				requestDetail.amount(),
				requestDetail.description(),
				requestDetail.buyerName(),
				requestDetail.buyerPhone(),
				requestDetail.items(),
				requestDetail.cancelUrl(),
				returnUrl,
				HMacProvider.hmacSha256(data),
				expired / 1000
		);


		//configure request to payos server
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-client-id", clientId);
		headers.set("x-api-key", apiKey);
		HttpEntity<PaymentRequestDetail> request = new HttpEntity<PaymentRequestDetail>(newRequest, headers);
		ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.POST, request, String.class);

		//get data from payos server
		if (response.hasBody()) {
			String responseBody = response.getBody();
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				return objectMapper.readValue(responseBody, PaymentCreationResponse.class);
			} catch (NullPointerException | JsonProcessingException e) {
				e.getLocalizedMessage();
			}
		}
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response<PaymentCreationResponse> requestPayment(PaymentRequestDetail requestDetail) {
		Response<PaymentCreationResponse> response = new Response<>();
		Set<String> errors = new HashSet<>();

		response.setMessage("Failed to create payment");
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());

		//check session
		Users user = getSessionUser(usersRepo);

		if (user == null) {
			response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			errors.add("Invalid session");
			response.setErrors(errors);
		} else {
			//create payment
			PaymentCreationResponse payment = doCreate(requestDetail);

			if (payment == null) {
				response.setStatusCode(HttpStatus.BAD_REQUEST.value());
				response.setMessage("Something wrong occurred, please try again");
				errors.add("Failed to create payment");
				response.setErrors(errors);
			} else {
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("Payment created");
				response.setData(payment);

			}
		}
		return response;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response<ProductInfoResponse> getProductInfo(ProductSearchRequest request) {
		Response<ProductInfoResponse> response = new Response<>();
		Set<String> errors = new HashSet<>();

		response.setMessage("Failed to find product info");
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());

		//check session
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

		if (userDetails == null) {
			response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			errors.add("Invalid session");
			response.setErrors(errors);
		} else {
			Users user = usersRepo.findByPhone(userDetails.getPhone()).orElse(null);

			if (user == null) {
				response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
				errors.add("Invalid session");
				response.setErrors(errors);
			} else {

				//find product by barcode or product id
				Products product;
				if (request.barcode() == null) {
					product = productsRepo.findById(request.productId()).orElse(null);
				} else {
					product = productsRepo.findByBarcode(request.barcode()).orElse(null);
				}

				//check product
				if (product == null) {
					response.setMessage("Product not found");
					errors.add("Invalid barcode");
					response.setErrors(errors);
				} else {
					response.setStatusCode(HttpStatus.OK.value());
					response.setMessage(product.getName());

					ProductInfoResponse responseData = new ProductInfoResponse(
							product.getId(),
							product.getName(),
							product.getBrand().getName(),
							product.getImages().stream().map(Images::getUrl).collect(Collectors.toSet()),
							product.getPrice()
					);

					response.setData(responseData);
				}
			}
		}
		return response;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PagedResponse<List<BillInfoResponse>> getAllBillsPaged(BillSearchRequest request, Pageable pageable) {
		PagedResponse<List<BillInfoResponse>> response = new PagedResponse<>();
		Set<String> errors = new HashSet<>();

		response.setMessage("Failed to fetch bills");
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());

		//check session
		Users user = getSessionUser(usersRepo);

		if (user == null) {
			response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			errors.add("Invalid session");
			response.setErrors(errors);
		} else {

			//search
			Page<Bills> billsPaged;

			if (request.cashierName() != null) {
				billsPaged = billsRepo.findByCreator_FullNameContains(request.cashierName(), pageable);
			} else if (request.cashierPhone() != null) {
				billsPaged = billsRepo.findByCreator_PhoneContains(request.cashierPhone(), pageable);
			} else if (request.customerPhone() != null) {
				billsPaged = billsRepo.findByCustomerPhoneContains(request.customerPhone(), pageable);
			} else if (request.customerName() != null) {
				billsPaged = billsRepo.findByCustomerNameContains(request.customerName(), pageable);
			} else {
				billsPaged = billsRepo.findAll(pageable);
			}

			//creating dto response for this function
			List<BillInfoResponse> billList = new ArrayList<>();
			for (Bills bill : billsPaged.getContent()) {
				billList.add(new BillInfoResponse(
						bill.getCustomerName(),
						bill.getCreationDate(),
						bill.getCreator().getFullName(),
						bill.isPaid(),
						bill.getTotalAmount()
				));
			}

			//response data
			response.setMessage("Success");
			response.setData(billList);
			response.setStatusCode(HttpStatus.OK.value());
		}
		return response;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response<BillDetailResponse> getBillDetail(Long billId) {
		Response<BillDetailResponse> response = new Response<>();
		Set<String> errors = new HashSet<>();

		response.setMessage("Failed to get bill detail");
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());

		//check session
		Users user = getSessionUser(usersRepo);

		if (user == null) {
			response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			errors.add("Invalid session");
			response.setErrors(errors);
		} else {
			Bills bill = billsRepo.findById(billId).orElse(null);

			if (bill == null) {
				response.setMessage("Bill not found");
				errors.add("Bill not found");
				response.setErrors(errors);
			} else {
				BillDetailResponse responseData = new BillDetailResponse(
						bill.getOrderCode(),
						bill.getCreator().getFullName(),
						bill.getTotalAmount(),
						bill.getCustomerName(),
						bill.getCustomerPhone(),
						bill.getPaymentMethod().getName(),
						bill.isPaid(),
						bill.getCreationDate(),
						bill.getStore().getName(),
						bill.getStore().getAddress().getDetailedAddress(),
						bill.getStore().getOwner().getPhone(),
						bill.getStore().getOpen(),
						bill.getStore().getClose()
				);

				response.setData(responseData);
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("Success");
			}
		}
		return response;
	}

}
