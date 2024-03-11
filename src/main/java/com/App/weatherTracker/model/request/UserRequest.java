package com.App.weatherTracker.model.request;

public class UserRequest {

	private String username;
	private String password;
	private Double scheduleMinutes;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getScheduleMinutes() {
		return scheduleMinutes;
	}
	public void setScheduleMinutes(Double scheduleMinutes) {
		this.scheduleMinutes = scheduleMinutes;
	}
	
	
	
}
