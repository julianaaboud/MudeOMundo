package com.mudeomundo.aplicativo.mudeomundo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;
import com.mudeomundo.aplicativo.mudeomundo.model.BuscaOngActivity;

public class MainActivity extends AppCompatActivity {

    private Button botaoLogin;
    private DatabaseReference referenciaFirebase;
    private Button botaoOng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referenciaFirebase = ConfiguracaoFirebase.getFirebase();


      botaoLogin = (Button) findViewById(R.id.botaoLoginId);
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        botaoOng = (Button) findViewById(R.id.buttonOngId);
        botaoOng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BuscaOngActivity.class));
            }
        });

    }
    public void abrirCadastroUsuario(View view) {
        Intent intent = new Intent(MainActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }

}