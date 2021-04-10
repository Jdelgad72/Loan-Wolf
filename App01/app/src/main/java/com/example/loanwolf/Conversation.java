package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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

public class Conversation extends AppCompatActivity {

    // Declare Variables
    ListView list;
    ArrayList<MessageListObject> messageList = new ArrayList<MessageListObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        User user = SharedPrefManager.getInfo();
        final String id = user.getId();
        final String email = getIntent().getStringExtra("email");
        final String name = getIntent().getStringExtra("name");

        TextView nameTxtView = findViewById(R.id.TextView1);
        nameTxtView.setText(name);

        // send String to server and find name matches from server
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/conversation.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONArray messages = obj.getJSONArray("messages");
                        JSONArray dates = obj.getJSONArray("dates");
                        JSONArray times = obj.getJSONArray("times");
                        JSONArray statuses = obj.getJSONArray("statuses");


                        for (int i = 0; i<messages.length(); i++) {
                            messageList.add(new MessageListObject("", "", messages.getString(i), statuses.getString(i), dates.getString(i), times.getString(i)));
                        }

                        list = (ListView) findViewById(R.id.listview);

                        final ListViewAdapterMessages adapter = new ListViewAdapterMessages(Conversation.this, getMessage());

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
                }){

            //ID Token Sent
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ID", id);
                params.put("email", email);
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

    public void ClickMessages(View view) {
        Intent intent = new Intent(Conversation.this, Messaging.class);
        startActivity(intent);
    }

    public void sendMessage(View view) {
        User user = SharedPrefManager.getInfo();
        final String id = user.getId();
        final String email = getIntent().getStringExtra("email");
        final String name = getIntent().getStringExtra("name");
        EditText messageEditText = findViewById(R.id.message);
        final String message = messageEditText.getText().toString();

        // send String to server and find name matches from server
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/sendMessage.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        finish();
                        startActivity(getIntent().putExtra("name", name).putExtra("email", email));

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
                params.put("email", email);
                params.put("message", message);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}