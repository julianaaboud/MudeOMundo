package com.mudeomundo.aplicativo.mudeomundo.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;

/**
 * Created by Juliana on 04/09/2017.
 */

    public class Usuario {
        private String id;
        private String nome;
        private String dt_nasc;
        private String cep;
        private String cidade;
        private String estado;
        private String telefone;
        private String email;
        private String senha;
        private boolean sexofem;
        private boolean sexomasc;


    public Usuario(){

    }

    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("usuarios").child(getId()).setValue(this);
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

    public String getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(String dt_nasc) {
        this.dt_nasc = dt_nasc;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isSexofem() {
        return sexofem;
    }

    public void setSexofem(boolean sexofem) {
        this.sexofem = sexofem;
    }

    public boolean isSexomasc() {
        return sexomasc;
    }

    public void setSexomasc(boolean sexomasc) {
        this.sexomasc = sexomasc;
    }
}