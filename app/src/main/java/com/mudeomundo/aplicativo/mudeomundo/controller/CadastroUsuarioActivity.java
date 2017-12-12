package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;
import com.mudeomundo.aplicativo.mudeomundo.model.Usuario;

import java.util.ArrayList;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText dt_nasc;
    private EditText cep;
    private EditText endereco;
    private EditText cidade;
    private EditText estado;
    private EditText telefone;
    private EditText email;
    private EditText senha;
    private String sexo;
    private RadioButton sexofem;
    private TextView botaoCadastrar;
    private Usuario usuario;
    private ArrayList<String> causasList = new ArrayList<>();

    private static final String TAG = CadastroUsuarioActivity.class.getName();
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = (EditText) findViewById(R.id.nomeId);
        dt_nasc = (EditText) findViewById(R.id.dtNascId);
        cep = (EditText) findViewById(R.id.cepId);
        endereco = (EditText) findViewById(R.id.enderecoId);
        cidade = (EditText) findViewById(R.id.cidadeId);
        estado = (EditText) findViewById(R.id.estadoId);
        telefone = (EditText) findViewById(R.id.telefoneId);
        email = (EditText) findViewById(R.id.emailId);
        senha = (EditText) findViewById(R.id.senhaId);
        sexofem = (RadioButton) findViewById(R.id.femId);
        botaoCadastrar = (TextView) findViewById(R.id.cadastrarId);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setEndereço(endereco.getText().toString());
                usuario.setDt_nasc(dt_nasc.getText().toString());
                usuario.setCep(cep.getText().toString());
                usuario.setCidade(cidade.getText().toString());
                usuario.setEstado(estado.getText().toString());
                usuario.setTelefone(telefone.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                sexo = (sexofem).isChecked() ? "feminino" : "masculino";
                usuario.setSexo(sexo);
                usuario.setCausas(causasList);

                cadastrarUsuario();
            }
        });

    }


    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.animalId:
                if (checked) {
                    causasList.add("animal");
                } else {
                    causasList.remove("animal");
                }
                break;
            case R.id.criancaId:
                if (checked) {
                    causasList.add("crianca");
                } else {
                    causasList.remove("crianca");
                }
                break;
            case R.id.idosoId:
                if (checked) {
                    causasList.add("idoso");
                } else {
                    causasList.remove("idoso");
                }
                break;
            case R.id.refugiadoId:
                if (checked) {
                    causasList.add("refugiado");
                } else {
                    causasList.remove("refugiado");
                }
                break;
            case R.id.meioAmbienteId:
                if (checked) {
                    causasList.add("meio ambiente");
                } else {
                    causasList.remove("meio ambiente");
                }
                break;
            case R.id.empoderamentoFemininoId:
                if (checked) {
                    causasList.add("empoderamento feminino");
                } else {
                    causasList.remove("empoderamento feminino");
                }
                break;
            case R.id.cegueiraId:
                if (checked) {
                    causasList.add("cegueira");
                } else {
                    causasList.remove("cegueira");
                }
                break;
            case R.id.paralisiaCerebralId:
                if (checked) {
                    causasList.add("paralisia cerebral");
                } else {
                    causasList.remove("paralisia cerebral");
                }
                break;
        }


        Log.d(TAG, "causas status " + causasList.toString());
    }

    private void cadastrarUsuario() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CadastroUsuarioActivity.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_LONG).show();

                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId(usuarioFirebase.getUid());
                    usuario.salvar();

                    autenticacao.signOut();
                    finish();

                } else {

                    String erroExcecao = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExcecao = "Digita uma senha mais forte, contendo mais caracteres e com letras e numeros!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "O e-mail digitado é inválido, digite um novo e-mail!";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "Este e-mail já está em uso!";
                    } catch (Exception e) {
                        erroExcecao = "ao efetuar cadastro!";
                        // e.printStackTrace();
                        //      Log.d("CadastroUsuarioActi", "cadastrarUsuario: " + e.getMessage());
                    }

                    Toast.makeText(CadastroUsuarioActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();

                    //   Log.i("banco", "Erro ao cadastrar usuário" + task.getException());
                }
            }
        });
    }
}
