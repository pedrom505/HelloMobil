package com.decisionmind.pedro.hellomobil.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.decisionmind.pedro.hellomobil.R;

public class AboutActivity extends AppCompatActivity {

    private Button button_Return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        button_Return = findViewById(R.id.button_Return);

        button_Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
