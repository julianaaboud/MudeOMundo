package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;
import com.mudeomundo.aplicativo.mudeomundo.model.Ong;

import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;

public class BuscaOngActivity extends AppCompatActivity {

    private Button botaoBuscaNome;
    private Button botaoBuscaMinhaLocalizacao;
    private EditText nomeOng;
    private ListView listaOng;
    private static String TAG = BuscaOngActivity.class.getName();
    ArrayList<Ong> arrayDeOng = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_ong);
        listaOng = (ListView) findViewById(R.id.listViewId);
        nomeOng = (EditText) findViewById(R.id.searchId);


        botaoBuscaMinhaLocalizacao = (Button) findViewById(R.id.botaoBuscaLocalizacaoAtual);
        botaoBuscaMinhaLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuscaOngActivity.this, MapsActivity.class));
            }
        });


        botaoBuscaNome = (Button) findViewById(R.id.botaoBuscaNomeId);
        botaoBuscaNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscaOngPorNome();
            }
        });

    }

    public void buscaOngPorNome() {
        //Busca no Firebase
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        Query buscaQuery = referenciaFirebase.child("ong");

        //Criando o adapter
        ArrayAdapter<Ong> adaptador = new ArrayAdapter<Ong>(this, simple_list_item_1, arrayDeOng);

        //Vinculando o adaptador ao listview
        listaOng.setAdapter(adaptador);


        buscaQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Log.d(TAG, "DataSnapshot: " + postSnapshot.getKey());

                     Ong ong = postSnapshot.getValue(Ong.class);
                    arrayDeOng.add(ong);
                    Log.d(TAG, "DataSnapshot ongModel data: " + ong.getNome());

                    if (ong.getNome().equals(nomeOng.getText().toString())) {
                        Log.d(TAG, "DataSnapshot ACHOU A ONG: " + ong.getNome());

                        arrayDeOng.add(ong);

                        return;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }



}

