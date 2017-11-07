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

public class InserirAcaoFragment extends Fragment {
    private EditText nome;
    private EditText endereco;
    private EditText telefone;
    private EditText email;
    private EditText proposito;
    private EditText data;
    private EditText cep;
    private EditText estado;
    private EditText cidade;
    private boolean status;
    private TextView botaoCadastrarAcao;
    private Acao acao;
    private static String TAG = InserirAcaoFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_acao, container, false);

        nome = (EditText) rootView.findViewById(R.id.nomeAcaoId);
        cep = (EditText) rootView.findViewById(R.id.cepAcaoId);
        cidade = (EditText) rootView.findViewById(R.id.cidadeAcaoId);
        estado = (EditText) rootView.findViewById(R.id.estadoAcaoId);
        telefone = (EditText) rootView.findViewById(R.id.telefoneAcaoId);
        email = (EditText) rootView.findViewById(R.id.emailAcaoId);
        data = (EditText) rootView.findViewById(R.id.dataAcaoId);
        endereco = (EditText) rootView.findViewById(R.id.enderecoAcaoId);
        proposito = (EditText) rootView.findViewById(R.id.propositoAcaoId);
        botaoCadastrarAcao = (TextView) rootView.findViewById(R.id.cadastrarAcaoId);

        botaoCadastrarAcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acao = new Acao();
                acao.setNome(nome.getText().toString());
                acao.setEndereco(endereco.getText().toString());
                acao.setCep(cep.getText().toString());
                acao.setCidade(cidade.getText().toString());
                acao.setEstado(estado.getText().toString());
                acao.setTelefone(telefone.getText().toString());
                acao.setEmail(email.getText().toString());
                acao.setProposito(proposito.getText().toString());
                acao.setData(data.getText().toString());
                cadastrarAcao();
                Log.d(TAG, "onCreateView");
            }
        });
        return rootView;
    }

    private void cadastrarAcao(){
        acao.salvar(getActivity());
        // Log.d(TAG, "cadastrarAcao");
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Inserir Ação");
    }

}
