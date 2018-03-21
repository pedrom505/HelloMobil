package com.decisionmind.pedro.hellomobil.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by pedro on 20/03/18.
 */

public final class configFireBase {

    private static DatabaseReference referenceFireBase;
    private static FirebaseAuth authentication;


    public static DatabaseReference getFireBase(){

        if(referenceFireBase == null){
            referenceFireBase = FirebaseDatabase.getInstance().getReference();
        }
        return referenceFireBase;
    }

    public static FirebaseAuth getFireBaseAuth(){
        if(authentication == null){
            authentication = FirebaseAuth.getInstance();
        }
        return authentication;
    }


}
