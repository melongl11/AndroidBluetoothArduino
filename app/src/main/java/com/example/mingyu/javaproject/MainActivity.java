package com.example.mingyu.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBManager dbManager = new DBManager(getApplicationContext(), "Food.db", null, 1);

        final EditText etName = (EditText) findViewById(R.id.et_foodname);
        final EditText etPrice = (EditText) findViewById(R.id.et_price);

        final TextView tvResult = (TextView) findViewById(R.id.tv_result);

        Button btnInsert = (Button) findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String price = etPrice.getText().toString();
                dbManager.insert("insert into FOOD_LIST values(null, '" + name + "', "+ price + ");");
                tvResult.setText( dbManager.PrintData());
            }
        });

        Button btnUpdate = (Button) findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String price = etPrice.getText().toString();
                dbManager.update("update FOOD_LIST set price = " + price + " where name = '" + name + "';");

                tvResult.setText(dbManager.PrintData());
            }

        });

        Button btnDelete = (Button) findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                dbManager.delete("delete from FOOD_LIST where name = '" + name + "';");

                tvResult.setText(dbManager.PrintData());
            }
        });

        Button btnSelect = (Button) findViewById(R.id.btn_select);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResult.setText(dbManager.PrintData());
            }
        });

        Button btnBluetooth = (Button) findViewById(R.id.btn_bluetooth);
        btnBluetooth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.btn_bluetooth) {
                    Intent intent = new Intent(MainActivity.this, BTManager.class);
                    startActivity(intent);
                }
            }
        });
    }
}
