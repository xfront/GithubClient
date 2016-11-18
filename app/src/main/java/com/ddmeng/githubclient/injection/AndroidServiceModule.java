package com.ddmeng.githubclient.injection;

import android.accounts.AccountManager;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AndroidServiceModule {

    @Provides
    @Singleton
    AccountManager provideAccountManager(Context context) {
        return AccountManager.get(context);
    }
}
