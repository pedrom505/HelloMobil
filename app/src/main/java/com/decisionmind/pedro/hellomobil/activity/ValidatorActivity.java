package com.decisionmind.pedro.hellomobil.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.decisionmind.pedro.hellomobil.R;
import com.decisionmind.pedro.hellomobil.helper.Preferences;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;

public class ValidatorActivity extends Activity {

    private EditText editText_validationCode;
    private Button button_validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validator);

        editText_validationCode = findViewById(R.id.editText_validationCode);
        button_validate = findViewById(R.id.button_validate);

        SimpleMaskFormatter simpleMaskValidationCode = new SimpleMaskFormatter("NNNN");
        MaskTextWatcher maskValidationCode = new MaskTextWatcher(editText_validationCode, simpleMaskValidationCode);

        editText_validationCode.addTextChangedListener( maskValidationCode);

        button_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recovering user preferences data

            }
        });


    }
}
