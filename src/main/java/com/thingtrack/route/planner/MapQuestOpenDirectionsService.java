package com.thingtrack.route.planner;

import java.io.UnsupportedEncodingException;

import com.thingtrack.route.planner.model.MapCoordinates;

public class MapQuestOpenDirectionsService extends URLConnectionRoutePlanner {

	public enum RouteType {
		
		SHORTEST("shortest"),
		FASTEST("fastest"),
		PEDESTRIAN("pedestrian"),
		MULTIMODAL("multimodal"),
		BICYCLE("bicycle");
		
		private String value;
		
		private RouteType(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}  
	
	public enum TimeType{
		
		NONE(0),
		CURRENT_TIME(1),
		CUSTOM_TIME(2);
		
		private int value;
		
		private TimeType(int value){
			this.value = value;
		}
		
		public int getValue(){
			return this.value;
		}
	}
	
	public enum DateType{
		
		SPECIFIC_DATE_AND_TIME(0),
		SUNDAY(1),
		MONDAY(2),
		TUESDAT(3),
		WEDNESDAY(4),
		THURSDAY(5),
		FRIDAY(6),
		SATURDAY(7);
		
		private int value;
		
		private DateType(int value){
			this.value = value;
		}
		
		public int getValue(){
			return this.value;
		}
	}
	
	public enum NarrativeType{
		
		TEXT("text"),
		HTML("html"),
		MICROFORMAT("microformat");
		
		private String value;
		
		private NarrativeType(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	
	public enum Unit{
		
		MILES("m"),
		KILOMETERS("k");
		
		private String value;
		
		private Unit(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	
	public enum ShapeFormat{
	
		NONE("none"),
		RAW("raw"),
		COMPRESSED("cmp"),
		COMPRESSED_6_DIGIT_PRECISION("cmp6");
		
		private String value;
		
		private ShapeFormat(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
		
	}
	
	public enum InputFormat{
		
		KEY_VALUE_PAIRS("key/value pairs"),
		JSON("json"),
		XML("xml");
		
		private String value;
		
		private InputFormat(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	
	public enum OutputFormat{
		
		KEY_VALUE_PAIRS("key/value pairs"),
		JSON("json"),
		XML("xml");
		
		private String value;
		
		private OutputFormat(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	
	public enum DrivingStyle{
		
		CAUTIOUS(1),
		NORMAL(2),
		AGRESSIVE(3);

		private int value;
		
		private DrivingStyle(int value){
			this.value = value;
		}
		
		public int getValue(){
			return this.value;
		}
	}
	
	private static final String BASE_URL = "http://open.mapquestapi.com/directions/v1/route?outFormat=json&from=40.037661,-76.305977&to=39.962532,-76.728099&callback=renderNarrative";
	  
	  //URL COMMON PARAMETERS
	  private static final String START_PARAM_KEY = "from=";
	  private static final String END_PARAM_KEY = "to=";
	  private static final String AMBIGUITIES_PARAM_KEY = "ambiguities=";
	  private static final String AVOIDS_PARAM_KEY ="avoids=";
	  private static final String ROUTE_TYPE_PARAM_KEY = "routeType=";
	  private static final String TIME_TYPE_PARAM_KEY = "timeType=";
	  private static final String DATE_TYPE_PARAM_KEY = "dateType=";
	  private static final String DATE_PARAM_KEY = "date=";
	  private static final String LOCAL_TIME_PARAM_KEY= "localTime=";
	  private static final String NARRATIVE_TYPE_PARAM_KEY = "narrativeType=";
	  private static final String ENHANCED_NARRATIVE_PARAM_KEY = "enhancedNarrative=";
	  private static final String UNIT_PARAM_KEY = "unit=";
	  private static final String SHAPE_FORMAT_PARAM_KEY = "shapeFormat=";
	  private static final String GENERALIZE_PARAM_KEY = "generalize=";
	  private static final String INPUT_FORMAT_PARAM_KEY = "inFormat=";
	  private static final String OUTPUT_FORMAT_PARAM_KEY = "outFormat=";
	  private static final String LOCALE_PARAM_KEY = "locale=";
	  private static final String DRIVING_STYLE = "drivingStyle=";
	  private static final String HIGHWAY_EFFICIENCY = "highwayEfficiency=";
	  
	  
	  
	
	@Override
	protected String getURL(String... addresses)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getURL(MapCoordinates... coordinates)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

}
