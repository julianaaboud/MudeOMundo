package com.mudeomundo.aplicativo.mudeomundo.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;

import java.util.ArrayList;

public class CausaFragment extends Fragment {
    private Bundle bundle;
    private String token;
    private FirebaseAuth autenticacao;
    private FirebaseDatabase database;
    DatabaseReference referenceUsuario;
    private Usuario usuario;
    private CheckBox animal;
    private CheckBox crianca;
    private CheckBox idoso;
    private CheckBox refugiado;
    private CheckBox meioAmbiente;
    private CheckBox cegueira;
    private CheckBox empoderamentoFeminino;
    private CheckBox paralisiaCerebral;

    private ArrayList<String> causasList = new ArrayList<>();

    private static final String TAG = CausaFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_causa, container, false);
        bundle = getArguments();
        token = bundle.getString("token");
        database = FirebaseDatabase.getInstance();
        referenceUsuario = ConfiguracaoFirebase.getFirebase();
        animal = rootView.findViewById(R.id.animalCausaId);
        crianca = rootView.findViewById(R.id.criancaCausaId);
        idoso = rootView.findViewById(R.id.idosoCausaId);
        refugiado = rootView.findViewById(R.id.refugiadoCausaId);
        meioAmbiente = rootView.findViewById(R.id.meioAmbienteCausaId);
        cegueira = rootView.findViewById(R.id.cegueiraCausaId);
        empoderamentoFeminino = rootView.findViewById(R.id.empoderamentoFemininoCausaId);
        paralisiaCerebral = rootView.findViewById(R.id.paralisiaCerebralCausaId);

        referenceUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                DataSnapshot data = dataSnapshot.child("usuarios").child(token).child("causas");
                Log.d(TAG, "usuarios " + data);

                causasList = (ArrayList<String>) data.getValue();
                for (String causa: causasList){
                    switch (causa){
                        case "animal":
                            animal.setChecked(true);
                            break;
                        case "crianca":
                            crianca.setChecked(true);
                            break;
                        case "idoso":
                            idoso.setChecked(true);
                            break;
                        case "meio ambiente":
                            meioAmbiente.setChecked(true);
                            break;
                        case "empoderamento feminino":
                            empoderamentoFeminino.setChecked(true);
                            break;
                        case "refugiado":
                            refugiado.setChecked(true);
                            break;
                        case "cegueira":
                            cegueira.setChecked(true);
                            break;
                        case "paralisia cerebral":
                            paralisiaCerebral.setChecked(true);
                            break;
                    }

                }
                Log.d(TAG, "Causas " + causasList.toString());
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
        getActivity().setTitle("Minhas Causas");
    }

}