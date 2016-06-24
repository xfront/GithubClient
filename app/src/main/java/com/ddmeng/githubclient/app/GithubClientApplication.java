package com.ddmeng.githubclient.app;

import android.app.Application;

import com.ddmeng.githubclient.component.DaggerMainComponent;
import com.ddmeng.githubclient.component.MainComponent;

public class GithubClientApplication extends Application {

    private MainComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMainComponent.builder().build();
    }

    public MainComponent getComponent() {
        return component;
    }
}
