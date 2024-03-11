package com.App.weatherTracker.model.request;


public class UserLocationWeatherHistoryUpdateRequest {

	private Long userLocationId;
	private String weather;
	private String weatherDescription;
	private Double avgTemparature;
	private Double pressure;
	private Double humidity;
	private int limitNo;
	
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
	public int getLimitNo() {
		return limitNo;
	}
	public void setLimitNo(int limitNo) {
		this.limitNo = limitNo;
	}
	public String getWeatherDescription() {
		return weatherDescription;
	}
	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}
	
	
	
	
}
