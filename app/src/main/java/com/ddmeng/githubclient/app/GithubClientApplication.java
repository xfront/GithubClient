package com.ddmeng.githubclient.app;

import android.app.Application;

import com.ddmeng.githubclient.component.DaggerMainComponent;
import com.ddmeng.githubclient.component.MainComponent;
import com.ddmeng.githubclient.module.MainModule;
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
