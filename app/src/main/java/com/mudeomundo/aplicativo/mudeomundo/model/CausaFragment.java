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
    private ArrayList<String> causasList = new ArrayList<>();

    private static final String TAG = CausaFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        bundle = getArguments();
        token = bundle.getString("token");
        database = FirebaseDatabase.getInstance();
        referenceUsuario = ConfiguracaoFirebase.getFirebase();
        animal = animal.findViewById(R.id.animalCausaId);
        referenceUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    snapshot = dataSnapshot.child("usuarios").child(token).child("causas");
                    Log.d(TAG, "snapshot" + snapshot);

                    Iterable<DataSnapshot> userChildren = snapshot.getChildren();

                    for (DataSnapshot user: userChildren){
                        if(user.getKey().equals(token)){
                            String key = user.getValue().toString();
                            Log.d(TAG, "onDataChange key: " + key);

                            usuario = user.getValue(Usuario.class);
                            //Log.d(TAG, "onDataChange key: " + usuario.getNome());

                            animal.isChecked();

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
        getActivity().setTitle("Menu Causa");
    }

}