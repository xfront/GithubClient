package com.ddmeng.githubclient.injection;

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
