package com.ngolamquangtin.appdatvexemphim.Fragment;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ngolamquangtin.appdatvexemphim.Activity.DetailTickerActivity;
import com.ngolamquangtin.appdatvexemphim.Adapter.TickerAdapter;
import com.ngolamquangtin.appdatvexemphim.DTO.Ticker;
import com.ngolamquangtin.appdatvexemphim.R;
import com.ngolamquangtin.appdatvexemphim.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class FragmentTicker extends Fragment {

    SwipeRefreshLayout refeslayoutticker;
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
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(2000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

    }

    private void addEvents() {
        refeslayoutticker.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tickers.clear();
                tickerAdapter.notifyDataSetChanged();
                loadDSVe();
                refeslayoutticker.setRefreshing(false);
            }
        });
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
        refeslayoutticker = view.findViewById(R.id.refeslayoutticker);
        lvticker = view.findViewById(R.id.lvTicker);
        tickers = new ArrayList<>();
        tickerAdapter = new TickerAdapter(getActivity(), tickers);
        lvticker.setAdapter(tickerAdapter);
    }
}
