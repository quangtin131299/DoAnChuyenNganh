package com.example.appdatvexemphim.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.appdatvexemphim.DTO.Ticker;
import com.example.appdatvexemphim.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TickerAdapter extends BaseAdapter {

    Context context;
    ArrayList<Ticker> tickers;

    public TickerAdapter(Context context, ArrayList<Ticker> tickers) {
        this.context = context;
        this.tickers = tickers;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Ticker> getTickers() {
        return tickers;
    }

    public void setTickers(ArrayList<Ticker> tickers) {
        this.tickers = tickers;
    }

    @Override
    public int getCount() {
        return tickers.size();
    }

    @Override
    public Object getItem(int position) {
        return tickers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_ticker, null);
            viewHolder = new ViewHolder();
            viewHolder.txtday = convertView.findViewById(R.id.txtday);
            viewHolder.txtghe = convertView.findViewById(R.id.txtghe);
            viewHolder.txtNgayDatVaThoiGian = convertView.findViewById(R.id.txtNgayDatVaThoiGian);
            viewHolder.txtTenrap = convertView.findViewById(R.id.txtTenrap);
            viewHolder.txtTenPhim = convertView.findViewById(R.id.txtTenPhim);
            viewHolder.txtTenPhong = convertView.findViewById(R.id.txtTenPhong);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Ticker ticker = tickers.get(position);
        viewHolder.txtTenrap.setText(ticker.getTenrap());
        viewHolder.txtghe.setText(ticker.getTenghe());
        viewHolder.txtday.setText(ticker.getTenghe().charAt(0) + "");
        viewHolder.txtTenPhim.setText(ticker.getTenphim());
        viewHolder.txtTenPhong.setText(ticker.getTenphong());

        viewHolder.txtNgayDatVaThoiGian.setText(ticker.getNgaydat() + " " + ticker.getGio());


        return convertView;
    }

    public class ViewHolder {
        TextView txtTenPhim, txtday, txtghe, txtTenrap, txtNgayDatVaThoiGian, txtTenPhong;
    }
}
