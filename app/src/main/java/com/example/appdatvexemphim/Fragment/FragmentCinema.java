package com.example.appdatvexemphim.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.appdatvexemphim.Adapter.RapAdapter;
import com.example.appdatvexemphim.DTO.Cinema;
import com.example.appdatvexemphim.R;
import com.example.appdatvexemphim.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class FragmentCinema extends Fragment {
    ListView lvCinema;
    RapAdapter rapAdapter;

    ArrayList<Cinema> arrCinema;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cinema, container, false);
        addControls(view);
        loadCinema();
        return view;
    }


    public void addControls(View view) {
        lvCinema = view.findViewById(R.id.lvCinema);
        arrCinema = new ArrayList<>();
        rapAdapter = new RapAdapter(getActivity().getApplicationContext(), arrCinema);
        lvCinema.setAdapter(rapAdapter);
    }

    public void loadCinema() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = Util.LINK_LOADRAPPHIM;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Cinema cinema = new Cinema();
                            cinema.setTenrap(jsonArray.getJSONObject(i).getString("TenRap"));
                            cinema.setDiachi(jsonArray.getJSONObject(i).getString("DiaChi"));
                            cinema.setHinh(jsonArray.getJSONObject(i).getString("Hinh"));
                            arrCinema.add(cinema);
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
}
