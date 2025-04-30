package com.example.mentalhealthapp;

public class MeditationPlan {
    private String category;
    private String title;
    private String duration;
    private int imageResource;

    public MeditationPlan(String category, String title, String duration, int imageResource) {
        this.category = category;
        this.title = title;
        this.duration = duration;
        this.imageResource = imageResource;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public int getImageResource() {
        return imageResource;
    }
} 