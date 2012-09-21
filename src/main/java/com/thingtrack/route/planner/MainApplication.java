/* 
 * Copyright 2009 IT Mill Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.thingtrack.route.planner;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.locationtextfield.GeocodedLocation;
import org.vaadin.addons.locationtextfield.GoogleGeocoder;
import org.vaadin.addons.locationtextfield.LocationTextField;
import org.vaadin.vol.Bounds;
import org.vaadin.vol.GoogleStreetMapLayer;
import org.vaadin.vol.Marker;
import org.vaadin.vol.MarkerLayer;
import org.vaadin.vol.OpenLayersMap;
import org.vaadin.vol.OpenStreetMapLayer;
import org.vaadin.vol.Point;
import org.vaadin.vol.PolyLine;
import org.vaadin.vol.Popup;
import org.vaadin.vol.Popup.PopupStyle;
import org.vaadin.vol.VectorLayer;

import com.thingtrack.route.planner.model.Leg;
import com.thingtrack.route.planner.model.Maneuver;
import com.thingtrack.route.planner.model.MapCoordinates;
import com.thingtrack.route.planner.model.Route;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Demonstration application that shows how to use a simple custom client-side
 * GWT component, the ColorPicker.
 */
@SuppressWarnings("serial")
public class MainApplication extends com.vaadin.Application {

	private enum STOP_TYPE {

		ORIGEN, DESTINO
	}

	private Window main = new Window("Route Planner Demo");
	private OpenLayersMap routeOpenLayersMap;
	private OpenStreetMapLayer openStreetMapLayer;
	private GoogleStreetMapLayer googleStreetMapLayer;
	private MarkerLayer markerLayer;
	VectorLayer polylinesLayer;
	private Marker originStopMarker;
	private Marker destinationStopMarker;
	private LocationTextField<GeocodedLocation> originStopLocationTextField;
	private LocationTextField<GeocodedLocation> destinationStopLocationTextField;
	private final GoogleGeocoder geocoder = GoogleGeocoder.getInstance();

	@Override
	public void init() {

		VerticalLayout mainLayout = buildMainLayout();
		main.addComponent(mainLayout);

		setMainWindow(main);

		setTheme("router.planner");

		// Add UI logic
		originStopLocationTextField
				.addListener(new Property.ValueChangeListener() {

					@Override
					public void valueChange(ValueChangeEvent event) {

						GeocodedLocation geocodedLocation = (GeocodedLocation) event
								.getProperty().getValue();

						if (geocodedLocation == null)
							return;

						if (originStopMarker != null)
							markerLayer.removeComponent(originStopMarker);

						originStopMarker = createStopMarker(STOP_TYPE.ORIGEN,
								geocodedLocation);
						markerLayer.addComponent(originStopMarker);

						// Center and Zoom the map
						Bounds bounds = null;

						if (destinationStopMarker != null) {

							Point originPoint = new Point(originStopMarker
									.getLon(), originStopMarker.getLat());
							Point destinationPoint = new Point(
									destinationStopMarker.getLon(),
									destinationStopMarker.getLat());
							bounds = new Bounds(originPoint, originPoint,
									destinationPoint, destinationPoint);

							routeOpenLayersMap.zoomToExtent(bounds);

						} else {
							routeOpenLayersMap.setCenter(
									originStopMarker.getLon(),
									originStopMarker.getLat());
							routeOpenLayersMap.setZoom(18);
						}
						
						routeCalculation(originStopMarker, destinationStopMarker);

					}
				});

		destinationStopLocationTextField
				.addListener(new Property.ValueChangeListener() {

					@Override
					public void valueChange(ValueChangeEvent event) {

						GeocodedLocation geocodedLocation = (GeocodedLocation) event
								.getProperty().getValue();

						if (geocodedLocation == null)
							return;

						if (destinationStopMarker != null)
							markerLayer.removeComponent(destinationStopMarker);

						destinationStopMarker = createStopMarker(
								STOP_TYPE.DESTINO, geocodedLocation);

						markerLayer.addComponent(destinationStopMarker);

						// Center and Zoom the map
						Bounds bounds = null;

						if (originStopMarker != null) {

							Point originPoint = new Point(originStopMarker
									.getLon(), originStopMarker.getLat());
							Point destinationPoint = new Point(
									destinationStopMarker.getLon(),
									destinationStopMarker.getLat());

							bounds = new Bounds(originPoint, originPoint,
									destinationPoint, destinationPoint);

							routeOpenLayersMap.zoomToExtent(bounds);

						} else {
							routeOpenLayersMap.setCenter(
									originStopMarker.getLon(),
									originStopMarker.getLat());
							routeOpenLayersMap.setZoom(18);
						}
						
						routeCalculation(originStopMarker, destinationStopMarker);
					}
				});

	}

	private VerticalLayout buildMainLayout() {

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);

		// Map definition
		routeOpenLayersMap = new OpenLayersMap();
		routeOpenLayersMap.setWidth("100%");
		routeOpenLayersMap.setHeight("550px");
		openStreetMapLayer = new OpenStreetMapLayer();
		googleStreetMapLayer = new GoogleStreetMapLayer();
		polylinesLayer = new org.vaadin.vol.VectorLayer();
		markerLayer = new MarkerLayer();

		// Apply Map Layers
		routeOpenLayersMap.addLayer(openStreetMapLayer);
		routeOpenLayersMap.addLayer(googleStreetMapLayer);
		routeOpenLayersMap.addLayer(polylinesLayer);
		routeOpenLayersMap.addLayer(markerLayer);

		routeOpenLayersMap.setCenter(22.30083, 60.452541);
		main.addComponent(routeOpenLayersMap);

		HorizontalLayout stopLocationTextFieldLayout = buildLocationTextFieldLayout();

		mainLayout.addComponent(stopLocationTextFieldLayout);

		return mainLayout;
	}

	private HorizontalLayout buildLocationTextFieldLayout() {

		HorizontalLayout stopLocationTextFieldLayout = new HorizontalLayout();
		stopLocationTextFieldLayout = new HorizontalLayout();
		stopLocationTextFieldLayout.setImmediate(false);
		stopLocationTextFieldLayout.setWidth("100.0%");
		stopLocationTextFieldLayout.setHeight("-1px");
		stopLocationTextFieldLayout.setMargin(false);
		stopLocationTextFieldLayout.setSpacing(true);

		// LocationTextField definition
		originStopLocationTextField = new LocationTextField<GeocodedLocation>(
				geocoder, GeocodedLocation.class, "Origen");

		originStopLocationTextField.setWidth("100%");
		originStopLocationTextField.setHeight("-1px");
		originStopLocationTextField.setRequired(true);
		originStopLocationTextField
				.setRequiredError(originStopLocationTextField.getCaption()
						+ " es un campo requerido");

		stopLocationTextFieldLayout.addComponent(originStopLocationTextField);
		stopLocationTextFieldLayout.setExpandRatio(originStopLocationTextField,
				1.0f);

		destinationStopLocationTextField = new LocationTextField<GeocodedLocation>(
				geocoder, GeocodedLocation.class, "Destino");

		destinationStopLocationTextField.setWidth("100%");
		destinationStopLocationTextField.setHeight("-1px");
		destinationStopLocationTextField.setRequired(true);
		destinationStopLocationTextField
				.setRequiredError(destinationStopLocationTextField.getCaption()
						+ " es un campo requerido");

		stopLocationTextFieldLayout
				.addComponent(destinationStopLocationTextField);
		stopLocationTextFieldLayout.setExpandRatio(
				destinationStopLocationTextField, 1.0f);

		return stopLocationTextFieldLayout;
	}

	private Marker createStopMarker(STOP_TYPE stopType,
			GeocodedLocation geocodedLocation) {

		Marker marker = new Marker(geocodedLocation.getLon(),
				geocodedLocation.getLat());
		marker.setIcon(new ThemeResource("icons/marker.png"), 24, 24);

		String street = geocodedLocation.getGeocodedAddress() != null ? geocodedLocation
				.getGeocodedAddress() : "";
		// String locality = geocodedLocation.getLocality() != null ?
		// geocodedLocation
		// .getLocality() : "";
		// String postalCode = geocodedLocation.getPostalCode() != null ?
		// geocodedLocation
		// .getPostalCode() : "";
		// String province = geocodedLocation.getAdministrativeAreaLevel1() !=
		// null ? geocodedLocation
		// .getAdministrativeAreaLevel1() : "";
		// String country = geocodedLocation.getCountry() != null ?
		// geocodedLocation
		// .getCountry() : "";

		final Popup popup = new Popup(marker.getLon(), marker.getLat(),
				"<p><b>" + stopType.toString()
						+ "</b></p><p><b>Dirección: </b>" + street);
		// + "</p><p><b> Localidad: </b>" + locality
		// + "</p><p><b>Código Postal: </b>" + postalCode
		// + "</p><p><b> Provincia: </b>" + province
		// + "</p><p><b> País: </b>" + country + "</p>");
		popup.setPopupStyle(PopupStyle.FRAMED_CLOUD);
		popup.setAnchor(marker);

		marker.addClickListener(new ClickListener() {

			@Override
			public void click(ClickEvent event) {

				routeOpenLayersMap.addPopup(popup);
			}
		});

		return marker;
	}

	private void routeCalculation(Marker originStopMarker,
			Marker destinationStopMarker) {

		if (originStopMarker == null || destinationStopMarker == null)
			return;

		MapQuestOpenDirectionsService mapQuestOpenDirectionsService = MapQuestOpenDirectionsService
				.getInstance();
		
		try {
			Route calculatedRoute = mapQuestOpenDirectionsService.getRoute(new MapCoordinates(originStopMarker.getLat(), originStopMarker.getLon()), new MapCoordinates(destinationStopMarker.getLat(), destinationStopMarker.getLon()));
			//Draw the path
			drawRoute(calculatedRoute);
			
			
			
		} catch (RoutePlannerException e) {
			e.printStackTrace();
		}

	}
	
	private void drawRoute(Route route){
		
		List<Point> routePoints = new ArrayList<Point>();
		
		for(MapCoordinates coordinates : route.getShape().getShapePoints())
			routePoints.add(new Point(coordinates.getLongitude(), coordinates.getLatitude()));
		
		
		PolyLine routePath = new PolyLine();
		
		Point[] points = new Point[routePoints.size()];
		routePoints.toArray(points);
		routePath.setPoints(points);
		polylinesLayer.addVector(routePath);
		
		getMainWindow().showNotification("Kilometros totales: " + route.getDistance() + " y consumo aproximado:" + route.getFuelUsed());
		
		
	}

}
