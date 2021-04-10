package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class wolfPackOptions extends AppCompatActivity {
    private SeekBar seekBar;
    private ProgressBar progressBar;
    private TextView intView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wolf_pack_options);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        intView = (TextView) findViewById(R.id.intView);
    }
    public void nextpage(View view) {
        startActivity(new Intent(wolfPackOptions.this, CreateWolfPack.class));
    }

    /*seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            progressBar.setProgress(progress);
            intView.setText("" + progress + "");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }); */

}