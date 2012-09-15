package com.thingtrack.route.planner.model;

import java.io.Serializable;

public class MapCoordinates implements Serializable {
	
	private double longitude;
	private double latitude;

	public MapCoordinates(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
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
