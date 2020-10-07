package com.example.appdatvexemphim.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.appdatvexemphim.Adapter.MainAdapter;
import com.example.appdatvexemphim.Fragment.FragmentCommingSoon;
import com.example.appdatvexemphim.Fragment.FragmentNearYou;
import com.example.appdatvexemphim.Fragment.FragmentPremieres;
import com.example.appdatvexemphim.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<String> arrayTilte = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addControl();
        prepearViewPager(viewPager, arrayTilte);
        setTabLayoutWithViewPager();
    }

    private void setTabLayoutWithViewPager() {
        tabLayout.setupWithViewPager(viewPager);
    }


    private void addControl() {
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        arrayTilte.add("Near You");
        arrayTilte.add("Comming soon");
        arrayTilte.add("Premieres");
    }

    private void prepearViewPager(ViewPager viewPager, ArrayList<String> arrayTilte) {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());

        FragmentNearYou fragmentNearYou = new FragmentNearYou();
        FragmentCommingSoon fragmentCommingSoon = new FragmentCommingSoon();
        FragmentPremieres fragmentPremieres = new FragmentPremieres();

        mainAdapter.addFragment(fragmentNearYou, arrayTilte.get(0));
        mainAdapter.addFragment(fragmentCommingSoon, arrayTilte.get(1));
        mainAdapter.addFragment(fragmentPremieres, arrayTilte.get(2));

        viewPager.setAdapter(mainAdapter);

    }

}
