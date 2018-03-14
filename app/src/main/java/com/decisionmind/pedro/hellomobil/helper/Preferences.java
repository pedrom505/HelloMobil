package com.decisionmind.pedro.hellomobil.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by pedro on 13/03/18.
 */

public class Preferences {

    private Context context;
    private SharedPreferences preferences;
    private String fileName = "HelloMobil.preferences";
    private int MODE = 0;
    private SharedPreferences.Editor editor;

    private String KEY_NAME = "name";
    private  String KEY_PHONE = "phone";
    private String KEY_TOKEN = "token";

    public Preferences(Context contextParameter){
        context = contextParameter;
        preferences = context.getSharedPreferences(fileName, MODE);
        editor = preferences.edit();
    }

    public void SaveUserPreferences (String name, String phone, String token){
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public HashMap<String, String> getUserData(){
        HashMap<String, String> userData = new HashMap<>();
        userData.put(KEY_NAME, preferences.getString(KEY_NAME, null));
        userData.put(KEY_PHONE, preferences.getString(KEY_PHONE, null));
        userData.put(KEY_TOKEN, preferences.getString(KEY_TOKEN, null));

        return userData;
    }

}
