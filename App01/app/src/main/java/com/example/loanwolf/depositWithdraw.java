package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class depositWithdraw extends AppCompatActivity {
    Button button0, button01, button02, button03, button04, button05, button06, button07, button08, button09, buttonDeposit, buttonWithdraw, buttonPeriod, buttonClear;
    TextView workspace, message;

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

    }
}