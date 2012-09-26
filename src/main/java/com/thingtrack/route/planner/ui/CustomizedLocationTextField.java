package com.thingtrack.route.planner.ui;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.locationtextfield.GeocodedLocation;
import org.vaadin.addons.locationtextfield.GoogleGeocoder;
import org.vaadin.addons.locationtextfield.LocationTextField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Property.ValueChangeNotifier;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;

public class CustomizedLocationTextField extends CustomComponent implements
		ValueChangeNotifier {
	@AutoGenerated
	private HorizontalLayout mainLayout;

	@AutoGenerated
	private LocationTextField<GeocodedLocation> stopLocationTextField;

	@AutoGenerated
	private Embedded stopIcon;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private List<ValueChangeListener> listeners = new ArrayList<ValueChangeListener>();

	private static final String STOP_ICON_URL = "http://icons.mqcdn.com/icons/stop.png";
	private GoogleGeocoder geocoder = GoogleGeocoder.getInstance();
	private String stopCaption;
	private String stopColor;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	public CustomizedLocationTextField(String caption) {

		this.stopCaption = caption;

		buildMainLayout();
		setCompositionRoot(mainLayout);

		stopLocationTextField.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {

				for (ValueChangeListener listener : listeners)
					listener.valueChange(event);
			}
		});
	}

	public CustomizedLocationTextField(String caption, String color) {

		this.stopCaption = caption;
		this.stopColor = color;

		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		stopLocationTextField.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {

				for (ValueChangeListener listener : listeners)
					listener.valueChange(event);
			}
		});
	}

	@AutoGenerated
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(true);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		setHeight("28px");

		// embedded_1
		stopIcon = new Embedded();
		stopIcon.setImmediate(false);
		stopIcon.setWidth("-1px");
		stopIcon.setHeight("-1px");

		if (stopColor != null)
			stopIcon.setSource(new ExternalResource(STOP_ICON_URL + "?text="
					+ stopCaption + "&color=" + stopColor));

		else
			stopIcon.setSource(new ExternalResource(STOP_ICON_URL + "?text="
					+ stopCaption));

		stopIcon.setType(1);
		stopIcon.setMimeType("image/png");
		mainLayout.addComponent(stopIcon);
		mainLayout.setComponentAlignment(stopIcon, Alignment.MIDDLE_LEFT);

		// locationTextField_1
		stopLocationTextField = new LocationTextField<GeocodedLocation>(
				geocoder, GeocodedLocation.class);
		stopLocationTextField.setImmediate(true);
		stopLocationTextField.setWidth("100%");
		stopLocationTextField.setHeight("-1px");
		stopLocationTextField.setDescription("Escribe la dirección de la parada lo más detallada posible");
		mainLayout.addComponent(stopLocationTextField);
		mainLayout.setComponentAlignment(stopLocationTextField,
				Alignment.MIDDLE_LEFT);
		mainLayout.setExpandRatio(stopLocationTextField, 1.0f);

		return mainLayout;
	}

	public String getStopCaption() {

		return this.stopCaption;
	}

	public void setStopCaption(String caption) {

		this.stopCaption = caption;
		stopIcon.setSource(new ExternalResource(STOP_ICON_URL + "?text="
				+ stopCaption + "&color=" + stopColor));
	}

	@Override
	public void addListener(ValueChangeListener listener) {
		listeners.add((ValueChangeListener) listener);
	}

	@Override
	public void removeListener(ValueChangeListener listener) {
		listeners.remove((ValueChangeListener) listener);
	}

	@Override
	public void setDescription(String description) {
		stopIcon.setDescription(description);
	}
	
	@Override
	public Object getData() {
		return stopLocationTextField.getData();
	}
	
	@Override
	public void setData(Object data) {
		stopLocationTextField.setData(data);
	}

}
