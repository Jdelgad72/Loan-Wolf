package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class JoinWolfPack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_wolf_pack);
    }
    public void buttonJoinWP(View view) {
        startActivity(new Intent(JoinWolfPack.this, Home.class));
    }
}