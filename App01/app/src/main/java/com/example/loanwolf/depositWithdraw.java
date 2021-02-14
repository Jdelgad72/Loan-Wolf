package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class depositWithdraw extends AppCompatActivity {
    Button button0, button01, button02, button03, button04, button05, button06, button07, button08, button09, buttonDeposit, buttonWithdraw, buttonPeriod, buttonClear, buttonBack;
    TextView workspace, message;
    String add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_withdraw);

        button0 = findViewById(R.id.button0);
        button01 = findViewById(R.id.button01);
        button02 = findViewById(R.id.button02);
        button03 = findViewById(R.id.button03);
        button04 = findViewById(R.id.button04);
        button05 = findViewById(R.id.button05);
        button06 = findViewById(R.id.button06);
        button07 = findViewById(R.id.button07);
        button08 = findViewById(R.id.button08);
        button09 = findViewById(R.id.button09);
        buttonDeposit = findViewById(R.id.buttonDeposit);
        buttonWithdraw = findViewById(R.id.buttonWithdarw);
        buttonPeriod = findViewById(R.id.buttonPeriod);
        buttonClear = findViewById(R.id.buttonClear);
        workspace = findViewById(R.id.workspace);
        message = findViewById(R.id.message);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "0");
            }
        });
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "1");
            }
        });
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "2");
            }
        });
        button03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "3");
            }
        });
        button04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "4");
            }
        });
        button05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "5");
            }
        });
        button06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "6");
            }
        });
        button07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "7");
            }
        });
        button08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "8");
            }
        });
        button09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "9");
            }
        });
        buttonPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + ".");
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workspace.setText("");
            }
        });

        backButton();
    }
        private void backButton(){
        buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(depositWithdraw.this, Profile.class));
            }
        });


    }
}