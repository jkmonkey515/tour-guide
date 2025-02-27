package com.app.tourguide.model;

import java.util.List;

public class Tour {
    private String title;
    private String category;
    private String region;
    private String theme;
    private String season;
    private String duration;
    private String description;

    public Tour(String title, String category, String region, String theme, String season, String duration, String description) {
        this.title = title;
        this.category = category;
        this.region = region;
        this.theme = theme;
        this.season = season;
        this.duration = duration;
        this.description = description;
    }

    // Getters
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getRegion() { return region; }
    public String getTheme() { return theme; }
    public String getSeason() { return season; }
    public String getDuration() { return duration; }
    public String getDescription() { return description; }
}

