package com.thingtrack.route.planner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.thingtrack.route.planner.model.Leg;
import com.thingtrack.route.planner.model.Location;
import com.thingtrack.route.planner.model.Maneuver;
import com.thingtrack.route.planner.model.MapCoordinates;
import com.thingtrack.route.planner.model.Route;
import com.thingtrack.route.planner.model.Shape;
import com.thingtrack.route.planner.model.Location.AdminAreaType;

public final class MapQuestOpenDirectionsService extends
		URLConnectionRoutePlanner<Route> {

	public enum RouteType {

		SHORTEST("shortest"), FASTEST("fastest"), PEDESTRIAN("pedestrian"), MULTIMODAL(
				"multimodal"), BICYCLE("bicycle");

		private String value;

		private RouteType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum TimeType {

		NONE(0), CURRENT_TIME(1), CUSTOM_TIME(2);

		private int value;

		private TimeType(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public enum DateType {

		SPECIFIC_DATE_AND_TIME(0), SUNDAY(1), MONDAY(2), TUESDAT(3), WEDNESDAY(
				4), THURSDAY(5), FRIDAY(6), SATURDAY(7);

		private int value;

		private DateType(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public enum NarrativeType {

		TEXT("text"), HTML("html"), MICROFORMAT("microformat");

		private String value;

		private NarrativeType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum Unit {

		MILES("m"), KILOMETERS("k");

		private String value;

		private Unit(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum ShapeFormat {

		NONE("none"), RAW("raw"), COMPRESSED("cmp"), COMPRESSED_6_DIGIT_PRECISION(
				"cmp6");

		private String value;

		private ShapeFormat(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

	}

	public enum InputFormat {

		KEY_VALUE_PAIRS("key/value pairs"), JSON("json"), XML("xml");

		private String value;

		private InputFormat(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum OutputFormat {

		KEY_VALUE_PAIRS("key/value pairs"), JSON("json"), XML("xml");

		private String value;

		private OutputFormat(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum DrivingStyle {

		CAUTIOUS(1), NORMAL(2), AGRESSIVE(3);

		private int value;

		private DrivingStyle(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	private static final String BASE_URL = "http://open.mapquestapi.com/directions/v1/route?generalize=0&outFormat=json&unit=k";

	// URL COMMON PARAMETERS
	public static final String START_PARAM_KEY = "from=";
	public static final String END_PARAM_KEY = "to=";
	public static final String INTERMEDIATE_LOCATIONS ="json=";
	public static final String AMBIGUITIES_PARAM_KEY = "ambiguities=";
	public static final String AVOIDS_PARAM_KEY = "avoids=";
	public static final String ROUTE_TYPE_PARAM_KEY = "routeType=";
	public static final String TIME_TYPE_PARAM_KEY = "timeType=";
	public static final String DATE_TYPE_PARAM_KEY = "dateType=";
	public static final String DATE_PARAM_KEY = "date=";
	public static final String LOCAL_TIME_PARAM_KEY = "localTime=";
	public static final String NARRATIVE_TYPE_PARAM_KEY = "narrativeType=";
	public static final String ENHANCED_NARRATIVE_PARAM_KEY = "enhancedNarrative=";
	public static final String UNIT_PARAM_KEY = "unit=";
	public static final String SHAPE_FORMAT_PARAM_KEY = "shapeFormat=";
	public static final String GENERALIZE_PARAM_KEY = "generalize=";
	public static final String INPUT_FORMAT_PARAM_KEY = "inFormat=";
	public static final String OUTPUT_FORMAT_PARAM_KEY = "outFormat=";
	public static final String LOCALE_PARAM_KEY = "locale=";
	public static final String DRIVING_STYLE = "drivingStyle=";
	public static final String HIGHWAY_EFFICIENCY = "highwayEfficiency=";

	private static MapQuestOpenDirectionsService instance;

	public static MapQuestOpenDirectionsService getInstance() {

		if (instance == null)
			instance = new MapQuestOpenDirectionsService();

		return instance;
	}

	private MapQuestOpenDirectionsService() {
	}

	@Override
	protected String getURL(String... addresses)
			throws UnsupportedEncodingException, JSONException {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < addresses.length; i++) {
			if (i == 0) {
				sb.append("&" + START_PARAM_KEY
						+ URLEncoder.encode(addresses[i], "UTF-8"));
			} else if (i == addresses.length - 1) {

				sb.append("&" + END_PARAM_KEY
						+ URLEncoder.encode(addresses[i], "UTF-8"));
			}
		}

		return BASE_URL + sb.toString();

	}

	@Override
	protected String getURL(MapCoordinates... coordinates)
			throws UnsupportedEncodingException, JSONException {

		StringBuilder sb = new StringBuilder();
		
		JSONObject intermediateLocsJsonObject = new JSONObject();
		JSONArray intermediateLocsJsonArray = new JSONArray();

		for (int i = 0; i < coordinates.length; i++) {
			if (i == 0) {
				sb.append("&" + START_PARAM_KEY + coordinates[i].getLat()
						+ "," + coordinates[i].getLng());
			} else /*if (i == coordinates.length - 1)*/ {

				sb.append("&" + END_PARAM_KEY + coordinates[i].getLat()
						+ "," + coordinates[i].getLng());
			}
//			else{
//				
//				JSONObject latLngJsonObject = new JSONObject();
//				JSONObject coordinatesJsonObject = new JSONObject();
//				coordinatesJsonObject.put("lat", coordinates[i].getLat());
//				coordinatesJsonObject.put("lng", coordinates[i].getLng());
//				latLngJsonObject.put("latLng", coordinatesJsonObject);
//				
//				intermediateLocsJsonArray.put(latLngJsonObject);
//				
//			}
		}
		
		//Add intermediate stops request param
		if(intermediateLocsJsonArray.length() > 0){
			
			intermediateLocsJsonObject.put("locations", intermediateLocsJsonArray);
			sb.append("&" + INTERMEDIATE_LOCATIONS + intermediateLocsJsonObject.toString());
		}
		
		return BASE_URL + sb.toString();
	}
	
	@Override
	protected Route createRoute(String input, MapCoordinates... coordinates)
			throws RoutePlannerException {

		Route route = new Route();

		try {

			JSONObject obj = new JSONObject(input);

			JSONObject routeJsonObject = obj.getJSONObject("route");

			route.setHasTollRoad(routeJsonObject.optBoolean("hasTollRoad"));
			route.setHasFerry(routeJsonObject.optBoolean("hasFerry"));
			route.setHasHighway(routeJsonObject.optBoolean("hasHighway"));
			route.setHasSeasonalClosure(routeJsonObject
					.optBoolean("hasSeasonalClosure"));
			route.setHasUnpaved(routeJsonObject.optBoolean("hasUnpaved"));
			route.setHasCountryCross(routeJsonObject
					.optBoolean("hasCountryCross"));

			route.setDistance(routeJsonObject.getDouble("distance"));
			route.setFuelUsed(routeJsonObject.getDouble("fuelUsed"));

			// Legs
			route.setLegs(createLegs(routeJsonObject.getJSONArray("legs")));
			
			//Locations
			route.setLocations(createLocations(routeJsonObject.getJSONArray("locations")));

			// Shape
			route.setShape(createShape(routeJsonObject.getJSONObject("shape")));

		} catch (JSONException e) {
			throw new RoutePlannerException(e.getMessage(), e);
		}

		return route;
	}

	private List<Leg> createLegs(JSONArray inputArray) throws JSONException {

		List<Leg> legs = new ArrayList<Leg>();
		for (int i = 0; i < inputArray.length(); i++) {

			Leg leg = new Leg();
			JSONObject legJsonObject = inputArray.getJSONObject(i);

			leg.setOrigIndex(legJsonObject.getInt("origIndex"));
			leg.setOrigNarrative(legJsonObject.getString("origNarrative"));
			leg.setDestIndex(legJsonObject.getInt("destIndex"));
			leg.setDestNarrative(legJsonObject.getString("destNarrative"));
			leg.setHasTollRoad(legJsonObject.optBoolean("hasTollRoad"));
			leg.setHasFerry(legJsonObject.optBoolean("hasFerry"));
			leg.setHasHighway(legJsonObject.optBoolean("hasHighway"));
			leg.setHasSeasonalClosure(legJsonObject
					.optBoolean("hasSeasonalClosure"));
			leg.setHasUnpaved(legJsonObject.optBoolean("hasUnpaved"));
			leg.setHasCountryCross(legJsonObject.optBoolean("hasCountryCross"));
			leg.setIndex(legJsonObject.getInt("index"));
			leg.setTime(legJsonObject.getInt("time"));
			leg.setFormattedTime(legJsonObject.getString("formattedTime"));

			// Maneuvers
			leg.setManeuvers(createManeuvers(legJsonObject
					.getJSONArray("maneuvers")));

			legs.add(leg);
		}

		return legs;
	}

	private List<Maneuver> createManeuvers(JSONArray inputArray)
			throws JSONException {

		List<Maneuver> maneuvers = new ArrayList<Maneuver>();

		for (int i = 0; i < inputArray.length(); i++) {

			Maneuver maneuver = new Maneuver();
			JSONObject maneuverJsonObject = inputArray.getJSONObject(i);

			maneuver.setIndex(maneuverJsonObject.getInt("index"));
			maneuver.setAttributes(maneuverJsonObject.getInt("attributes"));

			JSONObject startpointJsonObject = maneuverJsonObject
					.getJSONObject("startPoint");

			maneuver.setStartPoint(new MapCoordinates(startpointJsonObject
					.getDouble("lat"), startpointJsonObject.getDouble("lng")));

			maneuvers.add(maneuver);
		}

		return maneuvers;
	}

	private Shape createShape(JSONObject input) throws JSONException {

		Shape shape = new Shape();

		List<Integer> legIndexes = new ArrayList<Integer>();
		JSONArray legIndexesJsonArray = input.getJSONArray("legIndexes");

		for (int i = 0; i < legIndexesJsonArray.length(); i++)
			legIndexes.add(legIndexesJsonArray.getInt(i));

		shape.setLegIndex(legIndexes);

		List<Integer> maneuverIndexes = new ArrayList<Integer>();
		JSONArray maneuverIndexesJsonArray = input.getJSONArray("legIndexes");

		for (int i = 0; i < maneuverIndexesJsonArray.length(); i++)
			maneuverIndexes.add(maneuverIndexesJsonArray.getInt(i));

		shape.setLegIndex(maneuverIndexes);

		List<MapCoordinates> shapePoints = new ArrayList<MapCoordinates>();
		JSONArray shapePointsJsonArray = input.getJSONArray("shapePoints");

		for (int i = 0; i < shapePointsJsonArray.length() - 1;) {
			shapePoints.add(new MapCoordinates(shapePointsJsonArray.getDouble(i), shapePointsJsonArray.getDouble(i+1)));
			i=i+2;
		}

		shape.setShapePoints(shapePoints);

		return shape;

	}
	
	private List<Location> createLocations(JSONArray inputArray) throws JSONException{
		
		List<Location> locations = new ArrayList<Location>();
		

		for (int i = 0; i < inputArray.length(); i++) {
			
			Location location = new Location();
			JSONObject locationJsonObject = inputArray.getJSONObject(i);
			
			location.setAdminArea1(locationJsonObject.getString("adminArea1"));
			location.setAdminArea1Type(getAdminAreaType(locationJsonObject.getString("adminArea1Type")));
			
			location.setAdminArea3(locationJsonObject.getString("adminArea3"));
			location.setAdminArea3Type(getAdminAreaType(locationJsonObject.getString("adminArea3Type")));
			
			location.setAdminArea4(locationJsonObject.getString("adminArea4"));
			location.setAdminArea4Type(getAdminAreaType(locationJsonObject.getString("adminArea4Type")));
			
			location.setAdminArea5(locationJsonObject.getString("adminArea5"));
			location.setAdminArea5Type(getAdminAreaType(locationJsonObject.getString("adminArea5Type")));
			
			JSONObject displayLatLngJsonObject = locationJsonObject
					.getJSONObject("displayLatLng");

			location.setDisplayLatLng(new MapCoordinates(displayLatLngJsonObject
					.getDouble("lat"), displayLatLngJsonObject.getDouble("lng")));

			location.setDragPoint(locationJsonObject.getBoolean("dragPoint"));
			
			location.setGeocodeQuality(locationJsonObject.getString("geocodeQuality"));
			location.setGeocodeQualityCode(locationJsonObject.getString("geocodeQualityCode"));
			
			JSONObject latLngJsonObject = locationJsonObject
					.getJSONObject("latLng");

			location.setLatLng(new MapCoordinates(latLngJsonObject
					.getDouble("lat"), latLngJsonObject.getDouble("lng")));
			
			location.setLinkId(locationJsonObject.getInt("linkId"));
			
			location.setPostalCode(locationJsonObject.getString("postalCode"));
			
			location.setSideOFStreet(locationJsonObject.getString("sideOfStreet"));
			
			location.setStreet(locationJsonObject.getString("street"));
			
			location.setType(locationJsonObject.getString("type"));


			locations.add(location);
		}
		
		return locations;
		
	}
	
	private Location.AdminAreaType getAdminAreaType(String adminAreaType){
		
		if(Location.AdminAreaType.COUNTRY.getValue().equals(adminAreaType)){
			
			return AdminAreaType.COUNTRY;
		}
		if(Location.AdminAreaType.STATE.getValue().equals(adminAreaType)){
			
			return AdminAreaType.STATE;
		}
		if(Location.AdminAreaType.COUNTY.getValue().equals(adminAreaType)){
		
			return AdminAreaType.COUNTY;
		}
		if(Location.AdminAreaType.CITY.getValue().equals(adminAreaType)){
			
			return AdminAreaType.CITY;
		}
		
		return AdminAreaType.UNKNOWN;
	}
}
