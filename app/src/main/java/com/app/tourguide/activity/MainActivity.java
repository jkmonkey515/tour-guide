package com.app.tourguide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.tourguide.R;
import com.app.tourguide.fragment.Onboarding1Fragment;
import com.app.tourguide.fragment.Onboarding2Fragment;
import com.app.tourguide.fragment.Onboarding3Fragment;
import com.app.tourguide.fragment.Onboarding4Fragment;
import com.app.tourguide.fragment.Onboarding5Fragment;

public class MainActivity extends AppCompatActivity {
    int currentPageIndex = 0;
    TextView txvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txvTitle=findViewById(R.id.titleTextView);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState == null) {
            txvTitle.setText(getResources().getStringArray(R.array.trip_questions)[0]);
            loadFragment(new Onboarding1Fragment());
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    public void moveToNextFragment(View view) {
        String[] tripQuestions = getResources().getStringArray(R.array.trip_questions);
        if(currentPageIndex<tripQuestions.length-1)
            txvTitle.setText(tripQuestions[currentPageIndex+1]);
        switch (currentPageIndex){
            case 0:
                loadFragment(new Onboarding2Fragment());
                currentPageIndex++;

                break;
            case 1:
                loadFragment(new Onboarding3Fragment());
                currentPageIndex++;
                break;
            case 2:
                loadFragment(new Onboarding4Fragment());
                currentPageIndex++;
                break;
            case 3:
                loadFragment(new Onboarding5Fragment());
                currentPageIndex++;
                break;
            case 4:
                //Move to next page
                break;
        }
    }
}