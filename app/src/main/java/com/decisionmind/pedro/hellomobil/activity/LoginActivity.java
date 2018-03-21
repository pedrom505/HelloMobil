package com.decisionmind.pedro.hellomobil.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.decisionmind.pedro.hellomobil.R;
import com.decisionmind.pedro.hellomobil.helper.Permission;

public class LoginActivity extends Activity {

    private Button button_Login;


    private String[] permissions = new String[]{
            android.Manifest.permission.SEND_SMS,
            android.Manifest.permission.INTERNET

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Permission.valPermission(1, this, permissions);


        button_Login = findViewById(R.id.button_Login);
        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void openUserRegister (View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

}
