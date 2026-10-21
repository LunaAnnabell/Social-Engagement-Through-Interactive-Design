package com.example.loginpage;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

/**
 * Displays detailed information about a selected character,
 * including name, category, description, and image.
 * Allows navigation between characters and interaction with related activities.
 */
public class CharacterDetailActivity extends AppCompatActivity {

    private List<Characters> charactersList;
    private int currentIndex = 0;

    // UI components
    private TextView nameView, categoryView, descriptionView;
    private ImageView imageView;
    private LinearLayout backgroundContainer;
    private Button selectButton, btnViewFavorite;

    /**
     * Called when the activity is first created.
     * Sets up the UI, loads characters, and handles button interactions.
     *
     * @param savedInstanceState If the activity is being re-initialized, this contains the previous state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        // Initialize UI elements
        nameView = findViewById(R.id.characterName);
        categoryView = findViewById(R.id.characterCategory);
        descriptionView = findViewById(R.id.characterDescription);
        imageView = findViewById(R.id.characterImage);
        backgroundContainer = findViewById(R.id.backgroundContainer); // NEW
        ImageButton btnBack = findViewById(R.id.btnBack);
        ImageButton btnPrevious = findViewById(R.id.btnPreviousArrow);
        ImageButton btnNext = findViewById(R.id.btnNextArrow);
        btnViewFavorite = findViewById(R.id.btnViewFavourite);
        selectButton = findViewById(R.id.selectButton);

        // Load all characters
        FakeDatabase fakeDatabase = new FakeDatabase();
        charactersList = fakeDatabase.getCharacterList();

        // Get selected character from Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        if (name == null) {
            Log.e("CharacterDetailActivity", "Character name not found in intent");
            return;
        }
        // Find index of selected character
        for (int i = 0; i < charactersList.size(); i++) {
            if (charactersList.get(i).getName().equals(name)) {
                currentIndex = i;
                break;
            }
        }

        // Display the selected character
        updateCharacterView();

        // Handle navigation and button clicks
        btnBack.setOnClickListener(v -> finish());

        btnPrevious.setOnClickListener(v -> {
            currentIndex = (currentIndex - 1 + charactersList.size()) % charactersList.size();
            updateCharacterView();
        });

        btnNext.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % charactersList.size();
            updateCharacterView();
        });

        btnViewFavorite.setOnClickListener(v -> {
            Intent venueIntent = new Intent(this, FavoriteVenuesActivity.class);
            venueIntent.putExtra("name", charactersList.get(currentIndex).getName());
            startActivity(venueIntent);
        });

        selectButton.setOnClickListener(v -> {
            Intent timelineIntent = new Intent(CharacterDetailActivity.this, TimelineOfEventsActivity.class);
            timelineIntent.putExtra("characterName", charactersList.get(currentIndex).getName());
            Log.d("CharacterDetailActivity", "Passing characterName: " + charactersList.get(currentIndex).getName());
            startActivity(timelineIntent);
        });
    }

    /**
     * Updates the UI elements to show information about the currently selected character.
     * This includes text fields, image, background color, and button styles.
     */
    private void updateCharacterView() {
        Characters character = charactersList.get(currentIndex);

        nameView.setText(character.getName());
        categoryView.setText(character.getCategory());
        descriptionView.setText(character.getDescription());
        imageView.setImageResource(character.getImageResourceId());

        // Get lightened color for rounded background
        int lightThemeColor = Colours.lightenColor(character.getThemeColour(), 0.7f);

        // Set the color of the rounded background drawable dynamically
        GradientDrawable bgDrawable = (GradientDrawable) backgroundContainer.getBackground();
        if (bgDrawable != null) {
            bgDrawable.setColor(lightThemeColor);
        }

        // Set button tints dynamically
        int themeColor = character.getThemeColour();
        ColorStateList tint = ColorStateList.valueOf(themeColor);

        selectButton.setBackgroundTintList(tint);
        selectButton.setTextColor(Color.BLACK);

        btnViewFavorite.setBackgroundTintList(tint);
        btnViewFavorite.setTextColor(Color.BLACK);
    }
}
