package com.App.weatherTracker.model.response;

import java.util.Date;

public class UserLocationWeatherHistoryResponse {

	private Long userLocationWeatherHistoryId;
	private Long userLocationId;
	private String weather;
	private String weatherDescription;
	private Double avgTemparature;
	private Double pressure;
	private Double humidity;
	private Date createdOn;
	
	public Long getUserLocationWeatherHistoryId() {
		return userLocationWeatherHistoryId;
	}
	public void setUserLocationWeatherHistoryId(Long userLocationWeatherHistoryId) {
		this.userLocationWeatherHistoryId = userLocationWeatherHistoryId;
	}
	public Long getUserLocationId() {
		return userLocationId;
	}
	public void setUserLocationId(Long userLocationId) {
		this.userLocationId = userLocationId;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getWeatherDescription() {
		return weatherDescription;
	}
	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}
	public Double getAvgTemparature() {
		return avgTemparature;
	}
	public void setAvgTemparature(Double avgTemparature) {
		this.avgTemparature = avgTemparature;
	}
	public Double getPressure() {
		return pressure;
	}
	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	


	
	
}
