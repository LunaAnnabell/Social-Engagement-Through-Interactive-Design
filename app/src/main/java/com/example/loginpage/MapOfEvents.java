package com.example.loginpage;

import java.util.ArrayList;

/**
 * Manages collections of spatial events for map visualization and geolocation tracking.
 * Encapsulates event update logic for map markers and interactive layers.
 */
public class MapOfEvents {

    private ArrayList<Event> eventList = new ArrayList<>();

    /**
     * Replaces the current event list with an updated collection of events.
     *
     * @param events new list of events to plot on the map
     */
    public void updateMap(ArrayList<Event> events) {
        this.eventList.clear();
        this.eventList.addAll(events);
    }

    /**
     * Gets the list of events currently registered on the map.
     *
     * @return list of Event objects
     */
    public ArrayList<Event> getMapEvents() {
        return eventList;
    }
}
