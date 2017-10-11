package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.model.AvaliarFragment;
import com.mudeomundo.aplicativo.mudeomundo.model.CausaFragment;
import com.mudeomundo.aplicativo.mudeomundo.model.InserirFragment;
import com.mudeomundo.aplicativo.mudeomundo.model.ProfileFragment;
import com.mudeomundo.aplicativo.mudeomundo.model.Usuario;


public class UsuarioLogado extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth autenticacao;
    private FirebaseDatabase database;
    DatabaseReference referenceUsuario;
    private Usuario usuario;
    private EditText nomeEditText, emailEditText, dtNascEditText, cepEditText, cidadeEditText, estadoEditText, telefoneEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_logado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emailEditText = (EditText) findViewById(R.id.emailTextViewUsuarioLogado);
        nomeEditText = (EditText) findViewById(R.id.nomeTextViewUsuarioLogado);
        dtNascEditText = (EditText) findViewById(R.id.dtNascTextViewUsuarioLogado);
        cepEditText = (EditText) findViewById(R.id.cepTextViewUsuarioLogado);
        cidadeEditText = (EditText) findViewById(R.id.cidadeTextViewUsuarioLogado);
        estadoEditText = (EditText) findViewById(R.id.estadoTextViewUsuarioLogado);
        telefoneEditText = (EditText) findViewById(R.id.telefoneTextViewUsuarioLogado);

    /*
        referenceUsuario = database.getReference("usuarios");

        referenceUsuario = ConfiguracaoFirebase.getFirebase();
        referenceUsuario = database.getReference("usuarios");*/

        database = FirebaseDatabase.getInstance();
        referenceUsuario = FirebaseDatabase.getInstance().getReference().child("usuarios");

        referenceUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.child("usuarios").getValue(Usuario.class);
                //System.out.println(usuario);
                //String nomeUsuario = usuario.getNome();
                /*Usuario.getInstance().setNome(usuario.getNome());
                Usuario.getInstance().setCep(usuario.getCep());
                Usuario.getInstance().setCidade(usuario.getCidade());
                Usuario.getInstance().setEstado(usuario.getEstado());
                Usuario.getInstance().setTelefone(usuario.getTelefone());
                Usuario.getInstance().setEmail(usuario.getEmail());
                configurarValoresNosCamposVisuais();*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open

                , R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);



    }

        @Override
            protected void onResume() {
            super.onResume();
           // configurarValoresNosCamposVisuais();
        }

   /*     private void configurarValoresNosCamposVisuais(){
            nomeEditText.setText(Usuario.getInstance().getNome());
            telefoneEditText.setText(Usuario.getInstance().getTelefone());
            cidadeEditText.setText(Usuario.getInstance().getCidade());
            estadoEditText.setText(Usuario.getInstance().getEstado());
            cepEditText.setText(Usuario.getInstance().getCep());

        }
*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.usuario_logado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        // Handle navigation view item clicks here.
       /* int id = item.getItemId();

        if (id == R.id.nav_conta) {
         //   configurarValoresNosCamposVisuais();

        } else if (id == R.id.nav_causa) {


        } else if (id == R.id.nav_inserir) {

        } else if (id == R.id.nav_avaliar) {

        } else if (id == R.id.duvidas) {

        } else if (id == R.id.sobre) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_conta:
                fragment = new ProfileFragment();
                break;
            case R.id.nav_causa:
                fragment = new CausaFragment();
                break;
            case R.id.nav_inserir:
                fragment = new InserirFragment();
                break;
            case R.id.nav_avaliar:
                fragment = new AvaliarFragment();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}