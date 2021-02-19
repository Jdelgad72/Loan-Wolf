package com.example.loanwolf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    /*private EditText First;
    private EditText Last;
    private EditText DOB;
    private EditText Address;
    private EditText ZIP;
    private EditText State;
    private EditText Gender;
    private EditText Email;
    private Button Submit;
    private Button Return;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

    /*    First = (EditText) findViewById(R.id.editTextFirstName);
        Last = (EditText) findViewById(R.id.editTextLastName);
        DOB = (EditText) findViewById(R.id.editTextDOB);
        Address = (EditText) findViewById(R.id.editTextAddress);
        ZIP = (EditText) findViewById(R.id.editTextZIP);
        State = (EditText) findViewById(R.id.editTextState);
        Gender = (EditText) findViewById(R.id.editTextGender);
        Email = (EditText) findViewById(R.id.editTextEmail);
        Submit = (Button) findViewById(R.id.btnSubmit);
        Return = (Button) findViewById(R.id.btnReturn);

        //return to the profile page without changing anything
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfile.this, Profile.class));
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //in case a user tries to update a field and doesn't add a new one
                if(First.getText().toString().trim().length()==0 || Last.getText().toString().trim().length()==0)
                    || DOB.getText().toString().trim().length()==0 || Address.getText().toString().trim().length()==0)
                    || ZIP.getText().toString().trim().length()==0) || State.getText().toString().trim().length()==0)
                    || Gender.getText().toString().trim().length()==0) || Email.getText().toString().trim().length()==0)
                {
                    //the message saying that you need to fill out the fields
                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill out all text fields before saving", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                // send the updated info to server and validates
                String postUrl = "https://cgi.sice.indiana.edu/~team21/team-21/backend/WILLNEEDNEWPHP.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                //doesnt like that I have more than 4, will figure out later
                                User user = new User(
                                        userJson.getString("id"),
                                        userJson.getString("First"),
                                        userJson.getString("Last"),
                                        userJson.getString("DOB"),
                                        userJson.getString("Address"),
                                        userJson.getString("ZIP"),
                                        userJson.getString("State"),
                                        userJson.getString("Gender"),
                                        userJson.getString("email")
                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                // Checking if a new user. If so send to Paypal and if not send to home screen.
                                finish();
                                Intent i;
                                //got rid of the new user code
                                startActivity(i);
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
                    //ID Token Sent
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        //need to change the parameters that are being passed
                        params.put("idToken", idToken);
                        return params;
                    }
                };
            */}
        });
    }
}