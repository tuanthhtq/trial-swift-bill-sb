package io.github.tuanthhtq.trialswiftbillsb.services.impl;

import io.github.tuanthhtq.trialswiftbillsb.configs.implement.UserDetailsImpl;
import io.github.tuanthhtq.trialswiftbillsb.entities.Users;
import io.github.tuanthhtq.trialswiftbillsb.repositories.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author io.github.tuanthhtq
 */

abstract class CommonConcrete {

	public Users getSessionUser(UsersRepository userRepo) {
		//check session
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

		if (userDetails == null) {
			return null;
		} else {
			return userRepo.findByPhone(userDetails.getPhone()).orElse(null);
		}
	}
}
