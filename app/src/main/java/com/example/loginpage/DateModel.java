package com.example.loginpage;

/**
 * Represents a day in the calendar.
 * Stores the day number, whether it belongs to the current month,
 * and whether it has an event.
 */
public class DateModel {
    //The day number in the month
    public int day;

    // True if the day is part of the current displayed month
    public boolean isCurrentMonth;

    //True if there is an event on this day
    public boolean hasEvent;

    /**
     * Creates a DateModel with the day, if it's in the current month,
     * and if it has an event.
     *
     * @param day the day number
     * @param isCurrentMonth true if day is in the current month
     * @param hasEvent true if this day has an event
     */
    public DateModel(int day, boolean isCurrentMonth, boolean hasEvent) {
        this.day = day;
        this.isCurrentMonth = isCurrentMonth;
        this.hasEvent = hasEvent;
    }

    /**
     * Creates a DateModel with the day and if it's in the current month.
     * Assumes no event on this day.
     *
     * @param day the day number
     * @param isCurrentMonth true if day is in the current month
     */    public DateModel(int day, boolean isCurrentMonth) {
        this(day, isCurrentMonth, false);  // default hasEvent to false
    }
}
