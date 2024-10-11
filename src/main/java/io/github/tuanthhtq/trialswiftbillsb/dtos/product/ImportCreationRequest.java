package io.github.tuanthhtq.trialswiftbillsb.dtos.product;

import io.github.tuanthhtq.trialswiftbillsb.dtos.productDependent.SimpleSupplierDto;
import jakarta.validation.Valid;

import java.util.List;

/**
 * @author io.github.tuanthhtq
 */

public record ImportCreationRequest(
		@Valid
		SimpleSupplierDto supplier,

		@Valid
		List<ImportCreationProduct> products
) {
}
