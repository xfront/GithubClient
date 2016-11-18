package com.ddmeng.githubclient.account;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;

import com.ddmeng.githubclient.BuildConfig;
import com.ddmeng.githubclient.data.models.AccessTokenResponse;
import com.ddmeng.githubclient.data.models.User;
import com.ddmeng.githubclient.remote.GitHubService;
import com.ddmeng.githubclient.remote.ServiceGenerator;
import com.ddmeng.githubclient.utils.LogUtils;
import com.ddmeng.githubclient.utils.PreferencesUtils;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

@Singleton
public class AccountUtil {
    private static final String TAG = AccountUtil.class.getSimpleName();

    private AccountManager accountManager;
    private User currentUser;

    @Inject
    public AccountUtil(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void signIn(Activity activity) {
        accountManager.addAccount(BuildConfig.ACCOUNT_TYPE, AuthenticatorActivity.PARAM_AUTH_TOKEN_TYPE,
                null, null, activity, new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                        LogUtils.i(TAG, "accountManager callback: " + accountManagerFuture);
                        try {
                            Bundle result = accountManagerFuture.getResult();
                            String token = result.getString(AuthenticatorActivity.PARAM_TOKEN);
                            saveAccessToken(token);
                            getUserInformation();
                        } catch (OperationCanceledException | IOException | AuthenticatorException e) {
                            e.printStackTrace();
                        }
                    }
                }, null);
    }

    public String getAccessToken() {
        return PreferencesUtils.getAccessToken();
    }

    public void saveAccessToken(String accessToken) {
        PreferencesUtils.saveAccessToken(accessToken);
    }

    public boolean isSignedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Observable.Transformer<AccessTokenResponse, User> getUserInformation() {
        return new Observable.Transformer<AccessTokenResponse, User>() {
            @Override
            public Observable<User> call(Observable<AccessTokenResponse> accessTokenResponseObservable) {
                return accessTokenResponseObservable.map(new Func1<AccessTokenResponse, String>() {
                    @Override
                    public String call(AccessTokenResponse accessTokenResponse) {
                        String accessToken = accessTokenResponse.getAccessToken();
                        saveAccessToken(accessToken);
                        return accessToken;
                    }
                }).flatMap(new Func1<String, Observable<User>>() {
                    @Override
                    public Observable<User> call(String accessToken) {
                        ServiceGenerator.changeApiBaseUrl(ServiceGenerator.GITHUB_API_BASE_URL);
                        GitHubService gitHubService = ServiceGenerator.createService(GitHubService.class, accessToken);
                        return gitHubService.getCurrentUser();
                    }
                });
            }
        };
    }
}
