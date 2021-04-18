package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class depositWithdraw extends AppCompatActivity {
    Button button0, button01, button02, button03, button04, button05, button06, button07, button08, button09, buttonDeposit, buttonWithdraw, buttonPeriod, buttonClear, buttonBack;
    TextView workspace, message;
    String add;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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

        final String balance = getIntent().getStringExtra("balance");
        /*Buttons to let user input/edit values they want to withdraw or deposit from 0 to 9 along with a decimal.*/
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
        /* decimal for cents' amount*/
        buttonPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = workspace.getText().toString();
                workspace.setText(add + ".");
            }
        });
        /*3 buttons below are for the user to clear or make a withdraw or deposit */
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workspace.setText("");
            }
        });
        buttonDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = workspace.getText().toString();
                Intent intent = new Intent(depositWithdraw.this, test01.class);
                intent.putExtra("amount", amount);
                intent.putExtra("balance", balance);
                startActivity(intent);
            }
        });
        buttonWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(depositWithdraw.this, test01.class));
            }
        });
    }

    public void onCancel(int requestCode) {
        // Use this to handle a canceled activity, if the given requestCode is important.
        // You may want to use this callback to hide loading indicators, and prepare your UI for input
    }

    public void backButton(View view) {
        buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(depositWithdraw.this, Profile.class));
            }
        });
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mBraintreeFragment = BraintreeFragment.newInstance(this, mAuthorization);
            // mBraintreeFragment is ready to use!
        } catch (InvalidArgumentException e) {
            // There was an issue with your authorization string.
        }
    }

    private void PaypalPaymentsMethod() {
        PayPalPayment payment = new PayPalPayment(new BigDecimal(50),"USD"
        , "Test Payment", PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        
    }
        private void depositWithdraw(final String type) {


        /* Connection from between app and the user's Paypal from a php transaction file.*/
            /* String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/transaction.php";
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
                        /*    Log.d("RESPONSE1", String.valueOf(error));
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


    } */
}