package com.example.mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class SigninActivity extends AppCompatActivity {

    private ImageButton backButton;
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private CheckBox rememberMeCheckbox;
    private TextView forgotPasswordLink;
    private Button signInButton;
    private ImageButton googleSignIn;
    private ImageButton appleSignIn;
    private ImageButton facebookSignIn;
    private ImageButton twitterSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // Initialize views
        backButton = findViewById(R.id.backButton);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox);
        forgotPasswordLink = findViewById(R.id.forgotPasswordLink);
        signInButton = findViewById(R.id.signInButton);
        googleSignIn = findViewById(R.id.googleSignIn);
        appleSignIn = findViewById(R.id.appleSignIn);
        facebookSignIn = findViewById(R.id.facebookSignIn);
        twitterSignIn = findViewById(R.id.twitterSignIn);

        // Set click listeners
        backButton.setOnClickListener(v -> finish());
        
        signInButton.setOnClickListener(v -> {
            if (validateInputs()) {
                // TODO: Implement actual sign-in logic
                Toast.makeText(this, "Sign in successful!", Toast.LENGTH_SHORT).show();
                navigateToMainActivity();
            }
        });

        forgotPasswordLink.setOnClickListener(v -> {
            // TODO: Implement forgot password flow
            Toast.makeText(this, "Forgot password feature coming soon", Toast.LENGTH_SHORT).show();
        });

        // Social sign in buttons
        googleSignIn.setOnClickListener(v -> handleSocialSignIn("Google"));
        appleSignIn.setOnClickListener(v -> handleSocialSignIn("Apple"));
        facebookSignIn.setOnClickListener(v -> handleSocialSignIn("Facebook"));
        twitterSignIn.setOnClickListener(v -> handleSocialSignIn("Twitter"));
    }

    private boolean validateInputs() {
//        String email = emailEditText.getText().toString().trim();
//        String password = passwordEditText.getText().toString().trim();
//
//        if (email.isEmpty()) {
//            emailEditText.setError("Email is required");
//            return false;
//        }
//
//        if (password.isEmpty()) {
//            passwordEditText.setError("Password is required");
//            return false;
//        }

        return true;
    }

    private void handleSocialSignIn(String provider) {
        Toast.makeText(this, "Sign in with " + provider, Toast.LENGTH_SHORT).show();
        navigateToMainActivity();
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(SigninActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
} 