package com.thingtrack.route.planner.model;

import java.io.Serializable;

public class Sign implements Serializable {

	Maneuver.Direcction direcction;
	String extraText;
	String url;
	//It might be a enum structure
	String type;
}
