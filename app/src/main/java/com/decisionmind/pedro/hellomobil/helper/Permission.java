package com.decisionmind.pedro.hellomobil.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedro on 18/03/18.
 */

public class Permission {

    public static  boolean valPermission (int requestCode, Activity activity, String[] permissions){



        if(Build.VERSION.SDK_INT >= 23){
            List<String> permissionList = new ArrayList<String>();

            for(String permission: permissions){
                Boolean permissionFlag = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
                if (!permissionFlag){
                    permissionList.add(permission);
                }

            }
            if (permissionList.isEmpty())
                return true;

            String[] newPermission = new String [permissionList.size()];
            permissionList.toArray(newPermission);
            ActivityCompat.requestPermissions(activity, newPermission, requestCode);
        }
        return true;

    }

}
