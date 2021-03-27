package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class wolfPackOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wolf_pack_options);
    }
    public void nextpage(View view) {
        startActivity(new Intent(wolfPackOptions.this, CreateWolfPack.class));
    }
}