package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class notificationTest extends AppCompatActivity {

    //Initialize variables
    DrawerLayout drawerLayout;
    ListView list;
    ArrayList<PaymentScheduleListObject> paymentScheduleArrayList = new ArrayList<PaymentScheduleListObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);

        final User user = SharedPrefManager.getInfo();
/*
        TextView titleTextView = findViewById(R.id.title);
        TextView nameTextView = findViewById(R.id.drawerName);
        TextView emailTextView = findViewById(R.id.drawerEmail);
*/
/*
        //Sets textviews specific to user details
        final User user = SharedPrefManager.getInfo();
        emailTextView.setText(user.getEmail());
        nameTextView.setText(user.getFirstName() + ' ' + user.getLastName());
*/
        //Assign Variable
        drawerLayout = findViewById(R.id.drawer_layout);

        // Sends to backend to search for top 5 leaderboards.
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/paymentScheduleList.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        JSONArray date_array = obj.getJSONArray("date");
                        JSONArray amount_array = obj.getJSONArray("amount");

                        for (int i = 0; i < date_array.length(); i++) {
                            paymentScheduleArrayList.add(new PaymentScheduleListObject(date_array.getString(i), amount_array.getString(i)));
                        }

                        list = (ListView) findViewById(R.id.listview);

                        final ListViewAdapterPaymentSchedule adapter = new ListViewAdapterPaymentSchedule(notificationTest.this, getPayment());

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
                })
        {
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

        private ArrayList<PaymentScheduleListObject> getPayment(){
            ArrayList<PaymentScheduleListObject> payment = new ArrayList<PaymentScheduleListObject>();
            PaymentScheduleListObject p;

            for(int i=0; i<paymentScheduleArrayList.size(); i++){
                p=new PaymentScheduleListObject(paymentScheduleArrayList.get(i).getstartDate(), paymentScheduleArrayList.get(i).getamount());
                payment.add(p);
            }
            return payment;
        }
    }