package com.example.mingyu.javaproject;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/**
 * Created by melon on 2017-05-24.
 */

public class AlarmReceiver extends BroadcastReceiver {
    Set<BluetoothDevice> mDevices;
    BluetoothDevice mRemoteDevie;
    BluetoothSocket mSocket = null;
    OutputStream mOutputStream = null;
    char mCharDelimiter =  '\n';
    String mStrDelimiter = "\n";

    BluetoothDevice getDeviceFromBondedList(String macadd) {
        BluetoothDevice selectedDevice = null;
        for(BluetoothDevice device : mDevices) {
            if(macadd.equals(device.getAddress())) {
                selectedDevice = device;
                break;
            }
        }
        return selectedDevice;
    }

    void connectToSelectedDevice(String selectedDeviceAddress) {
        mRemoteDevie = getDeviceFromBondedList(selectedDeviceAddress);
        UUID uuid = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        try {
            mSocket = mRemoteDevie.createRfcommSocketToServiceRecord(uuid);
            mSocket.connect();


            mOutputStream = mSocket.getOutputStream();

            sendData("1");

        }catch(Exception e) {

        }
    }

    void sendData(String msg) {
        try{
            mOutputStream.write(msg.getBytes());  // 문자열 전송.
        }catch(Exception e) {

        }
    }
    @Override
    public void onReceive(Context context, Intent intent) {

    }

}
