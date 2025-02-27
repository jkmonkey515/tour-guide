package com.app.tourguide.adapter;

public class TourItem {
    private String title;
    private String description;

    public TourItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
