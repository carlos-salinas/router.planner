package com.thingtrack.route.planner.model;

import java.io.Serializable;

public class MapCoordinates implements Serializable {
	
	private double longitude;
	private double latitude;

	public MapCoordinates(double latitude, double longitude) {
		
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
