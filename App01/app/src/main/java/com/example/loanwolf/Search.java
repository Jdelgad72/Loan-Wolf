package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
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

public class Search extends AppCompatActivity{

    // Declare Variables
    ListView list;
    SearchView editsearch;
    ArrayList<UserNames> names = new ArrayList<UserNames>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // send String to server and find name matches from server
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/search.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONArray name_array = obj.getJSONArray("names");
                        JSONArray email_array = obj.getJSONArray("emails");

                        for (int i = 0; i<name_array.length(); i++) {
                            names.add(new UserNames(name_array.getString(i), email_array.getString(i)));
                        }

                        list = (ListView) findViewById(R.id.listview);
                        // Locate the EditText in listview_main.xml
                        editsearch = (SearchView) findViewById(R.id.search);

                        final ListViewAdapter adapter = new ListViewAdapter(Search.this, getUserNames());
                        list.setAdapter(adapter);

                        editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
                            @Override
                            public boolean onQueryTextSubmit(String query){
                                for(int i=0; i<names.size(); i++){
                                    if(names.get(i).getUserName().equals(query)){
                                        Intent intent = new Intent(Search.this, ViewProfile.class);
                                        intent.putExtra("USERNAME", names.get(i).getUserName());
                                        intent.putExtra("EMAIL", names.get(i).getEmail());
                                        startActivity(intent);
                                    }
                                }
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String query){
                                adapter.getFilter().filter(query);
                                return false;
                            }
                        });

                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                UserNames send = (UserNames) adapterView.getItemAtPosition(i);
                                Intent intent = new Intent(Search.this, ViewProfile.class);
                                intent.putExtra("USERNAME", send.getUserName());
                                intent.putExtra("EMAIL", send.getEmail());
                                startActivity(intent);
                            }
                        });
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
    }

    private ArrayList<UserNames> getUserNames(){
        ArrayList<UserNames> userNames = new ArrayList<UserNames>();
        UserNames p;

        for(int i=0; i<names.size(); i++){
            p=new UserNames(names.get(i).getUserName(), names.get(i).getEmail());
            userNames.add(p);
        }
        return userNames;
    }

    public void ClickHome(View view){
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }
}