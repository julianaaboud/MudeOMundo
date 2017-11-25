package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.model.BuscaAcaoFragment;
import com.mudeomundo.aplicativo.mudeomundo.model.BuscaOngFragment;

public class BuscaActivity extends AppCompatActivity {
    private int numberTela;
    private static String TAG = BuscaActivity.class.getName();
    private LinearLayout layoutBusca;
    private FragmentTransaction ft;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        numberTela = b.getInt("tela", 0);
        layoutBusca = (LinearLayout) findViewById(R.id.buscaLayout);

        Log.d(TAG, "numero tela " + numberTela);

        switch (numberTela) {

            case 1:
                Fragment fragmentOng = new BuscaOngFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.buscaLayout, fragmentOng);
                ft.commit();
                break;
            case 2:
                Fragment fragmentAcoes = new BuscaAcaoFragment();
                FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                ft1.replace(R.id.buscaLayout, fragmentAcoes);
                ft1.commit();
                break;
            default:
                break;
        }

    }
}
