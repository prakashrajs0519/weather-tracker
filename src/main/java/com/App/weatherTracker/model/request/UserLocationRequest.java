package com.App.weatherTracker.model.request;

public class UserLocationRequest {

	private Long userId;
	private String locationName;
	private Double latitude;
	private Double longitude;
	private Double scheduleTime;
	private boolean ifOnlyScheduleTimeUpdate;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(Double scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	public boolean isIfOnlyScheduleTimeUpdate() {
		return ifOnlyScheduleTimeUpdate;
	}
	public void setIfOnlyScheduleTimeUpdate(boolean ifOnlyScheduleTimeUpdate) {
		this.ifOnlyScheduleTimeUpdate = ifOnlyScheduleTimeUpdate;
	}

	
	
	
}
