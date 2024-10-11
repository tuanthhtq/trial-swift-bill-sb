package io.github.tuanthhtq.trialswiftbillsb.dtos.payment;

import java.util.List;

/**
 * @author io.github.tuanthhtq
 */

public record PaymentRequestDetail(
		int orderCode,
		int amount,
		String description,
		String buyerName,
		String buyerPhone,
		List<PaymentRequestItemDetail> items,
		String cancelUrl,
		String returnUrl,
		String signature,
		long expiredAt
) {
}
