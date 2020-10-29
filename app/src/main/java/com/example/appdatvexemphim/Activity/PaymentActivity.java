package com.example.appdatvexemphim.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.appdatvexemphim.DTO.TickerBook;
import com.example.appdatvexemphim.R;

public class PaymentActivity extends AppCompatActivity {

    TickerBook tickerBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        
        xulyDatVe();
    }

    private void xulyDatVe() {
        Intent intent = getIntent();
        if(intent.hasExtra("TICKERBOOK")){
            tickerBook = (TickerBook) intent.getSerializableExtra("TICKERBOOK");
        }


    }
}