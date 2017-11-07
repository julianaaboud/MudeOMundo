package com.mudeomundo.aplicativo.mudeomundo.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mudeomundo.aplicativo.mudeomundo.R;

public class InserirOngFragment extends Fragment {

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
                cadastrarOng();
                Log.d(TAG, "onCreateView");
            }
        });
        return rootView;
    }

    private void cadastrarOng(){
        ong.salvar(getActivity());
       // Log.d(TAG, "cadastrarOng");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Inserir Ong");


    }



}