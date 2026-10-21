package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * LoginActivity handles user Login interaction
 * It allows users to input their credentials and verifies them
 * If the credentials are valid, it navigates to WelcomeActivities
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * makes 2 private object which contains 2 variables.
     */
    private EditText usernameInput, passwordInput;
    /**
     * Button used to log in
     */
    private Button loginButton;
    /**
     * makes a private object which contains the variable resulText
     */
    private TextView resultText;

    /**
     * Overrides a superclass
     *
     */
    @Override
    /**
     * Called when the activity is starting. this is where most initializations should go.
     * Sets the layout and initializes UI components such as EditText and Button.
     * Adds a click listener to the login button to handle login logic.
     *
     * @param savedInstanceState if the activity is being re-initialized after previously
     * being shut down, this Bundle contains the most recent data. Otherwise, it is null.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /**
         * Initializes input fields and button.
         */
        usernameInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles the login button click event.
             * Retrieves input from username and password fields,
             * validates them using Userlogin class, and
             * navigates to WelcomeActivity if valid. Displays error otherwise.
             *
              * @param v The view clicked.
             */
            @Override
            public void onClick(View v) {
                String usernameStr = usernameInput.getText().toString().trim();
                String passwordStr = passwordInput.getText().toString().trim();

                Userlogin user = new Userlogin(usernameStr,passwordStr);

                if(user.isValidLogin()){

                    Intent intent = new Intent(LoginActivity.this, CharacterSelectionActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    resultText.setText("Invalid username or password.");


                }
            }
        });
    }
}
