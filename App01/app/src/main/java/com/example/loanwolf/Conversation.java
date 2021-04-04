package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Conversation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        final String name = getIntent().getStringExtra("name");

        TextView textView = findViewById(R.id.TextView1);
        textView.setText(name);
    }

    public void ClickMessages(View view) {
        Intent intent = new Intent(Conversation.this, Messaging.class);
        startActivity(intent);
    }
}