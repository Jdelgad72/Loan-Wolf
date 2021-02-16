package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    /* setting buttons and textviews*/
    Button buttonDepositWithdraw, buttonHome, buttonProfile, buttonMessage;
    TextView account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /* button for switching to the transaction screen */
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
        /* buttons for getting to to the home and message screen (NOT FINISHED) */
        /*

        switchButton02();
     private void switchButton02(){
         buttonHome = findViewById(R.id.buttonHome);

         buttonHome.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Profile.this, Home.class));
             }
         });

         switchButton03();
     private void switchButton02(){
         buttonMessage = findViewById(R.id.buttonMessage);

         buttonMessage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Profile.this, Message.class));
             }
         });

         */


    }
}