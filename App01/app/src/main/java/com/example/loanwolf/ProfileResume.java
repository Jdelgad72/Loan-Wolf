package com.example.loanwolf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class ProfileResume extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_resume);

        User user = SharedPrefManager.getInfo();
        final String id = user.getId();

        String name = getIntent().getStringExtra("USERNAME");

        final String email = getIntent().getStringExtra("EMAIL");

        final TextView txtView2 = findViewById(R.id.textView2);
        final TextView loanSentTxtView = findViewById(R.id.numLoanSent);
        final TextView amountSentTxtView = findViewById(R.id.amountSent);
        final TextView percentAcceptedTxtView = findViewById(R.id.percentAccepted);
        final TextView amountReceivedTxtView = findViewById(R.id.amountReceived);
        final TextView defaultRateTxtView = findViewById(R.id.defaultRate);
        final TextView numberPaymentTxtView = findViewById(R.id.numLoansTaken);

        txtView2.setText(name);

        // send Email and ID to server to communicate with database.
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/profileResume.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);
                    loanSentTxtView.setText(obj.getString("numOfLoanSent"));
                    amountSentTxtView.setText(obj.getString("amountSent"));
                    percentAcceptedTxtView.setText(obj.getString("percentLoanAccepted"));
                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        if(!obj.getString("defaultRate").equals("null")) {
                            defaultRateTxtView.setText(String.valueOf(Float.valueOf(obj.getString("defaultRate")) * 100));
                        }
                        numberPaymentTxtView.setText(obj.getString("numOfPayments"));
                        amountReceivedTxtView.setText(obj.getString("amountRecieved"));
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
            //ID Token Sent
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("searchEmail", email);
                params.put("requesterID", id);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void ClickViewProfile(View view) {
        redirectActivity(this, ViewProfile.class);
    }

    private static void redirectActivity(Activity activity, Class aClass){
        //Initialize intent
        Intent intent = new Intent(activity, aClass);
        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
    }
}