package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WolfPack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wolf_pack);
    }

    public void buttonView(View view) {
        startActivity(new Intent(WolfPack.this, ViewWolfPacks.class));
    }
    public void buttonJoin(View view) {
        startActivity(new Intent(WolfPack.this, JoinWolfPack.class));
    }
    public void buttonCreate(View view) {
        startActivity(new Intent(WolfPack.this, CreateWolfPack.class));
    }
}
