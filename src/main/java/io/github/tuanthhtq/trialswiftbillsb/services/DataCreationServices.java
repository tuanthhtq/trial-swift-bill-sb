package io.github.tuanthhtq.trialswiftbillsb.services;

import io.github.tuanthhtq.trialswiftbillsb.dtos.productDependent.SimpleIdNameDto;
import io.github.tuanthhtq.trialswiftbillsb.dtos.productDependent.SimpleSupplierDto;
import io.github.tuanthhtq.trialswiftbillsb.entities.ProductCategories;
import io.github.tuanthhtq.trialswiftbillsb.entities.Suppliers;

/**
 * @author io.github.tuanthhtq
 */

public interface DataCreationServices {

	/**
	 * Create new supplier if not exist
	 *
	 * @param request {@link SimpleSupplierDto}
	 * @return {@link Suppliers} or null if supplier is already exists
	 */
	Suppliers createSupplier(SimpleSupplierDto request);


	/**
	 * Create new category if not exist
	 *
	 * @param request {@link SimpleSupplierDto}
	 * @return {@link Suppliers} or null if supplier is already exists
	 */
	ProductCategories createProductCategory(SimpleIdNameDto request);
}
