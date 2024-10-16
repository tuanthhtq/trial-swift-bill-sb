package io.github.tuanthhtq.trialswiftbillsb.services;

import io.github.tuanthhtq.trialswiftbillsb.dtos.PagedResponse;
import io.github.tuanthhtq.trialswiftbillsb.dtos.Response;
import io.github.tuanthhtq.trialswiftbillsb.dtos.bill.BillDetailResponse;
import io.github.tuanthhtq.trialswiftbillsb.dtos.bill.BillInfoResponse;
import io.github.tuanthhtq.trialswiftbillsb.dtos.bill.BillSearchRequest;
import io.github.tuanthhtq.trialswiftbillsb.dtos.payment.PaymentCreationResponse;
import io.github.tuanthhtq.trialswiftbillsb.dtos.payment.PaymentRequestDetail;
import io.github.tuanthhtq.trialswiftbillsb.dtos.product.ProductInfoResponse;
import io.github.tuanthhtq.trialswiftbillsb.dtos.product.ProductSearchRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author io.github.tuanthhtq
 */

public interface CashierServices {

	/**
	 * Create new payment
	 *
	 * @param paymentRequestDetail {@link PaymentRequestDetail}
	 * @return {@link PaymentCreationResponse}
	 */
	Response<PaymentCreationResponse> requestPayment(PaymentRequestDetail paymentRequestDetail);

	/**
	 * Cancel pending payment
	 *
	 * @param orderCode order code returned by payos after create new payment request
	 * @return {@link String}
	 */
	Response<String> cancelPayment(int orderCode);


	/**
	 * Get product information by barcode
	 *
	 * @param request {@link ProductSearchRequest}
	 * @return {@link ProductInfoResponse}
	 */
	Response<ProductInfoResponse> getProductInfo(ProductSearchRequest request);

	/**
	 * Get all bill paged
	 *
	 * @param request  {@link ProductSearchRequest}
	 * @param pageable {@link Pageable}
	 * @return {@link List} of {@link BillInfoResponse}
	 */
	PagedResponse<List<BillInfoResponse>> getAllBillsPaged(BillSearchRequest request, Pageable pageable);


	/**
	 * Get bill detail
	 *
	 * @param billId bill_id
	 * @return {@link BillDetailResponse}
	 */
	Response<BillDetailResponse> getBillDetail(Long billId);


}
