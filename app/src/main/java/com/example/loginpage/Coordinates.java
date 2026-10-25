package com.example.loginpage;

/**
 * Represents a geographic coordinate defined by latitude and longitude.
 * Used by {@link Venue} entities for spatial mapping, geolocation filtering,
 * and future map provider integration.
 */
public class Coordinates {
    private double latitude;
    private double longitude;

    /**
     * Constructs a {@code Coordinates} object with the specified latitude and longitude.
     *
     * @param latitude  the latitude in decimal degrees
     * @param longitude the longitude in decimal degrees
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
