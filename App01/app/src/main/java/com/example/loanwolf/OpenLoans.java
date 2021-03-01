package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class OpenLoans extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_loans);
    }

    public void ClickViewProfile(View view) {
        Intent intent = new Intent(OpenLoans.this, Home.class);
        startActivity(intent);
    }

    public void CreateOpenLoan(View view) {
        Intent intent = new Intent(OpenLoans.this, Home.class);
        startActivity(intent);
    }
}