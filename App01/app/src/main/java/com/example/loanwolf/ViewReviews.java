package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewReviews extends AppCompatActivity {

    private ListView ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reviews);

        ListView = (ListView) findViewById(R.id.listview);

        ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add("Sample Text");

        //create array adapter
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        ListView.setAdapter(arrayAdapter);
        
    }
}