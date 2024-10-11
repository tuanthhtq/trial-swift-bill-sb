package io.github.tuanthhtq.trialswiftbillsb.dtos.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author io.github.tuanthhtq
 */

public record ChangePasswordRequest(

		@NotEmpty(message = "Old cannot be empty")
		String oldPassword,

		@NotEmpty(message = "New password cannot be empty")
		@Size(min = 4, message = "Minimum 4 characters")
		String newPassword,

		@NotEmpty(message = "Confirm password cannot be empty")
		String confirmPassword
) {
}
