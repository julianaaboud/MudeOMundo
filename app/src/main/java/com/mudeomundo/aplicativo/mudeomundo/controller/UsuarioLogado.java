package com.mudeomundo.aplicativo.mudeomundo.controller;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mudeomundo.aplicativo.mudeomundo.R;
import com.mudeomundo.aplicativo.mudeomundo.config.ConfiguracaoFirebase;
import com.mudeomundo.aplicativo.mudeomundo.model.Usuario;


public class UsuarioLogado extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth autenticacao;
    private Usuario usuario;
    private String contaUsuarioId;
    private TextView nomeUsuarioTextView;
    private FirebaseDatabase database;
    DatabaseReference referenceUsuario;
    private TextView nomeTextView;
    private TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_logado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        database = FirebaseDatabase.getInstance();
        referenceUsuario = database.getReference("usuario");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open

                , R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        // carrega dados Usuario
        emailTextView = (TextView) findViewById(R.id.emailTextViewUsuarioLogado);
        nomeTextView = (TextView) findViewById(R.id.nomeTextViewUsuarioLogado);
            autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        emailTextView.setText(autenticacao.getCurrentUser().getEmail());
        nomeTextView.setText(autenticacao.getCurrentUser().getNome());

        /*if (autenticacao.getCurrentUser() != null){
            email = autenticacao.getCurrentUser().getEmail();
            emailTextView.setText(email);
        }else{
            Toast.makeText(this, "Erro na autenticação", Toast.LENGTH_SHORT).show();
        }*/

    }


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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_conta) {
         //   configurarValoresNosCamposVisuais();

        } else if (id == R.id.nav_causa) {

        } else if (id == R.id.nav_inserir) {

        } else if (id == R.id.nav_avaliar) {

        } else if (id == R.id.duvidas) {

        } else if (id == R.id.sobre) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /*  public void onDataChange(DataSnapshot dataSnapshot) {
        Usuario usuario = dataSnapshot.getValue(Usuario.class);
        Usuario.getInstance().setNome(usuario.getNome());
        configurarValoresNosCamposVisuais();

    }

        private void configurarValoresNosCamposVisuais() {
        nomeUsuarioTextView.setText(Usuario.getInstance().getNome());
    }*/
}