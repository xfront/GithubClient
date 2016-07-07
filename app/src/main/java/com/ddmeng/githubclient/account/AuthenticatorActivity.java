package com.ddmeng.githubclient.account;

import android.accounts.AccountAuthenticatorActivity;
import android.os.Bundle;

import com.ddmeng.githubclient.R;

public class AuthenticatorActivity extends AccountAuthenticatorActivity {

    /** The Intent extra to store username. */
    public static final String PARAM_USERNAME = "username";
    /** The Intent extra to store username. */
    public static final String PARAM_AUTH_TOKEN_TYPE = "auth_token_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
