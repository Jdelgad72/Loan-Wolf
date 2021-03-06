package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OpenLoansCreate extends AppCompatActivity {
    private TextView intView;
    private SeekBar seekBar;
    private EditText amount;
    private EditText loanDate;
    private EditText payNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_open_loan);

        intView = (TextView) findViewById(R.id.intView);
        amount = (EditText) findViewById(R.id.amount);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        loanDate = (EditText) findViewById(R.id.loanDate);
        payNum = (EditText) findViewById(R.id.payNum);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);
        final RadioGroup radiogroup2 = (RadioGroup) findViewById(R.id.radiogroup2);
        Button btnDisplay = (Button) findViewById(R.id.button2);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                int selectedId2 = radiogroup2.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                RadioButton radioButton2 = (RadioButton) findViewById(selectedId2);

                Intent intent = new Intent(OpenLoansCreate.this, OpenLoanTerms.class);
                intent.putExtra("RADIO", String.valueOf(radioButton.getText()));
                intent.putExtra("RADIO2", String.valueOf(radioButton2.getText()));
                intent.putExtra("DATE", String.valueOf(loanDate.getText()));
                intent.putExtra("PAYMENTNUM", String.valueOf(payNum.getText()));
                intent.putExtra("VALUE", String.valueOf(amount.getText()));
                intent.putExtra("RATE", String.valueOf(intView.getText()));
                startActivity(intent);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                intView.setText("" + progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void ClickViewOpenLoans(View view) {
        Intent intent = new Intent(OpenLoansCreate.this, OpenLoans.class);
        startActivity(intent);
    }
}