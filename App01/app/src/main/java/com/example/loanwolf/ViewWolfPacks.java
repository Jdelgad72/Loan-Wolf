package com.example.loanwolf;

<<<<<<< HEAD
=======
import android.content.Intent;
>>>>>>> 93f04d6782b2807a0927365cf3058ffa3f46803c
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;
=======
import java.util.ArrayList;

>>>>>>> 93f04d6782b2807a0927365cf3058ffa3f46803c

public class ViewWolfPacks extends AppCompatActivity {

    ListView list;
    ArrayList<WolfPackListObject> wolfPackList = new ArrayList<WolfPackListObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wolf_packs);


        // send String to server and find name matches from server
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/wolfPackList.php";
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

                        for (int i = 0; i < borrowerLender.length(); i++) {
                            wolfPackList.add(new WolfPackListObject(borrowerLender.getString(i), openLoanIDArray.getString(i), amountArray.getString(i), interestRateArray.getString(i), paymentTypeArray.getString(i), startDateArray.getString(i), numPaymentsArray.getString(i)));
                        }

                        list = (ListView) findViewById(R.id.listview);

                        final ListViewAdapterWolfPack adapter = new ListViewAdapterWolfPack(ViewWolfPacks.this, getOpenLoanID());

                        list.setAdapter(adapter);

                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                WolfPackListObject send = (WolfPackListObject) adapterView.getItemAtPosition(i);
                                Intent intent = new Intent(ViewWolfPacks.this, WolfPackAccept.class);
                                intent.putExtra("OPENLOANID", send.getOpenLoanID());
                                intent.putExtra("borrowerLender", send.getBorrowerLender());
                                intent.putExtra("amount", send.getAmount());
                                intent.putExtra("interestRate", send.getInterestRate());
                                intent.putExtra("paymentType", send.getPaymentType());
                                intent.putExtra("startDate", send.getStartDate());
                                intent.putExtra("numPayments", send.getNumPayments());
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

    private ArrayList<WolfPackListObject> getOpenLoanID(){
        ArrayList<WolfPackListObject> openLoan = new ArrayList<WolfPackListObject>();
        WolfPackListObject p;

        for(int i=0; i<wolfPackList.size(); i++){
            p=new WolfPackListObject(wolfPackList.get(i).getBorrowerLender(), wolfPackList.get(i).getOpenLoanID(), wolfPackList.get(i).getAmount(), wolfPackList.get(i).getInterestRate(), wolfPackList.get(i).getPaymentType(), wolfPackList.get(i).getStartDate(), wolfPackList.get(i).getNumPayments());
            openLoan.add(p);
        }
        return openLoan;
    }


}