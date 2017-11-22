package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.model.Ong;

import java.util.List;

/**
 * Created by Juliana on 21/11/2017.
 */


public class OngAdapter extends RecyclerView.Adapter {
    private List<Ong> listOng;
    private Context context;
    private String[] mDataset;
    private final int ONG = 0;


    public OngAdapter(Context context, List<Ong> listOng) {
        this.listOng = listOng;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return ONG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.holder_ong, parent, false);
        OngViewHolder holder = new OngViewHolder(context, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        OngViewHolder ongViewHolder = (OngViewHolder) viewHolder;
        setOngViewHolder(ongViewHolder, position);
    }


    @Override
    public int getItemCount() {
        if (listOng != null && listOng.size() >= 0) {
            return listOng.size();
        } else {
            return 0;
        }
    }

    public void setOngViewHolder(OngViewHolder ongViewHolder, int position) {
        Ong ong = listOng.get(position);
        ongViewHolder.setNome(ong.getNome());
        ongViewHolder.setEmail(ong.getEmail());
        ongViewHolder.setCep(ong.getCep());
        ongViewHolder.setCidade(ong.getCidade());
        ongViewHolder.setEndereco(ong.getEndereco());
        ongViewHolder.setCnpj(ong.getCnpj());
        ongViewHolder.setTelefone(ong.getTelefone());
        ongViewHolder.setEstado(ong.getEstado());
        ongViewHolder.setProposito(ong.getProposito());
        ongViewHolder.setEmail(ong.getEmail());
    }
}

