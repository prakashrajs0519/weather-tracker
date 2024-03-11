package com.App.weatherTracker.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.App.weatherTracker.model.bo.UserSchedule;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Serializable> {

	UserSchedule findByUserId(Long id);
	
}
