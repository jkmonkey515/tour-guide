package com.app.tourguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.tourguide.R;
import com.app.tourguide.db.DataImporter;
import com.app.tourguide.enums.Category1Type;
import com.app.tourguide.enums.Category2Theme;
import com.app.tourguide.enums.Category3Region;
import com.app.tourguide.enums.Category4Season;
import com.app.tourguide.enums.Category5Duration;
import com.app.tourguide.fragment.Onboarding1Fragment;
import com.app.tourguide.fragment.Onboarding2Fragment;
import com.app.tourguide.fragment.Onboarding3Fragment;
import com.app.tourguide.fragment.Onboarding4Fragment;
import com.app.tourguide.fragment.Onboarding5Fragment;

public class MainActivity extends AppCompatActivity implements
        Onboarding1Fragment.OnCategory1SelectedListener,
        Onboarding2Fragment.OnCategory2SelectedListener,
        Onboarding3Fragment.OnCategory3SelectedListener,
        Onboarding4Fragment.OnCategory4SelectedListener,
        Onboarding5Fragment.OnCategory5SelectedListener {

    int currentPageIndex = 0;
    TextView txvTitle;
    private ProgressBar progressBar;

    private Category1Type selectedCategory1;
    private Category2Theme selectedCategory2;
    private Category3Region selectedCategory3;
    private Category4Season selectedCategory4;
    private Category5Duration selectedCategory5;

    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txvTitle = findViewById(R.id.titleTextView);
        progressBar = findViewById(R.id.progressBar);
        updateUI();

        nextButton = findViewById(R.id.btnNext);
        nextButton.setEnabled(false);

        nextButton.setOnClickListener(this::moveToNextFragment);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState == null) {
            txvTitle.setText(getResources().getStringArray(R.array.trip_questions)[0]);
            loadFragment(new Onboarding1Fragment());
        }

        new DataImporter(this).importDataFromJson();

    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    public void moveToNextFragment(View view) {
        currentPageIndex++;
        updateUI();

        Fragment nextFragment = null;
        switch (currentPageIndex) {
            case 1:
                nextFragment = new Onboarding2Fragment();
                break;
            case 2:
                nextFragment = new Onboarding3Fragment();
                break;
            case 3:
                nextFragment = new Onboarding4Fragment();
                break;
            case 4:
                nextFragment = new Onboarding5Fragment();
                break;
            case 5:
                next();
                return;
        }

        if (nextFragment != null) {
            loadFragment(nextFragment);
            nextButton.setEnabled(false); // Disable the button until new selection is made
        }
    }

    private void updateUI() {
        String[] tripQuestions = getResources().getStringArray(R.array.trip_questions);

        if (currentPageIndex < tripQuestions.length) {
            txvTitle.setText(tripQuestions[currentPageIndex]);
            progressBar.setMax(5);
            progressBar.setProgress(currentPageIndex + 1);
        }
    }

    private void next() {
        Intent intent = new Intent(MainActivity.this, TourGuideActivity.class);
        intent.putExtra("category1", selectedCategory1.toString());
        intent.putExtra("category2", selectedCategory2.toString());
        intent.putExtra("category3", selectedCategory3.toString());
        intent.putExtra("category4", selectedCategory4.toString());
        intent.putExtra("category5", selectedCategory5.toString());
        startActivity(intent);
    }


    @Override
    public void onCategory1Selected(Category1Type category) {
        selectedCategory1 = category;
        nextButton.setEnabled(true);
        Toast.makeText(this, "Selected Category1: " + category.name(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategory2Selected(Category2Theme category) {
        selectedCategory2 = category;
        nextButton.setEnabled(true);
        Toast.makeText(this, "Selected Category2: " + category.name(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategory3Selected(Category3Region category) {
        selectedCategory3 = category;
        nextButton.setEnabled(true);
        Toast.makeText(this, "Selected Category3: " + category.name(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategory4Selected(Category4Season category) {
        selectedCategory4 = category;
        nextButton.setEnabled(true);
        Toast.makeText(this, "Selected Category4: " + category.name(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategory5Selected(Category5Duration category) {
        selectedCategory5 = category;
        nextButton.setEnabled(true);
        Toast.makeText(this, "Selected Category5: " + category.name(), Toast.LENGTH_SHORT).show();
    }
}
