package com.thingtrack.route.planner.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vaadin.addons.locationtextfield.GeocodedLocation;
import org.vaadin.addons.locationtextfield.LocationTextField;
import org.vaadin.cssinject.CSSInject;

import com.thingtrack.route.planner.MapQuestOpenDirectionsService;
import com.thingtrack.route.planner.MapQuestOpenDirectionsService.RoutingOption;
import com.thingtrack.route.planner.RoutePlannerException;
import com.thingtrack.route.planner.RoutePlannerLayout;
import com.thingtrack.route.planner.model.Leg;
import com.thingtrack.route.planner.model.Maneuver;
import com.thingtrack.route.planner.model.MapCoordinates;
import com.thingtrack.route.planner.model.Route;
import com.thingtrack.route.planner.ui.CustomizedLocationTextField.StopType;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer.ComponentDetachListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.Reindeer;

public class RouteForm extends CustomComponent implements ValueChangeListener, ComponentDetachListener {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private Table maneuversTable;
	@AutoGenerated
	private Label resultadoLabel;
	@AutoGenerated
	private Button createRouteButton;
	@AutoGenerated
	private Button addStopButton;
	@AutoGenerated
	private CustomizedLocationTextField destStopcustomizedLocationTextField;
	@AutoGenerated
	private CustomizedLocationTextField origStopcustomizedLocationTextField;
	@AutoGenerated
	private OptionGroup avoidanceStrategiesOptionGroup;
	@AutoGenerated
	private OptionGroup routeTypeOptionGroup;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	
	private int stopAsciiCounter = 66;
	private Map<Component, MapCoordinates> stops = new HashMap<Component, MapCoordinates>();
	
	//Load the route's maneuvers
	BeanItemContainer<Maneuver> maneuverBeanItemContainer;
	
	private static final String ROUTE_TYPE_FASTEST_CAPTION = "Camino más rápido";
	private static final String ROUTE_TYPE_SHORTEST_CAPTION = "Camino más corto";
	
	private static final String AVOID_HIGHWAYS = "Autopistas";
	private static final String AVOID_TOLLS = "Peajes";
	private static final String AVOID_UNPAVED = "Vías sin pavimentar";
	private static final String AVOID_FERRIES = "Ferries";
	private static final String AVOID_COUNTRY_BORDERS = "Fronteras";
	private static final String AVOID_SEASONAL_ROADS = "Carreras estacionarias";
	private static final String AVOID_TIMED_RESTRICTIONS = "Restricciones temporales";
	
	private Date selectedStartingRouteDate;
	
	
	List<CustomizedLocationTextField> customizedLocationTextFields = new ArrayList<CustomizedLocationTextField>();
	

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public RouteForm() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		CSSInject routePlannesStyles = new CSSInject("/* Lay the options horizontally */"
		+ 	".v-select-optiongroup-horizontal .v-select-option {"
		+		"display: inline-block;"
		+	"}"
		
		+	"/* Some extra spacing is needed */"
		+	".v-select-optiongroup-horizontal"
		+	".v-select-option.v-radiobutton {"
		+		"padding-right: 10px;"
		+	"}"
		
		+	"/* Bolder captions*/" 
		+ 	".v-caption-bolder{" 
		+		"font-weight: bolder;" +
			"}" +
		"");
		
		
		mainLayout.addComponent(routePlannesStyles);
		
		routeTypeOptionGroup.setStyleName("horizontal");
		routeTypeOptionGroup.addStyleName("bolder");
		routeTypeOptionGroup.addItem(ROUTE_TYPE_FASTEST_CAPTION);
		routeTypeOptionGroup.addItem(ROUTE_TYPE_SHORTEST_CAPTION);
		
		// Use the multiple selection mode.
		avoidanceStrategiesOptionGroup.setMultiSelect(true);
		avoidanceStrategiesOptionGroup.setStyleName("horizontal");
		avoidanceStrategiesOptionGroup.addStyleName("bolder");
		
		avoidanceStrategiesOptionGroup.addItem(AVOID_HIGHWAYS);
		avoidanceStrategiesOptionGroup.addItem(AVOID_TOLLS);
		avoidanceStrategiesOptionGroup.addItem(AVOID_FERRIES);
		avoidanceStrategiesOptionGroup.addItem(AVOID_COUNTRY_BORDERS);
		avoidanceStrategiesOptionGroup.addItem(AVOID_SEASONAL_ROADS);
		avoidanceStrategiesOptionGroup.addItem(AVOID_TIMED_RESTRICTIONS);
		
		
		resultadoLabel.setStyleName(Reindeer.LABEL_H2);
		addStopButton.addStyleName(Reindeer.BUTTON_LINK);
		
		origStopcustomizedLocationTextField.setData(1);
		origStopcustomizedLocationTextField.addStyleName("bolder");
		origStopcustomizedLocationTextField.addListener((ValueChangeListener)this);
		destStopcustomizedLocationTextField.setData(2);
		destStopcustomizedLocationTextField.addListener((ValueChangeListener)this);
		
		
		addStopButton.addListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				stopAsciiCounter++;
				int destStopComponentIndex = verticalLayout_1.getComponentIndex(destStopcustomizedLocationTextField);
				CustomizedLocationTextField intermediateCustomizedLocationTextField = new CustomizedLocationTextField(destStopcustomizedLocationTextField.getStopCaption());
				intermediateCustomizedLocationTextField.setDescription("Parada intermedia");
				intermediateCustomizedLocationTextField.addListener((ValueChangeListener)RouteForm.this);
				intermediateCustomizedLocationTextField.addListener((ComponentDetachListener)RouteForm.this);
				intermediateCustomizedLocationTextField.setData(destStopcustomizedLocationTextField.getData());
				
				verticalLayout_1.addComponent(intermediateCustomizedLocationTextField, destStopComponentIndex);
				//Update last stop
				destStopcustomizedLocationTextField.setStopCaption(new String(new char[]{(char) stopAsciiCounter}));
				
				int lastIndex = ((Integer) destStopcustomizedLocationTextField.getData()) + 1;
				destStopcustomizedLocationTextField.setData(lastIndex);
				
				customizedLocationTextFields.add(customizedLocationTextFields.indexOf(destStopcustomizedLocationTextField), intermediateCustomizedLocationTextField);
			
			}
		});
		
		//Maneuvers Table
		maneuverBeanItemContainer = new BeanItemContainer<Maneuver>(Maneuver.class);
		
		maneuversTable.setContainerDataSource(maneuverBeanItemContainer);
		
		maneuversTable.addGeneratedColumn(DirectionIconColumnGenerator.DIRECTION_ICON_COLUMN_ID, new DirectionIconColumnGenerator());
		
		maneuversTable.setVisibleColumns(new String[]{DirectionIconColumnGenerator.DIRECTION_ICON_COLUMN_ID, "narrative", "distance"});
		
		maneuversTable.setColumnHeaders(new String[] { "Indicación", "Descripción", "Distancia"});
		
		maneuversTable.setColumnAlignment(DirectionIconColumnGenerator.DIRECTION_ICON_COLUMN_ID, Table.ALIGN_CENTER);
		maneuversTable.setColumnAlignment("distance", Table.ALIGN_RIGHT);
		
		maneuversTable.addListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
				
				Maneuver maneuver = (Maneuver) event.getItemId();
				
				RoutePlannerLayout routePlannerLayout=  (RoutePlannerLayout) RouteForm.this.getData();
				
				//Draw in the map
				routePlannerLayout.createManeuverPopup(maneuver);
			}
		});
	}
	
	
	public RouteForm(ClickListener createRouClickListener){
		
		this();
		createRouteButton.addListener(createRouClickListener);
		
		customizedLocationTextFields.add(origStopcustomizedLocationTextField);
		customizedLocationTextFields.add(destStopcustomizedLocationTextField);	
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		

		com.vaadin.ui.Field.ValueChangeEvent locationTextFieldEvent = (com.vaadin.ui.Field.ValueChangeEvent) event;
		
		GeocodedLocation geocodedLocation  = (GeocodedLocation) locationTextFieldEvent.getProperty().getValue();
		
		stops.put(locationTextFieldEvent.getComponent(), new MapCoordinates(geocodedLocation.getLat() , geocodedLocation.getLon()));
		
	}
	
	public void routeCalculation(){
		
		if(stops.size() < 2)
			return;
		
		List<Component> stopComponents = new ArrayList<Component>(stops.keySet());
		Collections.sort(stopComponents, new StopComparator());
		
		List<MapCoordinates> routeCoordinates = new ArrayList<MapCoordinates>();
		for(Component stopComponent : stopComponents)
			routeCoordinates.add(stops.get(stopComponent));
		
		MapQuestOpenDirectionsService mapQuestOpenDirectionsService = MapQuestOpenDirectionsService
				.getInstance();
		
		//Routing options
//		addRoutingOptions(mapQuestOpenDirectionsService);
		
		MapCoordinates[] routeCoordinatesArray = new MapCoordinates[routeCoordinates.size()];
		routeCoordinates.toArray(routeCoordinatesArray);
		
		try {
			Route calculatedRoute = mapQuestOpenDirectionsService.getRoute(routeCoordinatesArray);
			
			RoutePlannerLayout routePlannerLayout=  (RoutePlannerLayout) this.getData();
			
			//Draw in the map
			routePlannerLayout.drawRouteStroke(selectedStartingRouteDate, calculatedRoute);
			
			List<Maneuver> maneuvers = new ArrayList<Maneuver>();
			for(Leg leg : calculatedRoute.getLegs())
				maneuvers.addAll(leg.getManeuvers());
			
			maneuverBeanItemContainer.removeAllItems();
			maneuverBeanItemContainer.addAll(maneuvers);
			
			//Draw in the charts
			routePlannerLayout.drawCharts(calculatedRoute);
			
		} catch (RoutePlannerException e) {
			getApplication().getMainWindow().showNotification("Hubo un error calculando la ruta", Notification.TYPE_ERROR_MESSAGE);
		}
		
	}
	
	private void addRoutingOptions(MapQuestOpenDirectionsService mapQuestOpenDirectionsService){
		
		
		
				
		//Custom time
		mapQuestOpenDirectionsService.addRoutingOption(RoutingOption.TIME_TYPE, 2);
		mapQuestOpenDirectionsService.addRoutingOption(RoutingOption.DATE_TYPE , selectedStartingRouteDate);
		mapQuestOpenDirectionsService.addRoutingOption(RoutingOption.LOCAL_TIME , selectedStartingRouteDate);
	}
	
	/* PRIVATE CLASS */
	private class StopComparator implements Comparator<Component>{

		@Override
		public int compare(Component comp0, Component comp1) {
			
			@SuppressWarnings("rawtypes")
			Integer index0 = (Integer) ((LocationTextField) comp0).getData();
			@SuppressWarnings("rawtypes")
			Integer index1 = (Integer) ((LocationTextField) comp1).getData();
			
			return index0.compareTo(index1);
		}
	}

	private class DirectionIconColumnGenerator implements ColumnGenerator{

		static final String DIRECTION_ICON_COLUMN_ID = "direction-icon";

		
		@Override
		public Object generateCell(Table source, Object itemId, Object columnId) {

			Maneuver maneuverItem = (Maneuver) itemId;
			
			if(maneuverItem.getIconUrl() == null)
				return null;
			
			Embedded directionEmbedded = new Embedded("", new ExternalResource(maneuverItem.getIconUrl()));
			return directionEmbedded;
		}
		
		
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("300px");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("300px");
		setHeight("100.0%");
		
		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		mainLayout.addComponent(verticalLayout_1, "top:0.0px;left:0.0px;");
		
		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(false);
		verticalLayout_1.setSpacing(true);
		
		// routeTypeOptionGroup
		routeTypeOptionGroup = new OptionGroup();
		routeTypeOptionGroup.setCaption("Optimiza tu ruta:");
		routeTypeOptionGroup.setImmediate(false);
		routeTypeOptionGroup.setWidth("100.0%");
		routeTypeOptionGroup.setHeight("-1px");
		verticalLayout_1.addComponent(routeTypeOptionGroup);
		
		// avoidanceStrategiesOptionGroup
		avoidanceStrategiesOptionGroup = new OptionGroup();
		avoidanceStrategiesOptionGroup.setCaption("Evitar lo siguiente:");
		avoidanceStrategiesOptionGroup.setImmediate(false);
		avoidanceStrategiesOptionGroup.setWidth("100.0%");
		avoidanceStrategiesOptionGroup.setHeight("-1px");
		verticalLayout_1.addComponent(avoidanceStrategiesOptionGroup);
		
		// origStopcustomizedLocationTextField
		origStopcustomizedLocationTextField = new CustomizedLocationTextField(StopType.ORIGIN, new String(new char[]{65}), "green1");
		origStopcustomizedLocationTextField.setCaption("Paradas");
		origStopcustomizedLocationTextField.setImmediate(false);
		origStopcustomizedLocationTextField.setDescription("Primera parada");
		origStopcustomizedLocationTextField.setWidth("100.0%");
		origStopcustomizedLocationTextField.setHeight("-1px");
		verticalLayout_1.addComponent(origStopcustomizedLocationTextField);
		
		// destStopcustomizedLocationTextField
		destStopcustomizedLocationTextField = new CustomizedLocationTextField(StopType.DESTINATION, new String(new char[]{66}), "red1");
		destStopcustomizedLocationTextField.setImmediate(false);
		destStopcustomizedLocationTextField.setDescription("Última parada");
		destStopcustomizedLocationTextField.setWidth("100.0%");
		destStopcustomizedLocationTextField.setHeight("-1px");
		verticalLayout_1.addComponent(destStopcustomizedLocationTextField);
		
		// addStopButton
		addStopButton = new Button();
		addStopButton.setCaption("+ Añadir parada");
		addStopButton.setImmediate(true);
		addStopButton.setWidth("-1px");
		addStopButton.setHeight("-1px");
		verticalLayout_1.addComponent(addStopButton);
		
		// createRouteButton
		createRouteButton = new Button();
		createRouteButton.setCaption("Crear ruta");
		createRouteButton.setImmediate(true);
		createRouteButton.setWidth("-1px");
		createRouteButton.setHeight("-1px");
		verticalLayout_1.addComponent(createRouteButton);
		verticalLayout_1.setComponentAlignment(createRouteButton,
				new Alignment(6));
		
		// resultadoLabel
		resultadoLabel = new Label();
		resultadoLabel.setImmediate(false);
		resultadoLabel.setWidth("-1px");
		resultadoLabel.setHeight("-1px");
		resultadoLabel.setValue("Resultado");
		verticalLayout_1.addComponent(resultadoLabel);
		
		// maneuversTable
		maneuversTable = new Table();
		maneuversTable.setImmediate(false);
		maneuversTable.setWidth("100.0%");
		maneuversTable.setHeight("100.0%");
		verticalLayout_1.addComponent(maneuversTable);
		verticalLayout_1.setExpandRatio(maneuversTable, 1.0f);
		
		return verticalLayout_1;
	}

	@Override
	public void componentDetachedFromContainer(ComponentDetachEvent event) {

		if(!(event.getDetachedComponent() instanceof CustomizedLocationTextField))	
			return;
		
		CustomizedLocationTextField removingCustomizedLocationTextField = (CustomizedLocationTextField) event.getDetachedComponent();
		stops.remove(removingCustomizedLocationTextField.getStopLocationTextField());
		
		int removingComponentIndex = verticalLayout_1.getComponentIndex(removingCustomizedLocationTextField);
		int destComponentIndex = verticalLayout_1.getComponentIndex(destStopcustomizedLocationTextField);
		
		
		for(int i = removingComponentIndex+1; i <= destComponentIndex; i++){
			
			CustomizedLocationTextField customizedLocationTextField = (CustomizedLocationTextField) verticalLayout_1.getComponent(i);
			
			 int index = customizedLocationTextField.getStopCaption().charAt(0);
			 index--;
			customizedLocationTextField.setStopCaption(new String(new char[]{(char) index}));
		}
		
		stopAsciiCounter--;
		customizedLocationTextFields.remove(removingCustomizedLocationTextField);
		verticalLayout_1.removeComponent(removingCustomizedLocationTextField);
		
	}
	
	public List<CustomizedLocationTextField> getCustomizedLocationTextFields(){
		return customizedLocationTextFields;
	}

}
