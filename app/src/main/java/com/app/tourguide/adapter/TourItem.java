package com.app.tourguide.adapter;

import java.util.List;

import java.util.List;

public class TourItem {
    private int id;
    private String title;
    private String region;
    private String season;
    private String duration;
    private String description;
    private String country;
    private String flag;
    private List<String> schedule; // List of schedule items

    public TourItem(int id, String title, String region, String season, String duration,
                    String description, String country, String flag, List<String> schedule) {
        this.id = id;
        this.title = title;
        this.region = region;
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
    public String getRegion() { return region; }
    public String getSeason() { return season; }
    public String getDuration() { return duration; }
    public String getDescription() { return description; }
    public String getCountry() { return country; }
    public String getFlag() { return flag; }
    public List<String> getSchedule() { return schedule; }

    // Setters (if needed)
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setRegion(String region) { this.region = region; }
    public void setSeason(String season) { this.season = season; }
    public void setDuration(String duration) { this.duration = duration; }
    public void setDescription(String description) { this.description = description; }
    public void setCountry(String country) { this.country = country; }
    public void setFlag(String flag) { this.flag = flag; }
    public void setSchedule(List<String> schedule) { this.schedule = schedule; }

    @Override
    public String toString() {
        return "TourItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", region='" + region + '\'' +
                ", season='" + season + '\'' +
                ", duration='" + duration + '\'' +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                ", flag='" + flag + '\'' +
                ", schedule=" + schedule +
                '}';
    }
}


