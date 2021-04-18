package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    /* setting buttons and textviews*/
    Button buttonDepositWithdraw, buttonHome, buttonProfile, buttonMessage;
    TextView account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /* button for switching to the transaction screen */
        switchButton();

        account = findViewById(R.id.textView);
        final User user = SharedPrefManager.getInfo();

        // send String to server and retrieves balance in loanwolf account from server
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/loanWolfAccount.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        String amount = obj.getString("amount");
                        account.setText(amount);
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
                }) {
            //updated user information Sent
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", user.getId());
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void switchButton(){

        buttonDepositWithdraw = findViewById(R.id.buttonDepositWithdraw);

        buttonDepositWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String balance = account.getText().toString();
                Intent intent = new Intent(Profile.this, depositWithdraw.class);
                intent.putExtra("balance", balance);
                startActivity(intent);
            }
        });
        /* buttons for getting to to the home and message screen (NOT FINISHED) */
        /*

        switchButton02();
     private void switchButton02(){
         buttonHome = findViewById(R.id.buttonHome);

         buttonHome.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Profile.this, Home.class));
             }
         });

         switchButton03();
     private void switchButton02(){
         buttonMessage = findViewById(R.id.buttonMessage);

         buttonMessage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Profile.this, Message.class));
             }
         });

         */


    }
}