package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
/**
 * Shows the details of a selected event.
 * Displays title, venue, date, description, and image of the event.
 */
public class EventDetailsActivity extends AppCompatActivity {

    private List<Event> eventList;
    private int currentIndex = 0;

    private TextView titleView, venueView, dateView, descriptionView;
    private ImageView pictureView;

    /**
     * Called when the activity is created.
     * Sets up the views and loads event details based on the venue name passed in the intent.
     *
     * @param savedInstanceState saved state of the activity (can be null)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        titleView = findViewById(R.id.titleName);
        venueView = findViewById(R.id.VenueName);
        dateView = findViewById(R.id.eventDate);
        descriptionView = findViewById(R.id.eventDescription);
        pictureView = findViewById(R.id.eventImage);
        ImageButton btnBack = findViewById(R.id.btnBack);

        // Handle back button click
        btnBack.setOnClickListener(v -> finish());

        Coordinates dummyCoords = new Coordinates(0.0, 0.0); // Replace with actual coordinates if available

        // Get events with full Venue info and long descriptions
        FakeDatabase fakeDatabase = new FakeDatabase();
        eventList = fakeDatabase.getEventList();

        Intent intent = getIntent();
        String eventTitle = intent.getStringExtra("title");

        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).getTitle().equalsIgnoreCase(eventTitle)) {
                currentIndex = i;
                break;
            }
        }

        Event currentEvent = eventList.get(currentIndex);

        // Set data into views
        titleView.setText(currentEvent.getTitle());
        venueView.setText(currentEvent.getVenueName());
        dateView.setText(currentEvent.getDate());
        descriptionView.setText(currentEvent.getDescription());
        pictureView.setImageResource(currentEvent.getVenue().getImageResourceId());
    }
}



