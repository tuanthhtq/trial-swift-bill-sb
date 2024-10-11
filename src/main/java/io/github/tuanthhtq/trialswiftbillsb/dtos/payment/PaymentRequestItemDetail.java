package io.github.tuanthhtq.trialswiftbillsb.dtos.payment;

/**
 * @author io.github.tuanthhtq
 */

public record PaymentRequestItemDetail(
		int productId,
		String name,
		int price,
		int quantity
) {
}
