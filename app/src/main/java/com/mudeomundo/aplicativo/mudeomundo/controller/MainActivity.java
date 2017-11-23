package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;
import com.mudeomundo.aplicativo.mudeomundo.model.Ong;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView botaoLogin;
    private DatabaseReference referenciaFirebase;
    private Button botaoOng;
    private Button botaoAcao;
    private List<Ong> listOng = Ong.getInstance().getOngList();
    private static String TAG = MainActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referenciaFirebase = ConfiguracaoFirebase.getFirebase();


      botaoLogin = (TextView) findViewById(R.id.botaoLoginId);
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
                buscaListaDeOngs();
                startActivity(new Intent(MainActivity.this, BuscaOngActivity.class));
            }
        });

        botaoAcao = (Button) findViewById(R.id.buttonAçãoVoluntariaId);
        botaoAcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BuscaAcaoActivity.class));
            }
        });

    }
    public void abrirCadastroUsuario(View view) {
        Intent intent = new Intent(MainActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }

    public void buscaListaDeOngs() {
        //Busca no Firebase
        final DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        Query buscaQuery = referenciaFirebase.child("ong");

        //Snapshot
        buscaQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Ong ong = postSnapshot.getValue(Ong.class);
                    Log.d(TAG, "ong: " + ong.getNome());
                    listOng.add(ong);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}
