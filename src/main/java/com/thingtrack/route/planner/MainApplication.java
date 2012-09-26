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

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Demonstration application that shows how to use a simple custom client-side
 * GWT component, the ColorPicker.
 */
@SuppressWarnings("serial")
public class MainApplication extends com.vaadin.Application {

	private Window main;
	private VerticalLayout mainLayout;
	private RoutePlannerLayout routePlannerLayout;
	
	@Override
	public void init() {

		main = new Window();
		main.setSizeFull();
		mainLayout = buildMainLayout();
		main.setContent(mainLayout);
		
		setMainWindow(main);
		
	}

	private VerticalLayout buildMainLayout() {

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		
		routePlannerLayout = new RoutePlannerLayout();
		routePlannerLayout.setSizeFull();
		mainLayout.addComponent(routePlannerLayout);
		
		return mainLayout;
	}

}
