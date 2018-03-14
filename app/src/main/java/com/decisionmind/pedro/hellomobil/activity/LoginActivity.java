package com.decisionmind.pedro.hellomobil.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.decisionmind.pedro.hellomobil.R;
import com.decisionmind.pedro.hellomobil.helper.Preferences;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;
import java.util.Random;

public class LoginActivity extends Activity {

    // Edit Text
    private EditText editText_Name;
    private EditText editText_Phone;
    private EditText editText_DDD;
    private EditText editText_RC;

    //Button
    private Button button_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Instances of EditText
        editText_Name = findViewById(R.id.editText_Name);

        editText_RC = findViewById(R.id.editText_RC);
        SimpleMaskFormatter simpleMaskRC = new SimpleMaskFormatter("+NN");
        MaskTextWatcher maskRC = new MaskTextWatcher(editText_RC, simpleMaskRC);
        editText_RC.addTextChangedListener(maskRC);

        editText_DDD = findViewById(R.id.editText_DDD);
        SimpleMaskFormatter simpleMaskDDD = new SimpleMaskFormatter("NN");
        MaskTextWatcher maskDDD = new MaskTextWatcher(editText_DDD, simpleMaskDDD);
        editText_DDD.addTextChangedListener(maskDDD);

        editText_Phone = findViewById(R.id.editText_Phone);
        SimpleMaskFormatter simpleMaskPhone = new SimpleMaskFormatter("NNNNN-NNNN");
        MaskTextWatcher maskPhone = new MaskTextWatcher(editText_Phone, simpleMaskPhone);
        editText_Phone.addTextChangedListener(maskPhone);


        //Instances of buttons
        button_Register = findViewById(R.id.button_Register);
        button_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editText_Name.getText().toString();
                String completePhone =
                        editText_RC.getText().toString()+
                        editText_DDD.getText().toString() +
                        editText_Phone.getText().toString();
                completePhone = completePhone.replace("+", "");
                completePhone = completePhone.replace("-", "");

                //Generate Token
                Random random = new Random();
                int randomNumber = random.nextInt( 8999 ) + 1000;

                String token = String.valueOf(randomNumber);

                //Saving data to validation
                Preferences preferences = new Preferences(LoginActivity.this);
                preferences.SaveUserPreferences(userName, completePhone, token);

                HashMap<String, String> user = preferences.getUserData();

                Log.i("TOKEN", "NAME:" + user.get("name") + " PHONE:" + user.get("phone"));
            }
        });



    }
}
