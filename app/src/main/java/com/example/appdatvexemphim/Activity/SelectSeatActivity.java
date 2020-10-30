package com.example.appdatvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdatvexemphim.DTO.Room;
import com.example.appdatvexemphim.DTO.Seat;
import com.example.appdatvexemphim.DTO.Ticker;
import com.example.appdatvexemphim.DTO.TickerBook;
import com.example.appdatvexemphim.R;
import com.example.appdatvexemphim.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SelectSeatActivity extends AppCompatActivity {
    ArrayList<LinearLayout> arrlinear = new ArrayList<>();

    ArrayList<Seat> seats = new ArrayList<>();
    TextView txttime, txtsophong, txttenphim;
    ImageView imgback;
    TickerBook tickerBook;
    Button btnthanhtoan;
    Room room;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);

        addControls();
        addEvents();
        loadData();
        loadDataPhong();


    }

    private void loadDataPhong() {
        Intent i = getIntent();
        if (i.hasExtra("ID_MOVIE") && i.hasExtra("NGAYDATHIENTAI") && i.hasExtra("SUATCHIEU") && i.hasExtra("ID_CINEMA")) {
            tickerBook = (TickerBook) i.getSerializableExtra("TICKERBOOK");
            String url = String.format(Util.LINK_LOADPHONG, i.getStringExtra("SUATCHIEU"), i.getIntExtra("ID_MOVIE", 0), i.getIntExtra("ID_CINEMA", 0), i.getStringExtra("NGAYDATHIENTAI"));
            RequestQueue requestQueue = Volley.newRequestQueue(SelectSeatActivity.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        room = new Room();
                        room.setId(jsonObject.getInt("ID"));
                        room.setTenphong(jsonObject.getString("TenPhong"));
                        room.setThoigian(jsonObject.getString("Gio"));

                        updateRoom(room);
                    } catch (JSONException e) {
                        e.printStackTrace();
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

    private void updateRoom(Room room) {
        tickerBook.setIdphong(room.getId());
        txtsophong.setText(room.getTenphong());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("HH:mm");
        try {
            Date date = simpleDateFormat.parse(room.getThoigian());
            txttime.setText(output.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void addEvents() {
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectSeatActivity.this, PaymentActivity.class);
                i.putExtra("TICKERBOOK", tickerBook);
                i.putExtra("ID_PHONG", room.getId());
                startActivity(i);
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
                        if(tempimage.getTag() != null){
                            if(tempseat.getId() == Integer.parseInt(String.valueOf(tempimage.getTag()))){
                                tempimage.setImageResource(R.drawable.seatdadat);
                            }
                        }
                    }
                }
            }
        }

    }

    private void loadData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = Calendar.getInstance().getTime();
        Intent intent = getIntent();
        String url = "";
        if (intent.hasExtra("NGAYDATHIENTAI") && intent.hasExtra("ID_MOVIE") && intent.hasExtra("ID_CINEMA") && intent.hasExtra("SUATCHIEU")) {
            url = String.format(Util.LINK_LOADGHE, intent.getIntExtra("ID_CINEMA", 0), intent.getIntExtra("ID_MOVIE", 0), intent.getStringExtra("SUATCHIEU"), intent.getStringExtra("NGAYDATHIENTAI"));

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
                            seat.setTenghe(jsonArray.getJSONObject(i).getString("TenGhe"));
                            seat.setTrangthai(jsonArray.getJSONObject(i).getString("TrangThai"));
                            seats.add(seat);
                        }
                        updateUI();

                    } catch (JSONException e) {
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
        btnthanhtoan = findViewById(R.id.btnthanhtoan);

        Intent intent = getIntent();
        if (intent.hasExtra("TICKERBOOK")) {
            tickerBook = (TickerBook) intent.getSerializableExtra("TICKERBOOK");

        }

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
                                tickerBook.setIdghe(Integer.parseInt((String) imageView1.getTag()));
                            } else {
                                imageView1.setImageResource(R.drawable.seattrong);
                                tickerBook.setIdghe(0);
                            }
                        }
                    });


                }

            }
        }


    }
}
