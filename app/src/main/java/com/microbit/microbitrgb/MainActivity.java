package com.microbit.microbitrgb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView lblRed, lblGreen, lblBlue, lblStatus, lblColor;
    private Button btnConnect;
    private SeekBar sRed, sGreen, sBlue;

    private bluetooth mBluetooth;
    private SharedPreferences myConfig;

    public PopupWindow mPopupWindow;
    public LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainActivity mainActivity = this;

        lblRed = (TextView) findViewById(R.id.lRed);
        lblGreen = (TextView) findViewById(R.id.lGreen);
        lblBlue = (TextView) findViewById(R.id.lBlue);
        lblStatus = (TextView) findViewById(R.id.lStatus);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        lblColor = (TextView) findViewById(R.id.lblColor);

        sRed    = (SeekBar)  findViewById(R.id.pRed);
        sGreen  = (SeekBar)  findViewById(R.id.pGreen);
        sBlue   = (SeekBar)  findViewById(R.id.pBlue);

        mBluetooth = new bluetooth(this, lblStatus);
        mBluetooth.on();

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBluetooth.list();


            }
        });

        sRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                lblRed.setText(getString(R.string.lblRed) + "   " + (progress));
                ShowColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                lblGreen.setText(getString(R.string.lblGreen) + "    " + (progress));
                ShowColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                lblBlue.setText(getString(R.string.lblBlue) + "     " + (progress));
                ShowColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBluetooth.off();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this.getApplicationContext(), "Bluetooth uključen", Toast.LENGTH_LONG).show();

    }

    private void ShowColor() {
        int red     = sRed.getProgress();
        int green   = sGreen.getProgress();
        int blue    = sBlue.getProgress();

        if((red+green+blue) > 0) {
            lblColor.setBackgroundColor(Color.rgb(red,green,blue));
        } else {
            lblColor.setBackgroundColor(Color.TRANSPARENT);
        }
    }

}
