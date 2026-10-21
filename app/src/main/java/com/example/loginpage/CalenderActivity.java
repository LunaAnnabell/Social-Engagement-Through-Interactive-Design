package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/**
 * This activity shows a calendar with events for different characters.
 * You can switch between months and see events for the selected character.
 * When you tap a day with an event, it shows the event details.
 */
public class CalenderActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    RecyclerView calendarRecyclerView;
    TextView monthText;
    ImageButton prevMonthBtn, nextMonthBtn;

    LinearLayout eventDetailsPanel;
    TextView eventName, venueName, eventDescription, eventDate;

    Calendar currentCalendar = Calendar.getInstance();
    CalendarAdapter adapter;

    Map<String, Event> eventMap = new HashMap<>();
    List<Integer> daysWithEvents = new ArrayList<>();

    String selectedCharacter;
    private View characterColorSquare;
    private int characterThemeColor = 0xFFCCCCCC; // default gray

    /**
     * Called when the activity is created.
     * Sets up views, gets the selected character, and loads events.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthText = findViewById(R.id.monthText);
        prevMonthBtn = findViewById(R.id.prevMonthBtn);
        nextMonthBtn = findViewById(R.id.nextMonthBtn);

        eventDetailsPanel = findViewById(R.id.eventDetailsPanel);
        eventName = eventDetailsPanel.findViewById(R.id.titleName);
        venueName = eventDetailsPanel.findViewById(R.id.VenueName);
        eventDescription = eventDetailsPanel.findViewById(R.id.eventDescription);
        eventDate = eventDetailsPanel.findViewById(R.id.eventDate);
        characterColorSquare = findViewById(R.id.characterColorSquare);
        calendarRecyclerView.setLayoutManager(new GridLayoutManager(this, 7));

        // Get selected character from intent
        selectedCharacter = getIntent().getStringExtra("characterName");
        if (selectedCharacter == null) selectedCharacter = "";

        // Set initial theme and data
        applyCharacterTheme(selectedCharacter);
        loadCharacterEvents();
        updateCalendar();

        // Month navigation
        prevMonthBtn.setOnClickListener(v -> {
            currentCalendar.add(Calendar.MONTH, -1);
            updateCalendar();
            eventDetailsPanel.setVisibility(View.GONE);
        });

        nextMonthBtn.setOnClickListener(v -> {
            currentCalendar.add(Calendar.MONTH, 1);
            updateCalendar();
            eventDetailsPanel.setVisibility(View.GONE);
        });

        // Bottom nav icons
        ImageView calendarIcon = findViewById(R.id.calendarIcon);
        calendarIcon.setOnClickListener(v -> {
            Intent intent = new Intent(CalenderActivity.this, CalenderActivity.class);
            intent.putExtra("characterName", selectedCharacter);
            startActivity(intent);
            finish();
        });

        ImageView homeIcon = findViewById(R.id.homeIcon);
        homeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(CalenderActivity.this, TimelineOfEventsActivity.class);
            startActivity(intent);
        });
        ImageView profileIcon = findViewById(R.id.profileIcon);
        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(CalenderActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        // Category tab buttons
        findViewById(R.id.categorySocial).setOnClickListener(v -> switchCharacter("Tipsy"));
        findViewById(R.id.categoryMusic).setOnClickListener(v -> switchCharacter("Bongo"));
        findViewById(R.id.categoryActive).setOnClickListener(v -> switchCharacter("Rally"));
        findViewById(R.id.categoryCreative).setOnClickListener(v -> switchCharacter("Sketchy"));
        findViewById(R.id.categoryGeeky).setOnClickListener(v -> switchCharacter("Glitch"));
    }
    /**
     * Switches to a new character, updates theme and events.
     * Does nothing if the same character is selected.
     *
     * @param characterName the new character to switch to
     */
    private void switchCharacter(String characterName) {
        if (characterName.equalsIgnoreCase(selectedCharacter)) return;

        selectedCharacter = characterName;
        applyCharacterTheme(characterName);
        loadCharacterEvents();
        eventDetailsPanel.setVisibility(View.GONE);
        updateCalendar();
    }
    /**
     * Sets the theme color based on the selected character.
     * @param characterName the character to get the theme color from
     */
    private void applyCharacterTheme(String characterName) {
        FakeDatabase fakeDb = new FakeDatabase();
        List<Characters> charactersList = fakeDb.getCharacterList();

        for (Characters character : charactersList) {
            if (character.getName().equalsIgnoreCase(characterName)) {
                characterThemeColor = character.getThemeColour(); // Save it
                break;
            }
        }
        characterColorSquare.setBackgroundColor(characterThemeColor); // Use it
    }
    /**
     * Loads events for the selected character into a map.
     */
    private void loadCharacterEvents() {
        eventMap.clear();
        FakeDatabase fakeDb = new FakeDatabase();
        for (Event event : fakeDb.getEventList()) {
            if (event.getCharacterName().equalsIgnoreCase(selectedCharacter)) {
                String dateKey = convertToDateKey(event.getDate());
                if (dateKey != null) {
                    eventMap.put(dateKey, event);
                }
            }
        }
    }
    /**
     * Updates the calendar grid to show the current month and marks days with events.
     */
    private void updateCalendar() {
        daysWithEvents.clear();

        String currentMonthKey = getMonthKey(currentCalendar);

        for (Map.Entry<String, Event> entry : eventMap.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith(currentMonthKey)) {
                String[] parts = key.split("-");
                int day = Integer.parseInt(parts[2]);
                daysWithEvents.add(day);
            }
        }

        List<DateModel> days = new ArrayList<>();
        Calendar temp = (Calendar) currentCalendar.clone();
        temp.set(Calendar.DAY_OF_MONTH, 1);
        int paddingDays = temp.get(Calendar.DAY_OF_WEEK) - 1;
        int daysInMonth = temp.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < paddingDays; i++) {
            days.add(new DateModel(0, false));
        }

        for (int i = 1; i <= daysInMonth; i++) {
            boolean hasEvent = daysWithEvents.contains(i);
            days.add(new DateModel(i, true, hasEvent));
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        monthText.setText(sdf.format(currentCalendar.getTime()));

        adapter = new CalendarAdapter(days, this, characterThemeColor);
        calendarRecyclerView.setAdapter(adapter);
    }
    /**
     * Called when a day in the calendar is clicked.
     * Shows event details if an event exists on that day.
     *
     * @param day the day that was clicked
     */
    @Override
    public void onItemClick(int day) {
        if (day <= 0) return;

        String fullKey = String.format("%04d-%02d-%02d",
                currentCalendar.get(Calendar.YEAR),
                currentCalendar.get(Calendar.MONTH) + 1,
                day);

        Event event = eventMap.get(fullKey);

        if (event != null) {
            eventName.setText(event.getTitle());
            venueName.setText("Venue: " + event.getVenueName());
            eventDescription.setText(event.getDescription());
            ImageView eventImage = eventDetailsPanel.findViewById(R.id.eventImage);
            eventImage.setImageResource(event.getVenue().getImageResourceId());
            eventDate.setText("Date: " + event.getDate());

            eventDetailsPanel.setVisibility(View.VISIBLE);
        } else {
            eventDetailsPanel.setVisibility(View.GONE);
        }
    }
    /**
     * Converts an event date string to a "YYYY-MM-DD" key.
     * Returns null if the format is invalid.
     *
     * @param eventDate date string like "someText DD-MM"
     * @return formatted date key or null
     */
    private String convertToDateKey(String eventDate) {
        String[] parts = eventDate.split(" ");
        if (parts.length == 2) {
            String[] dateParts = parts[1].split("-");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = currentCalendar.get(Calendar.YEAR);
            return String.format("%04d-%02d-%02d", year, month, day);
        }
        return null;
    }
    /**
     * Creates a string key for the year and month of the calendar.
     * Format: "YYYY-MM"
     *
     * @param calendar calendar instance
     * @return formatted month key
     */
    private String getMonthKey(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return String.format("%04d-%02d", year, month);
    }
}
