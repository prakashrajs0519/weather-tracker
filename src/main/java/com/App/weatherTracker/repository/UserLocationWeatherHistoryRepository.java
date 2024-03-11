package com.App.weatherTracker.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.App.weatherTracker.model.bo.UserLocationWeatherHistory;

public interface UserLocationWeatherHistoryRepository  extends JpaRepository<UserLocationWeatherHistory, Serializable>{

	@Query(value="select his from UserLocationWeatherHistory his join fetch his.userLocation ul where ul.userLocationId = :userLocationId order by his.createdOn  ")
	List<UserLocationWeatherHistory> fetchUserLocationWeatherHistory(
			@Param("userLocationId") Long userLocationId/* , @Param("limitNo") int limitNo */);//Limit :limitNo

	List<UserLocationWeatherHistory> findByUserLocation_User_UserId(Long userLocationId);
	
}
