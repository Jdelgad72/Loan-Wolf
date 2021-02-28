package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Scroller;
import android.widget.Toast;

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

        //Code so the EditText can scroll
        Review.setScroller(new Scroller(getApplicationContext()));
        Review.setVerticalScrollBarEnabled(true);
        Review.setMinLines(1);
        Review.setMaxLines(6);

        //Code for a return button
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewReview.this, Profile.class));
            }
        });
        //Code for a submit button
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*//get which radio button is selected
                public void checkButton(View v){
                    int radioID = RadioGroup.getCheckedRadioButtonId();
                    selectedRadioButton = findViewById(radioID);
                }*/
                if (Review.getText().toString().trim().length() == 0) {
                    //the message saying that you need to fill out the fields
                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill out a Review before saving", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            }
        });
    }
}