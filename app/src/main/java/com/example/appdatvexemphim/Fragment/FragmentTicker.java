package com.example.appdatvexemphim.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appdatvexemphim.Adapter.TickerAdapter;
import com.example.appdatvexemphim.R;

public class FragmentTicker extends Fragment {

    ListView lvticker;
    TickerAdapter tickerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticker, container, false);
        addControls(view);
        return view;
    }

    private void addControls(View view) {
        lvticker = view.findViewById(R.id.lvTicker);
        tickerAdapter = new TickerAdapter(getActivity());
        lvticker.setAdapter(tickerAdapter);
    }
}
