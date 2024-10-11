package io.github.tuanthhtq.trialswiftbillsb.dtos.payment;

/**
 * @author io.github.tuanthhtq
 */

public record PaymentCreationPayload(
		String bin,
		String accountNumber,
		String accountName,
		Long amount,
		String description,
		int orderCode,
		String currency,
		String paymentLinkId,
		String status,
		String checkoutUrl,
		String qrCode
) {
}
