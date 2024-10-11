package io.github.tuanthhtq.trialswiftbillsb.services;

import io.github.tuanthhtq.trialswiftbillsb.dtos.Response;
import io.github.tuanthhtq.trialswiftbillsb.dtos.user.UserDetailResponse;

/**
 * @author io.github.tuanthhtq
 */

public interface UserServices {

	Response<UserDetailResponse> getUserDetail(Long userId);


}
