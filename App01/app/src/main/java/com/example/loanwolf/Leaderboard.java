package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Leaderboard extends AppCompatActivity {

    private Button Return;
    private android.widget.ListView ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Return = (Button) findViewById(R.id.btnReturn);
        ListView = (ListView) findViewById(R.id.listview);

        ArrayList<String> arrayList=new ArrayList<>();



        //create array adapter
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        ListView.setAdapter(arrayAdapter);

        //return to the profile page without changing anything
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Leaderboard.this, ViewProfile.class));
            }
        });
    }
}

//        arrayList.add("");