package com.App.weatherTracker;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication /* (scanBasePackages = "com.App.weatherTracker") */
//@EnableJpaRepositories(basePackages = "com.App.weatherTracker.repository")
@EnableScheduling
@ComponentScan(basePackages = "com.App.weatherTracker")
public class WeatherTrackerApplication {

	@Autowired
	private UserScheduleService userScheduleService;

	public static void main(String[] args) {
		SpringApplication.run(WeatherTrackerApplication.class, args);
	}

	@PostConstruct
	public void init() {
		userScheduleService.scheduleTasksForUsers();
	}
}
