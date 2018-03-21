package com.decisionmind.pedro.hellomobil.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.decisionmind.pedro.hellomobil.R;
import com.decisionmind.pedro.hellomobil.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.decisionmind.pedro.hellomobil.config.configFireBase;

public class RegisterActivity extends AppCompatActivity {

    private EditText editText_Name;
    private EditText editText_Email;
    private EditText editText_Password;
    private Button button_Register;
    private User user;

    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText_Name = findViewById(R.id.editText_Name);
        editText_Email = findViewById(R.id.editText_Email);
        editText_Password = findViewById(R.id.editText_Password);
        button_Register = findViewById(R.id.button_Register);

        button_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                user.setName(editText_Name.getText().toString());
                user.setEmail(editText_Email.getText().toString());
                user.setPassword(editText_Password.getText().toString());
                userRegister();
            }
        });

    }

    private void userRegister(){
        authentication = configFireBase.getFireBaseAuth();
        authentication.createUserWithEmailAndPassword(
                user.getEmail(),
                user.getPassword()
        ).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Cadastro do usuário realizado com sucesso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this, "Não foi possivel cadastrar o usuário", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
