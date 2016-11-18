package com.ddmeng.githubclient.account;

import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.os.Bundle;

import com.ddmeng.githubclient.BuildConfig;
import com.ddmeng.githubclient.R;
import com.ddmeng.githubclient.app.GithubClientApplication;
import com.ddmeng.githubclient.data.models.AccessTokenResponse;
import com.ddmeng.githubclient.data.models.User;
import com.ddmeng.githubclient.remote.OAuthService;
import com.ddmeng.githubclient.remote.ServiceGenerator;
import com.ddmeng.githubclient.utils.LogUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AuthenticatorActivity extends AccountAuthenticatorActivity {

    private static final String TAG = AuthenticatorActivity.class.getSimpleName();

    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_AUTH_TOKEN_TYPE = "auth_token_type";
    public static final String PARAM_TOKEN = "token";

    @Inject
    AccountManager accountManager;
    @Inject
    AccountUtil accountUtil;

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
                oauthService.getAccessToken(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, code)
                        .compose(accountUtil.getUserInformation())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<User>() {
                            @Override
                            public void onCompleted() {
                                finish();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(User user) {
                                LogUtils.i(TAG, "get access token response: " + accountUtil.getAccessToken());
                                accountUtil.setCurrentUser(user);
                                resultBundle = new Bundle();
                                resultBundle.putString(AccountManager.KEY_ACCOUNT_TYPE, BuildConfig.ACCOUNT_TYPE);
                                resultBundle.putString(PARAM_TOKEN, accountUtil.getAccessToken());
                            }
                        });
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
