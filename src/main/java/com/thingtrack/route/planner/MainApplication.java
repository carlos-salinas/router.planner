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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.vaadin.addons.locationtextfield.GeocodedLocation;
import org.vaadin.addons.locationtextfield.GoogleGeocoder;
import org.vaadin.addons.locationtextfield.LocationTextField;
import org.vaadin.vol.GoogleStreetMapLayer;
import org.vaadin.vol.Marker;
import org.vaadin.vol.MarkerLayer;
import org.vaadin.vol.OpenLayersMap;
import org.vaadin.vol.OpenStreetMapLayer;
import org.vaadin.vol.Point;
import org.vaadin.vol.PolyLine;
import org.vaadin.vol.Vector;
import org.vaadin.vol.VectorLayer;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Demonstration application that shows how to use a simple custom client-side
 * GWT component, the ColorPicker.
 */
@SuppressWarnings("serial")
public class MainApplication extends com.vaadin.Application {

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
		
		//TEST OPENLAYERS VECTORS
		Marker originMarker = new Marker(-5.8509273, 43.3637619);
		originMarker.setIcon(new ThemeResource("icons/marker.png"), 16, 16);
		markerLayer.addMarker(originMarker);
		
		Marker destinationMarker = new Marker(-5.6619264, 43.5452608);
		destinationMarker.setIcon(new ThemeResource("icons/marker.png"), 16, 16);
		markerLayer.addMarker(destinationMarker);
		
		PolyLine testRoute = new PolyLine();
		testRoute.setPoints(new Point(-5.8509273, 43.3637619), new Point(-5.6619264, 43.5452608));
		polylinesLayer.addVector(testRoute);
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

}
