package com.microbit.microbitrgb;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView lblRed, lblGreen, lblBlue, lblStatus, lblColor;
    Button btnConnect;
    SeekBar sRed, sGreen, sBlue;

    private SharedPreferences myConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: preferences();
        myConfig = PreferenceManager.getDefaultSharedPreferences(this);

        lblRed = (TextView) findViewById(R.id.lRed);
        lblGreen = (TextView) findViewById(R.id.lGreen);
        lblBlue = (TextView) findViewById(R.id.lBlue);
        lblStatus = (TextView) findViewById(R.id.lStatus);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        lblColor = (TextView) findViewById(R.id.lblColor);

        sRed    = (SeekBar)  findViewById(R.id.pRed);
        sGreen  = (SeekBar)  findViewById(R.id.pGreen);
        sBlue   = (SeekBar)  findViewById(R.id.pBlue);

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
