package com.decisionmind.pedro.hellomobil.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class LoginActivity extends Activity {

    private Button button_Login;
    private EditText editText_email;
    private EditText editText_Password;
    private User user;
    private FirebaseAuth authentication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CheckLoggedUser();

        editText_email = findViewById(R.id.editText_email);
        editText_Password = findViewById(R.id.editText_Password);
        button_Login = findViewById(R.id.button_Login);
        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                user.setEmail(editText_email.getText().toString());
                user.setPassword(editText_Password.getText().toString());
                LoginValidator();
            }
        });
    }

    public void CheckLoggedUser(){
        authentication = configFireBase.getFireBaseAuth();
        if(authentication.getCurrentUser() != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void LoginValidator(){
        authentication = configFireBase.getFireBaseAuth();
        authentication.signInWithEmailAndPassword(
                user.getEmail(),
                user.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Erro no processo de autenticação. Verifique usuário e senha!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void openUserRegister (View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

}
