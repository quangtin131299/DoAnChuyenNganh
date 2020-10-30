package com.example.appdatvexemphim.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdatvexemphim.Activity.DetalsMovieActivity;
import com.example.appdatvexemphim.Adapter.ListMovieAdapter;
import com.example.appdatvexemphim.DTO.Movie;
import com.example.appdatvexemphim.R;
import com.example.appdatvexemphim.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class FragmentNearYou extends Fragment {

    RecyclerView rvMovie;
    ListMovieAdapter listMovieAdapter;
    ArrayList<Movie> movies = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearyou, container, false);
        addControls(view);
        addEvents();
        loadDataPhimDangChieu();
        return view;
    }

    private void addEvents() {

    }

    private void addControls(View view) {
        rvMovie = view.findViewById(R.id.rvMovieNearYou);
        listMovieAdapter = new ListMovieAdapter(getActivity(), movies);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2 );
        rvMovie.setHasFixedSize(false);
        rvMovie.setLayoutManager(gridLayoutManager);
        rvMovie.setAdapter(listMovieAdapter);

    }

    public void loadDataPhimDangChieu() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = Util.LINK_LOADPHIMDANGCHIEU;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Movie movie = new Movie();
                            movie.setId(jsonArray.getJSONObject(i).getInt("ID"));
                            movie.setTenphim(jsonArray.getJSONObject(i).getString("TenPhim"));
                            movie.setThoigian(Integer.parseInt(jsonArray.getJSONObject(i).getString("ThoiGian")));
                            movie.setHinh(jsonArray.getJSONObject(i).getString("Hinh"));
                            movies.add(movie);
                            listMovieAdapter.notifyDataSetChanged();
                        }
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

