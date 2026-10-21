package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
/**
 * ProfileActivity displays the current user's profile information including username and password.
 * It also allows navigation to the calendar, home screen, and character detail screens.
 * The calendar screen starts with the currently selected character based on intent data.
 */
public class ProfileActivity extends AppCompatActivity {

    private TextView passwordValue;
    private Button viewPasswordBtn;
    private boolean isPasswordVisible = false;

    private List<Characters> charactersList;
    private FakeDatabase fakeDatabase;
    private int currentIndex = 0;
    /**
     * Called when the activity is starting. Sets up the UI, loads characters, and configures navigation.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // UI elements
        passwordValue = findViewById(R.id.passwordValue);
        viewPasswordBtn = findViewById(R.id.viewPasswordBtn);
        TextView usernameValue = findViewById(R.id.usernameValue);

        // Get username and password from Userlogin static methods
        String username = Userlogin.getUsername();
        String password = Userlogin.getPassword();

        // Display them
        usernameValue.setText(username != null ? username : "N/A");
        passwordValue.setText(password != null ? password : "N/A");

        // Hide password initially
        passwordValue.setTransformationMethod(PasswordTransformationMethod.getInstance());
        viewPasswordBtn.setText("View");

        // Toggle password visibility
        viewPasswordBtn.setOnClickListener(v -> {
            if (isPasswordVisible) {
                passwordValue.setTransformationMethod(PasswordTransformationMethod.getInstance());
                viewPasswordBtn.setText("View");
            } else {
                passwordValue.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                viewPasswordBtn.setText("Hide");
            }
            isPasswordVisible = !isPasswordVisible;
        });

        // Load characters from fake database
        fakeDatabase = new FakeDatabase();
        charactersList = fakeDatabase.getCharacterList();

        String characterName = getIntent().getStringExtra("characterName");
        for (int i = 0; i < charactersList.size(); i++) {
            if (charactersList.get(i).getName().equals(characterName)) {
                currentIndex = i;
                break;
            }
        }
        /**
         * Interaction with navigation buttons
         */
        // Button for Calender icon
        ImageView calendarIcon = findViewById(R.id.calendarIcon);
        calendarIcon.setOnClickListener(v -> {
            Characters selectedCharacter = charactersList.get(currentIndex);
            Intent intent = new Intent(ProfileActivity.this, CalenderActivity.class);
            intent.putExtra("characterName", selectedCharacter.getName()); // ✅ Pass the name
            startActivity(intent);
        });

        // Button navigation icons
        ImageView homeIcon = findViewById(R.id.homeIcon);
        homeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, TimelineOfEventsActivity.class);
            startActivity(intent);
        });
        // Button for profile icon
        ImageView profileIcon = findViewById(R.id.profileIcon);
        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        // Character category containers → open detail activity
        findViewById(R.id.categoryContainerTipsy).setOnClickListener(v -> openCharacterDetail("Tipsy"));
        findViewById(R.id.categoryContainerBongo).setOnClickListener(v -> openCharacterDetail("Bongo"));
        findViewById(R.id.categoryContainerRally).setOnClickListener(v -> openCharacterDetail("Rally"));
        findViewById(R.id.categoryContainerSketchy).setOnClickListener(v -> openCharacterDetail("Sketchy"));
        findViewById(R.id.categoryContainerGlitch).setOnClickListener(v -> openCharacterDetail("Glitch"));
    }
    /**
     * Opens the CharacterDetailActivity for a given character name.
     * @param characterName the name of the character to view in detail
     */
    private void openCharacterDetail(String characterName) {
        for (Characters character : charactersList) {
            if (character.getName().equalsIgnoreCase(characterName)) {
                Intent intent = new Intent(ProfileActivity.this, CharacterDetailActivity.class);
                intent.putExtra("name", character.getName());
                intent.putExtra("description", character.getDescription());
                intent.putExtra("imageResId", character.getImageResourceId());
                startActivity(intent);
                break;
            }
        }
    }
}
