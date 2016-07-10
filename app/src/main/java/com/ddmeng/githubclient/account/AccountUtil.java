package com.ddmeng.githubclient.account;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.Bundle;

import com.ddmeng.githubclient.BuildConfig;
import com.ddmeng.githubclient.utils.LogUtils;

import javax.inject.Inject;

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

                    }
                }, null);
    }
}
