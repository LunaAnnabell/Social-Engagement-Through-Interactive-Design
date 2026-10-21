package com.example.loginpage;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
/**
 * TimelineOfEventsActivity shows a character's timeline of events.
 * It displays up to two upcoming events, lets the user switch characters,
 * and handles navigation between screens.
 */
public class TimelineOfEventsActivity extends AppCompatActivity {

    private TextView characterNameTextView;
    private ImageView characterImageView;
    private ImageView timelineImage;
    private LinearLayout backgroundContainer;
    private List<Characters> charactersList;
    private List<Event> eventList;
    private int currentIndex = 0;
    /**
     * Called when the activity starts.
     * Sets up the views, loads data from the fake database,
     * and handles button clicks for navigation and category selection.
     *
     * @param savedInstanceState holds saved data if the activity is recreated
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_of_events);

        characterNameTextView = findViewById(R.id.characterNameTextView);
        characterImageView = findViewById(R.id.characterImageView);
        timelineImage = findViewById(R.id.timelineImage);
        backgroundContainer = findViewById(R.id.backgroundContainer);
        ImageButton btnBack = findViewById(R.id.btnBack);

        View eventBubble1 = findViewById(R.id.eventBubble1);
        TextView event1Date = eventBubble1.findViewById(R.id.eventDate);
        TextView event1Title = eventBubble1.findViewById(R.id.eventTitle);
        Button event1Button = eventBubble1.findViewById(R.id.eventButton);
        View event1Divider = eventBubble1.findViewById(R.id.eventDivider);

        View eventBubble2 = findViewById(R.id.eventBubble2);
        TextView event2Date = eventBubble2.findViewById(R.id.eventDate);
        TextView event2Title = eventBubble2.findViewById(R.id.eventTitle);
        Button event2Button = eventBubble2.findViewById(R.id.eventButton);
        View event2Divider = eventBubble2.findViewById(R.id.eventDivider);

        ImageButton btnNext = findViewById(R.id.btnTimelineNext);
        ImageButton btnPrev = findViewById(R.id.btnTimelinePrev);

        FakeDatabase fakeDatabase = new FakeDatabase();
        charactersList = fakeDatabase.getCharacterList();
        eventList = fakeDatabase.getEventList();

        String characterName = getIntent().getStringExtra("characterName");
        for (int i = 0; i < charactersList.size(); i++) {
            if (charactersList.get(i).getName().equals(characterName)) {
                currentIndex = i;
                break;
            }
        }

        updateTimeline(characterNameTextView, characterImageView, timelineImage, backgroundContainer,
                event1Date, event1Title, event1Button, event1Divider,
                event2Date, event2Title, event2Button, event2Divider);

        btnNext.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % charactersList.size();
            updateTimeline(characterNameTextView, characterImageView, timelineImage, backgroundContainer,
                    event1Date, event1Title, event1Button, event1Divider,
                    event2Date, event2Title, event2Button, event2Divider);
        });

        btnPrev.setOnClickListener(v -> {
            currentIndex = (currentIndex - 1 + charactersList.size()) % charactersList.size();
            updateTimeline(characterNameTextView, characterImageView, timelineImage, backgroundContainer,
                    event1Date, event1Title, event1Button, event1Divider,
                    event2Date, event2Title, event2Button, event2Divider);
        });

        TextView categorySocial = findViewById(R.id.categorySocial);
        TextView categoryMusic = findViewById(R.id.categoryMusic);
        TextView categoryActive = findViewById(R.id.categoryActive);
        TextView categoryCreative = findViewById(R.id.categoryCreative);
        TextView categoryGeeky = findViewById(R.id.categoryGeeky);

        View.OnClickListener categoryClickListener = v -> {
            String clickedCategory = ((TextView) v).getText().toString();
            for (int i = 0; i < charactersList.size(); i++) {
                if (charactersList.get(i).getCategory().equalsIgnoreCase(clickedCategory)) {
                    currentIndex = i;
                    updateTimeline(characterNameTextView, characterImageView, timelineImage, backgroundContainer,
                            event1Date, event1Title, event1Button, event1Divider,
                            event2Date, event2Title, event2Button, event2Divider);
                    break;
                }
            }
        };

        categorySocial.setOnClickListener(categoryClickListener);
        categoryMusic.setOnClickListener(categoryClickListener);
        categoryActive.setOnClickListener(categoryClickListener);
        categoryCreative.setOnClickListener(categoryClickListener);
        categoryGeeky.setOnClickListener(categoryClickListener);


        // Interaction for navigation bar
        ImageView calendarIcon = findViewById(R.id.calendarIcon);
        calendarIcon.setOnClickListener(v -> {
            Characters selectedCharacter = charactersList.get(currentIndex);
            Intent intent = new Intent(TimelineOfEventsActivity.this, CalenderActivity.class);
            intent.putExtra("characterName", selectedCharacter.getName()); // ✅ Pass the name
            startActivity(intent);
        });

        ImageView homeIcon = findViewById(R.id.homeIcon);
        homeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(TimelineOfEventsActivity.this, TimelineOfEventsActivity.class);
            startActivity(intent);
        });
        ImageView profileIcon = findViewById(R.id.profileIcon);
        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(TimelineOfEventsActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

    }

    /**
     * Updates all UI elements with data for the current character.
     * Shows up to two events and applies theme colors.
     *
     * @param nameView text view for character name
     * @param imageView image view for character picture
     * @param timelineImage image view for the timeline graphic
     * @param bg container for background color
     * @param date1 first event date view
     * @param title1 first event title view
     * @param btn1 first event button
     * @param div1 first event divider view
     * @param date2 second event date view
     * @param title2 second event title view
     * @param btn2 second event button
     * @param div2 second event divider view
     */
    private void updateTimeline(TextView nameView, ImageView imageView, ImageView timelineImage,
                                LinearLayout bg, TextView date1, TextView title1, Button btn1, View div1,
                                TextView date2, TextView title2, Button btn2, View div2) {

        Characters c = charactersList.get(currentIndex);

        int themeColor = c.getThemeColour();
        int lightBackgroundColor = Colours.lightenColor(themeColor, 0.7f);

        // Update rounded rectangle background color
        View roundedBackground = findViewById(R.id.roundedBackground);
        GradientDrawable backgroundDrawable = (GradientDrawable) roundedBackground.getBackground();
        backgroundDrawable.setColor(lightBackgroundColor);


        nameView.setText(c.getName());
        imageView.setImageResource(c.getImageResourceId());
        timelineImage.setColorFilter(themeColor, android.graphics.PorterDuff.Mode.SRC_IN);

        Event[] matchedEvents = new Event[2];
        int count = 0;
        for (Event event : eventList) {
            if (event.getCharacterName().equalsIgnoreCase(c.getName()) && count < 2) {
                matchedEvents[count++] = event;
            }
        }

        if (matchedEvents[0] != null) {
            date1.setText(matchedEvents[0].getDate());
            title1.setText(matchedEvents[0].getTitle());
            div1.setBackgroundColor(themeColor);
            btn1.setBackgroundTintList(ColorStateList.valueOf(themeColor));
            btn1.setTextColor(Color.WHITE);
            btn1.setOnClickListener(v -> navigateToEvent(matchedEvents[0].getTitle()));
        } else {
            date1.setText("");
            title1.setText("");
            div1.setBackgroundColor(Color.TRANSPARENT);
            btn1.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
            btn1.setOnClickListener(null);
        }

        if (matchedEvents[1] != null) {
            date2.setText(matchedEvents[1].getDate());
            title2.setText(matchedEvents[1].getTitle());
            div2.setBackgroundColor(themeColor);
            btn2.setBackgroundTintList(ColorStateList.valueOf(themeColor));
            btn2.setTextColor(Color.WHITE);
            btn2.setOnClickListener(v -> navigateToEvent(matchedEvents[1].getTitle()));
        } else {
            date2.setText("");
            title2.setText("");
            div2.setBackgroundColor(Color.TRANSPARENT);
            btn2.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
            btn2.setOnClickListener(null);
        }
    }
    /**
     * Opens the event details screen for a given venue name.
     * @param eventTitle the name of the event
     */
    public void navigateToEvent(String eventTitle) {
        Intent intent = new Intent(this, EventDetailsActivity.class);
        intent.putExtra("title", eventTitle);
        startActivity(intent);
    }

}
