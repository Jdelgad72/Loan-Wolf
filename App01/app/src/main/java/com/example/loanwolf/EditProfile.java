package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditProfile extends AppCompatActivity {

    private EditText First;
    private EditText Last;
    private EditText DOB;
    private EditText Address;
    private EditText ZIP;
    private EditText State;
    private EditText Gender;
    private EditText Email;
    private Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        First = (EditText) findViewById(R.id.editTextFirstName);
        Last = (EditText) findViewById(R.id.editTextLastName);
        DOB = (EditText) findViewById(R.id.editTextDOB);
        Address = (EditText) findViewById(R.id.editTextAddress);
        ZIP = (EditText) findViewById(R.id.editTextZIP);
        State = (EditText) findViewById(R.id.editTextState);
        Gender = (EditText) findViewById(R.id.editTextGender);
        Email = (EditText) findViewById(R.id.editTextEmail);
        Submit = (Button) findViewById(R.id.btnSubmit);
    }
}