package com.ddmeng.githubclient.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils {

    private static Context context;
    private static final String USER_INFO_PREFERENCES = "user_info_preferences";
    private static final String ACCESS_TOKEN = "access_token";

    public static void setContext(Context context) {
        PreferencesUtils.context = context;
    }

    public static SharedPreferences getUserInfoPreferences() {
        return context.getSharedPreferences(USER_INFO_PREFERENCES, Context.MODE_PRIVATE);
    }


    public static String getAccessToken() {
        return getUserInfoPreferences().getString(ACCESS_TOKEN, null);
    }

    public static void saveAccessToken(String token) {
        getUserInfoPreferences().edit().putString(ACCESS_TOKEN, token).commit();
    }
}
