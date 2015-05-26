package com.ufpimaps.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ufpimaps.R;
import com.ufpimaps.controllers.TestConnection;
import com.ufpimaps.models.GeoPointsDatabase;

/**
 * Classe Main Activy que gerencia a interface principal da aplicacao e delega as atividades do
 * Drawer ao Navigation Drawer
 */
public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        AnchorsFragment.OnFragmentInteractionListener {


    /**
     * Fragmento gerenciador dos comportamentos, interacoes e apresentacao do Navigation Drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Objeto que armazena o titulo da ultima tela utilizada.
     */
    private CharSequence mTitle;

    /**
     * Gerenciador de Fragmentos
     */
    //private FragmentManager fragmentManager = getSupportFragmentManager();

    /**
     * Fragmento generico que origina os fragmentos gerado pelo Navigation Drawer.
     */
    private Fragment mainFragment = null;

    /**
     * Pacote que armazena os argumentos enviados a um fragmento quando necessario
     */
    private Bundle args = new Bundle();

    /**
     * Objeto que manipula o banco de dados interno
     */

    private GeoPointsDatabase geoPointsDatabase = new GeoPointsDatabase(this);

    public static final int TELA_ALERTA_TENTATIVA_1 = 1;
    public static final int TELA_ALERTA_TENTATIVA_2 = 2;

    private TestConnection testaConexao;


    /**
     * Metodo executado na criacao da activity main (principal) e seta todos os parametros
     * necessarios para a sua execucao
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Inicia a super classe onCreate da Activity
         */
        super.onCreate(savedInstanceState);

        /**
         * Seta a View principal
         */
        setContentView(R.layout.activity_main);

        testaConexao = new TestConnection(this);


        /**
         * Inicializa o fragmento do Navigation Drawer com o layout pre definido
         */
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        /**
         * Recebe o titulo da ultima tela armazenada
         */
        mTitle = getTitle();

        /**
         * Metodo que seta o Drawer
         */
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        if(testaConexao.isConnected()){
            Log.v("TestaConexao" , "Está conectado");
            geraMapa();
        }else{
            Intent iniciarWifi = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
            criarTelaDeAlerta("Sem conexão", "Inciar Conexão WiFi?", iniciarWifi, null, 1);
        }
        /**
         * Popular o banco de dados interno
         */

        //geoPointsDatabase.populateDatabase();
        //List<Node> nodes = geoPointsDatabase.getAllNodes();
        //for (Node n : nodes) {
        //    Log.d("Nó " + n.getIdNode(), "Desc:" + n.getDescription());
        //}


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
        /**
         * Metodo que seta o primeira fragmento que ira aparecer quando a aplicacao for inicializada.
         */
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
                mainFragment = new MapFragment();
                args.putInt("mapType", position);
                mainFragment.setArguments(args);
                break;
            case 5:
                mainFragment = new FeedbackFragment();
                break;
            case 6:
                mainFragment = new AboutFragment();
                break;
        }

        /**
         * Troca o fragmento atual pelo fragmento selecionado no Navigation Drawer
         */
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mainFragment)
                .commit();

    }

    /**
     * Seta os titulos das secoes do navigation drawer
     *
     * @param number Numero da Secao de cada titulo
     */
    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section_anchors);
                break;
            case 2:
                mTitle = getString(R.string.title_section_trace_routes);
                break;
            case 3:
                mTitle = getString(R.string.title_section_normal_map);
                break;
            case 4:
                mTitle = getString(R.string.title_section_satelite_map);
                break;
            case 5:
                mTitle = getString(R.string.title_section_hibrid_map);
                break;
            case 6:
                mTitle = getString(R.string.title_section_feedback);
                break;
            case 7:
                mTitle = getString(R.string.title_section_about);
                break;
        }
    }

    /**
     * Metodo para restaurar a action bar
     */
    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    /**
     * Metodo para a Criar o Menu de Opcoes na Action Bar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Metodo que retorna o item selecionado no Menu de Opcoes na Action Bar
     * @param item Item selecionado
     * @return
     */
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


    /**
     * Metodo que recebe as interacoes do fragment
     * @param id
     */
    @Override
    public void onFragmentInteraction(String id) {

    }
}