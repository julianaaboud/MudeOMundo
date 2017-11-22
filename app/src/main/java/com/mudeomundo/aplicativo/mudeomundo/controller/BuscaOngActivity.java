package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.model.Ong;

import java.util.List;

public class BuscaOngActivity extends AppCompatActivity {

    private Button botaoBuscaNome;
    private Button botaoBuscaMinhaLocalizacao;
    private EditText nomeOng;
    private ListView listaOng;
    private static String TAG = BuscaOngActivity.class.getName();
    private List<Ong> listOng;
    private RecyclerView recyclerViewOng;
   // private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager ongLayoutManager;
    private OngAdapter ongAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_ong);
        listaOng = (ListView) findViewById(R.id.listViewId);
        nomeOng = (EditText) findViewById(R.id.searchId);
        recyclerViewOng = (RecyclerView) findViewById(R.id.recycler);
        listOng = Ong.getInstance().getOngList();

        //Setando Adapter
        ongAdapter = new OngAdapter(this, listOng);
        recyclerViewOng.setAdapter(ongAdapter);

        //Setando Geranciador de Layout
        ongLayoutManager = new LinearLayoutManager(this);
        recyclerViewOng.setLayoutManager(ongLayoutManager);

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
             //   buscaListaDeOngs();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        ongAdapter.notifyDataSetChanged();
    }

  /*  public void buscaListaDeOngs() {
        //Busca no Firebase
        final DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        Query buscaQuery = referenciaFirebase.child("ong");

        //Snapshot
        buscaQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Query ongQuery = referenciaFirebase.child("ong").orderByChild("nome").limitToFirst(10);
                    //Log.d(TAG, "DataSnapshot ongQuery: " + ongQuery);
                    //String name = (String) postSnapshot.child("nome").getValue();
                    //Log.d(TAG, "DataSnapshot name: " + name);
                    Ong ong = postSnapshot.getValue(Ong.class);
                    listOng.add(ong);
                }

            }

            *//*   for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                   Log.d(TAG, "DataSnapshot nome digitado: " + nomeOng.getText());

                if (ong.getNome().equals(nomeOng.getText().toString())) {
                    Log.d(TAG, "DataSnapshot ACHOU A ONG: " + ong.getNome());

                }*//*
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });

    }*/

    //Criando o adapter
    //ArrayAdapter<Ong> adaptador = new ArrayAdapter<Ong>(this, simple_list_item_1, arrayDeOng);

    //Vinculando o adaptador ao listview
    //listaOng.setAdapter(adaptador);

}

        /*       buscaQuery.addValueEventListener(new ValueEventListener() {
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
*/





