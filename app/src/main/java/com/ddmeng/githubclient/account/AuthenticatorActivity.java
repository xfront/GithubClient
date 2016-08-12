package com.ddmeng.githubclient.account;

import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.os.Bundle;

import com.ddmeng.githubclient.BuildConfig;
import com.ddmeng.githubclient.R;
import com.ddmeng.githubclient.app.GithubClientApplication;
import com.ddmeng.githubclient.models.AccessTokenResponse;
import com.ddmeng.githubclient.network.OAuthService;
import com.ddmeng.githubclient.network.ServiceGenerator;
import com.ddmeng.githubclient.utils.LogUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticatorActivity extends AccountAuthenticatorActivity {

    private static final String TAG = AuthenticatorActivity.class.getSimpleName();

    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_AUTH_TOKEN_TYPE = "auth_token_type";
    public static final String PARAM_TOKEN = "token";

    @Inject
    AccountManager accountManager;
    private AccountAuthenticatorResponse accountAuthenticatorResponse = null;
    private Bundle resultBundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((GithubClientApplication) getApplication()).getComponent().inject(this);
        ButterKnife.bind(this);
        showSignIn();
        accountAuthenticatorResponse = getIntent().getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);
    }

    private void showSignIn() {

        RequestAccessDialog dialog = RequestAccessDialog.show(BuildConfig.CLIENT_ID, BuildConfig.CALLBACK_URL);
        dialog.setCallback(new RequestAccessDialog.RequestAccessCallback() {
            @Override
            public void onCompleted(String code) {
                ServiceGenerator.changeApiBaseUrl(OAuthService.BASE_URL);
                OAuthService oauthService = ServiceGenerator.createService(OAuthService.class);
                Call<AccessTokenResponse> callToGetAccessToken = oauthService.getAccessToken(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, code);

                callToGetAccessToken.enqueue(new Callback<AccessTokenResponse>() {
                    @Override
                    public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
                        LogUtils.d(TAG, "response message: " + response.message());
                        AccessTokenResponse res = response.body();
                        LogUtils.i(TAG, "get access token response: " + res.getAccessToken());

                        resultBundle = new Bundle();

                        resultBundle.putString(AccountManager.KEY_ACCOUNT_TYPE, BuildConfig.ACCOUNT_TYPE);
                        resultBundle.putString(PARAM_TOKEN, res.getAccessToken());
                        finish();
                    }

                    @Override
                    public void onFailure(Call<AccessTokenResponse> call, Throwable t) {

                    }
                });
                ServiceGenerator.changeApiBaseUrl(ServiceGenerator.GITHUB_API_BASE_URL);
            }
        });
        dialog.show(getFragmentManager(), RequestAccessDialog.TAG);
    }

    @Override
    public void finish() {
        if (accountAuthenticatorResponse != null) {
            // send the result bundle back if set, otherwise send an error.
            if (resultBundle != null) {
                accountAuthenticatorResponse.onResult(resultBundle);
            } else {
                accountAuthenticatorResponse.onError(AccountManager.ERROR_CODE_CANCELED, "canceled");
            }
            accountAuthenticatorResponse = null;
        }
        super.finish();
    }
}
