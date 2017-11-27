package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;
import com.mudeomundo.aplicativo.mudeomundo.model.Acao;
import com.mudeomundo.aplicativo.mudeomundo.model.Ong;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView botaoLogin;
   // private Button botaoOng;
   // private Button botaoAcao;
    private List<Ong> listOng = Ong.getInstance().getOngList();
    private static String TAG = MainActivity.class.getName();
    private List<Acao> listAcao = Acao.getInstance().getAcaoList();
    private Intent buscaIntent;
    final DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buscaListaDeAcoes();
        buscaListaDeOngs();


      botaoLogin = (TextView) findViewById(R.id.botaoLoginId);
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

      /*  botaoOng = (Button) findViewById(R.id.buttonOngId);
        botaoOng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscaIntent = new Intent(MainActivity.this, BuscaActivity.class);
                buscaIntent.putExtra("tela", 1);
                startActivity(buscaIntent);
            }
        });

        botaoAcao = (Button) findViewById(R.id.buttonAçãoVoluntariaId);
        botaoAcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscaIntent = new Intent(MainActivity.this, BuscaActivity.class);
                buscaIntent.putExtra("tela", 2);
                startActivity(buscaIntent);
            }
        });*/

    }

    public void abrirCadastroUsuario(View view) {
        Intent intent = new Intent(MainActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }

    public void buscaListaDeOngs() {
        //Busca no Firebase
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

    public void buscaListaDeAcoes() {
        //Busca no Firebase
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
