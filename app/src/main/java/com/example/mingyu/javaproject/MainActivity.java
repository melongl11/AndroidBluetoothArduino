package com.example.mingyu.javaproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> bulbList;

    ListView list;
    ListAdapter adapter;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listview);
        dbManager = new DBManager(this, "Bulb.db", null, 1);

        SQLiteDatabase db = dbManager.getWritableDatabase();
        Cursor c = db.rawQuery("select * from BULB_LIST", null);
        adapter = new SimpleCursorAdapter(this, R.layout.list_item, c, new String[]{"Name", "address"}, new int[]{R.id.Name, R.id.address});

        list.setAdapter(adapter);


        Button btnBluetooth = (Button) findViewById(R.id.btn_bluetooth);
        btnBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn_bluetooth) {
                    Intent intent = new Intent(MainActivity.this, BTManager.class);
                    startActivity(intent);
                }
            }
        });

    }
}

