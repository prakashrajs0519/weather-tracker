package com.App.weatherTracker.model.response;

public class UserLocationResponse {

	private Long userLocationId;
	private Long userId;
	private Double longitude;
	private Double latitude;
	private byte isActive;//0 means inActive
	private String locationName;//0 means inActive
	private Double scheduleMinutes;//0 means inActive

	public Long getUserLocationId() {
		return userLocationId;
	}
	public void setUserLocationId(Long userLocationId) {
		this.userLocationId = userLocationId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public byte getIsActive() {
		return isActive;
	}
	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Double getScheduleMinutes() {
		return scheduleMinutes;
	}
	public void setScheduleMinutes(Double scheduleMinutes) {
		this.scheduleMinutes = scheduleMinutes;
	}
	
	

}
