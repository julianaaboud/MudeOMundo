package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;
import com.mudeomundo.aplicativo.mudeomundo.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button botaoLogar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.editTextNomeId);
        senha = (EditText) findViewById(R.id.editTextPasswordId);
        botaoLogar = (Button) findViewById(R.id.buttonLogarId);


        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                validarLogin();
            }
        });
    }

    private void validarLogin() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, UsuarioLogado.class);
                    startActivity(intent);

                } else {
                    Log.i("loginfail", "erro" + task.getException());
                    Toast.makeText(LoginActivity.this, "Erro ao fazer login ", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}