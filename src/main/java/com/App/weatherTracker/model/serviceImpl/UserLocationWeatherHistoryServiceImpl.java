package com.App.weatherTracker.model.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.App.weatherTracker.model.bo.UserLocation;
import com.App.weatherTracker.model.bo.UserLocationWeatherHistory;
import com.App.weatherTracker.model.mapper.UserLocationWeatherHistoryMapper;
import com.App.weatherTracker.model.request.UserLocationWeatherHistoryUpdateRequest;
import com.App.weatherTracker.model.response.UserLocationWeatherHistoryResponse;
import com.App.weatherTracker.model.service.UserLocationWeatherHistoryService;
import com.App.weatherTracker.repository.UserLocationRepository;
import com.App.weatherTracker.repository.UserLocationWeatherHistoryRepository;

@Service
public class UserLocationWeatherHistoryServiceImpl implements UserLocationWeatherHistoryService {

	@Autowired
	UserLocationRepository userLocationRepository;

	@Autowired
	UserLocationWeatherHistoryRepository userLocationWeatherHistoryRepo;

	@Autowired
	UserLocationWeatherHistoryMapper mapper;

	@Override
	public String updateUserLocationWeatherHistory(UserLocationWeatherHistoryUpdateRequest req) {
		// create
		Date date = new Date();
		UserLocationWeatherHistory history = new UserLocationWeatherHistory();
		UserLocation location = new UserLocation();
		location.setUserLocationId(req.getUserLocationId());
		history.setUserLocation(location);
		history.setAvgTemparature(req.getAvgTemparature());
		history.setPressure(req.getPressure());
		history.setHumidity(req.getHumidity());
		history.setWeather(req.getWeather());
		history.setWeatherDescription(req.getWeatherDescription());
		history.setModifiedOn(date);
		history.setCreatedOn(date);
		history.setModifiedBy(-1l);
		history.setCreatedBy(-1l);
		userLocationWeatherHistoryRepo.save(history);
//		List<UserLocationWeatherHistory> userLocationWeatherHistoryList = userLocationWeatherHistoryRepo
//				.fetchUserLocationWeatherHistory(req.getUserLocationId()/* , 5 */);
//	    List<UserLocationWeatherHistoryResponse>responseList = mapper.userLocationWeatherHistoryListToUserLocationWeatherHistoryResponseList(userLocationWeatherHistoryList);	    

		return "200";
	}

	@Override
	public List<UserLocationWeatherHistoryResponse> fetchUserLocationWeatherHistoryByLimit(
			UserLocationWeatherHistoryUpdateRequest req) {

		/*
		 * List<UserLocationWeatherHistory> userLocationWeatherHistoryList =
		 * userLocationWeatherHistoryRepo .fetchUserLocationWeatherHistory(12l , 5 );
		 */

		List<UserLocationWeatherHistory> userLocationWeatherHistoryList = userLocationWeatherHistoryRepo
				.findByUserLocation_User_UserId(req.getUserLocationId());
		List<UserLocationWeatherHistoryResponse> responseList = mapper
				.userLocationWeatherHistoryListToUserLocationWeatherHistoryResponseList(userLocationWeatherHistoryList);

		return responseList;
	}

}
