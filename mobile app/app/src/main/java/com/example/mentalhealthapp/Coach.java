package com.example.mentalhealthapp;

public class Coach {
    private final String name;
    private final String specialty;
    private final int imageResourceId;

    public Coach(String name, String specialty, int imageResourceId) {
        this.name = name;
        this.specialty = specialty;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
} 