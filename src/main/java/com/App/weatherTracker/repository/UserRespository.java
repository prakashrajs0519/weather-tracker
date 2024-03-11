package com.App.weatherTracker.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.App.weatherTracker.model.bo.User;


public interface UserRespository extends JpaRepository<User, Serializable>{
	
	User findByUserIdAndIsActive(Long userId, byte isActive);
	User findByUserNameAndIsActive(String name, byte isActive);


}
