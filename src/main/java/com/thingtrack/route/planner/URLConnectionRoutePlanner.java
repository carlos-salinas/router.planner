package com.thingtrack.route.planner;

import java.io.UnsupportedEncodingException;

import com.thingtrack.route.planner.model.MapCoordinates;


/**
 * Implementation that fetches data from an URL and delegates to implementing class to decode
 */
public abstract class URLConnectionRoutePlanner {

	/**
     * Retrieve the full URL to fetch
     * @param addresses input address
     * @return full URL
     * @throws UnsupportedEncodingException if subclass uses {@link java.net.URLEncoder} and it fails
     */
    protected abstract String getURL(String ... addresses) throws UnsupportedEncodingException;
    
    /**
     * Retrieve the full URL to fetch
     * @param coordinates input {@link com.thingtrack.route.planner.model.MapCoordinates }
     * @return full URL
     * @throws UnsupportedEncodingException if subclass uses {@link java.net.URLEncoder} and it fails
     */
    protected abstract String getURL(MapCoordinates ... coordinates) throws UnsupportedEncodingException;
}
