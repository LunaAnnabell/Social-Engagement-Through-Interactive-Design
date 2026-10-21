package com.example.loginpage;
/**
 * Represents a place where an event happens.
 * Stores information like name, address, city, and location coordinates.
 */
public class Venue {
    private String name;
    private String address;
    private Coordinates coordinates;
    private String cityName;
    private String description;
    private String type;
    private int imageResourceId;

    /**
     * Creates a venue with a name, address, and coordinates.
     *
     * @param name the name of the venue
     * @param address the street address of the venue
     * @param coordinates the latitude and longitude of the venue
     */
    public Venue(String name, String address, Coordinates coordinates) {
        this.name = name;
        this.address = address;
        this.coordinates = coordinates;
    }

    /**
     * Creates a venue with full details.
     *
     * @param name the name of the venue
     * @param address the street address
     * @param coordinates the location coordinates
     * @param cityName the city where the venue is located
     * @param type the type of venue (e.g., park, gallery)
     * @param description a short description of the venue
     * @param imageResourceId the image resource ID for display
     */
    public Venue(String name, String address, Coordinates coordinates, String cityName, String type, String description, int imageResourceId) {
        this.name = name;
        this.address = address;
        this.coordinates = coordinates;
        this.cityName = cityName;
        this.type = type;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    /**
     * Returns the venue name.
     * @return the name of the venue
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the venue address.
     * @return the address of the venue
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the city name of the venue.
     * @return the name of the city
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Returns the coordinates of the venue.
     * @return the location coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Returns the description of the venue.
     *
     * @return the description text
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of venue.
     * @return the venue type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the image resource ID.
     *
     * @return the ID used for the venue's image
     */
    public int getImageResourceId() {
        return imageResourceId;
    }
}
