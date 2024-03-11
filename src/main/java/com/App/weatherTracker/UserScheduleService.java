package com.App.weatherTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.App.weatherTracker.model.bo.UserLocation;
import com.App.weatherTracker.model.bo.UserLocationWeatherHistory;
import com.App.weatherTracker.model.bo.UserSchedule;
import com.App.weatherTracker.model.request.UserLocationWeatherHistoryUpdateRequest;
import com.App.weatherTracker.model.response.WeatherDetailResponse;
import com.App.weatherTracker.model.service.UserLocationWeatherHistoryService;
import com.App.weatherTracker.repository.UserLocationRepository;
import com.App.weatherTracker.repository.UserScheduleRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserScheduleService {

	
    @Autowired
    private TaskScheduler taskScheduler;
    
    @Autowired
    private UserScheduleRepository userScheduleRepository;
    
    @Autowired
    UserLocationWeatherHistoryService userLocationWeatherHistoryService;
    
    @Autowired 
    private UserLocationRepository userLocRepo;

    public Map<Long, String> loadUserCronExpressions() {
        Map<Long, String> userCronExpressions = new HashMap<>();
        List<UserSchedule> userSchedules = userScheduleRepository.findAll(); // Fetch user schedules from database
        for (UserSchedule userSchedule : userSchedules) {
            userCronExpressions.put(userSchedule.getUserId(), userSchedule.getCronExpression());
        }
        return userCronExpressions;
    }
    
    public void scheduleTasksForUsers() {
        Map<Long, String> userCronExpressions = loadUserCronExpressions();
        for (Map.Entry<Long, String> entry : userCronExpressions.entrySet()) {
            Long userId = entry.getKey();
            String cronExpression = entry.getValue();
            scheduleTaskForUser(userId, cronExpression);
        }
    }

    private void scheduleTaskForUser(Long userId, String cronExpression) {
    	String weatherApiKey = "500c619e5ccd58fabda20e71b84bddda";
    	List<UserLocation> userLocationList = userLocRepo.findByUser_UserIdAndIsActive(userId, (byte) 1);

        	UserLocation userLocation = userLocationList.get(0);
        	Double latitude = userLocation.getLatitude();
        	Double longitude = userLocation.getLongitude();
        	final RestTemplate restTemplate = new RestTemplate();
    	
        	
    	taskScheduler.schedule(() -> {
        		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + weatherApiKey + "&units=metric";
                ResponseEntity<WeatherDetailResponse> response = restTemplate.getForEntity(apiUrl, WeatherDetailResponse.class);
               
                
                if (response.getStatusCode().is2xxSuccessful()) {
                	WeatherDetailResponse weatherDetailResponse = response.getBody();
                    UserLocationWeatherHistoryUpdateRequest req = new UserLocationWeatherHistoryUpdateRequest();
                    req.setAvgTemparature(weatherDetailResponse.getMain().getTemp());
                    req.setHumidity((double) weatherDetailResponse.getMain().getHumidity());
                    req.setPressure((double) weatherDetailResponse.getMain().getPressure());
                    req.setUserLocationId(userLocation.getUserLocationId());
                    userLocationWeatherHistoryService.updateUserLocationWeatherHistory(req);
                    // Process the response
                    System.out.println("API response: " + weatherDetailResponse);
                } else {
                    System.err.println("Failed to call the API. Status code: " + response.getStatusCodeValue());
                }
        		System.out.println("Executing task for user " + userId);
    	}, new CronTrigger(cronExpression));
    }
    
    
    
}
