package com.ufpimaps.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ufpimaps.R;

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
    private FragmentManager fragmentManager = getSupportFragmentManager();

    /**
     * Fragmento generico que origina os fragmentos gerado pelo Navigation Drawer.
     */
    private Fragment mainFragment = null;

    /**
     * Pacote que armazena os argumentos enviados a um fragmento quando necessario
     */

    private Bundle args = new Bundle();

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
                args.putInt("tipoDeMapa", position);
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

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section_ancoras);
                break;
            case 2:
                mTitle = getString(R.string.title_section_tracar_rotas);
                break;
            case 3:
                mTitle = getString(R.string.title_section_mapa_normal);
                break;
            case 4:
                mTitle = getString(R.string.title_section_mapa_satelite);
                break;
            case 5:
                mTitle = getString(R.string.title_section_mapa_hibrido);
                break;
            case 6:
                mTitle = getString(R.string.title_section_feedback);
                break;
            case 7:
                mTitle = getString(R.string.title_section_about);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


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

    @Override
    public void onFragmentInteraction(String id) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
