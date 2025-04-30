package com.example.mentalhealthapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class FavoritesActivity extends AppCompatActivity {

    private ImageButton backButton;
    private ImageButton searchButton;
    private TextView allCategoryBtn;
    private TextView meditationsCategoryBtn;
    private TextView breathingCategoryBtn;
    private TextView articlesCategoryBtn;
    private TextView viewAllMeditationsButton;
    private TextView viewAllBreathingButton;
    private TextView viewAllArticlesButton;
    
    // Meditation cards
    private CardView introMeditationCard;
    private CardView abundanceMeditationCard;
    private CardView sleepSanctuaryCard;
    
    // Breathing cards
    private CardView anxietyEaseBreathCard;
    private CardView focusBoosterBreathCard;
    private CardView peacefulSleepCard;
    
    // Article cards
    private CardView mindfulnessArticleCard;
    private CardView stressReliefArticleCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        
        initViews();
        setupClickListeners();
    }
    
    private void initViews() {
        // Header buttons
        backButton = findViewById(R.id.backButton);
        searchButton = findViewById(R.id.searchButton);
        
        // Category buttons
        allCategoryBtn = findViewById(R.id.allCategoryBtn);
        meditationsCategoryBtn = findViewById(R.id.meditationsCategoryBtn);
        breathingCategoryBtn = findViewById(R.id.breathingCategoryBtn);
        articlesCategoryBtn = findViewById(R.id.articlesCategoryBtn);
        
        // View all buttons
        viewAllMeditationsButton = findViewById(R.id.viewAllMeditationsButton);
        viewAllBreathingButton = findViewById(R.id.viewAllBreathingButton);
        viewAllArticlesButton = findViewById(R.id.viewAllArticlesButton);
        
        // Meditation cards
        introMeditationCard = findViewById(R.id.introMeditationCard);
        abundanceMeditationCard = findViewById(R.id.abundanceMeditationCard);
        sleepSanctuaryCard = findViewById(R.id.sleepSanctuaryCard);
        
        // Breathing cards
        anxietyEaseBreathCard = findViewById(R.id.anxietyEaseBreathCard);
        focusBoosterBreathCard = findViewById(R.id.focusBoosterBreathCard);
        peacefulSleepCard = findViewById(R.id.peacefulSleepCard);
        
        // Article cards
        mindfulnessArticleCard = findViewById(R.id.mindfulnessArticleCard);
        stressReliefArticleCard = findViewById(R.id.stressReliefArticleCard);
    }
    
    private void setupClickListeners() {
        // Header button listeners
        backButton.setOnClickListener(v -> finish());
        
        searchButton.setOnClickListener(v -> {
            Toast.makeText(this, "Search feature coming soon", Toast.LENGTH_SHORT).show();
        });
        
        // Category button listeners
        allCategoryBtn.setOnClickListener(v -> selectCategory("ALL"));
        meditationsCategoryBtn.setOnClickListener(v -> selectCategory("MEDITATIONS"));
        breathingCategoryBtn.setOnClickListener(v -> selectCategory("BREATHING"));
        articlesCategoryBtn.setOnClickListener(v -> selectCategory("ARTICLES"));
        
        // View all button listeners
        viewAllMeditationsButton.setOnClickListener(v -> {
            Toast.makeText(this, "View all meditations", Toast.LENGTH_SHORT).show();
        });
        
        viewAllBreathingButton.setOnClickListener(v -> {
            Toast.makeText(this, "View all breathing exercises", Toast.LENGTH_SHORT).show();
        });
        
        viewAllArticlesButton.setOnClickListener(v -> {
            Toast.makeText(this, "View all articles", Toast.LENGTH_SHORT).show();
        });
        
        // Meditation card listeners
        introMeditationCard.setOnClickListener(v -> {
            Toast.makeText(this, "Intro to Meditation", Toast.LENGTH_SHORT).show();
        });
        
        abundanceMeditationCard.setOnClickListener(v -> {
            Toast.makeText(this, "Abundance Meditation", Toast.LENGTH_SHORT).show();
        });
        
        sleepSanctuaryCard.setOnClickListener(v -> {
            Toast.makeText(this, "Sleep Sanctuary", Toast.LENGTH_SHORT).show();
        });
        
        // Breathing card listeners
        anxietyEaseBreathCard.setOnClickListener(v -> {
            Toast.makeText(this, "Anxiety Ease Breath", Toast.LENGTH_SHORT).show();
        });
        
        focusBoosterBreathCard.setOnClickListener(v -> {
            Toast.makeText(this, "Focus Booster Breath", Toast.LENGTH_SHORT).show();
        });
        
        peacefulSleepCard.setOnClickListener(v -> {
            Toast.makeText(this, "Peaceful Sleep", Toast.LENGTH_SHORT).show();
        });
        
        // Article card listeners
        mindfulnessArticleCard.setOnClickListener(v -> {
            Toast.makeText(this, "Mindfulness Article", Toast.LENGTH_SHORT).show();
        });
        
        stressReliefArticleCard.setOnClickListener(v -> {
            Toast.makeText(this, "Stress Relief Article", Toast.LENGTH_SHORT).show();
        });
    }
    
    private void selectCategory(String category) {
        // Reset all buttons to unselected state
        allCategoryBtn.setBackgroundResource(R.drawable.category_unselected);
        meditationsCategoryBtn.setBackgroundResource(R.drawable.category_unselected);
        breathingCategoryBtn.setBackgroundResource(R.drawable.category_unselected);
        articlesCategoryBtn.setBackgroundResource(R.drawable.category_unselected);
        
        allCategoryBtn.setTextColor(getResources().getColor(android.R.color.black, getTheme()));
        meditationsCategoryBtn.setTextColor(getResources().getColor(android.R.color.black, getTheme()));
        breathingCategoryBtn.setTextColor(getResources().getColor(android.R.color.black, getTheme()));
        articlesCategoryBtn.setTextColor(getResources().getColor(android.R.color.black, getTheme()));
        
        // Get section views
        View meditationsSection = findViewById(R.id.meditationsSection);
        View breathingSection = findViewById(R.id.breathingSection);
        View articlesSection = findViewById(R.id.articlesSection);
        
        // Show all sections by default
        meditationsSection.setVisibility(View.VISIBLE);
        breathingSection.setVisibility(View.VISIBLE);
        articlesSection.setVisibility(View.VISIBLE);
        
        // Set the selected button and filter content
        switch (category) {
            case "ALL":
                allCategoryBtn.setBackgroundResource(R.drawable.category_selected);
                allCategoryBtn.setTextColor(getResources().getColor(android.R.color.white, getTheme()));
                break;
                
            case "MEDITATIONS":
                meditationsCategoryBtn.setBackgroundResource(R.drawable.category_selected);
                meditationsCategoryBtn.setTextColor(getResources().getColor(android.R.color.white, getTheme()));
                breathingSection.setVisibility(View.GONE);
                articlesSection.setVisibility(View.GONE);
                break;
                
            case "BREATHING":
                breathingCategoryBtn.setBackgroundResource(R.drawable.category_selected);
                breathingCategoryBtn.setTextColor(getResources().getColor(android.R.color.white, getTheme()));
                meditationsSection.setVisibility(View.GONE);
                articlesSection.setVisibility(View.GONE);
                break;
                
            case "ARTICLES":
                articlesCategoryBtn.setBackgroundResource(R.drawable.category_selected);
                articlesCategoryBtn.setTextColor(getResources().getColor(android.R.color.white, getTheme()));
                meditationsSection.setVisibility(View.GONE);
                breathingSection.setVisibility(View.GONE);
                break;
        }
    }
} 