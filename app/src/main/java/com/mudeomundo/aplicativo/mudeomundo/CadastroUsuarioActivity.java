package com.mudeomundo.aplicativo.mudeomundo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;
import com.mudeomundo.aplicativo.mudeomundo.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText dt_nasc;
    private EditText cep;
    private EditText cidade;
    private EditText estado;
    private EditText telefone;
    private EditText email;
    private EditText senha;
    private RadioButton sexofem;
    private RadioButton sexomasc;
    private Button botaoCadastrar;
    private Usuario usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = (EditText) findViewById(R.id.editTextNomeId);
        dt_nasc = (EditText) findViewById(R.id.editTextDtNascId);
        cep = (EditText) findViewById(R.id.editTextCepId);
        cidade = (EditText) findViewById(R.id.editTextCidadeId);
        estado = (EditText) findViewById(R.id.editTextEstadoId);
        telefone = (EditText) findViewById(R.id.editTextTelefoneId);
        email = (EditText) findViewById(R.id.editTextEmailId);
        senha = (EditText) findViewById(R.id.editTextSenhaId);
        sexofem = (RadioButton) findViewById(R.id.radioButtonFemId);
        sexomasc = (RadioButton) findViewById(R.id.radioButtonMascId);
        botaoCadastrar = (Button) findViewById(R.id.buttonCadastrarId);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setDt_nasc(dt_nasc.getText().toString());
                usuario.setCep(cep.getText().toString());
                usuario.setCidade(cidade.getText().toString());
                usuario.setEstado(estado.getText().toString());
                usuario.setTelefone(telefone.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                usuario.setSexofem(sexofem.getText().equals(true));
                usuario.setSexomasc(sexomasc.getText().equals(true));
                cadastrarUsuario();
            }
        });
    }


    private void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroUsuarioActivity.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_LONG).show();

                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId(usuarioFirebase.getUid());
                    usuario.salvar();

                    autenticacao.signOut();
                    finish();

                } else {

                    String erroExcecao = "";

                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        erroExcecao = "Digita uma senha mais forte, contendo mais caracteres e com letras e numeros!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao = "O e-mail digitado é inválido, digite um novo e-mail!";
                    }catch (FirebaseAuthUserCollisionException e){
                        erroExcecao = "Este e-mail já está em uso!";
                    }catch (Exception e){
                        erroExcecao = "ao efetuar cadastro!";
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroUsuarioActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();

                   Log.i("banco", "Erro ao cadastrar usuário" + task.getException());
                }
            }
        });
    }
}
