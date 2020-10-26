package com.example.appdatvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdatvexemphim.DTO.Seat;
import com.example.appdatvexemphim.R;
import com.example.appdatvexemphim.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;


public class SelectSeatActivity extends AppCompatActivity {

    ArrayList<LinearLayout> arrlinear = new ArrayList<>();

    ArrayList<Seat> seats = new ArrayList<>();
    TextView txttime, txtsophong, txttenphim;
    ImageView imgback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);

        addControls();
        addEvents();
        loadData();


    }

    private void addEvents() {
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateUI() {
        for (int l = 0; l <= seats.size() - 1; l++) {
            for (int i = 0; i < arrlinear.size(); i++) {
                for (int j = 0; j < arrlinear.get(i).getChildCount(); j++) {
                    LinearLayout linearLayout = (LinearLayout) arrlinear.get(i).getChildAt(j);
                    for (int k = 0; k < linearLayout.getChildCount(); k++) {
                        Seat tempseat = seats.get(l);
                        ImageView tempimage = (ImageView) linearLayout.getChildAt(k);
                        if (tempseat.getTenghe().equals(tempimage.getTag() + "")) {
                            if (tempseat.getTrangthai().equalsIgnoreCase("Đã đặt")) {
                                tempimage.setImageResource(R.drawable.seatdadat);
                            }
                        }
                    }
                }
            }
        }
        Intent i = getIntent();
        txtsophong.setText(seats.get(0).getTenphong());
        if(i.hasExtra("TEN_PHIM"))
            txttenphim.setText(i.getStringExtra("TEN_PHIM"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("HH:mm");
        try {
            Date date = simpleDateFormat.parse(seats.get(0).getGio());
            txttime.setText(output.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void loadData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = Calendar.getInstance().getTime();
        Intent intent = getIntent();
        String url = "";
        if (intent.hasExtra("NGAYDATHIENTAI") && intent.hasExtra("ID_MOVIE") && intent.hasExtra("ID_CINEMA") && intent.hasExtra("SUATCHIEU")) {
            url = String.format(Util.LINK_LOADGHE, intent.getIntExtra("ID_CINEMA", 0), intent.getIntExtra("ID_MOVIE", 0), intent.getStringExtra("SUATCHIEU"), intent.getStringExtra("NGAYDATHIENTAI"));
            Log.d("dsadasdsadsa", url);
        }

        RequestQueue requestQueue = Volley.newRequestQueue(SelectSeatActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Seat seat = new Seat();
                            seat.setId(jsonArray.getJSONObject(i).getInt("ID"));
                            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
                            SimpleDateFormat output = new SimpleDateFormat("hh:mm");
                            Date date1 = format.parse(jsonArray.getJSONObject(i).getString("Gio"));
                            seat.setGio(output.format(date1));
                            seat.setTenphong(jsonArray.getJSONObject(i).getString("TenPhong"));
                            seat.setTenghe(jsonArray.getJSONObject(i).getString("TenGhe"));
                            seat.setTrangthai(jsonArray.getJSONObject(i).getString("TrangThai"));
                            seats.add(seat);
                        }
                        Log.d("dhjskahkdsa", seats.size() + "");
                        updateUI();

                    } catch (JSONException | ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);

    }

    private void addControls() {

        txttime = findViewById(R.id.txttime);
        txtsophong = findViewById(R.id.txtsophong);
        txttenphim = findViewById(R.id.txttenphim);
        imgback = findViewById(R.id.imgback);

        arrlinear.add((LinearLayout) findViewById(R.id.nlinearnhomghe1));
        arrlinear.add((LinearLayout) findViewById(R.id.nlinearnhomghe2));
        arrlinear.add((LinearLayout) findViewById(R.id.nlinearnhomghe3));
        arrlinear.add((LinearLayout) findViewById(R.id.nlinearnhomghe4));
        arrlinear.add((LinearLayout) findViewById(R.id.nlinearnhomghe5));
        arrlinear.add((LinearLayout) findViewById(R.id.nlinearnhomghe6));

        for (int i = 0; i < arrlinear.size(); i++) {
            for (int j = 0; j < arrlinear.get(i).getChildCount(); j++) {
                LinearLayout linearLayout = (LinearLayout) arrlinear.get(i).getChildAt(j);
                for (int k = 0; k < linearLayout.getChildCount(); k++) {
                    final ImageView imageView = (ImageView) linearLayout.getChildAt(k);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImageView imageView1 = (ImageView) v;
                            if (imageView1.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.seattrong).getConstantState()) {
                                imageView1.setImageResource(R.drawable.seatchon);
                            } else {
                                imageView1.setImageResource(R.drawable.seattrong);
                            }
                        }
                    });


                }

            }
        }


    }


}


