package com.App.weatherTracker.model.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.App.weatherTracker.model.bo.User;
import com.App.weatherTracker.model.bo.UserLocation;
import com.App.weatherTracker.model.bo.UserSchedule;
import com.App.weatherTracker.model.request.UserLocationRequest;
import com.App.weatherTracker.model.response.UserLocationResponse;
import com.App.weatherTracker.model.service.UserLocationService;
import com.App.weatherTracker.repository.UserLocationRepository;
import com.App.weatherTracker.repository.UserRespository;
import com.App.weatherTracker.repository.UserScheduleRepository;

@Service
public class UserLocationServiceImpl implements UserLocationService {

	@Autowired
	UserLocationRepository userLocationRepository;
	
	@Autowired
	UserRespository userRepo;
	
	@Autowired
	UserScheduleRepository userScheduleRepo;
	
	
	@Transactional
	@Override
	public UserLocationResponse createOrUpdateUserLocation(UserLocationRequest userRequest) throws Exception {
		UserLocationResponse response = new UserLocationResponse();
		Date date = new Date();  
		if(userRequest.isIfOnlyScheduleTimeUpdate() && userRequest.getScheduleTime()!=null) {
			User user = userRepo.findByUserIdAndIsActive(userRequest.getUserId(), (byte) 1);
			if(user!=null) {
				user.setScheduleMinutes(userRequest.getScheduleTime());
				user.setModifiedOn(date);
				user.setModifiedBy(user.getUserId());
				userRepo.save(user);
			    String cron1 = "0 0/c * 1/1 * *";//0 0/c * 1/1 * *
			    cron1 = cron1.replaceAll("c", Integer.valueOf(userRequest.getScheduleTime().intValue()).toString());
				UserSchedule userSchedule = userScheduleRepo.findByUserId(user.getUserId());
			if(userSchedule==null) {
			    userSchedule =new UserSchedule();
				userSchedule.setUserId(userRequest.getUserId());
			} 
			System.out.println(cron1 + "****");
			userSchedule.setCronExpression(cron1);
			userScheduleRepo.save(userSchedule);
			
			} else {
	            throw new Exception("No User Exists!");  
			}
		} else {
			List<UserLocation>userLocationResponseList = userLocationRepository.findByUser_UserIdAndIsActive(userRequest.getUserId(), (byte) 1);
			if(userLocationResponseList != null) {
				userLocationResponseList.forEach(e->e.setIsActive((byte) 0));	
			}
			UserLocation userLocation = new UserLocation();
			User user1 = userRepo.findByUserIdAndIsActive(userRequest.getUserId(), (byte) 1);
			userLocation.setUser(user1);
			userLocation.setLatitude(userRequest.getLatitude());
			userLocation.setLongitude(userRequest.getLongitude());
			userLocation.setLocationName(userRequest.getLocationName());
			userLocation.setIsActive((byte) 1);
			userLocation.setModifiedOn(date);
			userLocation.setCreatedOn(date);
			userLocation.setModifiedBy(userRequest.getUserId());
			userLocation.setCreatedBy(userRequest.getUserId());
			userLocation = userLocationRepository.save(userLocation);
			
			if(userRequest.getScheduleTime()!=null) {
				User user = userRepo.findByUserIdAndIsActive(userRequest.getUserId(), (byte) 1);
				if(user!=null) {
					user.setScheduleMinutes(userRequest.getScheduleTime());
					userRepo.save(user);
					user.setModifiedOn(date);
					user.setModifiedBy(user.getUserId());
					response.setScheduleMinutes(user.getScheduleMinutes());
		
				    String cron1 = "0 0/c * 1/1 * *";//0 0/c * 1/1 * *
				    cron1 = cron1.replaceAll("c", Integer.valueOf(userRequest.getScheduleTime().intValue()).toString());
					UserSchedule userSchedule = userScheduleRepo.findByUserId(user.getUserId());
				if(userSchedule==null) {
				    userSchedule =new UserSchedule();
					userSchedule.setUserId(userRequest.getUserId());
				} 
				System.out.println(cron1 + "****");
				userSchedule.setCronExpression(cron1);
				userScheduleRepo.save(userSchedule);
				
				
				} else {
		            throw new Exception("No User Exists!");  
				}
			} else {
				User user = userRepo.findByUserIdAndIsActive(userRequest.getUserId(), (byte) 1);
				response.setScheduleMinutes(user.getScheduleMinutes());
			}
			
			response.setUserId(userLocation.getUser().getUserId());
			response.setLongitude(userLocation.getLongitude());
			response.setLatitude(userLocation.getLatitude());
			response.setUserLocationId(userLocation.getUserLocationId());
			response.setLocationName(userLocation.getLocationName());
		}
		return response;
	}

}
