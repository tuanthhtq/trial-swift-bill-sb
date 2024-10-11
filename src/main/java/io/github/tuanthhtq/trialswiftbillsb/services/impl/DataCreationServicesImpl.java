package io.github.tuanthhtq.trialswiftbillsb.services.impl;

import io.github.tuanthhtq.trialswiftbillsb.dtos.productDependent.SimpleSupplierDto;
import io.github.tuanthhtq.trialswiftbillsb.entities.Suppliers;
import io.github.tuanthhtq.trialswiftbillsb.repositories.SuppliersRepository;
import io.github.tuanthhtq.trialswiftbillsb.services.DataCreationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author io.github.tuanthhtq
 */

@Service
public class DataCreationServicesImpl implements DataCreationServices {
	private final SuppliersRepository suppliersRepo;


	@Autowired
	public DataCreationServicesImpl(
			SuppliersRepository suppliersRepo
	) {
		this.suppliersRepo = suppliersRepo;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Suppliers createSupplier(SimpleSupplierDto request) {
		try {
			Suppliers supplier = new Suppliers(
					request.name(),
					request.address(),
					request.phone()
			);
			return suppliersRepo.saveAndFlush(supplier);
		} catch (Exception ex) {
			System.err.println(ex.getLocalizedMessage());
			return null;
		}
	}
}
