package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewWolfPacks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wolf_packs);
    }
    public void buttonView01(View view) {
        startActivity(new Intent(ViewWolfPacks.this, WolfPackInfo.class));
    }
    public void buttonView02(View view) {
        startActivity(new Intent(ViewWolfPacks.this, WolfPackInfo.class));
    }
}