package com.App.weatherTracker.model.service;

import java.util.List;

import com.App.weatherTracker.model.bo.UserLocationWeatherHistory;
import com.App.weatherTracker.model.request.UserLocationWeatherHistoryUpdateRequest;
import com.App.weatherTracker.model.response.UserLocationWeatherHistoryResponse;

public interface UserLocationWeatherHistoryService {


	List<UserLocationWeatherHistoryResponse> fetchUserLocationWeatherHistoryByLimit(
			UserLocationWeatherHistoryUpdateRequest req);

	String updateUserLocationWeatherHistory(
			UserLocationWeatherHistoryUpdateRequest req);
	
	
}
