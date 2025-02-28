package com.app.tourguide.model;

import java.util.List;

import java.util.List;

public class Tour {
    private int id;
    private String title;
    private String category;
    private String region;
    private String theme;
    private String season;
    private String duration;
    private String description;
    private String country;
    private String flag;
    private List<String> schedule; // List instead of a comma-separated string

    public Tour(int id, String title, String category, String region, String theme, String season,
                String duration, String description, String country, String flag, List<String> schedule) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.region = region;
        this.theme = theme;
        this.season = season;
        this.duration = duration;
        this.description = description;
        this.country = country;
        this.flag = flag;
        this.schedule = schedule;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getRegion() { return region; }
    public String getTheme() { return theme; }
    public String getSeason() { return season; }
    public String getDuration() { return duration; }
    public String getDescription() { return description; }
    public String getCountry() { return country; }
    public String getFlag() { return flag; }
    public List<String> getSchedule() { return schedule; }

    // Setters (optional, depending on your use case)
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCategory(String category) { this.category = category; }
    public void setRegion(String region) { this.region = region; }
    public void setTheme(String theme) { this.theme = theme; }
    public void setSeason(String season) { this.season = season; }
    public void setDuration(String duration) { this.duration = duration; }
    public void setDescription(String description) { this.description = description; }
    public void setCountry(String country) { this.country = country; }
    public void setFlag(String flag) { this.flag = flag; }
    public void setSchedule(List<String> schedule) { this.schedule = schedule; }
}


