package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateWolfPack extends AppCompatActivity {
    EditText username;
    Button invite, finished;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wolf_pack);
        username = (EditText) findViewById(R.id.username);
        invite = (Button) findViewById(R.id.invite);
        finished = (Button) findViewById(R.id.finished);

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.equals("Justin Delgado")){
                    Toast.makeText(CreateWolfPack.this, "Invitation Sent", Toast.LENGTH_SHORT).show();
                }
                if (invite.equals("Sam Jaworski")){
                    Toast.makeText(CreateWolfPack.this, "Invitation Sent", Toast.LENGTH_SHORT).show();
                }
                if (invite.equals("Felipe Garcilazo")){
                    Toast.makeText(CreateWolfPack.this, "Invitation Sent", Toast.LENGTH_SHORT).show();
                }
                if (invite.equals("Test Test")){
                    Toast.makeText(CreateWolfPack.this, "Invitation Sent", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CreateWolfPack.this, "User not found", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}