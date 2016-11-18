package com.ddmeng.githubclient.app;

import android.app.Application;

import com.ddmeng.githubclient.injection.DaggerMainComponent;
import com.ddmeng.githubclient.injection.MainComponent;
import com.ddmeng.githubclient.injection.MainModule;
import com.ddmeng.githubclient.utils.PreferencesUtils;

public class GithubClientApplication extends Application {

    private MainComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();

        PreferencesUtils.setContext(this);
    }

    public MainComponent getComponent() {
        return component;
    }
}
