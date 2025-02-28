package com.app.tourguide.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.tourguide.R;
import com.app.tourguide.adapter.ItemAdapter;
import com.app.tourguide.adapter.TourItem;
import com.app.tourguide.model.Tour;
import com.app.tourguide.model.TourGuideResponse;
import com.app.tourguide.utils.JsonParser;
import com.app.tourguide.utils.TourSearch;

import java.util.ArrayList;
import java.util.List;

public class TourGuideActivity extends AppCompatActivity {

    ListView listView;
    private TourSearch tourSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tour_guide);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);

        loadData();
    }

    private void loadData() {

        String category1type = getIntent().getStringExtra("category1");
        String category2theme = getIntent().getStringExtra("category2");
        String category3region = getIntent().getStringExtra("category3");
        String category4season = getIntent().getStringExtra("category4");
        String category5duration = getIntent().getStringExtra("category5");

        tourSearch = new TourSearch(this);
        List<Tour> filteredTours = tourSearch.searchTours(category1type, category2theme, category3region, category4season, category5duration);

        loadUI(filteredTours);
    }

    private void loadUI(List<Tour> filteredTours) {
        List<TourItem> items = new ArrayList<>();

        if (filteredTours.isEmpty()) {
            // Add an empty state message when no results are found
            items.add(new TourItem(
                    -1,  // Default ID for empty state
                    "No Tours Available",
                    "N/A",  // No specific region
                    "N/A",  // No specific season
                    "N/A",  // No duration
                    "Try adjusting your search filters.",
                    "N/A",  // No specific country
                    "",  // No flag URL
                    new ArrayList<>() // Empty schedule list
            ));
        } else {
            for (Tour tour : filteredTours) {
                items.add(new TourItem(
                        tour.getId(),  // Ensure Tour class has getId()
                        tour.getTitle(),
                        tour.getRegion(),
                        tour.getSeason(),
                        tour.getDuration(),
                        tour.getDescription(),
                        tour.getCountry(),  // Ensure Tour class has getCountry()
                        tour.getFlag(),  // Ensure Tour class has getFlag()
                        tour.getSchedule() // Ensure Tour class has getSchedule() returning List<String>
                ));
            }
        }


        ItemAdapter adapter = new ItemAdapter(this, items);
        listView.setAdapter(adapter);
    }


}