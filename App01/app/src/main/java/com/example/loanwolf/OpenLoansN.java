package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OpenLoansN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_loans_n);
    }

    public void buttonView(View view) {
        startActivity(new Intent(OpenLoansN.this, ViewWolfPacks.class));
    }
}