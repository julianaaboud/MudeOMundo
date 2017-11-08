package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;

public class BuscaOngActivity extends AppCompatActivity {

    private Button botaoBuscaNome;
    private Button botaoBuscaMinhaLocalizacao;
    private String nomeOng;
    private static String TAG = BuscaOngActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_ong);

        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();

        botaoBuscaMinhaLocalizacao = (Button) findViewById(R.id.botaoBuscaLocalizacaoAtual);
        botaoBuscaMinhaLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuscaOngActivity.this, MapsActivity.class));
            }
        });


        botaoBuscaNome = (Button) findViewById(R.id.botaoBuscaNomeId);




        //Query de busca no banco
        Query buscaQuery = referenciaFirebase.child("ong");

        // Busca no Firebase
        buscaQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    Log.d(TAG, "entrou no for" + postSnapshot);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });

    }
}
