package com.App.weatherTracker.model.service;

import com.App.weatherTracker.model.request.UserRequest;
import com.App.weatherTracker.model.response.UserResponse;

public interface UserService {

	UserResponse createUser(UserRequest req) throws Exception;

	UserResponse validateUser(String userName, String password) throws Exception; 
	
}
