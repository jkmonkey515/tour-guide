package com.app.tourguide.model;

import java.util.List;

public class Tour {
    private String title;
    private List<String> category;
    private String region;
    private List<String> theme;
    private String season;
    private String duration;
    private String description;

    public String getTitle() {
        return title;
    }

    public List<String> getCategory() {
        return category;
    }

    public String getRegion() {
        return region;
    }

    public List<String> getTheme() {
        return theme;
    }

    public String getSeason() {
        return season;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }
}
