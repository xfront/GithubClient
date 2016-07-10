package com.ddmeng.githubclient.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module(includes = {AndroidServiceModule.class})
public class MainModule {
    private Context context;

    public MainModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }
}
