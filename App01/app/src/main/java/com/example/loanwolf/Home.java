package com.example.loanwolf;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Home extends AppCompatActivity {

    //Initialize variables
    DrawerLayout drawerLayout;
    ListView list;
    ArrayList<LeaderboardListObject> leaderboardArrayList = new ArrayList<LeaderboardListObject>();


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
        final User user = SharedPrefManager.getInfo();
        emailTextView.setText(user.getEmail());
        nameTextView.setText(user.getFirstName() + ' ' + user.getLastName());

        //Assign Variable
        drawerLayout = findViewById(R.id.drawer_layout);

        // Sends to backend to search for top 5 leaderboards.
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/leaderboard.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONArray name_array = obj.getJSONArray("names");
                        JSONArray email_array = obj.getJSONArray("emails");
                        JSONArray ratings_array = obj.getJSONArray("ratings");

                        for (int i = 0; i<name_array.length(); i++) {
                            leaderboardArrayList.add(new LeaderboardListObject(String.valueOf(i+1), name_array.getString(i), email_array.getString(i), ratings_array.getString(i)));
                        }

                        list = (ListView) findViewById(R.id.leaderboardsList);

                        final ListViewAdapterLeaderboard adapter = new ListViewAdapterLeaderboard(Home.this, getLeaderboard());

                        list.setAdapter(adapter);

                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                LeaderboardListObject send = (LeaderboardListObject) adapterView.getItemAtPosition(i);
                                Intent intent = new Intent(Home.this, ViewProfile.class);
                                intent.putExtra("USERNAME", send.getname());
                                intent.putExtra("EMAIL", send.getemail());
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

        // send String to server and find name matches from server
        String postUrl2 = "https://cgi.sice.indiana.edu/~team21/team-21/backend/notification.php";
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, postUrl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);

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
                        Log.d("VolleyError", String.valueOf(error));
                    }
                }) {
            //updated user information Sent
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ID", user.getId());
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest2);
    }

    private ArrayList<LeaderboardListObject> getLeaderboard(){
        ArrayList<LeaderboardListObject> leaderboard = new ArrayList<LeaderboardListObject>();
        LeaderboardListObject p;

        for(int i=0; i<leaderboardArrayList.size(); i++){
            p=new LeaderboardListObject(leaderboardArrayList.get(i).getrank(), leaderboardArrayList.get(i).getname(), leaderboardArrayList.get(i).getemail(), leaderboardArrayList.get(i).getstarRating());
            leaderboard.add(p);
        }
        return leaderboard;
    }

    private static void redirectActivity(Activity activity, Class aClass){
        //Initialize intent
        Intent intent = new Intent(activity, aClass);
        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
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

    public void ClickSearch(View view){
        redirectActivity(this, Search.class);
    }

    public void ClickMessages(View view){
        redirectActivity(this, Messaging.class);
    }

    public void ClickViewPayments(View view){
        //redirect activity to dashboard
        redirectActivity(this, ViewPaymentSchedule.class);
    }

    public void ClickViewPortfolio(View view){
        //redirect activity to about us
        redirectActivity(this, Leaderboard.class);
    }

    public void ClickLogout(View view){
        //Logout of App
        SharedPrefManager.getInstance(this).logout();
    }

    @Override
    protected void onPause(){
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }

    public void Buttonclick(View view) {
        redirectActivity(this, Profile.class);
    }

    public void WolfPack(View view) {
        startActivity(new Intent(Home.this, WolfPack.class));
    }

    public void OpenLoans(View view) {
        startActivity(new Intent(Home.this, OpenLoans.class));
    }

    public void editprofile(View view) {
        startActivity(new Intent(Home.this, EditProfile.class));
    }

    public void LeaderboardText(View view) {
        startActivity(new Intent(Home.this, Leaderboard.class));
    }
}
