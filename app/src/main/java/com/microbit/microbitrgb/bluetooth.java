package com.microbit.microbitrgb;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


/**
 * Created by Karlo on 20-Dec-17.
 */

public class bluetooth {


    private static final int REQUEST_ENABLE_BT = 1;

    private TextView lblBluetooth;
    private boolean isConnected;

    private MainActivity controlActivity;

    private  BluetoothAdapter mBluetoothAdapter;

    public bluetooth(MainActivity activity, TextView textView) {
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
            Toast.makeText(controlActivity.getApplicationContext(), "Bluetooth je već uključen!", Toast.LENGTH_LONG).show();
        }
    }

    public void off() {
        mBluetoothAdapter.disable();
        Toast.makeText(controlActivity.getApplicationContext(), "Bluetooth isključen!",Toast.LENGTH_LONG).show();
    }

    public void list() {

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        List<String> list = new ArrayList<>();

         if (pairedDevices.size() > 0) {
             // There are paired devices. Get the name and address of each paired device.
             for (BluetoothDevice device : pairedDevices) {
                 String deviceName = device.getName();
                 list.add(deviceName);
                 String deviceHardwareAddress = device.getAddress(); // MAC address
             }
         }

        CharSequence[] items = list.toArray(new CharSequence[list.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(controlActivity);
        builder.setTitle("Make your selection");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                Toast.makeText(controlActivity.getApplicationContext(), Integer.toString(item),Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }


}
