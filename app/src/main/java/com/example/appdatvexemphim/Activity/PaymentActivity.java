package com.example.appdatvexemphim.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdatvexemphim.DTO.TickerBook;
import com.example.appdatvexemphim.R;
import com.example.appdatvexemphim.Util.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        if (intent.hasExtra("TICKERBOOK") && intent.hasExtra("ID_PHONG")) {
            tickerBook = (TickerBook) intent.getSerializableExtra("TICKERBOOK");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            String url = String.format(Util.LINK_DATVE, simpleDateFormat.format(calendar.getTime()), tickerBook.getIdsuat(), tickerBook.getIdghe(), tickerBook.getIdphim(), tickerBook.getIdkhachhang(), tickerBook.getIdrap(),intent.getIntExtra("ID_PHONG", 0));
            Log.d("//////", url);
            RequestQueue requestQueue = Volley.newRequestQueue(PaymentActivity.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response != null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this).setMessage(response);
                        builder.show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(stringRequest);
        }


    }
}