package com.thingtrack.route.planner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Route implements Serializable {
	
	boolean hasTollRoad;
	boolean hasFerry;
	boolean hasHighway;
	boolean hasSeasonalClosure;
	boolean hasUnpaved;
	boolean hasCountryCross;
	List<MapCoordinates> boundingBox; 
	String time;
	String formattedTime;
	double distance;
	double fuelUsed;
	List<Leg> legs = new ArrayList<Leg>();
	List<Integer> locationSequence = new ArrayList<Integer>();
	List<Location> locations = new ArrayList<Location>();
	List<String> options = new ArrayList<String>();
	int realTime;
	String sessionId; 
}
