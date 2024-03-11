package com.App.weatherTracker.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.App.weatherTracker.model.bo.UserLocation;
@Repository
public interface UserLocationRepository extends JpaRepository<UserLocation, Serializable>{

	List<UserLocation> findByUser_UserIdAndIsActive(Long userId, byte isActive);
	
}
