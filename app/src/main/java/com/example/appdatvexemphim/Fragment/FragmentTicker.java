package com.example.appdatvexemphim.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdatvexemphim.Activity.DetailTickerActivity;
import com.example.appdatvexemphim.Adapter.TickerAdapter;
import com.example.appdatvexemphim.DTO.Ticker;
import com.example.appdatvexemphim.R;
import com.example.appdatvexemphim.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragmentTicker extends Fragment {

    ListView lvticker;
    TickerAdapter tickerAdapter;
    SharedPreferences sharedPreferences;
    ArrayList<Ticker> tickers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticker, container, false);
        addControls(view);
        addEvents();
        loadDSVe();
        return view;
    }

    private void loadDSVe() {
        sharedPreferences = getActivity().getSharedPreferences("datalogin", Context.MODE_PRIVATE);
        String iduser = sharedPreferences.getString("id", "0");
        String url = String.format(Util.LINK_LOADDSVE, Integer.parseInt(iduser));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i++){
                            Ticker ticker = new Ticker();
                            ticker.setId(jsonArray.getJSONObject(i).getInt("ID"));
                            ticker.setDiachirap(jsonArray.getJSONObject(i).getString("DiaChi"));
                            ticker.setHinhphim(jsonArray.getJSONObject(i).getString("Hinh"));
                            ticker.setThoigianphim(jsonArray.getJSONObject(i).getString("ThoiGian"));
                            ticker.setTenphong(jsonArray.getJSONObject(i).getString("TenPhong"));
                            ticker.setGio(jsonArray.getJSONObject(i).getString("Gio"));
                            ticker.setTenphim(jsonArray.getJSONObject(i).getString("TenPhim"));
                            ticker.setTenrap(jsonArray.getJSONObject(i).getString("TenRap"));
                            ticker.setNgaydat(jsonArray.getJSONObject(i).getString("NgayDat"));
                            ticker.setTenghe(jsonArray.getJSONObject(i).getString("TenGhe"));
                            tickers.add(ticker);
                            tickerAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.e("error_cc", response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }

    private void addEvents() {
        lvticker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), DetailTickerActivity.class);
                Ticker ticker = tickers.get(position);
                i.putExtra("TICKER", ticker);
                startActivity(i);
            }
        });
    }

    private void addControls(View view) {
        lvticker = view.findViewById(R.id.lvTicker);
        tickers = new ArrayList<>();
        tickerAdapter = new TickerAdapter(getActivity(), tickers);
        lvticker.setAdapter(tickerAdapter);
    }
}
