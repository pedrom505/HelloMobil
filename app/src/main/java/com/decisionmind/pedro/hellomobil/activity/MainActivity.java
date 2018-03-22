package com.decisionmind.pedro.hellomobil.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.decisionmind.pedro.hellomobil.R;
import com.google.firebase.auth.FirebaseAuth;
import com.decisionmind.pedro.hellomobil.config.configFireBase;

public class MainActivity extends Activity {

    private Button button_press;
    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_press = findViewById(R.id.button_press);
        button_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authentication = configFireBase.getFireBaseAuth();
                authentication.signOut();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
