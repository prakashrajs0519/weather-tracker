package com.App.weatherTracker.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.App.weatherTracker.model.request.UserLocationRequest;
import com.App.weatherTracker.model.request.UserLocationWeatherHistoryUpdateRequest;
import com.App.weatherTracker.model.request.UserRequest;
import com.App.weatherTracker.model.response.UserLocationResponse;
import com.App.weatherTracker.model.response.UserLocationWeatherHistoryResponse;
import com.App.weatherTracker.model.response.UserResponse;
import com.App.weatherTracker.model.service.UserLocationService;
import com.App.weatherTracker.model.service.UserLocationWeatherHistoryService;
import com.App.weatherTracker.model.service.UserService;

@RestController
public class WeatherAppController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserLocationService userLocationService;
	
	@Autowired
	UserLocationWeatherHistoryService userLocationWeatherHistoryService;

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
	public String demo() {
		return "demoo";
	}
	
	@PostMapping(value= "/createUser")//create only user
	public UserResponse createUser(@RequestBody UserRequest userRequest) throws Exception {
		return userService.createUser(userRequest);    
	}
	
	@PostMapping(value= "/validateUser/{userName}/{password}")//create only user
	public UserResponse validateUser(@PathVariable("userName") String userName, @PathVariable("password") String password) throws Exception {
		return userService.validateUser(userName, password);    
	}
	
	
	@PostMapping(value= "/createUserLocation")//create/updates the location, scheduleTime, also updates only scheduleTime
	public UserLocationResponse createOrUpdateUserLocation(@RequestBody UserLocationRequest userRequest) throws Exception {
		return userLocationService.createOrUpdateUserLocation(userRequest);
	}
	
	@PostMapping(value= "/fetchUserLocationHistoryResponse")
	public List<UserLocationWeatherHistoryResponse> fetchUserLocationHistoryResponse(@RequestBody  UserLocationWeatherHistoryUpdateRequest userRequest) throws Exception {
		return userLocationWeatherHistoryService.fetchUserLocationWeatherHistoryByLimit(userRequest);
	}
	
	
	
	
}
