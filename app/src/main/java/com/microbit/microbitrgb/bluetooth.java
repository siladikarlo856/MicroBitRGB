package com.microbit.microbitrgb;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;


/**
 * Created by Karlo on 20-Dec-17.
 */

public class bluetooth {


    private static final int REQUEST_ENABLE_BT = 1;

    private TextView lblBluetooth;
    private boolean isConnected;

    private Activity controlActivity;

    private  BluetoothAdapter mBluetoothAdapter;

    public bluetooth(Activity activity, TextView textView) {
        this.controlActivity    = activity;
        this.lblBluetooth       = textView;

        lblBluetooth.setText(R.string.lblBtNotCfg);
        isConnected = false;
    }
    public void on() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            controlActivity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

        } else {
            Toast.makeText(controlActivity.getApplicationContext(), "BLuetooth je već uključen!", Toast.LENGTH_LONG).show();
        }
    }

    public void off() {
        mBluetoothAdapter.disable();
        Toast.makeText(controlActivity.getApplicationContext(), "Bluetooth isključen!",Toast.LENGTH_LONG).show();
    }

    public void list() {
         Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

         if (pairedDevices.size() > 0) {
             // There are paired devices. Get the name and address of each paired device.
             for (BluetoothDevice device : pairedDevices) {
                 String deviceName = device.getName();
                 String deviceHardwareAddress = device.getAddress(); // MAC address
             }
         }
    }


}
