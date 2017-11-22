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
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static String TAG = MapsActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng posicaoOng = pegaCoordenadaDoEndereco("Rua Guararema 41, Vila Gumercindo, São Paulo");
        if (posicaoOng != null){
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoOng, 17);
            googleMap.moveCamera(update);
        }
        Ong ong = new Ong();

        Log.d(TAG, " ENDERECO >>>>>> " + ong.getEndereco());
            LatLng coordenada = pegaCoordenadaDoEndereco(ong.getEndereco());
            if (coordenada != null){
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenada);
                marcador.title(ong.getNome());
                googleMap.addMarker(marcador);
            }


      //  mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private LatLng pegaCoordenadaDoEndereco(String endereco){

        try {
            Geocoder geocoder = new Geocoder(getApplicationContext());
            List<Address> resultados = geocoder.getFromLocationName("Rua Guararema 41, Vila Gumercindo, São Paulo", 1);
            if(!resultados.isEmpty()){
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }
        }catch (IOException e){
            Log.d (TAG, e.getMessage());
        }
        return null;
    }
}
