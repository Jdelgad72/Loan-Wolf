package com.example.loanwolf;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;

public class Paypal extends AppCompatActivity {
    private static final String TAG = "paymentExample";
    public static final String PAYPAL_KEY = "";
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
    private static PayPalConfiguration config;
    PayPalPayment thingsToBuy;
    Button order;



}