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
import com.decisionmind.pedro.hellomobil.helper.Preferences;
import com.decisionmind.pedro.hellomobil.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.decisionmind.pedro.hellomobil.config.configFireBase;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.decisionmind.pedro.hellomobil.helper.Base64Custom;

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

                String name = editText_Name.getText().toString();
                String email = editText_Email.getText().toString();
                String password = editText_Password.getText().toString();
                if(name.equals("") || email.equals("") || password.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Por favor, preencha todos os campos acima!", Toast.LENGTH_LONG).show();
                }else{
                    user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    userRegister();
                }
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
                    FirebaseUser firebaseUser = task.getResult().getUser();

                    String userID = Base64Custom.CodeBase64(user.getEmail());

                    user.setId(userID);
                    user.save();

                    Preferences preferences = new Preferences(RegisterActivity.this);
                    preferences.SaveUserPreferences(userID);

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{

                    String error;

                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        error = "Digite uma senha mais forte. Ela deve conter caracteres e numeros!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        error = "O e-mail digitado é invalido!";
                    }catch (FirebaseAuthUserCollisionException e){
                        error = "O e-mail informado já está sendo utilizado!";
                    }catch (Exception e){
                        error = "Não foi possivel efetuar o cadastro!";
                    }
                    Toast.makeText(RegisterActivity.this, "Erro: " + error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
