package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
        final String name = getIntent().getStringExtra("USERNAME");
        final String email = getIntent().getStringExtra("EMAIL");

        TextView txtView2 = findViewById(R.id.TextView2);
        final TextView loanSentTxtView = findViewById(R.id.numLoanSent);
        final TextView amountSentTxtView = findViewById(R.id.amountSent);
        final TextView percentAcceptedTxtView = findViewById(R.id.percentAccepted);
        final TextView amountReceivedTxtView = findViewById(R.id.amountReceived);
        final TextView defaultRateTxtView = findViewById(R.id.defaultRate);
        final TextView numberPaymentTxtView = findViewById(R.id.numLoansTaken);
        ImageView backBtn = findViewById(R.id.back_btn);

        txtView2.setText(name);

        // send Email and ID to server to communicate with database.
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/profileResume.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //Value handling to check values are not null. If null then make it 0.
                        if(obj.getString("amountSent").equals("null")){
                            amountSentTxtView.setText("0");
                        }else{
                            amountSentTxtView.setText(obj.getString("amountSent"));
                        }

                        if(obj.getString("percentLoanAccepted").equals("null")){
                            percentAcceptedTxtView.setText("0");
                        }else{
                            percentAcceptedTxtView.setText(String.valueOf(Float.valueOf(obj.getString("percentLoanAccepted")) * 100));
                        }

                        if(!obj.getString("defaultRate").equals("null")) {
                            defaultRateTxtView.setText(String.valueOf(Float.valueOf(obj.getString("defaultRate")) * 100));
                        }else{
                            defaultRateTxtView.setText("0");
                        }

                        if(obj.getString("amountRecieved").equals("null")){
                            amountReceivedTxtView.setText("0");
                        }else {
                            amountReceivedTxtView.setText(obj.getString("amountRecieved"));
                        }

                        loanSentTxtView.setText(obj.getString("numOfLoanSent"));
                        numberPaymentTxtView.setText(obj.getString("numOfPayments"));
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

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileResume.this, ViewProfile.class);
                intent.putExtra("USERNAME", name);
                intent.putExtra("EMAIL", email);
                startActivity(intent);
            }
        });
    }
}