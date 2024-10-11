package io.github.tuanthhtq.trialswiftbillsb.dtos.payment;

/**
 * @author io.github.tuanthhtq
 */

public record PaymentCreationResponse(
		String code,
		String desc,
		PaymentCreationPayload data,
		String signature
) {
}
