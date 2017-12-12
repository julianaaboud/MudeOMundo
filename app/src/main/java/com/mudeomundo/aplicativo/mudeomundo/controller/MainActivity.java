package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;
import com.mudeomundo.aplicativo.mudeomundo.model.Acao;
import com.mudeomundo.aplicativo.mudeomundo.model.Ong;
import com.mudeomundo.aplicativo.mudeomundo.model.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView botaoLogin;
    private List<Ong> listOng = Ong.getInstance().getOngList();
    private static String TAG = MainActivity.class.getName();
    private List<Acao> listAcao = Acao.getInstance().getAcaoList();
    final DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
    private EditText email;
    private EditText senha;
    private TextView botaoLogar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buscaListaDeAcoes();
        buscaListaDeOngs();

        email = (EditText) findViewById(R.id.editTextNomeId);
        senha = (EditText) findViewById(R.id.editTextPasswordId);
        botaoLogar = (TextView) findViewById(R.id.botaoLogar);


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
        Log.d(TAG, "validarLogin");
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail().trim(),
                usuario.getSenha().trim()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "onCreate Login if");
                if (task.isSuccessful()) {
                    Log.d(TAG, "onCreate Login Sucesso");
                    Intent intent = new Intent(MainActivity.this, UsuarioLogadoActivity.class);
                    intent.putExtra("token", autenticacao.getCurrentUser().getUid());
                    startActivity(intent);

                } else {
                    //    Log.i("loginfail", "erro" + task.getException());
                    Toast.makeText(MainActivity.this, "Erro ao fazer login ", Toast.LENGTH_LONG).show();
                }
            }
        });
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
                    Log.d(TAG, "DataSnapshot ação: " + postSnapshot);
                    Acao acao = postSnapshot.getValue(Acao.class);
                    listAcao.add(acao);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

}
