package com.thingtrack.route.planner.model;

import java.io.Serializable;

public class Location implements Serializable {

	enum AdminAreaType {
		
		COUNTRY,
		STATE,
		COUNTY,
		CITY;
	}
	
	String adminArea1; 
	AdminAreaType adminArea1Type;
	String adminArea3;
	AdminAreaType adminArea3Type;
	String adminArea4;
	AdminAreaType adminArea4Type;
	String adminArea5;
	AdminAreaType adminArea5Type;
	MapCoordinates displayLatLng;
	boolean dragPoint;
	String geocodeQuality;
	String geocodeQualityCode;
	MapCoordinates latLng;
	int linkId;
	int postalCode;
	//FIXME: It might be a enumeration
	String sideOFStreet;
	String street;
	//FIXME: It might be a enumeration
	String type;
}
