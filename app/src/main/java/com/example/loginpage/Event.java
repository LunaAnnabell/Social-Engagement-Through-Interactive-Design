package com.example.loginpage;

/**
 * Represents an event with a date, title, description, image, venue, and related character persona.
 */
public class Event {
    private String date;
    private String title;
    private String description;
    private int imageResourceId;
    private Venue venue;
    private String characterName;


    private String dateKey; // Normalized key like "2025-05-22"

    /**
     * Creates an Event object.
     *
     * @param date the date string (e.g. "Thursday 22-05")
     * @param title the event title
     * @param description the event description
     * @param imageResourceId the resource ID for the event image
     * @param venue the Venue object for the event location
     * @param characterName the character related to this event
     */
    public Event(String date, String title, String description, int imageResourceId, Venue venue, String characterName) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.venue = venue;
        this.characterName = characterName;

        // Extract day and month from the date string like "Thursday 22-05"
        try {
            String[] parts = date.split(" ");
            String[] dayMonth = parts[1].split("-");

            int day = Integer.parseInt(dayMonth[0]);
            int month = Integer.parseInt(dayMonth[1]);
            int year = 2025; //Default year

            // Format to "yyyy-MM-dd"
            this.dateKey = String.format("%04d-%02d-%02d", year, month, day);
        } catch (Exception e) {
            this.dateKey = null; // fallback if parsing fails
        }
    }
    /** Returns the character name related to the event */
    public String getCharacterName() {
        return characterName;
    }

    /** Returns the date string of the event */
    public String getDate() {
        return date;
    }

    /** Returns the event title */
    public String getTitle() {
        return title;
    }

    /** Returns the event description */
    public String getDescription() {
        return description;
    }

    /** Returns the venue object */
    public Venue getVenue() {
        return venue;
    }

    /** Returns the name of the venue */
    public String getVenueName() {
        return venue.getName();
    }

    /** Returns the coordinates of the venue */
    public Coordinates getCoordinates() {
        return venue.getCoordinates();
    }
}


