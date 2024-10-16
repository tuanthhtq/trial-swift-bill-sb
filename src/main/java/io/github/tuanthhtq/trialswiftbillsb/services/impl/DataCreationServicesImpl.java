package io.github.tuanthhtq.trialswiftbillsb.services.impl;

import io.github.tuanthhtq.trialswiftbillsb.dtos.productDependent.SimpleIdNameDto;
import io.github.tuanthhtq.trialswiftbillsb.dtos.productDependent.SimpleSupplierDto;
import io.github.tuanthhtq.trialswiftbillsb.entities.MeasureUnit;
import io.github.tuanthhtq.trialswiftbillsb.entities.ProductBrands;
import io.github.tuanthhtq.trialswiftbillsb.entities.ProductCategories;
import io.github.tuanthhtq.trialswiftbillsb.entities.Suppliers;
import io.github.tuanthhtq.trialswiftbillsb.repositories.BrandsRepository;
import io.github.tuanthhtq.trialswiftbillsb.repositories.CategoriesRepository;
import io.github.tuanthhtq.trialswiftbillsb.repositories.MeasureUnitRepository;
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
	private final CategoriesRepository categoriesRepo;
	private final BrandsRepository brandsRepo;
	private final MeasureUnitRepository measureUnitRepo;

	@Autowired
	public DataCreationServicesImpl(
			SuppliersRepository suppliersRepo,
			CategoriesRepository categoriesRepo,
			BrandsRepository brandsRepo,
			MeasureUnitRepository measureUnitRepo
	) {
		this.suppliersRepo = suppliersRepo;
		this.categoriesRepo = categoriesRepo;
		this.brandsRepo = brandsRepo;
		this.measureUnitRepo = measureUnitRepo;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Suppliers getSupplier(SimpleSupplierDto request) {
		try {
			Suppliers supplier = new Suppliers(
					request.name(),
					request.address(),
					request.phone()
			);
			return suppliersRepo.save(supplier);
		} catch (Exception ex) {
			System.err.println(ex.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public ProductCategories getProductCategory(SimpleIdNameDto request) {
		try {
			return categoriesRepo.findByName(request.name())
					.orElseGet(() -> categoriesRepo.save(new ProductCategories(request.name())));
		} catch (Exception ex) {
			System.err.println(ex.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductBrands getProductBrand(SimpleIdNameDto request) {
		try {
			return brandsRepo.findByName(request.name())
					.orElseGet(() -> brandsRepo.save(new ProductBrands(request.name())));
		} catch (Exception ex) {
			System.err.println(ex.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MeasureUnit getMeasureUnit(SimpleIdNameDto request) {
		try {
			return measureUnitRepo.findByName(request.name())
					.orElseGet(() -> measureUnitRepo.save(new MeasureUnit(request.name())));
		} catch (Exception ex) {
			System.err.println(ex.getLocalizedMessage());
			return null;
		}
	}
}
