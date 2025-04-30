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

public class SignupActivity extends AppCompatActivity {

    private ImageButton backButton;
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private CheckBox termsCheckbox;
    private TextView termsLink;
    private TextView signInLink;
    private Button signUpButton;
    private ImageButton googleSignIn;
    private ImageButton appleSignIn;
    private ImageButton facebookSignIn;
    private ImageButton twitterSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        backButton = findViewById(R.id.backButton);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        termsCheckbox = findViewById(R.id.termsCheckbox);
        termsLink = findViewById(R.id.termsLink);
        signInLink = findViewById(R.id.signInLink);
        signUpButton = findViewById(R.id.signUpButton);
        googleSignIn = findViewById(R.id.googleSignIn);
        appleSignIn = findViewById(R.id.appleSignIn);
        facebookSignIn = findViewById(R.id.facebookSignIn);
        twitterSignIn = findViewById(R.id.twitterSignIn);

        // Set click listeners
        backButton.setOnClickListener(v -> finish());
        
        signUpButton.setOnClickListener(v -> {
            if (validateInputs()) {
                // TODO: Implement actual signup logic
                Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                navigateToMainActivity();
            }
        });

        termsLink.setOnClickListener(v -> {
            // TODO: Show terms and conditions
            Toast.makeText(this, "Terms and Conditions", Toast.LENGTH_SHORT).show();
        });

        signInLink.setOnClickListener(v -> {
            navigateToSigninActivity();
        });

        // Social sign in buttons
        googleSignIn.setOnClickListener(v -> handleSocialSignIn("Google"));
        appleSignIn.setOnClickListener(v -> handleSocialSignIn("Apple"));
        facebookSignIn.setOnClickListener(v -> handleSocialSignIn("Facebook"));
        twitterSignIn.setOnClickListener(v -> handleSocialSignIn("Twitter"));
    }

    private boolean validateInputs() {
        // String email = emailEditText.getText().toString().trim();
        // String password = passwordEditText.getText().toString().trim();

        // if (email.isEmpty()) {
        //     emailEditText.setError("Email is required");
        //     return false;
        // }

        // if (password.isEmpty()) {
        //     passwordEditText.setError("Password is required");
        //     return false;
        // }

        // if (!termsCheckbox.isChecked()) {
        //     Toast.makeText(this, "Please agree to the Terms and Conditions", Toast.LENGTH_SHORT).show();
        //     return false;
        // }

        return true;
    }

    private void handleSocialSignIn(String provider) {
        Toast.makeText(this, "Sign in with " + provider, Toast.LENGTH_SHORT).show();
        // TODO: Implement social sign in
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    
    private void navigateToSigninActivity() {
        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
        startActivity(intent);
    }
} 