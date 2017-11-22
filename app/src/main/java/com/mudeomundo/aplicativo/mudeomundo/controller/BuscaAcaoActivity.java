package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;
import com.mudeomundo.aplicativo.mudeomundo.model.Acao;

import java.util.ArrayList;
import java.util.List;

public class BuscaAcaoActivity extends AppCompatActivity {
    private AcaoAdapter acaoAdapter;
    private RecyclerView recyclerViewAcao;
    private RecyclerView.LayoutManager acaoLayoutManager;
    private static String TAG = BuscaAcaoActivity.class.getName();
    private List<Acao> listAcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_acao);
        recyclerViewAcao = (RecyclerView) findViewById(R.id.recyclerAcao);
        listAcao = new ArrayList<>();

        //Setando Adapter
        acaoAdapter = new AcaoAdapter(this, listAcao);
        recyclerViewAcao.setAdapter(acaoAdapter);

        //Setando Geranciador de Layout
        acaoLayoutManager = new LinearLayoutManager(this);
        recyclerViewAcao.setLayoutManager(acaoLayoutManager);

        buscaListaDeAcoes();
    }

    @Override
    protected void onResume(){
        super.onResume();
        acaoAdapter.notifyDataSetChanged();
    }

    public void buscaListaDeAcoes() {
        //Busca no Firebase
        final DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        Query buscaQuery = referenciaFirebase.child("acao");
        Log.d(TAG, "DataSnapshot buscaQuery: " + buscaQuery);
        //Snapshot
        buscaQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Query ongQuery = referenciaFirebase.child("ong").orderByChild("nome").limitToFirst(10);
                    //Log.d(TAG, "DataSnapshot ongQuery: " + ongQuery);
                    //String name = (String) postSnapshot.child("nome").getValue();
                    Log.d(TAG, "DataSnapshot postSnapshot: " + postSnapshot);
                    Acao acao = postSnapshot.getValue(Acao.class);
                    listAcao.add(acao);
                }
        //      Log.d(TAG, "DataSnapshot acao: " + listAcao);
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }
}
