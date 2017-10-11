package com.mudeomundo.aplicativo.mudeomundo.model;

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
    private String proposito;
    private String email;
    private String telefone;
    private boolean status;
    private static Ong instance;


    public Ong() {

    }

    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("ong").child(getId()).setValue(this);
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
}