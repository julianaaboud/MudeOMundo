package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.model.Ong;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

import static com.mudeomundo.aplicativo.mudeomundo.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Ong> listOng = Ong.getInstance().getOngList();
    private Array arrayDeOng;
    private static String TAG = MapsActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

    }

  /*  private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            mMarker = mMap.addMarker(new MarkerOptions().position(loc));
            if(mMap != null){
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
            }
        }
    };*/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        String enderecoOng;
        String nomeOng;
        String telefoneOng;
        //Laço para pegar o endereço
        for (Ong ong : listOng) {
            enderecoOng = ong.getEndereco() + " " + ong.getCidade();
            nomeOng = ong.getNome();
            telefoneOng = ong.getTelefone();
            Log.d(TAG, "endereco ong " + enderecoOng);
            LatLng posicaoOngs = pegaCoordenadaDoEndereco(enderecoOng);
            Log.d(TAG, "posicao ong " + posicaoOngs);

            googleMap.addMarker(new MarkerOptions().position(posicaoOngs).title(nomeOng).snippet(telefoneOng));
        }

        mMap = googleMap;

        LatLng posicaoOng = pegaCoordenadaDoEndereco("Rua Guararema 41, Vila Gumercindo São Paulo");
        if (posicaoOng != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoOng, 10);
            googleMap.moveCamera(update);
        }
        Ong ong = new Ong();

        Log.d(TAG, " ENDERECO >>>>>> " + ong.getEndereco());
        LatLng coordenada = pegaCoordenadaDoEndereco(ong.getEndereco() + " " + ong.getCidade());
        Log.d(TAG, "coordenada " + coordenada);
        if (coordenada != null) {
            MarkerOptions marcador = new MarkerOptions();
            marcador.position(coordenada);
            marcador.title(ong.getNome());
            googleMap.addMarker(marcador);
        }
    }

    private LatLng pegaCoordenadaDoEndereco(String endereco) {

        try {
            Geocoder geocoder = new Geocoder(getApplicationContext());
            List<Address> resultados = geocoder.getFromLocationName(endereco, 1);
            if (!resultados.isEmpty()) {
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
        return null;
    }
}
