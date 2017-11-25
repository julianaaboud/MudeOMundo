package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.model.Acao;

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
        listAcao = Acao.getInstance().getAcaoList();

        //Setando Adapter
        acaoAdapter = new AcaoAdapter(this, listAcao);
        recyclerViewAcao.setAdapter(acaoAdapter);

        //Setando Geranciador de Layout
        acaoLayoutManager = new LinearLayoutManager(this);
        recyclerViewAcao.setLayoutManager(acaoLayoutManager);

    }

    @Override
    protected void onResume(){
        super.onResume();
        acaoAdapter.notifyDataSetChanged();
    }

}
