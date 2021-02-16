package com.example.loanwolf;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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
    private Button Return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
/*
        First = (EditText) findViewById(R.id.editTextFirstName);
        Last = (EditText) findViewById(R.id.editTextLastName);
        DOB = (EditText) findViewById(R.id.editTextDOB);
        Address = (EditText) findViewById(R.id.editTextAddress);
        ZIP = (EditText) findViewById(R.id.editTextZIP);
        State = (EditText) findViewById(R.id.editTextState);
        Gender = (EditText) findViewById(R.id.editTextGender);
        Email = (EditText) findViewById(R.id.editTextEmail);
        Submit = (Button) findViewById(R.id.btnSubmit);
        Return = (Button) findViewById(R.id.btnReturn);

        //return to the profile page without changing anything
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfile.this, Profile.class));
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //in case a user tries to update a field and doesn't add a new one
                if(First.getText().toString().trim().length()==0|| Last.getText().toString().trim().length()==0)
                    DOB.getText().toString().trim().length()==0 || Address.getText().toString().trim().length()==0)
                    || ZIP.getText().toString().trim().length()==0) || State.getText().toString().trim().length()==0)
                    || Gender.getText().toString().trim().length()==0) || Email.getText().toString().trim().length()==0)
                {
                    //the message saying that you need to fill out the fields
                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill out all text fields before saving", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            }
        });*/
    }
}