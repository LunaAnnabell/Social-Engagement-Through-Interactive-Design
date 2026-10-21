package com.example.loginpage;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
/**
 * Activity that shows a character's favorite venues.
 * It displays the character image, name, city,
 * and a list of venue details dynamically.
 */
public class FavoriteVenuesActivity extends AppCompatActivity {

    private List<Characters> charactersList;
    private int currentIndex = 0;
    private TextView characterName, venueCityName;
    private ImageButton btnBack;
    private ImageView characterImage;

    /**
     * Called when the activity is created.
     * Sets up views and updates the venue list.
     * @param savedInstanceState saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorite_venues);

        characterImage = findViewById(R.id.characterImage);
        characterName = findViewById(R.id.characterName);
        venueCityName = findViewById(R.id.in_cityName);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        updateFavoriteVenueView();
    }
    /**
     * Creates a view displaying details of a single venue.
     * @param venue the venue to display
     * @return a LinearLayout containing venue info views
     */
    private View createVenueView(Venue venue) {

        // Venue Name TextView
        TextView venueName = new TextView(this);
        venueName.setText(venue.getName());
        venueName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        venueName.setTypeface(null, Typeface.BOLD);
        venueName.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nameParams.setMargins(dpToPx(0), dpToPx(8), dpToPx(0), dpToPx(4));
        venueName.setLayoutParams(nameParams);

        // Venue Type TextView
        TextView venueType = new TextView(this);
        venueType.setText(venue.getType());
        venueType.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        venueType.setTextColor(Color.DKGRAY);
        LinearLayout.LayoutParams typeParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        typeParams.setMargins(dpToPx(0), dpToPx(0), dpToPx(0), dpToPx(8));
        venueType.setLayoutParams(typeParams);

        // Venue ImageView
        ImageView venueImage = new ImageView(this);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                dpToPx(365), dpToPx(200));
        imageParams.setMargins(dpToPx(0), dpToPx(0), dpToPx(0), dpToPx(8));
        venueImage.setLayoutParams(imageParams);
        venueImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        venueImage.setImageResource(venue.getImageResourceId());

        // Venue Description TextView
        TextView venueDescription = new TextView(this);
        venueDescription.setText(venue.getDescription());
        venueDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        venueDescription.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams descParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        descParams.setMargins(dpToPx(0), dpToPx(0), dpToPx(0), dpToPx(8));
        venueDescription.setLayoutParams(descParams);

        // View Details Button
        Button btnViewDetails = new Button(this);
        btnViewDetails.setText("View Details");
        btnViewDetails.setAllCaps(false);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnParams.setMargins(dpToPx(0), dpToPx(0), dpToPx(0), dpToPx(16));
        btnViewDetails.setLayoutParams(btnParams);
        btnViewDetails.setOnClickListener(v -> finish());

        // Parent layout for this venue's info
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, dpToPx(8), 0, dpToPx(8));
        layout.setLayoutParams(layoutParams);
        layout.setGravity(Gravity.CENTER_VERTICAL);
       // layout.setBackgroundColor(Color.parseColor("#F5F5F5")); // optional light background

        // Add all views to the parent layout
        layout.addView(venueName);
        layout.addView(venueType);
        layout.addView(venueImage);
        layout.addView(venueDescription);
        layout.addView(btnViewDetails);

        return layout;
    }
    /**
     * Updates the UI with the character's favorite venues.
     * Loads character from intent extra and shows venues dynamically.
     */
    private void updateFavoriteVenueView() {
        String name = getIntent().getStringExtra("name");
        FakeDatabase fakeDatabase = new FakeDatabase();
        charactersList = fakeDatabase.characterList;

        Characters character = null;
        for (Characters c : charactersList) {
            if (c.getName().equals(name)) {
                character = c;
                break;
            }
        }

        if (character == null) {
            finish();  // Character not found; exit gracefully
            return;
        }
        // Debug: print number of favorite venues for this character
        if (character.getVenues() != null) {
        }
        if (!character.getVenues().isEmpty()) {
            Venue venueCity = character.getVenues().get(0);
            venueCityName.setText("In " + venueCity.getCityName());
        } else {
            venueCityName.setText("No favorite venues found ");
        }

        characterImage.setImageResource(character.getImageResourceId());
        characterName.setText(character.getName() + "'s Favorite Locations");

        LinearLayout venueList = findViewById(R.id.venueInfo);
        venueList.removeAllViews();  // clear old views if any
        for (Venue venue : character.getVenues()) {
            View venueView = createVenueView(venue);
            venueList.addView(venueView);
        }

    }

    /**
     * Converts dp (density independent pixels) to actual pixels.
     * @param dp the value in dp
     * @return equivalent pixels for the device
     */
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
