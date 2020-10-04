package com.example.appdatvexemphim.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdatvexemphim.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.RotatingCircle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress);
        Sprite doubleBounce = new RotatingCircle();
        progressBar.setIndeterminateDrawable(doubleBounce);

        new PrefetchData().execute();
    }


    class PrefetchData extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            //Nap data ở đây
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            },5000);


        }
    }
}
