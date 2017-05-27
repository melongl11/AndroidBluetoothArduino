package com.example.mingyu.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by melon on 2017-05-27.
 */

public class ALActivity extends AppCompatActivity{

    EditText mEditHour;
    EditText mEditMin;
    TextView mTextMac;
    Button mButtonReserv;
    Button mButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserv);

        Intent i = getIntent();
        final String macadd = i.getStringExtra("macadd");

        mEditHour = (EditText)findViewById(R.id.hour);
        mEditMin = (EditText)findViewById(R.id.minute);
        mTextMac = (TextView)findViewById(R.id.macadd);

        mTextMac.setText(macadd);

        mButtonReserv = (Button)findViewById(R.id.btn_reservset);
        mButtonReserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ALManager am = new ALManager();
                am.setAlarm(getApplicationContext(),Integer.parseInt(mEditHour.getText().toString()),Integer.parseInt(mEditMin.getText().toString()), macadd);
                Toast.makeText(getApplicationContext(), Integer.parseInt(mEditHour.getText().toString()) + "시 "+Integer.parseInt(mEditMin.getText().toString()) + "분 알람 설정 완료", Toast.LENGTH_LONG).show();
            }
        });


    }
}
