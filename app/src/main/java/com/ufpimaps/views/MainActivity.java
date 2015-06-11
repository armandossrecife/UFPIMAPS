package com.ufpimaps.views;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ufpimaps.R;
import com.ufpimaps.controllers.TestConnection;
import com.ufpimaps.models.ApplicationObject;
import com.ufpimaps.models.GeoPointsDatabase;

/**
 * Classe Main Activy que gerencia a interface principal da aplicacao e delega as atividades do
 * Drawer ao Navigation Drawer
 */
public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        AnchorsFragment.OnFragmentInteractionListener{

    private static final int TELA_ALERTA_TENTATIVA_1 = 1;
    private static final int TELA_ALERTA_TENTATIVA_2 = 2;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private int mCurrentSelectedPosition = 0;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private Fragment mainFragment = null;
    private Bundle args = new Bundle();
    private GeoPointsDatabase geoPointsDatabase = new GeoPointsDatabase(this);
    private TestConnection testaConexao;

    /**
     * Metodo executado na criacao da activity main (principal) e seta todos os parametros
     * necessarios para a sua execucao
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testaConexao = new TestConnection(this);

        mDrawerList = (ListView) findViewById(R.id.navigation_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if(testaConexao.isConnected()){
            Log.v("TestaConexao" , "Está conectado");
            geraMapa();
        }else{
            Intent iniciarWifi = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
            criarTelaDeAlerta("Sem conexão", "Inciar Conexão WiFi?", iniciarWifi, null, 1);
        }
    }

    private void addDrawerItems() {
        String[] titles = {
                getString(R.string.title_section_anchors),
                getString(R.string.title_section_trace_routes),
                getString(R.string.title_section_normal_map),
                getString(R.string.title_section_satelite_map),
                getString(R.string.title_section_hibrid_map),
                getString(R.string.title_section_feedback),
                getString(R.string.title_section_about)
        };
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setItemChecked(mCurrentSelectedPosition, true);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("UFPI Maps");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerList.setItemChecked(mCurrentSelectedPosition, true);

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void criarTelaDeAlerta(String titulo, String mensagem, Intent acaoPositiva, Intent acaoNegativa, int tentativa){
        Intent iniciarTelaDeAlerta = new Intent(this, AlertScreen.class );
        iniciarTelaDeAlerta.putExtra("titulo", titulo);
        iniciarTelaDeAlerta.putExtra("mensagem", mensagem);
        iniciarTelaDeAlerta.putExtra("acaoPositiva", acaoPositiva);
        iniciarTelaDeAlerta.putExtra("acaoNegativa", acaoNegativa);
        startActivityForResult(iniciarTelaDeAlerta, tentativa);
    }

    @Override
    protected void onActivityResult(int tipoDeConexaoRequisitada, int resultado, Intent dadosRetornados) {
        super.onActivityResult(tipoDeConexaoRequisitada, resultado, dadosRetornados);

        if(tipoDeConexaoRequisitada == TELA_ALERTA_TENTATIVA_1){
            if(resultado == RESULT_OK){
                if(testaConexao.isConnected()){
                    geraMapa();
                }else{
                    Toast.makeText(this, "Sem conexão Wifi", Toast.LENGTH_LONG).show();
                    Intent iniciarRedesMoveis = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    criarTelaDeAlerta("Sem Conexão", "Iniciar Conexão via Redes Móveis?", iniciarRedesMoveis, null, TELA_ALERTA_TENTATIVA_2);
                }
            }else{
                Toast.makeText(this, "Sem conexão Wifi", Toast.LENGTH_LONG).show();
                Intent iniciarRedesMoveis = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                criarTelaDeAlerta("Sem Conexão", "Iniciar Conexão via Redes Móveis?", iniciarRedesMoveis, null, TELA_ALERTA_TENTATIVA_2);
            }
        }else if(tipoDeConexaoRequisitada == TELA_ALERTA_TENTATIVA_2){
            if(resultado == RESULT_OK){
                if(testaConexao.isConnected()){
                    geraMapa();
                }else{
                    Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_LONG).show();
                    finish();
                }
            }else{
                Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_LONG).show();
                finish();
            }
        }

    }

    private void geraMapa(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MapFragment())
                .commit();
    }
    /**
     * Metodo que recebe indica o item selecionado no Navigation Drawer e substitui o fragmento
     * que e representado por essa posicao
     * @param position Posicao selecionada no Navigation Drawer
     */
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        switch (position){
            case 0:
                mainFragment = new AnchorsFragment();
                break;
            case 1:
                mainFragment = new TraceRouteFragment();
                break;
            case 2:case 3:case 4:
                mainFragment = ((ApplicationObject) getApplication()).mapa;//new MapFragment();
                if (((ApplicationObject) getApplication()).mapa.getArguments() == null) {
                    args.putInt("mapType", position);
                    mainFragment.setArguments(args);
                } else {
                    ((ApplicationObject) getApplication()).mapa.getArguments().putInt("mapType", position);
                }
                break;
            case 5:
                mainFragment = new FeedbackFragment();
                break;
            case 6:
                mainFragment = new AboutFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mainFragment)
                .commit();
    }

    /**
     * Metodo que recebe as interacoes do fragment
     * @param id
     */
    @Override
    public void onFragmentInteraction(String id) {

    }

    protected GeoPointsDatabase getGeoPointsDatabase(){
        return geoPointsDatabase;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void selectItem(int position) {
        if (mDrawerList != null) {
            mDrawerList.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawers();
        }
        onNavigationDrawerItemSelected(position);
    }
}