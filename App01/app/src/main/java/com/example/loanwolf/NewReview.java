package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewReview extends AppCompatActivity {

    private EditText Review;
    private EditText Rating;
    private Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review);

        //Get widgets from XML
        Review = (EditText) findViewById(R.id.editReview);
        Rating = (EditText) findViewById(R.id.editRating);
        Submit = (Button) findViewById(R.id.btnSubmit);

        //get ID
        User user = SharedPrefManager.getInfo();
        final String id = user.getId();
        final String email = user.getEmail();

        //Get ID of users page we're on
        final String otherID = getIntent().getStringExtra("USERNAME");
        final String otherEmail = getIntent().getStringExtra("EMAIL");

        //Code so the EditText can scroll
        Review.setScroller(new Scroller(getApplicationContext()));
        Review.setVerticalScrollBarEnabled(true);
        Review.setMinLines(1);
        Review.setMaxLines(6);

        //Code for a submit button
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Review.getText().toString().trim().length() == 0) {
                    //the message saying that you need to fill out the fields
                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill out a Review before saving", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                if (Rating.getText().toString().trim().length() <= 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Rating must be greater than 0", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                if (Rating.getText().toString().trim().length() >= 5) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Rating must be less than or equal to 5", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                // send the updated info to server and validates
                String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/newReview.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                Log.d("RESPONSE1", obj.getString("message"));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                Log.d("RESPONSE2", obj.getString("message"));
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
                    //updated review information Sent
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("id", id);
                        params.put("review", Review.getText().toString().trim());
                        params.put("rating", Rating.getText().toString().trim());
                        params.put("otherID", otherID);
                        params.put("otherEmail", otherEmail);
                        return params;
                    }
                };
                VolleySingleton.getInstance(NewReview.this).addToRequestQueue(stringRequest);
            }//end public void for onclick listener
        });//end submit onclick listener
    }//end on create
}//end main