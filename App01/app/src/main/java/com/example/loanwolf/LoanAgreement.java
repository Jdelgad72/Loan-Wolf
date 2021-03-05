package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoanAgreement extends AppCompatActivity {
    private Button button2;
    private TextView intView;
    private ProgressBar progressBar;
    private SeekBar seekBar;
    private EditText amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_agreement);

        intView = (TextView) findViewById(R.id.intView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        //String value= amount.getText().toString();
        //final int finalValue=Integer.parseInt(value);

        //final String rate= intView.getText().toString();
       // final int interestRate=Integer.parseInt(rate);







        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);
        Button btnDisplay = (Button) findViewById(R.id.button2);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoanAgreement.this, Terms.class);
                // intent.putExtra("VALUE", finalValue);
                // intent.putExtra("RATE", interestRate);
                startActivity(intent);

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(LoanAgreement.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
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


}