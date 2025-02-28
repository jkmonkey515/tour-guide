package com.app.tourguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.tourguide.R;

public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_started);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button getStartedButton = findViewById(R.id.getStartedButton);

        String category1type = getIntent().getStringExtra("category1");
        String category2theme = getIntent().getStringExtra("category2");
        String category3region = getIntent().getStringExtra("category3");
        String category4season = getIntent().getStringExtra("category4");
        String category5duration = getIntent().getStringExtra("category5");

        getStartedButton.setOnClickListener(v -> {
            Intent intent = new Intent(GetStartedActivity.this, TourGuideActivity.class);
            intent.putExtra("category1", category1type);
            intent.putExtra("category2", category2theme);
            intent.putExtra("category3", category3region);
            intent.putExtra("category4", category4season);
            intent.putExtra("category5", category5duration);
            startActivity(intent);
        });
    }
}
