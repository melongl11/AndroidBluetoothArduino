package com.example.mingyu.javaproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ArrayList<HashMap<String, String>> bulbList;

    ListView list;
    ListAdapter adapter;
    DBManager dbManager;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listview);
        dbManager = new DBManager(this, "Bulb.db", null, 1);

        SQLiteDatabase db = dbManager.getWritableDatabase();
        c = db.rawQuery("select * from BULB_LIST", null);
        adapter = new SimpleCursorAdapter(this, R.layout.list_item, c, new String[]{"Name", "address"}, new int[]{R.id.Name, R.id.address});

        list.setAdapter(adapter);
        list.setOnItemClickListener(this);


        Button btnBluetooth = (Button) findViewById(R.id.btn_bluetooth);
        btnBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn_bluetooth) {
                    Intent intent = new Intent(MainActivity.this, BTManager.class);
                    intent.putExtra("Connect", 0);
                    startActivity(intent);
                }
            }
        });

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(this, BTManager.class);

        c.moveToPosition(position);
        intent.putExtra("MacAddress", c.getString(2));
        intent.putExtra("Connect", 1);
        startActivityForResult(intent, 0);
    }

}

