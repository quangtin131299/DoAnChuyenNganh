package com.example.appdatvexemphim.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.GridLayoutManager.DefaultSpanSizeLookup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatvexemphim.Adapter.ListMovieAdapter;
import com.example.appdatvexemphim.R;

public class FragmentNearYou extends Fragment {

    RecyclerView rvMovie;

    ListMovieAdapter listMovieAdapter ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearyou, container, false);

        addControls(view);
        return view;
    }

    private void addControls(View view) {
        rvMovie = view.findViewById(R.id.rvMovie);
        listMovieAdapter = new ListMovieAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(gridLayoutManager);
        rvMovie.setAdapter(listMovieAdapter);

    }
}
