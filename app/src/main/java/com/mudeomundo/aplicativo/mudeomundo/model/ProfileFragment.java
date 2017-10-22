package com.mudeomundo.aplicativo.mudeomundo.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;

public class ProfileFragment extends Fragment {
    private Bundle bundle;
    private String token;
    private EditText nome;
    private EditText cep;
    private EditText cidade;
    private EditText estado;
    private EditText telefone;
    private EditText email;
    private EditText senha;

    private FirebaseAuth autenticacao;
    private FirebaseDatabase database;
    DatabaseReference referenceUsuario;
    private Usuario usuario;


    private static final String TAG = ProfileFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bundle = getArguments();
        token = bundle.getString("token");
        database = FirebaseDatabase.getInstance();
        //    referenceUsuario = database.getReference("usuario");
        referenceUsuario = ConfiguracaoFirebase.getFirebase();

        Log.d(TAG, "onCreateView token:" + token);

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        nome = (EditText) rootView.findViewById(R.id.nomeTextViewUsuarioLogado);
        cep = (EditText) rootView.findViewById(R.id.cepTextViewUsuarioLogado);
        cidade = (EditText) rootView.findViewById(R.id.cidadeTextViewUsuarioLogado);
        estado = (EditText) rootView.findViewById(R.id.estadoTextViewUsuarioLogado);
        telefone = (EditText) rootView.findViewById(R.id.telefoneTextViewUsuarioLogado);
        email = (EditText) rootView.findViewById(R.id.emailTextViewUsuarioLogado);

        referenceUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    snapshot = dataSnapshot.child("usuarios");

                    Iterable<DataSnapshot> userChildren = snapshot.getChildren();

                    for (DataSnapshot user: userChildren){
                        if(user.getKey().equals(token)){
                            String key = user.getValue().toString();
                            Log.d(TAG, "onDataChange key: " + key);

                            usuario = user.getValue(Usuario.class);
                            Log.d(TAG, "onDataChange key: " + usuario.getNome());

                            nome.setText(usuario.getNome());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu Profile");

    }

}
