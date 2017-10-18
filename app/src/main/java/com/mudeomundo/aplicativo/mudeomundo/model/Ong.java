package com.mudeomundo.aplicativo.mudeomundo.model;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;

/**
 * Created by Juliana on 11/10/2017.
 */

public class Ong {
    private String id;
    private String nome;
    private String endereco;
    private String cep;
    private String cidade;
    private String estado;
    private String proposito;
    private String email;
    private String telefone;
    private boolean status;
    private static Ong instance;
    private static String TAG = Ong.class.getName();

    public Ong() {

    }

    public void salvar(){
        Log.d(TAG, "cadastrarOng salvar");
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("ong").setValue(this);
    }
    @Exclude
    public static Ong getInstance (){
        return instance == null ? instance = new Ong() : instance;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public static void setInstance(Ong instance) {
        Ong.instance = instance;
    }
}