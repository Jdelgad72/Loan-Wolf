package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Messaging extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
    }

    public void ClickHome(View view) {
        Intent intent = new Intent(Messaging.this, Home.class);
        startActivity(intent);
    }
}