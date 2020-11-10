package com.ngolamquangtin.appdatvexemphim.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ngolamquangtin.appdatvexemphim.DTO.MovieDetail;
import com.ngolamquangtin.appdatvexemphim.R;
import com.ngolamquangtin.appdatvexemphim.Util.Util;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetalsMovieActivity extends AppCompatActivity {

    ImageView imghinhphim;
    TextView txttenphim, txtmota;
    Button btnhuy, btndatve;
    SharedPreferences sharedPreferences;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detals_movie);
        addControls();
        getLifecycle().addObserver(youTubePlayerView);
        addEvents();
        loadDetailMovie();

    }

    private void loadDetailMovie() {
        RequestQueue requestQueue = Volley.newRequestQueue(DetalsMovieActivity.this);
        Intent i = getIntent();
        if (i.hasExtra("ID_MOVIE")) {
            String url = String.format(Util.LINK_MOVIEDETAIL, i.getIntExtra("ID_MOVIE", -1));
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            final MovieDetail movieDetail = new MovieDetail();
                            movieDetail.setId(jsonArray.getJSONObject(0).getInt("ID"));
                            movieDetail.setTenphim(jsonArray.getJSONObject(0).getString("TenPhim"));
                            movieDetail.setHinh(jsonArray.getJSONObject(0).getString("Hinh"));
                            movieDetail.setTenloai(jsonArray.getJSONObject(0).getString("TenLoai"));
                            movieDetail.setMota(jsonArray.getJSONObject(0).getString("MoTa"));
                            movieDetail.setThoigian(jsonArray.getJSONObject(0).getInt("ThoiGian"));
                            movieDetail.setNgaykhoichieu(jsonArray.getJSONObject(0).getString("NgayKhoiChieu"));
                            movieDetail.setTrailer(jsonArray.getJSONObject(0).getString("Trailer"));
                            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                                @Override
                                public void onReady(YouTubePlayer youTubePlayer) {
                                    String idmovie = movieDetail.getTrailer();
                                    youTubePlayer.loadVideo(idmovie, 0);

                                }
                            });
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
            txtmota.setText(movieDetail.getMota() + "\n\n" + "Thể loại: " + movieDetail.getTenloai() + "\n" + "Ngày khởi chiếu: " + simpleDateFormat.format(date) + "\n" + "Thoi gian: " + movieDetail.getThoigian() + " phút");
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
                if (ktdangnhap() == 1) {
                    Intent i1 = getIntent();
                    Intent i = new Intent(DetalsMovieActivity.this, ChooseSessionActivity.class);
                    i.putExtra("ID_MOVIE", i1.getIntExtra("ID_MOVIE", 0));
                    i.putExtra("TEN_PHIM", txttenphim.getText());
                    startActivity(i);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetalsMovieActivity.this).setMessage("Bạn chưa đăng nhập!");
                    builder.show();
                    Intent intentlogin = new Intent(DetalsMovieActivity.this, LoginActivity.class);
                    startActivity(intentlogin);
                }

            }
        });

    }

    private void addControls() {
        imghinhphim = findViewById(R.id.imghinhphim);
        txttenphim = findViewById(R.id.txttenphim);
        txtmota = findViewById(R.id.txtmota);
        btnhuy = findViewById(R.id.btnhuy);
        btndatve = findViewById(R.id.btndatve);
        sharedPreferences = getSharedPreferences("datalogin", Context.MODE_PRIVATE);
        youTubePlayerView = findViewById(R.id.youtube_player_view);

    }

    public int ktdangnhap() {
        String trangthai = sharedPreferences.getString("trangthai", "");
        if (trangthai.equals("") == false) {
            if (trangthai.equals("1")) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }
}

