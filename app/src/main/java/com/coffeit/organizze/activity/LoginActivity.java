package com.coffeit.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coffeit.organizze.R;
import com.coffeit.organizze.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.coffeit.organizze.config.configFirebase;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login;
    private User user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        login = findViewById(R.id.buttonLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = email.getText().toString(), textPassword = password.getText().toString();

                if (!textEmail.isEmpty()) {
                    if (!textPassword.isEmpty()) {
                        user = new User();
                        user.setEmail(textEmail);
                        user.setPassword(textPassword);
                        ValidateLogin();
                    } else {
                        Toast.makeText(LoginActivity.this, "Preencha a senha", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Preencha o email", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    public void ValidateLogin(){
        auth = configFirebase.getFirebaseAuth();
        auth.signInWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //Toast.makeText(LoginActivity.this, "Sucesso  ao logar usuário", Toast.LENGTH_SHORT).show();
                    NavigateHome();

                } else {
                    String exception = "";
                    try {
                        throw task.getException();
                    }  catch (FirebaseAuthInvalidCredentialsException e) {
                        exception = "Email e senha não correspondem a um usuário cadastrado";
                    } catch (FirebaseAuthInvalidUserException e) {
                        exception = "Usuário não existe ";
                    }  catch (Exception e) {
                        exception = "Erro ao logar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this, exception, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void NavigateHome(){
        startActivity(new Intent(this,HomeActivity.class));
        finish();
    }
}
