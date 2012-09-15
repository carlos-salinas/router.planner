package com.thingtrack.route.planner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Leg implements Serializable {
	
	public enum RoadGradeStrategy{
		
		DEFAULT_STRATEGY,
		AVOID_UP_HILL,
		AVOID_DOWN_HILL,
		AVOID_ALL_HILLS,
		FAVOR_UP_HILL,
		FAVOR_DOWN_HILL,
		FAVOR_ALL_HILLS;
	}
	
	int origIndex;
	String origNarrative;
	int destIndex;
	String destNarrative;
	boolean hasTollRoad;
	boolean hasFerry;
	boolean hasHighway;
	boolean hasSeasonalClosure;
	boolean hasUnpaved;
	boolean hasCountryCross;
	int index;
	int time;	
	String formattedTime;
	double distance;
	List<Maneuver> maneuvers = new ArrayList<Maneuver>();
	List<RoadGradeStrategy> roadGradeStrategy = new ArrayList<Leg.RoadGradeStrategy>();

}
