package com.thingtrack.route.planner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Maneuver implements Serializable {

	enum Direcction{
		
		NONE,
		NORTH,
		NORTHWEST,
		NORTHEAST,
		SOUTH,
		SOUTHEAST,
		SOUTHWEST,
		WEST,
		EAST;
	}
	
	enum TurnType{
		
		STRAIGHT,
		SLIGHT_RIGHT,
		RIGHT,
		SHARP_RIGHT,
		REVERSE,
		SHARP_LEFT,
		LEFT,
		SLIGHT_LEFT,
		RIGHT_U_TURN,
		LEFT_U_TURN,
		RIGHT_MERGE,
		LEFT_MERGE,
		RIGHT_ON_RAMP,
		LEFT_ON_RAMP,
		RIGHT_OFF_RAMP,
		LEFT_OFF_RAMP,
		RIGHT_FORK,
		LEFT_FORM,
		STRAIGHT_FORK;
	}
	
	enum TransportMode{
		
		AUTO;
		//TODO: Define the rest of modes
	}
	
	int index;
	int attributes;
	Direcction direcction;
	double distance;
	String formattedTime;
	String iconURl;
	TurnType turnType;
	MapCoordinates startPoint;
	List<String> streets = new ArrayList<String>();
	int time;
	//TODO: linkIds
	//TODO: maneuverNotes
	String mapURl;
	String narrative;
	TransportMode transportMode;
	List<Sign> signs = new ArrayList<Sign>(); 
	
}
