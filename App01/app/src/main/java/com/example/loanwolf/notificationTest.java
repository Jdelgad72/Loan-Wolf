package com.example.loanwolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class notificationTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);
    }
    public void buttonDaily(View view) {
        startActivity(new Intent(notificationTest.this, ViewPaymentSchedule.class));
    }
    public void buttonWeekly(View view) {
        startActivity(new Intent(notificationTest.this, ViewWeeklySchedule.class));
    }
    public void buttonMonthly(View view) {
        startActivity(new Intent(notificationTest.this, ViewMonthlySchedule.class));
    }
}