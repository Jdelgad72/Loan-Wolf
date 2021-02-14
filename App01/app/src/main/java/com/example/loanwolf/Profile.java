package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    Button buttonDepositWithdraw, buttonHome, buttonProfile, buttonMessage;
    TextView account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        switchButton();
    }

    private void switchButton(){

        buttonDepositWithdraw = findViewById(R.id.buttonDepositWithdraw);

        buttonDepositWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, depositWithdraw.class));
            }
        });

    }
}