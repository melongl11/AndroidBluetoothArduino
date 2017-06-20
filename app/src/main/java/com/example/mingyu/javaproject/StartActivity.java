package com.example.mingyu.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by melon on 2017-06-21.
 */

public class StartActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
