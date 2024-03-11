package com.App.weatherTracker.model.response;

import java.util.List;

public class WeatherDetailResponse {

	private Wind wind;
	private Main main;
	private List<Weather> weather;
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public List<Weather> getWeather() {
		return weather;
	}
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}
	
	
}
