package com.example.appdatvexemphim.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdatvexemphim.DTO.Movie;
import com.example.appdatvexemphim.DTO.MovieDetail;
import com.example.appdatvexemphim.R;
import com.example.appdatvexemphim.Util.Util;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DetalsMovieActivity extends AppCompatActivity {

    ImageView imghinhphim;
    TextView txttenphim, txtmota;
    Button btnhuy, btndatve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detals_movie);
        addControls();
        addEvents();
        loadDetailMovie();

    }

    private void loadDetailMovie() {
        RequestQueue requestQueue = Volley.newRequestQueue(DetalsMovieActivity.this);
        Intent i = getIntent();
        if(i.hasExtra("ID_MOVIE")) {
            String url = String.format(Util.LINK_MOVIEDETAIL, i.getIntExtra("ID_MOVIE", -1));
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            MovieDetail movieDetail = new MovieDetail();
                            movieDetail.setId(jsonArray.getJSONObject(0).getInt("ID"));
                            movieDetail.setTenphim(jsonArray.getJSONObject(0).getString("TenPhim"));
                            movieDetail.setHinh(jsonArray.getJSONObject(0).getString("Hinh"));
                            movieDetail.setTenloai(jsonArray.getJSONObject(0).getString("TenLoai"));
                            movieDetail.setMota(jsonArray.getJSONObject(0).getString("MoTa"));
                            movieDetail.setThoigian(jsonArray.getJSONObject(0).getInt("ThoiGian"));
                            movieDetail.setNgaykhoichieu(jsonArray.getJSONObject(0).getString("NgayKhoiChieu"));
                            updateUI(movieDetail);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("/////", error.toString());
                }
            });
            requestQueue.add(stringRequest);
        }
    }

    private void updateUI(final MovieDetail movieDetail) {
        txttenphim.setText(movieDetail.getTenphim());
        Log.d("//////", movieDetail.getHinh());
        Picasso.with(DetalsMovieActivity.this).load(movieDetail.getHinh()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imghinhphim.setBackground(new BitmapDrawable(DetalsMovieActivity.this.getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
        DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
        try {
            Date date = inputFormat.parse(movieDetail.getNgaykhoichieu());
            Log.d("AAAAAA",simpleDateFormat.format(date));
            txtmota.setText(movieDetail.getMota() + "\n\n"+ "Thể loại: " + movieDetail.getTenloai() + "\n" + "Ngày khởi chiếu: " + simpleDateFormat.format(date) + "\n" +"Thoi gian: " + movieDetail.getThoigian() + " phút");
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    private void addEvents() {
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btndatve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = getIntent();
                Intent i = new Intent(DetalsMovieActivity.this, ChooseSessionActivity.class);
                i.putExtra("ID_MOVIE", i1.getIntExtra("ID_MOVIE", 0));
                i.putExtra("TEN_PHIM", txttenphim.getText());
                startActivity(i);
            }
        });

    }

    private void addControls() {
        imghinhphim = findViewById(R.id.imghinhphim);
        txttenphim = findViewById(R.id.txttenphim);
        txtmota = findViewById(R.id.txtmota);
        btnhuy = findViewById(R.id.btnhuy);
        btndatve = findViewById(R.id.btndatve);
    }
}

