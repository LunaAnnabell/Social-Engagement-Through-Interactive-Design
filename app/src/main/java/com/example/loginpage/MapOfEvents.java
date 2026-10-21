package com.example.loginpage;
// NOT USED at this point, but intended for the map implementation
import java.util.ArrayList;

/**
 * MapOfEvents stores and manages a list of events to be shown on a map.
 * It allows updating and retrieving the list of events.
 */
public class MapOfEvents {
    //Attributes
    private ArrayList<Event> eventList = new ArrayList<>();

    //Constructor

    //Methods
    /**
     * Replaces the current event list with a new one.
     * @param events new list of events to show on the map
     */
    public void updateMap(ArrayList<Event> events) {
        this.eventList.clear();
        this.eventList.addAll(events);
    }
    /**
     * Gets the list of events currently set for the map.
     * @return list of Event objects
     */
    public ArrayList<Event> getMapEvents(){
    return eventList;
    }
}
