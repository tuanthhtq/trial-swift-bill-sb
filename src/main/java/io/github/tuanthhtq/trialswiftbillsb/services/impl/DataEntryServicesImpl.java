package io.github.tuanthhtq.trialswiftbillsb.services.impl;

import io.github.tuanthhtq.trialswiftbillsb.dtos.Response;
import io.github.tuanthhtq.trialswiftbillsb.dtos.product.ImportCreationRequest;
import io.github.tuanthhtq.trialswiftbillsb.dtos.product.ImportCreationResponse;
import io.github.tuanthhtq.trialswiftbillsb.entities.Suppliers;
import io.github.tuanthhtq.trialswiftbillsb.entities.Users;
import io.github.tuanthhtq.trialswiftbillsb.repositories.SuppliersRepository;
import io.github.tuanthhtq.trialswiftbillsb.repositories.UsersRepository;
import io.github.tuanthhtq.trialswiftbillsb.services.DataEntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashSet;
import java.util.Set;

/**
 * @author io.github.tuanthhtq
 */

@Service
public class DataEntryServicesImpl extends CommonConcrete implements DataEntryServices {

	private final UsersRepository usersRepo;
	private final SuppliersRepository suppliersRepo;

	@Autowired
	public DataEntryServicesImpl(UsersRepository usersRepo,
	                             SuppliersRepository suppliersRepository) {
		this.usersRepo = usersRepo;
		this.suppliersRepo = suppliersRepository;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Response<ImportCreationResponse> importGoods(ImportCreationRequest request, BindingResult bindingResult) {
		Response<ImportCreationResponse> response = new Response<>();
		Set<String> errors = new HashSet<>();

		response.setMessage("Failed to import data");

		if (bindingResult.hasErrors()) {
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			response.setErrors(errors);
			return response;
		} else {
			//check session
			Users user = getSessionUser(usersRepo);

			if (user == null) {
				response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
				errors.add("Invalid session");
				response.setErrors(errors);
				return response;
			} else {

				//check supplier
				if (request.supplier().id() == null) {  //supplier == null mean not exists
				}
				Suppliers supplier = suppliersRepo.findById(request.supplier().id()).orElse(null);

				if (supplier == null) {
					supplier = new Suppliers(

					);
				}

			}
		}


		return null;
	}
}
