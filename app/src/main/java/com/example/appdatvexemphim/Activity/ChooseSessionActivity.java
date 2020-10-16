package com.example.appdatvexemphim.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appdatvexemphim.Adapter.RapAdapter;
import com.example.appdatvexemphim.Adapter.TimeAdapter;
import com.example.appdatvexemphim.DTO.Cinema;
import com.example.appdatvexemphim.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ChooseSessionActivity extends AppCompatActivity {


    ArrayList<Cinema> cinemas = new ArrayList<>();
    RapAdapter rapAdapter;
    Spinner spinner;
    TextView txtChooseDate;

    TimeAdapter timeAdapter;

    RecyclerView rvTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_session);


        addControls();
        addEvents();

    }

    private void addEvents() {
        txtChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseDate();
            }
        });
    }

    private void addControls() {
        txtChooseDate = findViewById(R.id.txtChooseDate);
        rvTime = findViewById(R.id.lvTime);
        timeAdapter = new TimeAdapter(getApplicationContext());
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvTime.setLayoutManager(linearLayoutManager);
        rvTime.setAdapter(timeAdapter);

    }


    public void ChooseDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(ChooseSessionActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            }
        },Calendar.getInstance().getTime().getYear(), Calendar.getInstance().getTime().getMonth(),Calendar.getInstance().getTime().getDay());
        datePickerDialog.show();
    }
}