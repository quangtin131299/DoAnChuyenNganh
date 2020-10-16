package com.example.appdatvexemphim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appdatvexemphim.DTO.Cinema;
import com.example.appdatvexemphim.R;

import java.util.ArrayList;

public class RapAdapter extends BaseAdapter {


    Context context;
    ArrayList<Cinema> cinemas;


    public RapAdapter(Context context, ArrayList<Cinema> cinemas) {
        this.context = context;
        this.cinemas = cinemas;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return cinemas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_cinema, null);
        TextView tvtenrap = convertView.findViewById(R.id.tvtenrap);
        TextView tvdiachi = convertView.findViewById(R.id.tvdiachi);

        return convertView;
    }
}
