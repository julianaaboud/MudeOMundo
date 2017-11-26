package com.mudeomundo.aplicativo.mudeomundo.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.controller.OngAdapter;

import java.util.List;


public class BuscaOngFragment extends Fragment {
    private Button botaoBuscaNome;
    private Button botaoBuscaMinhaLocalizacao;
    private EditText nomeOng;
    private ListView listaOng;
    private static String TAG = BuscaOngFragment.class.getName();
    private RecyclerView recyclerViewOng;
    private RecyclerView.LayoutManager ongLayoutManager;
    private OngAdapter ongAdapter;
    private List<Ong> listOng = Ong.getInstance().getOngList();
    private List<Ong> novaListOng;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_busca_ong, container, false);

        listaOng = (ListView) rootView.findViewById(R.id.listViewId);
        nomeOng = (EditText) rootView.findViewById(R.id.editTextBuscaNomeIdFragment);
        recyclerViewOng = (RecyclerView) rootView.findViewById(R.id.recyclerFragmentOng);
        listOng = Ong.getInstance().getOngList();

        //Setando Adapter
        ongAdapter = new OngAdapter(getActivity(), listOng);
        recyclerViewOng.setAdapter(ongAdapter);

        //Setando Geranciador de Layout
        ongLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewOng.setLayoutManager(ongLayoutManager);

       /* botaoBuscaMinhaLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MapsActivity.class));
            }
        });*/

        botaoBuscaNome = (Button) rootView.findViewById(R.id.botaoBuscaNomeIdFragment);
        botaoBuscaNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeOngBusca = nomeOng.getText().toString();
                Log.d(TAG, "nomeOng " + nomeOng.getText().toString());
                for (Ong ong: listOng){
                    Log.d(TAG, "ong listOng " + ong.getNome());
                    String nomeOngBanco = ong.getNome();
                    if ( nomeOngBanco == nomeOngBusca){
                        Log.d(TAG, "entrou no if ");
                        Ong novaOng = ong;
                        Log.d(TAG, "novaOng " + novaOng);
                        novaListOng.add(novaOng);
                        Log.d(TAG, "novaList " + novaListOng);
                    }
                    else{
                        Toast.makeText(getActivity(), "Nenhuma ONG foi encontrada", Toast.LENGTH_SHORT).show();
                    }
                }
                ongAdapter = new OngAdapter(getActivity(), novaListOng);
                recyclerViewOng.setAdapter(ongAdapter);
                ongAdapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        ongAdapter.notifyDataSetChanged();
    }

    /*    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private OngAdapter ongAdapter;
    private RecyclerView.LayoutManager ongLayoutManager;
    private RecyclerView recyclerViewOng;
    private List<Ong> listOng;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

     public BuscaOngFragment() {
           // Required empty public constructor
       }

       /**
        * Use this factory method to create a new instance of
        * this fragment using the provided parameters.
        *
        * @param param1 Parameter 1.
        * @param param2 Parameter 2.
        * @return A new instance of fragment BuscaOngFragment.

    // TODO: Rename and change types and number of parameters
    public static BuscaOngFragment newInstance(String param1, String param2) {
        BuscaOngFragment fragment = new BuscaOngFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_busca_ong, container, false);

        recyclerViewOng = (RecyclerView) rootView.findViewById(R.id.recyclerFragmentOng);
        listOng = Ong.getInstance().getOngList();

        //Setando Adapter
        ongAdapter = new OngAdapter(getActivity(), listOng);
        recyclerViewOng.setAdapter(ongAdapter);

        //Setando Geranciador de Layout
        ongLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewOng.setLayoutManager(ongLayoutManager);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
