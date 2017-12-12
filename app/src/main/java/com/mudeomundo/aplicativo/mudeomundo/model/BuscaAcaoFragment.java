package com.mudeomundo.aplicativo.mudeomundo.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.controller.AcaoAdapter;

import java.util.List;

public class BuscaAcaoFragment extends Fragment {

    private AcaoAdapter acaoAdapter;
    private RecyclerView recyclerViewAcao;
    private RecyclerView.LayoutManager acaoLayoutManager;
    private List<Acao> listAcao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_busca_acao, container, false);

        recyclerViewAcao = (RecyclerView) rootView.findViewById(R.id.recyclerFragmentAcao);
        listAcao = Acao.getInstance().getAcaoList();

        //Setando Adapter
        acaoAdapter = new AcaoAdapter(getActivity(), listAcao);
        recyclerViewAcao.setAdapter(acaoAdapter);

        //Setando Geranciador de Layout
        acaoLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewAcao.setLayoutManager(acaoLayoutManager);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Lista de Ações Voluntárias");
    }

    /*// TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private AcaoAdapter acaoAdapter;
    private RecyclerView recyclerViewAcao;
    private RecyclerView.LayoutManager acaoLayoutManager;
    private static String TAG = BuscaAcaoActivity.class.getName();
    private List<Acao> listAcao;

    *//**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuscaAcaoFragment.
     *//*
    // TODO: Rename and change types and number of parameters
    public static BuscaAcaoFragment newInstance(String param1, String param2) {
        BuscaAcaoFragment fragment = new BuscaAcaoFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_busca_acao, container, false);

        recyclerViewAcao = (RecyclerView) rootView.findViewById(R.id.recyclerFragmentAcao);
        listAcao = Acao.getInstance().getAcaoList();

        //Setando Adapter
        acaoAdapter = new AcaoAdapter(getActivity(), listAcao);
        recyclerViewAcao.setAdapter(acaoAdapter);

        //Setando Geranciador de Layout
        acaoLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewAcao.setLayoutManager(acaoLayoutManager);

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
        acaoAdapter.notifyDataSetChanged();
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

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
