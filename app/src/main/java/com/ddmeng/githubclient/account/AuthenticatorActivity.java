package com.ddmeng.githubclient.account;

import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.widget.TextView;

import com.ddmeng.githubclient.BuildConfig;
import com.ddmeng.githubclient.R;
import com.ddmeng.githubclient.app.GithubClientApplication;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthenticatorActivity extends AccountAuthenticatorActivity {

    private static final String TAG = AuthenticatorActivity.class.getSimpleName();

    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_AUTH_TOKEN_TYPE = "auth_token_type";


    @BindView(R.id.input_username)
    TextView username;
    @BindView(R.id.input_password)
    TextView password;

    @Inject
    AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((GithubClientApplication) getApplication()).getComponent().inject(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_in)
    void submitSignIn() {

        RequestAccessDialog dialog = RequestAccessDialog.show(BuildConfig.CLIENT_ID, BuildConfig.CALLBACK_URL);
        dialog.show(getFragmentManager(), RequestAccessDialog.TAG);
    }
}
