package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Handles user authentication interaction.
 * Validates credentials against demo data (admin / 1234) and transitions
 * into the main character selection screen upon successful authentication.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput, passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr = usernameInput.getText().toString().trim();
                String passwordStr = passwordInput.getText().toString().trim();

                Userlogin user = new Userlogin(usernameStr, passwordStr);

                if (user.isValidLogin()) {
                    Intent intent = new Intent(LoginActivity.this, CharacterSelectionActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials. Use demo: admin / 1234", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
