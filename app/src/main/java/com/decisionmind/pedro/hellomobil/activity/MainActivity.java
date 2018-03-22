package com.decisionmind.pedro.hellomobil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.decisionmind.pedro.hellomobil.R;
import com.google.firebase.auth.FirebaseAuth;
import com.decisionmind.pedro.hellomobil.config.configFireBase;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar_main;
    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar_main = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_add:
                return true;
            case R.id.item_search:
                return true;
            case R.id.item_settings:
                return true;
            case R.id.item_exit:
                UserLogout();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void UserLogout(){
        authentication = configFireBase.getFireBaseAuth();
        authentication.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
