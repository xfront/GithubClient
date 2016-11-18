package com.ddmeng.githubclient.account;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;

import com.ddmeng.githubclient.BuildConfig;
import com.ddmeng.githubclient.data.models.User;
import com.ddmeng.githubclient.remote.GitHubService;
import com.ddmeng.githubclient.remote.ServiceGenerator;
import com.ddmeng.githubclient.utils.LogUtils;
import com.ddmeng.githubclient.utils.PreferencesUtils;

import java.io.IOException;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AccountUtil {
    private static final String TAG = AccountUtil.class.getSimpleName();

    private AccountManager accountManager;

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

    private void getUserInformation() {

        GitHubService gitHubService = ServiceGenerator.createService(GitHubService.class, getAccessToken());
        LogUtils.i(TAG, "token: " + getAccessToken());
        gitHubService.getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        LogUtils.i(TAG, "get current user: " + user.getName());
                    }
                });

    }
}
