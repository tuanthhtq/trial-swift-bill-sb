package io.github.tuanthhtq.trialswiftbillsb.dtos.product;

/**
 * @author io.github.tuanthhtq
 */

public record ImportCreationResponse(
		String creatorName,
		float totalCost,
		float estimatedIncome
) {
}
