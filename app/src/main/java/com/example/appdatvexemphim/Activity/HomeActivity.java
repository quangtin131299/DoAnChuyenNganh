package com.example.appdatvexemphim.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appdatvexemphim.Adapter.MainAdapter;
import com.example.appdatvexemphim.Fragment.FragmentCinema;
import com.example.appdatvexemphim.Fragment.FragmentCommingSoon;
import com.example.appdatvexemphim.Fragment.FragmentDetailsTicker;
import com.example.appdatvexemphim.Fragment.FragmentMovie;
import com.example.appdatvexemphim.Fragment.FragmentTicker;
import com.example.appdatvexemphim.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    ViewPager viewPager;
    ArrayList<String> arrayTilte = new ArrayList<>();
    BottomNavigationView bottomNavigationViewHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addControl();
        addEvent();
        prepearViewPager(viewPager, arrayTilte);
    }

    private void addEvent() {
        bottomNavigationViewHome.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.itemmovie:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.itemticker:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.itemcinema:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.itemfavourite:
                        break;
                    case R.id.itemprofile:
                        break;
                }
                return true;
            }
        });

    }


    private void addControl() {

        viewPager = findViewById(R.id.viewpagerhome);
        bottomNavigationViewHome = findViewById(R.id.bottomnavigationhome);

    }

    private void prepearViewPager(ViewPager viewPager, ArrayList<String> arrayTilte) {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        FragmentMovie fragmentMovie = new FragmentMovie();
        FragmentTicker fragmentTicker = new FragmentTicker();
        FragmentCinema fragmentCinema = new FragmentCinema();


        mainAdapter.addFragment(fragmentMovie, "Movie");
        mainAdapter.addFragment(fragmentTicker, "Ticker");
        mainAdapter.addFragment(fragmentCinema, "Cinema");
        viewPager.setAdapter(mainAdapter);

    }

}
