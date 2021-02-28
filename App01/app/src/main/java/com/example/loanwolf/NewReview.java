package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class NewReview extends AppCompatActivity {

    private EditText Review;
    private android.widget.RadioGroup RadioGroup;
    private RadioButton One;
    private RadioButton Two;
    private RadioButton Three;
    private RadioButton Four;
    private RadioButton Five;
    private Button Return;
    private Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review);

        //Get widgets from XML
        Review = (EditText) findViewById(R.id.editReview);
        RadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        One = (RadioButton) findViewById(R.id.radioOne);
        Two = (RadioButton) findViewById(R.id.radioTwo);
        Three = (RadioButton) findViewById(R.id.radioThree);
        Four = (RadioButton) findViewById(R.id.radioFour);
        Five = (RadioButton) findViewById(R.id.radioFive);
        Return = (Button) findViewById(R.id.btnReturn);
        Submit = (Button) findViewById(R.id.btnSubmit);
    }
}