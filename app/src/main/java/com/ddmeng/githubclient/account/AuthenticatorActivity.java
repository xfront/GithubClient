package com.ddmeng.githubclient.account;

import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.widget.TextView;

import com.ddmeng.githubclient.BuildConfig;
import com.ddmeng.githubclient.R;
import com.ddmeng.githubclient.app.GithubClientApplication;
import com.ddmeng.githubclient.model.AccessTokenResponse;
import com.ddmeng.githubclient.network.OAuthService;
import com.ddmeng.githubclient.network.ServiceGenerator;
import com.ddmeng.githubclient.utils.LogUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        dialog.setCallback(new RequestAccessDialog.RequestAccessCallback() {
            @Override
            public void onCompleted(String code) {
                ServiceGenerator.changeApiBaseUrl(OAuthService.BASE_URL);
                OAuthService oauthService = ServiceGenerator.createService(OAuthService.class);
                Call<AccessTokenResponse> callToGetAccessToken = oauthService.getAccessToken(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, code);

                callToGetAccessToken.enqueue(new Callback<AccessTokenResponse>() {
                    @Override
                    public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
                        AccessTokenResponse res = response.body();
                        LogUtils.i(TAG, "get access token response: " + res.getAccessToken());
                    }

                    @Override
                    public void onFailure(Call<AccessTokenResponse> call, Throwable t) {

                    }
                });
            }
        });
        dialog.show(getFragmentManager(), RequestAccessDialog.TAG);
    }
}
