package com.decisionmind.pedro.hellomobil.config;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by pedro on 20/03/18.
 */

public final class configFireBase {

    private static DatabaseReference referenceFireBase;

    public static DatabaseReference getFireBase(){

        if(referenceFireBase == null){
            referenceFireBase = FirebaseDatabase.getInstance().getReference();
        }
        return referenceFireBase;
    }


}
