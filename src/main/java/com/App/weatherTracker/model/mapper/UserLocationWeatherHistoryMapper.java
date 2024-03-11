package com.App.weatherTracker.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


import com.App.weatherTracker.model.bo.UserLocationWeatherHistory;
import com.App.weatherTracker.model.response.UserLocationWeatherHistoryResponse;

@Mapper(componentModel = "spring")
public interface UserLocationWeatherHistoryMapper {

	@Mappings({
	    @Mapping(source = "userLocation.userLocationId", target = "userLocationId"),
	})	
	UserLocationWeatherHistoryResponse userLocationWeatherHistoryToUserLocationWeatherHistoryResponse(UserLocationWeatherHistory userLocationWeatherHistoryResponse);
	List<UserLocationWeatherHistoryResponse> userLocationWeatherHistoryListToUserLocationWeatherHistoryResponseList(List<UserLocationWeatherHistory> userLocationWeatherHistoryResponseList);

}
