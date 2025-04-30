package com.example.mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class QuotesActivity extends AppCompatActivity {

    private ImageButton backButton;
    private ImageButton searchButton;
    private TextView allCategoryBtn;
    private TextView generalCategoryBtn;
    private TextView workProductivityCategoryBtn;
    private TextView personalGrowthCategoryBtn;
    private TextView viewAllGeneralButton;
    private TextView viewAllWorkButton;
    private TextView viewAllPersonalGrowthButton;
    
    // General quote cards
    private CardView wordsOfWisdomCard;
    private CardView mindBodySoulCard;
    private CardView changeYourPerspectiveCard;
    
    // Work & Productivity cards
    private CardView inspirationAtWorkCard;
    private CardView findingPurposeCard;
    private CardView gettingThingsDoneCard;
    
    // Personal Growth cards
    private CardView balanceHarmonyCard;
    private CardView selfDiscoveryCard;
    private CardView innerPeaceCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        
        initViews();
        setupClickListeners();
    }
    
    private void initViews() {
        // Header buttons
        backButton = findViewById(R.id.backButton);
        searchButton = findViewById(R.id.searchButton);
        
        // Category buttons
        allCategoryBtn = findViewById(R.id.allCategoryBtn);
        generalCategoryBtn = findViewById(R.id.generalCategoryBtn);
        workProductivityCategoryBtn = findViewById(R.id.workProductivityCategoryBtn);
        personalGrowthCategoryBtn = findViewById(R.id.personalGrowthCategoryBtn);
        
        // View all buttons
        viewAllGeneralButton = findViewById(R.id.viewAllGeneralButton);
        viewAllWorkButton = findViewById(R.id.viewAllWorkButton);
        viewAllPersonalGrowthButton = findViewById(R.id.viewAllPersonalGrowthButton);
        
        // General quote cards
        wordsOfWisdomCard = findViewById(R.id.wordsOfWisdomCard);
        mindBodySoulCard = findViewById(R.id.mindBodySoulCard);
        changeYourPerspectiveCard = findViewById(R.id.changeYourPerspectiveCard);
        
        // Work & Productivity cards
        inspirationAtWorkCard = findViewById(R.id.inspirationAtWorkCard);
        findingPurposeCard = findViewById(R.id.findingPurposeCard);
        gettingThingsDoneCard = findViewById(R.id.gettingThingsDoneCard);
        
        // Personal Growth cards
        balanceHarmonyCard = findViewById(R.id.balanceHarmonyCard);
        selfDiscoveryCard = findViewById(R.id.selfDiscoveryCard);
        innerPeaceCard = findViewById(R.id.innerPeaceCard);
    }
    
    private void setupClickListeners() {
        // Header button listeners
        backButton.setOnClickListener(v -> finish());
        
        searchButton.setOnClickListener(v -> {
            Toast.makeText(this, "Search feature coming soon", Toast.LENGTH_SHORT).show();
        });
        
        // Category button listeners
        allCategoryBtn.setOnClickListener(v -> selectCategory("ALL"));
        generalCategoryBtn.setOnClickListener(v -> selectCategory("GENERAL"));
        workProductivityCategoryBtn.setOnClickListener(v -> selectCategory("WORK"));
        personalGrowthCategoryBtn.setOnClickListener(v -> selectCategory("GROWTH"));
        
        // View all button listeners
        viewAllGeneralButton.setOnClickListener(v -> {
            Toast.makeText(this, "View all general quotes", Toast.LENGTH_SHORT).show();
        });
        
        viewAllWorkButton.setOnClickListener(v -> {
            Toast.makeText(this, "View all work & productivity quotes", Toast.LENGTH_SHORT).show();
        });
        
        viewAllPersonalGrowthButton.setOnClickListener(v -> {
            Toast.makeText(this, "View all personal growth quotes", Toast.LENGTH_SHORT).show();
        });
        
        // General quote card listeners
        wordsOfWisdomCard.setOnClickListener(v -> {
            navigateToQuoteDetail(
                "general",
                "Words of Wisdom",
                "In the end, it's not the years in your life that count. It's the life in your years.",
                "Abraham Lincoln",
                R.drawable.wordsofwisdom
            );
        });
        
        mindBodySoulCard.setOnClickListener(v -> {
            navigateToQuoteDetail(
                "general",
                "Mind, Body & Soul",
                "The greatest wealth is health.",
                "Virgil",
                R.drawable.mindbodysoul
            );
        });
        
        changeYourPerspectiveCard.setOnClickListener(v -> {
            navigateToQuoteDetail(
                "general",
                "Change Your Perspective",
                "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty.",
                "Winston Churchill",
                R.drawable.changeyour
            );
        });
        
        // Work & Productivity card listeners
        inspirationAtWorkCard.setOnClickListener(v -> {
            navigateToQuoteDetail(
                "work",
                "Inspiration at Work",
                "The only way to do great work is to love what you do.",
                "Steve Jobs",
                R.drawable.inspiration
            );
        });
        
        findingPurposeCard.setOnClickListener(v -> {
            navigateToQuoteDetail(
                "work",
                "Finding Purpose",
                "The two most important days in your life are the day you are born and the day you find out why.",
                "Mark Twain",
                R.drawable.findingpurpose
            );
        });
        
        gettingThingsDoneCard.setOnClickListener(v -> {
            navigateToQuoteDetail(
                "work",
                "Getting Things Done",
                "Productivity is never an accident. It is always the result of a commitment to excellence, intelligent planning, and focused effort.",
                "Paul J. Meyer",
                R.drawable.gettingthingsdone
            );
        });
        
        // Personal Growth card listeners
        balanceHarmonyCard.setOnClickListener(v -> {
            navigateToQuoteDetail(
                "personal",
                "Balance & Harmony",
                "Life is like riding a bicycle. To keep your balance, you must keep moving.",
                "Albert Einstein",
                R.drawable.balance
            );
        });
        
        selfDiscoveryCard.setOnClickListener(v -> {
            navigateToQuoteDetail(
                "personal",
                "Self Discovery",
                "Knowing yourself is the beginning of all wisdom.",
                "Aristotle",
                R.drawable.selfdiscovery
            );
        });
        
        innerPeaceCard.setOnClickListener(v -> {
            navigateToQuoteDetail(
                "personal",
                "Inner Peace",
                "Peace comes from within. Do not seek it without.",
                "Buddha",
                R.drawable.innerpeace
            );
        });
    }
    
    private void selectCategory(String category) {
        // Reset all buttons to unselected state
        allCategoryBtn.setBackgroundResource(R.drawable.category_unselected);
        generalCategoryBtn.setBackgroundResource(R.drawable.category_unselected);
        workProductivityCategoryBtn.setBackgroundResource(R.drawable.category_unselected);
        personalGrowthCategoryBtn.setBackgroundResource(R.drawable.category_unselected);
        
        allCategoryBtn.setTextColor(getResources().getColor(android.R.color.black, getTheme()));
        generalCategoryBtn.setTextColor(getResources().getColor(android.R.color.black, getTheme()));
        workProductivityCategoryBtn.setTextColor(getResources().getColor(android.R.color.black, getTheme()));
        personalGrowthCategoryBtn.setTextColor(getResources().getColor(android.R.color.black, getTheme()));
        
        // Get section views
        View generalSection = findViewById(R.id.generalSection);
        View workProductivitySection = findViewById(R.id.workProductivitySection);
        View personalGrowthSection = findViewById(R.id.personalGrowthSection);
        
        // Show all sections by default
        generalSection.setVisibility(View.VISIBLE);
        workProductivitySection.setVisibility(View.VISIBLE);
        personalGrowthSection.setVisibility(View.VISIBLE);
        
        // Set the selected button and filter content
        switch (category) {
            case "ALL":
                allCategoryBtn.setBackgroundResource(R.drawable.category_selected);
                allCategoryBtn.setTextColor(getResources().getColor(android.R.color.white, getTheme()));
                break;
                
            case "GENERAL":
                generalCategoryBtn.setBackgroundResource(R.drawable.category_selected);
                generalCategoryBtn.setTextColor(getResources().getColor(android.R.color.white, getTheme()));
                workProductivitySection.setVisibility(View.GONE);
                personalGrowthSection.setVisibility(View.GONE);
                break;
                
            case "WORK":
                workProductivityCategoryBtn.setBackgroundResource(R.drawable.category_selected);
                workProductivityCategoryBtn.setTextColor(getResources().getColor(android.R.color.white, getTheme()));
                generalSection.setVisibility(View.GONE);
                personalGrowthSection.setVisibility(View.GONE);
                break;
                
            case "GROWTH":
                personalGrowthCategoryBtn.setBackgroundResource(R.drawable.category_selected);
                personalGrowthCategoryBtn.setTextColor(getResources().getColor(android.R.color.white, getTheme()));
                generalSection.setVisibility(View.GONE);
                workProductivitySection.setVisibility(View.GONE);
                break;
        }
    }

    private void navigateToQuoteDetail(String category, String title, String quote, String author, int imageResourceId) {
        Intent intent = new Intent(this, QuoteDetailActivity.class);
        intent.putExtra("category", category);
        intent.putExtra("title", title);
        intent.putExtra("quote", quote);
        intent.putExtra("author", author);
        intent.putExtra("imageResourceId", imageResourceId);
        startActivity(intent);
    }
} 