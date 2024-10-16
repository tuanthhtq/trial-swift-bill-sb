package io.github.tuanthhtq.trialswiftbillsb.services;

import io.github.tuanthhtq.trialswiftbillsb.dtos.productDependent.SimpleIdNameDto;
import io.github.tuanthhtq.trialswiftbillsb.dtos.productDependent.SimpleSupplierDto;
import io.github.tuanthhtq.trialswiftbillsb.entities.MeasureUnit;
import io.github.tuanthhtq.trialswiftbillsb.entities.ProductBrands;
import io.github.tuanthhtq.trialswiftbillsb.entities.ProductCategories;
import io.github.tuanthhtq.trialswiftbillsb.entities.Suppliers;

/**
 * @author io.github.tuanthhtq
 */

public interface DataCreationServices {

	/**
	 * Return or create new supplier if not exist
	 *
	 * @param request {@link SimpleSupplierDto}
	 * @return {@link Suppliers} or null if supplier is already exists
	 */
	Suppliers getSupplier(SimpleSupplierDto request);


	/**
	 * Return or create new category if not exist
	 *
	 * @param request {@link SimpleSupplierDto}
	 * @return {@link ProductCategories} or null if supplier is already exists
	 */
	ProductCategories getProductCategory(SimpleIdNameDto request);

	/**
	 * Return or create new category if not exist
	 *
	 * @param request {@link SimpleSupplierDto}
	 * @return {@link ProductCategories} or null if supplier is already exists
	 */
	ProductBrands getProductBrand(SimpleIdNameDto request);

	/**
	 * Return or create new measure unit if not exist
	 *
	 * @param request {@link SimpleSupplierDto}
	 * @return {@link MeasureUnit} or null if supplier is already exists
	 */
	MeasureUnit getMeasureUnit(SimpleIdNameDto request);


}
