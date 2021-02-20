package com.example.loanwolf;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Home extends AppCompatActivity {

    //Initialize variables
    DrawerLayout drawerLayout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView titleTextView = findViewById(R.id.title);
        TextView nameTextView = findViewById(R.id.drawerName);
        TextView emailTextView = findViewById(R.id.drawerEmail);

        //Sets textviews specific to user details
        titleTextView.setText("Home");
        User user = SharedPrefManager.getInfo();
        emailTextView.setText(user.getEmail());
        nameTextView.setText(user.getFirstName() + ' ' + user.getLastName());

        //Assign Variable
        drawerLayout = findViewById(R.id.drawer_layout);

    }

    public void ClickMenu(View view){
        //Open drawer
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout){
        //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        //Close Drawer
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout){
        //Close drawer layout
        //Check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //When drawer is open close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
/*
    public void ClickHome(View view){
        //Recreate activity
        recreate();
    }
*/

    public void ClickSearch(View view){
        redirectActivity(this, Search.class);
    }

    public void ClickViewPayments(View view){
        //redirect activity to dashboard
        redirectActivity(this, Home.class);
    }

    public void ClickViewPortfolio(View view){
        //redirect activity to about us
        redirectActivity(this, Home.class);
    }

    public void ClickLogout(View view){
        //Logout of App
        SharedPrefManager.getInstance(this).logout();
    }

    private static void redirectActivity(Activity activity, Class aClass){
        //Initialize intent
        Intent intent = new Intent(activity, aClass);
        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }

    public void Buttonclick(View view) {
        redirectActivity(this, test01.class);

    }

    public void editprofile(View view) {
        startActivity(new Intent(Home.this, EditProfile.class));
    }
}