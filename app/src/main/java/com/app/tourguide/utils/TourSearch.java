package com.app.tourguide.utils;

import com.app.tourguide.enums.Category5Duration;
import com.app.tourguide.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourSearch {
    public static List<Tour> searchTours(List<Tour> tours, String category1type, String category2theme,
                                         String category3region, String category4season, String category5duration) {
        List<Tour> filteredTours = new ArrayList<>();

        for (Tour tour : tours) {
            boolean matchesCategory = (category1type == null || tour.getCategory().contains(category1type));
            boolean matchesTheme = (category2theme == null || tour.getTheme().contains(category2theme));
            boolean matchesRegion = (category3region == null || tour.getRegion().equalsIgnoreCase(category3region));
            boolean matchesSeason = (category4season == null || tour.getSeason().equalsIgnoreCase(category4season));

            // Mapping duration from string to Category5Duration enum
            String jsonDuration = tour.getDuration().toLowerCase(); // Example: "7 days"
            Category5Duration durationEnum = null;

            if (jsonDuration.contains("3 days")) {
                durationEnum = Category5Duration.THREEDAYS;
            } else if (jsonDuration.contains("5 days")) {
                durationEnum = Category5Duration.FIVEDAYS;
            } else if (jsonDuration.contains("7 days")) {
                durationEnum = Category5Duration.SEVENDAYS;
            }

            boolean matchesDuration = (category5duration == null ||
                    durationEnum != null && durationEnum.name().equalsIgnoreCase(category5duration));

            if (matchesCategory && matchesTheme && matchesRegion && matchesSeason && matchesDuration) {
                filteredTours.add(tour);
            }

            // Limit to maximum 3 results
            if (filteredTours.size() == 3) {
                break;
            }
        }

        return filteredTours;
    }

}
