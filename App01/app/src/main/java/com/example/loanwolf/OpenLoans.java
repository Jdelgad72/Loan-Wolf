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

import androidx.appcompat.app.AppCompatActivity;

public class OpenLoans extends AppCompatActivity {

    // Declare Variables
    ListView list;
    ArrayList<OpenLoanListObject> openLoanList = new ArrayList<OpenLoanListObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_loans);

        // send String to server and find name matches from server
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/openLoanList.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONArray borrowerLender = obj.getJSONArray("borrowerLender");
                        JSONArray openLoanIDArray = obj.getJSONArray("openLoanID");
                        JSONArray amountArray = obj.getJSONArray("amount");
                        JSONArray interestRateArray = obj.getJSONArray("interestRate");
                        JSONArray paymentTypeArray = obj.getJSONArray("paymentType");//weekly, monthly, or daily.
                        JSONArray startDateArray = obj.getJSONArray("startDate");
                        JSONArray numPaymentsArray = obj.getJSONArray("numPayments");

                        for (int i = 0; i<borrowerLender.length(); i++) {
                            openLoanList.add(new OpenLoanListObject(borrowerLender.getString(i), openLoanIDArray.getString(i), amountArray.getString(i), interestRateArray.getString(i), paymentTypeArray.getString(i), startDateArray.getString(i), numPaymentsArray.getString(i)));
                        }

                        list = (ListView) findViewById(R.id.listview);

                       // final ListViewAdapterUserNames adapter = new ListViewAdapterUserNames(OpenLoans.this, getOpenLoanID());

                       // list.setAdapter(adapter);

                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                OpenLoanListObject send = (OpenLoanListObject) adapterView.getItemAtPosition(i);
                                Intent intent = new Intent(OpenLoans.this, ViewProfile.class);
                                intent.putExtra("OPENLOANID", send.getOpenLoanID());
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

    private ArrayList<OpenLoanListObject> getOpenLoanID(){
        ArrayList<OpenLoanListObject> openLoan = new ArrayList<OpenLoanListObject>();
        UserNames p;

        for(int i=0; i<openLoanList.size(); i++){
         //   p=new OpenLoanListObject(openLoanList.get(i).getUserName(), openLoanList.get(i).getEmail());
          //  openLoanList.add(p);
        }
        return openLoan;
    }

    public void CreateOpenLoan(View view) {
        Intent intent = new Intent(OpenLoans.this, Home.class);
        startActivity(intent);
    }

    public void ClickHome(View view) {
        Intent intent = new Intent(OpenLoans.this, Home.class);
        startActivity(intent);
    }
}