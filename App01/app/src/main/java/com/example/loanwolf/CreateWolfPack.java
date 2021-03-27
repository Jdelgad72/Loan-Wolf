package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                if (username.getText().toString().equals("Sam Jaworski") || username.getText().toString().equals("sam jaworski") || username.getText().toString().equals("Justin Delgado") || username.getText().toString().equals("justin delgado") || username.getText().toString().equals("Felipe Garcilazo") || username.getText().toString().equals("felipe garcilazo")) {
                    Toast.makeText(CreateWolfPack.this, "Invitation Sent", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(CreateWolfPack.this, "User not found", Toast.LENGTH_LONG).show();
                }
            }
        });
        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateWolfPack.this, Terms.class));
            }
        });
    }
}