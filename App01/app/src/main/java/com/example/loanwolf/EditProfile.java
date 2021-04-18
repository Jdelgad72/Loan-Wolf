package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class EditProfile extends AppCompatActivity {

    private EditText First;
    private EditText Last;
    private EditText DOB;
    private EditText Address;
    private EditText ZIP;
    private EditText State;
    private EditText Gender;
    private Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        First = (EditText) findViewById(R.id.editTextFirstName);
        Last = (EditText) findViewById(R.id.editTextLastName);
        DOB = (EditText) findViewById(R.id.editTextDOB);
        Address = (EditText) findViewById(R.id.editTextAddress);
        ZIP = (EditText) findViewById(R.id.editTextZIP);
        State = (EditText) findViewById(R.id.editTextState);
        Gender = (EditText) findViewById(R.id.editTextGender);
        //Email = (EditText) findViewById(R.id.editTextEmail);
        Submit = (Button) findViewById(R.id.btnSubmit);

        //get ID
        User user = SharedPrefManager.getInfo();
        final String id = user.getId();
        final String email = user.getEmail();

        // retrieves the info of the user
        String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/editProfile.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        Log.d("RESPONSE1", obj.getString("message"));

                        String firstResponse = obj.getString("first");
                        String lastResponse = obj.getString("last");
                        String dobResponse = obj.getString("dob");
                        String addressResponse = obj.getString("address");
                        String zipResponse = obj.getString("zip");
                        String stateResponse = obj.getString("state");
                        String genderResponse = obj.getString("gender");

                        First.setText(firstResponse);
                        Last.setText(lastResponse);
                        DOB.setText(dobResponse);
                        Address.setText(addressResponse);
                        ZIP.setText(zipResponse);
                        State.setText(stateResponse);
                        Gender.setText(genderResponse);
                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        Log.d("RESPONSE2", obj.getString("message"));
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
                params.put("id", id);
                return params;
            }
        };
        VolleySingleton.getInstance(EditProfile.this).addToRequestQueue(stringRequest);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //in case a user tries to update a field and doesn't add a new one
                if(First.getText().toString().trim().length()==0 || Last.getText().toString().trim().length()==0
                        || DOB.getText().toString().trim().length()==0  || Address.getText().toString().trim().length()==0
                        || ZIP.getText().toString().trim().length()==0  || State.getText().toString().trim().length()==0
                        || Gender.getText().toString().trim().length()==0){
                    //the message saying that you need to fill out the fields
                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill out all text fields before saving", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                User user = new User(
                        id,
                        email,
                        First.getText().toString().trim(),
                        Last.getText().toString().trim()
                );

                //storing the user in shared preferences
                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                // send the updated info to server and validates
                String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/updateUser.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                Log.d("RESPONSE1", obj.getString("message"));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                Log.d("RESPONSE2", obj.getString("message"));
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
                        params.put("id", id);
                        params.put("first", First.getText().toString().trim());
                        params.put("last", Last.getText().toString().trim());
                        params.put("dob", DOB.getText().toString().trim());
                        params.put("address", Address.getText().toString().trim());
                        params.put("zip", ZIP.getText().toString().trim());
                        params.put("state", State.getText().toString().trim());
                        params.put("gender", Gender.getText().toString().trim());
                        return params;
                    }
                };
                VolleySingleton.getInstance(EditProfile.this).addToRequestQueue(stringRequest);

            }
        });
    }
}