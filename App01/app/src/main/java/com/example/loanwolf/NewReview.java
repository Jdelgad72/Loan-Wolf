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
    }
}