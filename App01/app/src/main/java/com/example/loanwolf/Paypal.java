package com.example.loanwolf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalOAuthScopes;
import com.paypal.android.sdk.payments.PayPalProfileSharingActivity;
import com.paypal.android.sdk.payments.PayPalService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Paypal extends AppCompatActivity {

    private static final int REQUEST_CODE_PROFILE_SHARING = 7171;
    private static PayPalConfiguration config = new PayPalConfiguration()

            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)

            .clientId("AYXBDSgbIoe0KNpb_YnIOOYhmqnEOoGQfo2aj1TNDq_0BSK3CofUdoPJXxNssjvOz5Md-mJq1pkcx30L")

            // Minimally, you will need to set three merchant information properties.
            // These should be the same values that you provided to PayPal when you registered your app.
            .merchantName("LoanWolf");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        startService(intent);
        Button btnSharingProfile = findViewById(R.id.profileSharingBtn);
        btnSharingProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Paypal.this, PayPalProfileSharingActivity.class);

                // send the same configuration for restart resiliency
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

                intent.putExtra(PayPalProfileSharingActivity.EXTRA_REQUESTED_SCOPES, getOauthScopes());

                startActivityForResult(intent, REQUEST_CODE_PROFILE_SHARING);
            }
        });
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }


    private PayPalOAuthScopes getOauthScopes() {
        /* create the set of required scopes
         * Note: see https://developer.paypal.com/docs/integration/direct/identity/attributes/ for mapping between the
         * attributes you select for this app in the PayPal developer portal and the scopes required here.
         */
        Set<String> scopes = new HashSet<String>(
                Arrays.asList(PayPalOAuthScopes.PAYPAL_SCOPE_EMAIL, PayPalOAuthScopes.PAYPAL_SCOPE_ADDRESS) );
        return new PayPalOAuthScopes(scopes);
    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            PayPalAuthorization auth = data
                    .getParcelableExtra(PayPalProfileSharingActivity.EXTRA_RESULT_AUTHORIZATION);
            if (auth != null) {
                String authorization_code = auth.getAuthorizationCode();

                sendAuthorizationToServer(auth);

            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("ProfileSharingExample", "The user canceled.");
        } else if (resultCode == PayPalProfileSharingActivity.RESULT_EXTRAS_INVALID) {
            Log.i("ProfileSharingExample",
                    "Probably the attempt to previously start the PayPalService had an invalid PayPalConfiguration. Please see the docs.");
        }
    }
    private void sendAuthorizationToServer(PayPalAuthorization authorization) {

        // TODO:
        // Send the authorization response to your server, where it can exchange the authorization code
        // for OAuth access and refresh tokens.
        //
        // Your server must then store these tokens, so that your server code can use it
        // for getting user profile data in the future.

    }
}