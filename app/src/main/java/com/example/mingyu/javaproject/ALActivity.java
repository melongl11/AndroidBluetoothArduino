package com.example.mingyu.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by melon on 2017-05-27.
 */

public class ALActivity extends AppCompatActivity{

    EditText mEditHour;
    EditText mEditMin;
    TimePicker mTimePicker;
    Button mButtonReserv;
    Button mButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserv);

        Intent i = getIntent();
        final String macadd = i.getStringExtra("macadd");


        mTimePicker = (TimePicker)findViewById(R.id.timePicker);



        mButtonReserv = (Button)findViewById(R.id.btn_reservset);
        mButtonReserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int h = mTimePicker.getCurrentHour();
                int m = mTimePicker.getCurrentMinute();
                ALManager am = new ALManager();
                am.setAlarm(getApplicationContext(),h,m, macadd);
                Toast.makeText(getApplicationContext(), h + "시 "+m + "분 알람 설정 완료", Toast.LENGTH_LONG).show();
            }
        });


    }
}
