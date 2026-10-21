package com.example.loginpage;

import android.graphics.Color; // Import Color class to use Color constants
import java.util.ArrayList;

/**
 * Represents a character with information like name, category, description, image, colors,
 * as well as lists of events and venues related to the character.
 */
public class Characters {

    // Attributes
    private String name;
    private String category;
    private String description;
    private int imageResourceId; // Resource ID for the image in the drawable folder
    private int themeColour;
    private int backgroundColour; // Default value for backgroundColour
    private MapOfEvents map;    // Ensure MapOfEvents class is properly imported
    private ArrayList<Event> events;  // Ensure Event class is properly imported
    private ArrayList<Venue> venues; // Ensure Venue class is properly imported

    /**
     * Constructs a new Characters object.
     *
     * @param name              the character's name
     * @param category          the category of the character (e.g., Social, Music)
     * @param description       a short description of the character
     * @param imageResourceId   an image resource ID representing the character
     * @param themeColour       the color associated with the character's theme (used for UI styling)
     * @param backgroundColour  the background color associated with the character
     */
    public Characters(String name, String category, String description, int imageResourceId, int themeColour, int backgroundColour) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.themeColour = themeColour;
        this.backgroundColour = backgroundColour;
        this.events = new ArrayList<>();
        this.venues = new ArrayList<>();
    }

    /**
     * Creates a new Characters object with all details including a background color.
     *
     * @param name             the character's name
     * @param category         the character's category (like Social, Music)
     * @param description      a short description of the character
     * @param imageResourceId  the image resource ID for the character's image
     * @param themeColour      the theme color for UI styling related to this character
     */
    public Characters(String name, String category, String description, int imageResourceId, int themeColour) {
        this(name, category, description, imageResourceId, themeColour, Color.WHITE);  // Default background color is white
    }

    // Methods

    /**
     * Adds an event to the character’s list of events.
     * Also adds the event's venue if it is not already in the list.
     * @param event the event to add
     */
    public void addEvent(Event event) {
        events.add(event);
        if (!venues.contains(event.getVenue())) {
            venues.add(event.getVenue());
        }
    }

    /**
     * Removes an event from the character’s list of events.
     *NOT USED at this point
     * @param event the event to remove
     */
    public void removeEvent(Event event) {
        events.remove(event);
        // Optionally remove venue if no other events use it
    }

    /**
     * Returns the list of events for this character.
     * @return a list of events
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Returns the list of venues associated with this character.
     * @return a list of venues
     */
    public ArrayList<Venue> getVenues() {
        return venues;
    }


    /**
     * Creates and sets up a map showing the locations of the character's events.
     *NOT USED at this point
     */
    public void createMap() {
        this.map = new MapOfEvents();
        map.updateMap(events);
    }


    /**
     * Returns the theme color used for this character (for UI consistency).
     * @return the theme color as an integer
     */
    public int getThemeColour() {
        return themeColour;
    }


    /**
     * Returns the image resource ID used to display this character’s image.
     * @return the image resource ID
     */
    public int getImageResourceId() {
        return imageResourceId;
    }

    /**
     * Returns the character's name.
     * @return the name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a description of the character.
     * @return the description as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the category of the character.
     * @return the category as a String
     */
    public String getCategory() {
        return category;
    }
}
