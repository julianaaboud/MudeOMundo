package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.model.Acao;
import com.mudeomundo.aplicativo.mudeomundo.model.Ong;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

import static com.mudeomundo.aplicativo.mudeomundo.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Ong> listOng = Ong.getInstance().getOngList();
    private List<Acao> listAcao = Acao.getInstance().getAcaoList();
    private Array arrayDeOng;
    private static String TAG = MapsActivity.class.getName();
    private Location currentLocation = null;
    private LocationManager locationManager;
    private Location loc;

  private LocationListener locationListener =
            new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    currentLocation = location;

                }

                 @Override
                public void onStatusChanged(String provider, int status,
                                            Bundle extras) {
                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {
                }
            };

    /* private void drawMarker(Location location) {
        if (mMap != null) {
            mMap.clear();
            LatLng gps = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(gps)
                    .title("Current Position"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gps, 12));
        }

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //loc = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]
            permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_GPS:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) ==
                            PackageManager.PERMISSION_GRANTED) {locationManager.requestLocationUpdates
                            (LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    }
                }
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        //verifica se a permissão ainda não foi concedida pelo usuário
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //verifica se deve-se exibir uma explicação sobre a necessidade da permissão
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "Para exibir coordenadas o app precisa do GPS",
                        Toast.LENGTH_SHORT).show();
            }
            //pede permissão
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_GPS);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    private static final int REQUEST_GPS = 1000;

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

            googleMap.addMarker(new MarkerOptions().position(posicaoOngs).title(nomeOng).snippet(telefoneOng).icon(BitmapDescriptorFactory.defaultMarker()));
        }
        String enderecoAcao;
        String nomeAcao;
        for (Acao acao : listAcao) {
            enderecoAcao = acao.getEndereco() + " " + acao.getCidade();
            nomeAcao = acao.getNome();
            LatLng posicaoAcao = pegaCoordenadaDoEndereco(enderecoAcao);
            googleMap.addMarker(new MarkerOptions().position(posicaoAcao).title(nomeAcao).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
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
