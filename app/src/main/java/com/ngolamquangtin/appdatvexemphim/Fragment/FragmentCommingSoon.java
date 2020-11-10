package com.ngolamquangtin.appdatvexemphim.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ngolamquangtin.appdatvexemphim.Adapter.ListMovieCommingSoonAdapter;
import com.ngolamquangtin.appdatvexemphim.DTO.Movie;
import com.ngolamquangtin.appdatvexemphim.R;
import com.ngolamquangtin.appdatvexemphim.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class FragmentCommingSoon extends Fragment {

    RecyclerView rvMovieCommingSoon;
    ListMovieCommingSoonAdapter listMovieAdapter;
    ArrayList<Movie> movies;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commingsoon, container, false);
        addControls(view);
        loadMovieCommingSoon();
        return view;
    }

    private void loadMovieCommingSoon() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = Util.LINK_LOADPHIMSAPCHIEU;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i ++){
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

            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    private void addControls(View view) {
        rvMovieCommingSoon = view.findViewById(R.id.rvMovieCommingSoon);
        movies = new ArrayList<>();
        listMovieAdapter = new ListMovieCommingSoonAdapter(getActivity(), movies);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvMovieCommingSoon.setHasFixedSize(true);
        rvMovieCommingSoon.setLayoutManager(gridLayoutManager);
        rvMovieCommingSoon.setAdapter(listMovieAdapter);
    }
}
