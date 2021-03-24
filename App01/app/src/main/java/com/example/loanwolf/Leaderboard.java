package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Leaderboard extends AppCompatActivity {

    private Button Return;
    private android.widget.ListView ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        // send String to server and find name matches from server
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/XXXXXXXXXXXXXXXXXXXXXXX.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONArray rank = obj.getJSONArray("rank");
                        JSONArray name = obj.getJSONArray("name");
                        JSONArray email = obj.getJSONArray("email");
                        JSONArray starRating = obj.getJSONArray("starRating");

                        list = (ListView) findViewById(R.id.listview);

                        final ListViewAdapterOpenLoans adapter = new ListViewAdapterOpenLoans(Leaderboard.this, getOpenLoanID());


                        list.setAdapter(adapter);

                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("RESPONSE1", String.valueOf(e));
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VolleyError", String.valueOf(error));
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

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