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

    private String KEY_ID = "IDLogginUser";

    public Preferences(Context contextParameter){
        context = contextParameter;
        preferences = context.getSharedPreferences(fileName, MODE);
        editor = preferences.edit();
    }

    public void SaveUserPreferences (String userID){
        editor.putString(KEY_ID, userID);

        editor.commit();
    }

    public String getID(){
        return preferences.getString(KEY_ID, null);
    }


}
