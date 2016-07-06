package com.ddmeng.githubclient.account;

import android.accounts.AccountAuthenticatorActivity;
import android.os.Bundle;

import com.ddmeng.githubclient.R;

public class AuthenticatorActivity extends AccountAuthenticatorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
