package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Messaging extends AppCompatActivity {

    // Declare Variables
    ListView list;
    ArrayList<MessageListObject> messageList = new ArrayList<MessageListObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        User user = SharedPrefManager.getInfo();
        final String id = user.getId();

        // send String to server and find name matches from server
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/messages.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONArray names = obj.getJSONArray("names");
                        JSONArray emails = obj.getJSONArray("emails");
                        JSONArray messages = obj.getJSONArray("messages");
                        JSONArray dates = obj.getJSONArray("dates");
                        JSONArray times = obj.getJSONArray("times");

                        for (int i = 0; i<names.length(); i++) {
                            messageList.add(new MessageListObject(names.getString(i), emails.getString(i), messages.getString(i), "", dates.getString(i), times.getString(i)));
                        }

                        list = (ListView) findViewById(R.id.listview);

                        final ListViewAdapterMessages adapter = new ListViewAdapterMessages(Messaging.this, getMessage());

                        list.setAdapter(adapter);

                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                MessageListObject send = (MessageListObject) adapterView.getItemAtPosition(i);
                                Intent intent = new Intent(Messaging.this, Conversation.class);
                                intent.putExtra("name", send.getName());
                                intent.putExtra("email", send.getEmail());
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
                }){

            //ID Token Sent
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ID", id);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private ArrayList<MessageListObject> getMessage(){
        ArrayList<MessageListObject> messages = new ArrayList<MessageListObject>();
        MessageListObject p;

        for(int i=0; i<messageList.size(); i++){
            p=new MessageListObject(messageList.get(i).getName(), messageList.get(i).getEmail(), messageList.get(i).getMessage(), messageList.get(i).getStatus(), messageList.get(i).getDate(), messageList.get(i).getTime());
            messages.add(p);
        }
        return messages;
    }

    public void ClickHome(View view) {
        Intent intent = new Intent(Messaging.this, Home.class);
        startActivity(intent);
    }
}