package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

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

public class depositWithdraw extends AppCompatActivity {
    Button button0, button01, button02, button03, button04, button05, button06, button07, button08, button09, buttonDeposit, buttonWithdraw, buttonPeriod, buttonClear, buttonBack;
    TextView workspace, message;
    String add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_withdraw);

        button0 = findViewById(R.id.button0);
        button01 = findViewById(R.id.button01);
        button02 = findViewById(R.id.button02);
        button03 = findViewById(R.id.button03);
        button04 = findViewById(R.id.button04);
        button05 = findViewById(R.id.button05);
        button06 = findViewById(R.id.button06);
        button07 = findViewById(R.id.button07);
        button08 = findViewById(R.id.button08);
        button09 = findViewById(R.id.button09);
        buttonDeposit = findViewById(R.id.buttonDeposit);
        buttonWithdraw = findViewById(R.id.buttonWithdarw);
        buttonPeriod = findViewById(R.id.buttonPeriod);
        buttonClear = findViewById(R.id.buttonClear);
        message = findViewById(R.id.message);
        workspace = findViewById(R.id.workspace);
        /*Buttons to let user input/edit values they want to withdraw or deposit.*/
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "0");
            }
        });
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "1");
            }
        });
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "2");
            }
        });
        button03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "3");
            }
        });
        button04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "4");
            }
        });
        button05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "5");
            }
        });
        button06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "6");
            }
        });
        button07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "7");
            }
        });
        button08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "8");
            }
        });
        button09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + "9");
            }
        });
        buttonPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + ".");
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workspace.setText("");
            }
        });
        buttonDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                depositWithdraw("deposit");
            }
        });
        buttonWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                depositWithdraw("withdraw");
            }
        });
    /*Button for user to go back to the profile screen*/
        backButton();
    }
        private void backButton(){
        buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(depositWithdraw.this, Profile.class));
            }
        });

    }
        private void depositWithdraw(final String type) {


        /* Connection from between app and paypal*/
            String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/authenticate.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        //converting response to json object
                        JSONObject obj = new JSONObject(response);
                        Log.d("RESPONSE1", String.valueOf(obj));
                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

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
                            /*  Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();*/
                            Log.d("RESPONSE1", String.valueOf(error));
                        }
                    }) {
                //ID Token Sent
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("amount", workspace.getText().toString());
                    params.put("type", type);
                    return params;
                }
            };

            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
}