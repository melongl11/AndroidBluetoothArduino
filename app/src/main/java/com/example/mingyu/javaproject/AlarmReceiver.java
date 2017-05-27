package com.example.mingyu.javaproject;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/**
 * Created by melon on 2017-05-24.
 */

public class AlarmReceiver extends AppCompatActivity {
    Set<BluetoothDevice> mDevices;
    BluetoothDevice mRemoteDevice;
    BluetoothSocket mSocket = null;
    OutputStream mOutputStream = null;
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    TextView mTextMacadd;

    String macadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        mTextMacadd = (TextView)findViewById(R.id.macadd);

        Intent i = getIntent();
        macadd = i.getStringExtra("macadd");
        mTextMacadd.setText(macadd);

        connectToSelectedDevice(macadd);
    }

    BluetoothDevice getDeviceFromBondedList(String macadd) {
        mDevices = mBluetoothAdapter.getBondedDevices();
        BluetoothDevice selectedDevice = null;
        try {
            for (BluetoothDevice device : mDevices) {
                if (macadd.equals(device.getAddress())) {
                    selectedDevice = device;
                    break;
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "getDeviceFromBondedList Error", Toast.LENGTH_LONG).show();
        }
        return selectedDevice;
    }

    void connectToSelectedDevice(String selectedDeviceAddress) {
        mRemoteDevice = getDeviceFromBondedList(selectedDeviceAddress);
        UUID uuid = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        try {
            mSocket = mRemoteDevice.createRfcommSocketToServiceRecord(uuid);
            mSocket.connect();


            mOutputStream = mSocket.getOutputStream();

            sendData("a");

        }catch(Exception e) {
            Toast.makeText(getApplicationContext(), "connectError", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    void sendData(String msg) {
        try{
            mOutputStream.write(msg.getBytes());  // 문자열 전송.
        }catch(Exception e) {
            Toast.makeText(getApplicationContext(), "sendError", Toast.LENGTH_LONG).show();
        }
    }

}
