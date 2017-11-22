package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mudeomundo.aplicativo.mudeomundo.R;

/**
 * Created by Juliana on 21/11/2017.
 */

public class OngViewHolder extends RecyclerView.ViewHolder {
    private TextView nome;
    private TextView telefone;
    private TextView endereco;
    private TextView cidade;
    private TextView estado;
    private TextView cep;
    private TextView cnpj;
    private TextView proposito;
    private TextView email;
    private TextView status;
    private Context context;


        public OngViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            nome = (TextView) view.findViewById(R.id.textViewRecyclerNome);
            telefone = (TextView) view.findViewById(R.id.textViewRecyclerTelefone);
            endereco = (TextView) view.findViewById(R.id.textViewRecyclerEndereco);
            cidade = (TextView) view.findViewById(R.id.textViewRecyclerCidade);
            estado = (TextView) view.findViewById(R.id.textViewRecyclerEstado);
            cep = (TextView) view.findViewById(R.id.textViewRecyclerCep);
            cnpj = (TextView) view.findViewById(R.id.textViewRecyclerCnpj);
            proposito = (TextView) view.findViewById(R.id.textViewRecyclerProposito);
            email = (TextView) view.findViewById(R.id.textViewRecyclerEmail);
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

    public void setCnpj(String cnpjText) {
        cnpj.setText(cnpjText);
    }

    public void setProposito(String propositoText) {
        proposito.setText(propositoText);
    }

    public void setEmail(String emailText) {
       email.setText(emailText);
    }

    public void setStatus(String statusText) {
        status.setText(statusText);
    }

}
