package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Report extends AppCompatActivity {
    EditText et;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        et = findViewById(R.id.editTextTextMultiLine);
        btn = findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et.getText().toString().isEmpty() || et.getText().toString()==null) {
                    Toast.makeText(getApplicationContext(), "User Reported", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Report Unsuccessful", Toast.LENGTH_LONG).show();

                }
            }
        });


    }
}