package com.ddmeng.githubclient.component;

import com.ddmeng.githubclient.account.AuthenticatorActivity;
import com.ddmeng.githubclient.app.MainActivity;
import com.ddmeng.githubclient.module.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity activity);

    void inject(AuthenticatorActivity activity);
}
