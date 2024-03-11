package com.App.weatherTracker.model.service;

import com.App.weatherTracker.model.request.UserLocationRequest;
import com.App.weatherTracker.model.response.UserLocationResponse;
import com.App.weatherTracker.model.response.UserResponse;

public interface UserLocationService {

	UserLocationResponse createOrUpdateUserLocation(UserLocationRequest userRequest) throws Exception;

}
