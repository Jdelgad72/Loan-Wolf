package com.example.loanwolf;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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

import androidx.appcompat.app.AppCompatActivity;

public class Leaderboard extends AppCompatActivity {

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

//                        list = (ListView) findViewById(R.id.listview);

                       // final ListViewAdapterOpenLoans adapter = new ListViewAdapterOpenLoans(Leaderboard.this, getOpenLoanID());


                  //      list.setAdapter(adapter);
                        //list = (ListView) findViewById(R.id.listview);

                        //final ListViewAdapterOpenLoans adapter = new ListViewAdapterOpenLoans(Leaderboard.this, getOpenLoanID());


                        //list.setAdapter(adapter);

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

        //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


        ListView = (ListView) findViewById(R.id.listview);

        ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add("Diego Lugo");
        arrayList.add("Justin Delgado");
        arrayList.add("test1");
        arrayList.add("Felipe Garcilazo");
        arrayList.add("test2");
        
        //create array adapter
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        ListView.setAdapter(arrayAdapter);
        arrayList.add("");
    }
}

