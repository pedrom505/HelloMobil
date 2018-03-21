package com.decisionmind.pedro.hellomobil.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.decisionmind.pedro.hellomobil.R;
import com.google.firebase.database.DatabaseReference;
import com.decisionmind.pedro.hellomobil.config.configFireBase;

public class MainActivity extends Activity {

    private DatabaseReference referenceFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referenceFirebase = configFireBase.getFireBase();
        referenceFirebase.child("point").setValue("800");

    }
}
