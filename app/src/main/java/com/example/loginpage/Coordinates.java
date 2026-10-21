package com.example.loginpage;
//This file is not used at this point but thought to be used for the map implementation

/**
 * The {@code Coordinates} class represents a geographical location
 * defined by its latitude and longitude.
 */
public class Coordinates {
    //Attributes
    private double latitude;
    private double longitude;

    //Methods
    /**
     * Constructs a {@code Coordinates} object with the specified latitude and longitude.
     *
     * @param latitude  the latitude of the location
     * @param longitude the longitude of the location
     */
    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns the latitude of this coordinate.
     * @return the latitude value
     */
    public double getLatitude() {
        return latitude;
    }
    /**
     * Returns the longitude of this coordinate.
     * @return the longitude value
     */
    public double getLongitude() {
        return longitude;
    }
}
