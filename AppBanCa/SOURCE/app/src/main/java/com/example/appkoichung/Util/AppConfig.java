package com.example.appkoichung.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class AppConfig {
    public static String SHARED_PREFERENCES_NAME = "KOI_CHUNG";
    public static String USER_ID ="userID";
    public static String STATUS="status";


    public static SharedPreferences.Editor getEditor(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return editor;
    }
    public static SharedPreferences getShared(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
        return sharedPreferences;
    }
    //
    public static void setUserID(Context context, int userID) {
        getEditor(context).putInt(USER_ID, userID).apply();
    }
    //
    public static int getUserID(Context context) {
        return getShared(context).getInt(USER_ID, -1);
    }
}
