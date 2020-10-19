package com.example.appdatvexemphim.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appdatvexemphim.Adapter.MovieFavouriteAdapter;
import com.example.appdatvexemphim.R;

public class FragmentFavourite extends Fragment {

    ListView lvMovieFavourite;
    MovieFavouriteAdapter movieFavouriteAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        addControls(view);
        return view;
    }

    private void addControls(View view) {
        lvMovieFavourite = view.findViewById(R.id.lvmoviefavourite);
        movieFavouriteAdapter = new MovieFavouriteAdapter(getActivity().getApplicationContext());
        lvMovieFavourite.setAdapter(movieFavouriteAdapter);
    }


}
