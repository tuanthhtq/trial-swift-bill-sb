package io.github.tuanthhtq.trialswiftbillsb.dtos.user;

/**
 * @author io.github.tuanthhtq
 */

public record StoreInfoResponse(
		String name,
		String address,
		String ownerId,
		String ownerName,
		String ownerAddress,
		String ownerPhone,
		String open,
		String close
) {
}
