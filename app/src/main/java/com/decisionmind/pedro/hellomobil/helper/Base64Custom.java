package com.decisionmind.pedro.hellomobil.helper;

import android.util.Base64;

/**
 * Created by pedro on 22/03/18.
 */

public class Base64Custom {

    public static String CodeBase64(String text){
        return Base64.encodeToString(text.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String DecodeBase64 (String text){
        return new String(Base64.decode(text, Base64.DEFAULT));
    }

}
