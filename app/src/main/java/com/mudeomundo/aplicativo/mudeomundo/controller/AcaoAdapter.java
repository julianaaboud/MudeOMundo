package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.model.Acao;

import java.util.List;

/**
 * Created by Juliana on 22/11/2017.
 */

public class AcaoAdapter extends RecyclerView.Adapter {
    private List<Acao> listAcao;
    private Context context;
    private String[] mDataset;
    private final int ACAO = 0;


    public AcaoAdapter(Context context, List<Acao> listAcao) {
        this.listAcao = listAcao;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return ACAO;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.holder_acao, parent, false);
        AcaoViewHolder holder = new AcaoViewHolder(context, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        AcaoViewHolder acaoViewHolder = (AcaoViewHolder) viewHolder;
        setAcaoViewHolder(acaoViewHolder, position);
    }


    @Override
    public int getItemCount() {
        if (listAcao != null && listAcao.size() >= 0) {
            return listAcao.size();
        } else {
            return 0;
        }
    }

    public void setAcaoViewHolder(AcaoViewHolder acaoViewHolder, int position) {
        Acao acao = listAcao.get(position);
        acaoViewHolder.setNome(acao.getNome());
        acaoViewHolder.setData(acao.getData());
        acaoViewHolder.setEmail(acao.getEmail());
        acaoViewHolder.setCep(acao.getCep());
        acaoViewHolder.setCidade(acao.getCidade());
        acaoViewHolder.setEndereco(acao.getEndereco());
        acaoViewHolder.setTelefone(acao.getTelefone());
        acaoViewHolder.setEstado(acao.getEstado());
        acaoViewHolder.setProposito(acao.getProposito());
        acaoViewHolder.setEmail(acao.getEmail());
    }
}

