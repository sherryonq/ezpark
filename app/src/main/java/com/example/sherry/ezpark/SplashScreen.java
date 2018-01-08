package com.example.sherry.ezpark;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 2000;

    ProgressBar progressBar;
    TextView textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
