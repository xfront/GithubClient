package com.ddmeng.githubclient.injection;

import com.ddmeng.githubclient.account.AccountUtil;
import com.ddmeng.githubclient.account.AuthenticatorActivity;
import com.ddmeng.githubclient.app.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {

    AccountUtil getAccountUtil();

    void inject(MainActivity activity);

    void inject(AuthenticatorActivity activity);
}
