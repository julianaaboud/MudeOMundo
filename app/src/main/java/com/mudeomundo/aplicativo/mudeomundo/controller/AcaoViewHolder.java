package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mudeomundo.aplicativo.mudeomundo.R;

/**
 * Created by Juliana on 22/11/2017.
 */

public class AcaoViewHolder extends RecyclerView.ViewHolder {
    private TextView nome;
    private TextView telefone;
    private TextView endereco;
    private TextView cidade;
    private TextView estado;
    private TextView cep;
    private TextView data;
    private TextView proposito;
    private TextView email;


    private Context context;


    public AcaoViewHolder(Context context, View view) {
        super(view);
        this.context = context;
        nome = (TextView) view.findViewById(R.id.textViewRecyclerAcaoNome);
        telefone = (TextView) view.findViewById(R.id.textViewRecyclerAcaoTelefone);
        endereco = (TextView) view.findViewById(R.id.textViewRecyclerAcaoEndereco);
        cidade = (TextView) view.findViewById(R.id.textViewRecyclerAcaoCidade);
        estado = (TextView) view.findViewById(R.id.textViewRecyclerAcaoEstado);
        cep = (TextView) view.findViewById(R.id.textViewRecyclerAcaoCep);
        data = (TextView) view.findViewById(R.id.textViewRecyclerAcaoData);
        proposito = (TextView) view.findViewById(R.id.textViewRecyclerAcaoProposito);
        email = (TextView) view.findViewById(R.id.textViewRecyclerAcaoEmail);
    }

    public void setNome(String nomeText) {
        nome.setText(nomeText);
    }

    public void setTelefone(String telefoneText) {
        telefone.setText(telefoneText);
    }

    public void setEndereco(String enderecoText) {
        endereco.setText(enderecoText);
    }

    public void setCidade(String cidadeText) {
        cidade.setText(cidadeText);
    }

    public void setEstado(String estadoText) {
        estado.setText(estadoText);
    }

    public void setCep(String cepText) {
        cep.setText(cepText);
    }

    public void setData(String cnpjText) {
        data.setText(cnpjText);
    }

    public void setProposito(String propositoText) {
        proposito.setText(propositoText);
    }

    public void setEmail(String emailText) {
        email.setText(emailText);
    }


}