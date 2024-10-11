package io.github.tuanthhtq.trialswiftbillsb.dtos.product;

/**
 * @author io.github.tuanthhtq
 */

public record ProductSearchRequest(
		String barcode,
		Long productId
) {
}
