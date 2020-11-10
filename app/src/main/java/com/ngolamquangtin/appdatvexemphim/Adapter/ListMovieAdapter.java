package com.ngolamquangtin.appdatvexemphim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ngolamquangtin.appdatvexemphim.Activity.DetalsMovieActivity;
import com.ngolamquangtin.appdatvexemphim.DTO.Movie;
import com.ngolamquangtin.appdatvexemphim.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ViewHolder> {

    Context context;
    ArrayList<Movie> arrMovie;

    public ListMovieAdapter(Context context, ArrayList<Movie> arrMovie) {
        this.context = context;
        this.arrMovie = arrMovie;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datamovienearyou, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Movie movie = arrMovie.get(position);
        if (movie != null) {

            holder.txttenphim.setText(movie.getTenphim());
            holder.txtthoigian.setText(movie.getThoigian() + " min");
            Picasso.with(context).load(movie.getHinh()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    holder.imgphim.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return arrMovie.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txttenphim, txtthoigian;
        ImageView imgphim;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txttenphim = itemView.findViewById(R.id.txttenphim);
            txtthoigian = itemView.findViewById(R.id.txtthoigian);
            imgphim = itemView.findViewById(R.id.imgphim);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     Movie movie = arrMovie.get(getLayoutPosition());
                    Intent intent = new Intent(context, DetalsMovieActivity.class);
                    intent.putExtra("ID_MOVIE", movie.getId());
                    context.startActivity(intent);
                }
            });
        }

    }
}
