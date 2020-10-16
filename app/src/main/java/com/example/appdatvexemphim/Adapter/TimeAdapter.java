package com.example.appdatvexemphim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatvexemphim.R;

import java.util.ArrayList;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {


    Context context;

    public TimeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public  TimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new TimeAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_time,null));
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
