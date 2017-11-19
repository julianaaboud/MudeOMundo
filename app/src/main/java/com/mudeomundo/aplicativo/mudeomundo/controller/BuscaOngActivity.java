package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;
import com.mudeomundo.aplicativo.mudeomundo.model.Ong;

import java.util.ArrayList;
import java.util.List;

public class BuscaOngActivity extends AppCompatActivity {

    private Button botaoBuscaNome;
    private Button botaoBuscaMinhaLocalizacao;
    private EditText nomeOng;
    private ListView listaOng;
    private static String TAG = BuscaOngActivity.class.getName();
    ArrayList<Ong> arrayDeOng = new ArrayList<>();
    private List<Ong> listOng;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_ong);
        listaOng = (ListView) findViewById(R.id.listViewId);
        nomeOng = (EditText) findViewById(R.id.searchId);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);

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

            }
        });

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
                                                     arrayDeOng.add(ong);
                                                     listOng = arrayDeOng;
                                                 }
                                                 Log.d(TAG, "DataSnapshot arrayDeOng: " + arrayDeOng);
                                             }

                                             /*   for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                                                    Log.d(TAG, "DataSnapshot nome digitado: " + nomeOng.getText());

                                                 if (ong.getNome().equals(nomeOng.getText().toString())) {
                                                     Log.d(TAG, "DataSnapshot ACHOU A ONG: " + ong.getNome());

                                                 }*/
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });

        //Recebendo os dados do banco
        listOng = arrayDeOng;

        //Adapter
        recyclerView.setAdapter(new NossoAdapter(listOng, this));

        //Criando o adapter
        //ArrayAdapter<Ong> adaptador = new ArrayAdapter<Ong>(this, simple_list_item_1, arrayDeOng);

        //Vinculando o adaptador ao listview
        //listaOng.setAdapter(adaptador);

    }
    public class NossoAdapter extends RecyclerView.Adapter{
        private List<Ong> listOng;
        private Context context;

        public NossoAdapter(List<Ong> listOng){
            this.listOng = listOng;
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.textViewRecycle, parent, false);
            NossoViewHolder holder = new NossoViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        }

        @Override
        public int getItemCount() {
            return listOng.size();
        }
    }



    public class NossoViewHolder extends RecyclerView.ViewHolder {
        final TextView nome;
        final TextView telefone;
        final TextView endereco;
        final TextView cidade;
        final TextView estado;
        final TextView cep;
        final TextView cnpj;
        final TextView proposito;
        final TextView email;
        final TextView status;

        public NossoViewHolder(View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.textViewRecycleNome);
            telefone = (TextView) view.findViewById(R.id.textViewRecycleTelefone);
            endereco = (TextView) view.findViewById(R.id.textViewRecycleEndereco);
            cidade = (TextView) view.findViewById(R.id.textViewRecycleCidade);
            estado = (TextView) view.findViewById(R.id.textViewRecycleEstado);
            cep = (TextView) view.findViewById(R.id.textViewRecycleCep);
            cnpj = (TextView) view.findViewById(R.id.textViewRecycleCnpj);
            proposito = (TextView) view.findViewById(R.id.textViewRecycleProposito);
            email = (TextView) view.findViewById(R.id.textViewRecycleEmail);
            status = (TextView) view.findViewById(R.id.textViewRecycleStatus);
        }
    }



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





