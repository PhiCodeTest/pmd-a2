package com.example.mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class QuoteDetailActivity extends AppCompatActivity {

    private ImageButton backButton;
    private TextView categoryText, quoteTitle, quoteText;
    private TextView authorText;
    private CardView likeButton;
    private CardView shareButton;
    private ImageView backgroundImage;
    private boolean isLiked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_detail);
        
        initViews();
        setupListeners();
        
        // Get data from intent
        String category = getIntent().getStringExtra("category");
        String title = getIntent().getStringExtra("title");
        String quote = getIntent().getStringExtra("quote");
        String author = getIntent().getStringExtra("author");
        int imageResourceId = getIntent().getIntExtra("imageResourceId", R.drawable.wordsofwisdom);
        
        // Display data
        if (category != null) {
            switch (category) {
                case "general":
                    categoryText.setText("General");
                    break;
                case "work":
                    categoryText.setText("Work & Productivity");
                    break;
                case "personal":
                    categoryText.setText("Personal Growth");
                    break;
                default:
                    categoryText.setText("Quote");
                    break;
            }
        }

        if (title != null) {
            quoteTitle.setText(title);
        }

        if (quote != null) {
            quoteText.setText(quote);
        }

        if (author != null) {
            authorText.setText("~ " + author + " ~");
        }
        
        // Set the background image directly using the resource ID passed from the previous activity
        backgroundImage.setImageResource(imageResourceId);
    }
    
    private void initViews() {
        backButton = findViewById(R.id.backButton);
        categoryText = findViewById(R.id.categoryText);
        quoteTitle = findViewById(R.id.quoteTitle);
        quoteText = findViewById(R.id.quoteText);
        authorText = findViewById(R.id.authorText);
        likeButton = findViewById(R.id.likeButton);
        shareButton = findViewById(R.id.shareButton);
        backgroundImage = findViewById(R.id.backgroundImage);
    }
    
    private void setupListeners() {
        backButton.setOnClickListener(v -> finish());
        
        likeButton.setOnClickListener(v -> {
            toggleLike();
        });
        
        shareButton.setOnClickListener(v -> {
            shareQuote();
        });
    }
    
    private void toggleLike() {
        isLiked = !isLiked;
        
        ImageView likeIcon = (ImageView) likeButton.getChildAt(0);
        if (likeIcon != null) {
            if (isLiked) {
                likeIcon.setImageResource(R.drawable.baseline_favorite_24);
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
            } else {
                likeIcon.setImageResource(R.drawable.baseline_favorite_border_24);
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
            }
        }
    }
    
    private void shareQuote() {
        String shareText = quoteText.getText() + "\n\n- " + authorText.getText().toString().replace("~", "").trim();
        
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        
        startActivity(Intent.createChooser(shareIntent, "Share Quote"));
    }
} 