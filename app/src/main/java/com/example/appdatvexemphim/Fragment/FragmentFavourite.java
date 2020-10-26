package com.example.appdatvexemphim.Fragment;

import android.content.Intent;
import android.graphics.Movie;
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
import com.example.appdatvexemphim.Activity.ChooseSessionActivity;
import com.example.appdatvexemphim.Adapter.MovieFavouriteAdapter;
import com.example.appdatvexemphim.DTO.MovieFavourite;
import com.example.appdatvexemphim.R;
import com.example.appdatvexemphim.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class FragmentFavourite extends Fragment {

    ListView lvMovieFavourite;
    MovieFavouriteAdapter movieFavouriteAdapter;
    ArrayList<MovieFavourite> movieFavourites;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        addControls(view);
        addEvents();
        loadMovieFavourite();
        return view;
    }

    private void addEvents() {
        lvMovieFavourite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ChooseSessionActivity.class);
                i.putExtra("ID_MOVIE", movieFavourites.get(position).getId());
            }
        });
    }

    private void loadMovieFavourite() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = Util.LINK_MOVIEFAVOURITE;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response != null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i++){
                            MovieFavourite movieFavourite = new MovieFavourite();
                            movieFavourite.setId(jsonArray.getJSONObject(i).getInt("ID"));
                            movieFavourite.setHinh(jsonArray.getJSONObject(i).getString("Hinh"));
                            movieFavourite.setTenphim(jsonArray.getJSONObject(i).getString("TenPhim"));
                            movieFavourite.setThoigian(Integer.parseInt(jsonArray.getJSONObject(i).getString("ThoiGian")));
                            movieFavourite.setMota(jsonArray.getJSONObject(i).getString("MoTa"));
                            movieFavourites.add(movieFavourite);
                            movieFavouriteAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ABCCCCC", error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    private void addControls(View view) {
        lvMovieFavourite = view.findViewById(R.id.lvmoviefavourite);
        movieFavourites = new ArrayList<>();
        movieFavouriteAdapter = new MovieFavouriteAdapter(getActivity().getApplicationContext(), movieFavourites);
        lvMovieFavourite.setAdapter(movieFavouriteAdapter);
    }


}
