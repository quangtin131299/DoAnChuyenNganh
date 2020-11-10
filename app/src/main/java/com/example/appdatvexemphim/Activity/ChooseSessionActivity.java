package com.example.appdatvexemphim.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdatvexemphim.Adapter.RapAdapter;
import com.example.appdatvexemphim.Adapter.TimeAdapter;
import com.example.appdatvexemphim.DTO.Cinema;
import com.example.appdatvexemphim.DTO.TickerBook;
import com.example.appdatvexemphim.DTO.XuatChieu;
import com.example.appdatvexemphim.R;
import com.example.appdatvexemphim.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChooseSessionActivity extends AppCompatActivity implements TimeAdapter.onClickListenerRecyclerView {


    ArrayList<Cinema> cinemas = new ArrayList<>();
    ArrayList<XuatChieu> xuatChieus = new ArrayList<>();
    RapAdapter rapAdapter;
    Spinner spinner;
    TextView txtChooseDate;
    Button btnChonGhe;
    TimeAdapter timeAdapter;
    RecyclerView rvTime;
    ImageView imgback;
    Button btnchonngay;

    TickerBook tickerBook = new TickerBook();

    SharedPreferences sharedPreferences;


    XuatChieu xctemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_session);


        addControls();
        loadCinema();

        addEvents();

    }

    public void loadCinema() {
        RequestQueue requestQueue = Volley.newRequestQueue(ChooseSessionActivity.this);
        String url = Util.LINK_LOADRAPPHIM;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Cinema cinema = new Cinema();
                            cinema.setId(jsonArray.getJSONObject(i).getInt("ID"));
                            cinema.setTenrap(jsonArray.getJSONObject(i).getString("TenRap"));
                            cinema.setDiachi(jsonArray.getJSONObject(i).getString("DiaChi"));
                            cinema.setHinh(jsonArray.getJSONObject(i).getString("Hinh"));
                            cinemas.add(cinema);
                            rapAdapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("AAAAAAAAA", error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    private void addEvents() {
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnChonGhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = getIntent();
                if (i1.hasExtra("ID_MOVIE") && i1.hasExtra("TEN_PHIM")) {
                    Intent intent = new Intent(ChooseSessionActivity.this, SelectSeatActivity.class);
                    Cinema cinema = (Cinema) spinner.getSelectedItem();
                    if (cinema != null) {
                        intent.putExtra("ID_CINEMA", cinema.getId());

                    }
                    if (xctemp != null) {
                        intent.putExtra("SUATCHIEU", xctemp.getThoigian());
                    }
                    intent.putExtra("ID_MOVIE", i1.getIntExtra("ID_MOVIE", 0));
                    tickerBook.setIdphim(i1.getIntExtra("ID_MOVIE", 0));
                    SimpleDateFormat formatngaydat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat outputformatngaydat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date ngaydat = formatngaydat.parse(txtChooseDate.getText().toString().trim());
                        tickerBook.setNgaydat(outputformatngaydat.format(ngaydat));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String idkhachhang = sharedPreferences.getString("id", "");
                    if (idkhachhang.equals("") == false) {
                        tickerBook.setIdkhachhang(Integer.parseInt(idkhachhang));
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    intent.putExtra("NGAYDATHIENTAI", simpleDateFormat.format(Calendar.getInstance().getTime()));
                    intent.putExtra("TEN_PHIM", i1.getStringExtra("TEN_PHIM"));
                    intent.putExtra("TICKERBOOK", tickerBook);
                    intent.putExtra("TEN_RAP", cinema.getTenrap());
//                    if (tickerBook.getIdsuat() != 0 && tickerBook.getNgaydat().equals("") == false) {
                        startActivity(intent);
//                    }else{
//                        Toast.makeText(ChooseSessionActivity.this, "Bạn phải chọn đẩy ba phần", Toast.LENGTH_SHORT).show();
//                    }


                }

            }
        });

        btnchonngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseDate();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cinema cinema = cinemas.get(position);
                Intent intent = getIntent();
                if (intent.hasExtra("ID_MOVIE")) {
                    xuatChieus.clear();
                    timeAdapter.notifyDataSetChanged();
                    loadXuatChieu(cinema.getId(), intent.getIntExtra("ID_MOVIE", 0));
                }
                tickerBook.setIdrap(cinema.getId());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addControls() {
        txtChooseDate = findViewById(R.id.txtChooseDate);
        btnChonGhe = findViewById(R.id.btnChonGhe);
        spinner = findViewById(R.id.spinner);
        rvTime = findViewById(R.id.lvTime);
        imgback = findViewById(R.id.imgback);
        rapAdapter = new RapAdapter(ChooseSessionActivity.this, cinemas);
        spinner.setAdapter(rapAdapter);
        timeAdapter = new TimeAdapter(getApplicationContext(), xuatChieus, ChooseSessionActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvTime.setLayoutManager(linearLayoutManager);
        rvTime.setAdapter(timeAdapter);
        btnchonngay = findViewById(R.id.btnchonngay);

        sharedPreferences = getSharedPreferences("datalogin", Context.MODE_PRIVATE);

    }


    public void ChooseDate() {
        final Calendar calendar = Calendar.getInstance();
        final int daydefault = calendar.get(Calendar.DAY_OF_MONTH);
        final int monthdefault = calendar.get(Calendar.MONTH);
        final int yeardefault = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(ChooseSessionActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (daydefault == dayOfMonth && monthdefault == month && yeardefault == year) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    txtChooseDate.setText(simpleDateFormat.format(calendar.getTime()));
                } else {
                    Toast.makeText(ChooseSessionActivity.this, "Ngày không hợp lệ", Toast.LENGTH_SHORT).show();
                }

            }
        }, yeardefault, monthdefault, daydefault);
        datePickerDialog.show();
    }


    public void loadXuatChieu(int idrap, int idphim) {
        RequestQueue requestQueue = Volley.newRequestQueue(ChooseSessionActivity.this);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        simpleDateFormat.format(Calendar.getInstance().getTime())
        String url = String.format(Util.LINK_LOADSUATCHIEUTHEORAP, idrap, idphim, simpleDateFormat.format(Calendar.getInstance().getTime()));
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            XuatChieu xuatChieu = new XuatChieu();
                            xuatChieu.setId(jsonArray.getJSONObject(i).getInt("ID"));
                            xuatChieu.setThoigian(jsonArray.getJSONObject(i).getString("Gio"));
                            xuatChieus.add(xuatChieu);
                            timeAdapter.notifyDataSetChanged();
                        }
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
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(2000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

    }

    @Override
    public void onClicked(int position, View view) {
        xctemp = xuatChieus.get(position);
        Toast.makeText(ChooseSessionActivity.this, "Bạn đã chọn suất " + xctemp.getThoigian(), Toast.LENGTH_LONG).show();
        tickerBook.setIdsuat(xctemp.getId());

    }
}