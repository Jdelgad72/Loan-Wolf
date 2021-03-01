package com.example.loanwolf;

import android.app.Activity;
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

public class ViewProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        final Button btnMessage = findViewById(R.id.messageBtn);
        Button btnLoan = findViewById(R.id.loanBtn);
        final Button btnReport = findViewById(R.id.reportBtn);
        final Button btnMakeReview = findViewById(R.id.makeReview);
        Button profileResumeBtn = findViewById(R.id.profileResume);

        final String name = getIntent().getStringExtra("USERNAME");
        final String email = getIntent().getStringExtra("EMAIL");
        User user = SharedPrefManager.getInfo();
        final String id = user.getId();
        String sharedEmail = user.getEmail();

        if(sharedEmail.equals(email)){
            btnMessage.setVisibility(View.INVISIBLE);
            btnLoan.setVisibility(View.INVISIBLE);
            btnReport.setVisibility(View.INVISIBLE);
            btnMakeReview.setVisibility(View.INVISIBLE);
        }

        TextView nameTxtView = findViewById(R.id.TextView2);
        final TextView dateTxtView = findViewById(R.id.dateMemberJoined);
        final TextView ratingTxtView = findViewById(R.id.ratingTxtView);
        final TextView reviewerNameTxtView = findViewById(R.id.reviewerName);
        final TextView recentRatingTxtView = findViewById(R.id.recentRatingTxtView);
        final TextView commentTxtView = findViewById(R.id.commentTxtView);
        final TextView defaultRateTxtView = findViewById(R.id.numberStat);
        final TextView numberPaymentTxtView = findViewById(R.id.numberLoans);

        nameTxtView.setText(name);

        // send Email and ID to server to communicate with database.
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/viewUser.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        if(!obj.getBoolean("pastCurrentInteraction")){
                            btnMessage.setVisibility(View.INVISIBLE);
                            btnReport.setVisibility(View.INVISIBLE);
                            btnMakeReview.setVisibility(View.INVISIBLE);
                        }
                        dateTxtView.setText(obj.getString("dateJoined"));
                        ratingTxtView.setText(obj.getString("rating"));
                        reviewerNameTxtView.setText(obj.getString("recentReviewer"));
                        recentRatingTxtView.setText(obj.getString("recentStarRating"));
                        commentTxtView.setText(obj.getString("recentComment"));
                        if(!obj.getString("defaultRate").equals("null")) {
                            defaultRateTxtView.setText(String.valueOf(Float.valueOf(obj.getString("defaultRate")) * 100));
                        }
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

        profileResumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewProfile.this, ProfileResume.class);
                intent.putExtra("USERNAME", name);
                intent.putExtra("EMAIL", email);
                startActivity(intent);
            }
        });
    }

    public void ClickSearch(View view) {
        redirectActivity(this, Search.class);
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