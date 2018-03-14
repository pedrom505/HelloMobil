package com.decisionmind.pedro.hellomobil.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.decisionmind.pedro.hellomobil.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class LoginActivity extends Activity {

    private EditText editText_Name;
    private EditText editText_Phone;
    private EditText editText_DDD;
    private EditText editText_RC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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





    }
}
