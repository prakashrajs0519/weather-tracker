package com.App.weatherTracker.model.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="USER_LOCATION_WEATHER_HISTORY")
@Entity
public class UserLocationWeatherHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_LOCATION_WEATHER_HISTORY_ID")
	private Long userLocationWeatherHistoryId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_LOCATION_ID")
	private UserLocation userLocation;
	
	@Column(name="WEATHER")
	private String weather;
	
	@Column(name="WEATHER_DESCRIPTION")
	private String weatherDescription;
	
	@Column(name="AVERAGE_TEMPERATURE")
	private Double avgTemparature;
	
	@Column(name="PRESSURE")
	private Double pressure;
	
	@Column(name="HUMIDITY")
	private Double humidity;
	
	@Column(name="CREATED_BY")
	private Long createdBy;

	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="MODIFIED_BY" )
	private Long modifiedBy;

	@Column(name="MODIFIED_ON")
	private Date modifiedOn;

	public Long getUserLocationWeatherHistoryId() {
		return userLocationWeatherHistoryId;
	}

	public void setUserLocationWeatherHistoryId(Long userLocationWeatherHistoryId) {
		this.userLocationWeatherHistoryId = userLocationWeatherHistoryId;
	}

	public UserLocation getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(UserLocation userLocation) {
		this.userLocation = userLocation;
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

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	

}
