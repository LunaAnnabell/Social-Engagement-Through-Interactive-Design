package com.example.loginpage;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
/**
 * Activity that displays a scrollable list of characters for selection.
 * Each character is shown with an image and category in a rounded card layout.
 * Clicking a character opens its detail page.
 */
public class CharacterSelectionActivity extends AppCompatActivity {

    // List of characters (already created in the Characters class)
    FakeDatabase fakeDatabase= new FakeDatabase();
    List<Characters> charactersList = fakeDatabase.getCharacterList();

    /**
     * Called when the activity is starting.
     * Sets up the layout and dynamically adds character views to the list.
     *
     * @param savedInstanceState saved instance state bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        // Get the LinearLayout that will contain the character views
        LinearLayout characterListLayout = findViewById(R.id.characterList);

        // Iterate over the characters and add them to the layout
        for (Characters character : charactersList) {
            View characterView = createCharacterView(character);  // Create a clickable view for each character
            characterListLayout.addView(characterView);  // Add the created view to the layout
        }
    }

    /**
     * Creates a horizontally oriented view representing a character with a rounded background.
     * The view includes the character's image and category text and is clickable to open details.
     *
     * @param character the Characters object whose details are displayed
     * @return a view representing the character, ready to be added to a layout
     */
    private View createCharacterView(Characters character) {
        // Create outer layout for card
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setPadding(32, 32, 32, 32); // Inner padding for content
        layout.setGravity(Gravity.CENTER_VERTICAL);

        // Create rounded background with lightened color
        GradientDrawable background = new GradientDrawable();
        int lightThemeColor = Colours.lightenColor(character.getThemeColour(), 0.7f);  // lighten by 30%
        background.setColor(lightThemeColor);
        background.setCornerRadius(30f);  // Rounded corners
        layout.setBackground(background);

        // Set layout params (spacing between cards)
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 30); // Margin between cards
        layout.setLayoutParams(params);

        // Image
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(character.getImageResourceId());
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(300, 300);
        imageParams.setMargins(0, 0, 24, 0);
        imageView.setLayoutParams(imageParams);

        // Text
        TextView nameView = new TextView(this);
        nameView.setText(character.getCategory());
        nameView.setTextSize(25f);
        nameView.setTextColor(Color.BLACK); // Adjust based on background if needed
        nameView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        // Add views to layout
        layout.addView(imageView);
        layout.addView(nameView);

        // Make clickable
        layout.setClickable(true);
        layout.setFocusable(true);
        layout.setOnClickListener(v -> {
            Intent intent = new Intent(this, CharacterDetailActivity.class);
            intent.putExtra("name", character.getName());
            startActivity(intent);
        });

        return layout;
    }

}
