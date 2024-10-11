package io.github.tuanthhtq.trialswiftbillsb.dtos.auth;

import java.util.Set;

/**
 * @author io.github.tuanthhtq
 */

public record AuthResponse(
		String phone,
		String accessToken,
		Set<String> roles
) {
}
