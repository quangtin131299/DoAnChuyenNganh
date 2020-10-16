package com.example.appdatvexemphim.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appdatvexemphim.Adapter.RapAdapter;
import com.example.appdatvexemphim.DTO.Cinema;
import com.example.appdatvexemphim.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentCinema extends Fragment {


    ListView lvCinema;
    RapAdapter rapAdapter;

    ArrayList<Cinema> arrCinema;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cinema, container, false);
        addControls(view);
        return view;
    }


    public void addControls(View view) {
        lvCinema = view.findViewById(R.id.lvCinema);
        arrCinema = new ArrayList<>();
        rapAdapter = new RapAdapter(getActivity().getApplicationContext(), arrCinema);
        lvCinema.setAdapter(rapAdapter);
    }
}
