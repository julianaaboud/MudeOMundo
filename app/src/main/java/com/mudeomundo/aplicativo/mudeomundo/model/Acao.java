package com.mudeomundo.aplicativo.mudeomundo.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juliana on 07/11/2017.
 */

public class Acao {
    private String nome;
    private String endereco;
    private String cep;
    private String data;
    private String cidade;
    private String estado;
    private String proposito;
    private String email;
    private String telefone;
    private static Acao instance;
    private static String TAG = Acao.class.getName();
    private static List<Acao> acaoList = new ArrayList<>();

    public Acao(){

    }
    public void salvar(final Context context) {
        Log.d(TAG, "cadastrarAcao salvar");
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase = referenciaFirebase.child("acao").push();
        referenciaFirebase.setValue(this, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Toast.makeText(context, "Sucesso ao cadastrar Ação", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Exclude
    public static Acao getInstance (){
        return instance == null ? instance = new Acao() : instance;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Acao{" +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cep='" + cep + '\'' +
                ", data='" + data + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", proposito='" + proposito + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    public static List<Acao> getAcaoList() {
        return acaoList;
    }

    public static void setAcaoList(List<Acao> acaoList) {
        Acao.acaoList = acaoList;
    }
}
