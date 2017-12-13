package com.mudeomundo.aplicativo.mudeomundo.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.controller.Validacao;

import java.util.ArrayList;

public class InserirOngFragment extends Fragment implements View.OnClickListener{

    private EditText nome;
    private EditText endereco;
    private EditText telefone;
    private EditText email;
    private EditText proposito;
    private EditText cnpj;
    private EditText cep;
    private EditText estado;
    private EditText cidade;
    private boolean status;
    private TextView botaoCadastrarOng;
    private Ong ong;
    private CheckBox animal, crianca, idoso, meioAmbiente, refugiado, cegueira, paralisiaCerebral, empoderamentoFeminino;
    private ArrayList<String> causasList = new ArrayList<>();

    private static String TAG = InserirOngFragment.class.getName();
   // private DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     //   referenciaFirebase.child("ong");

        View rootView = inflater.inflate(R.layout.fragment_ong, container, false);

        nome = (EditText) rootView.findViewById(R.id.nomeOngId);
        cep = (EditText) rootView.findViewById(R.id.cepOngId);
        cidade = (EditText) rootView.findViewById(R.id.cidadeOngId);
        estado = (EditText) rootView.findViewById(R.id.estadoOngId);
        telefone = (EditText) rootView.findViewById(R.id.telefoneOngId);
        email = (EditText) rootView.findViewById(R.id.emailOngId);
        cnpj = (EditText) rootView.findViewById(R.id.cnpjOngId);
        endereco = (EditText) rootView.findViewById(R.id.enderecoOngId);
        proposito = (EditText) rootView.findViewById(R.id.propositoOngId);
        botaoCadastrarOng = (TextView) rootView.findViewById(R.id.cadastrarOngId);
        animal = (CheckBox) rootView.findViewById(R.id.animalOngId);
        crianca = (CheckBox) rootView.findViewById(R.id.criancaOngId);
        idoso = (CheckBox) rootView.findViewById(R.id.idosoOngId);
        meioAmbiente = (CheckBox) rootView.findViewById(R.id.meioAmbienteOngId);
        refugiado = (CheckBox) rootView.findViewById(R.id.refugiadoOngId);
        cegueira = (CheckBox) rootView.findViewById(R.id.cegueiraOngId);
        paralisiaCerebral = (CheckBox) rootView.findViewById(R.id.paralisiaCerebralOngId);
        empoderamentoFeminino = (CheckBox) rootView.findViewById(R.id.empoderamentoFemininoOngId);

        animal.setOnClickListener(this);
        crianca.setOnClickListener(this);
        idoso.setOnClickListener(this);
        meioAmbiente.setOnClickListener(this);
        refugiado.setOnClickListener(this);
        cegueira.setOnClickListener(this);
        paralisiaCerebral.setOnClickListener(this);
        empoderamentoFeminino.setOnClickListener(this);


        botaoCadastrarOng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ong = new Ong();
                ong.setNome(nome.getText().toString());
                ong.setEndereco(endereco.getText().toString());
                ong.setCep(cep.getText().toString());
                ong.setCidade(cidade.getText().toString());
                ong.setEstado(estado.getText().toString());
                ong.setTelefone(telefone.getText().toString());
                ong.setEmail(email.getText().toString());
                ong.setProposito(proposito.getText().toString());
                ong.setCnpj(cnpj.getText().toString());
                ong.setCausas(causasList);
                cadastrarOng();
                Log.d(TAG, "onCreateView");
            }
        });
        return rootView;
    }

    @Override
    public void onClick(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.animalOngId:
                if (checked) {
                    causasList.add("animal");
                } else {
                    causasList.remove("animal");
                }
                break;
            case R.id.criancaOngId:
                if (checked) {
                    causasList.add("crianca");
                } else {
                    causasList.remove("crianca");
                }
                break;
            case R.id.idosoOngId:
                if (checked) {
                    causasList.add("idoso");
                } else {
                    causasList.remove("idoso");
                }
                break;
            case R.id.refugiadoOngId:
                if (checked) {
                    causasList.add("refugiado");
                } else {
                    causasList.remove("refugiado");
                }
                break;
            case R.id.meioAmbienteOngId:
                if (checked) {
                    causasList.add("meio ambiente");
                } else {
                    causasList.remove("meio ambiente");
                }
                break;
            case R.id.empoderamentoFemininoOngId:
                if (checked) {
                    causasList.add("empoderamento feminino");
                } else {
                    causasList.remove("empoderamento feminino");
                }
                break;
            case R.id.cegueiraOngId:
                if (checked) {
                    causasList.add("cegueira");
                } else {
                    causasList.remove("cegueira");
                }
                break;
            case R.id.paralisiaCerebralOngId:
                if (checked) {
                    causasList.add("paralisia cerebral");
                } else {
                    causasList.remove("paralisia cerebral");
                }
                break;
        }
    }

    public void onCheckboxClickedOng(View view) {


    }

    private void cadastrarOng(){
        if (Validacao.isCNPJ(ong.getCnpj())) {
            ong.salvar(getActivity(), ong);
           // Log.d(TAG, "cnpj valido");
        }
        else{
            Toast.makeText(getActivity(), "CNPJ Inválido", Toast.LENGTH_LONG).show();
          //  Log.d(TAG, "cnpj inválido");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Inserir Ong");

    }



}