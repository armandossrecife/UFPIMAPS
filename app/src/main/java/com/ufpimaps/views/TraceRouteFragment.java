package com.ufpimaps.views;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ufpimaps.R;
import com.ufpimaps.interfaces.InterfaceGetGeopoints;
import com.ufpimaps.models.ApplicationObject;
import com.ufpimaps.system.AsyncTaskGetGeopoints;
import com.ufpimaps.system.AsyncTaskTraceRoute;

/**
 * Created by HugoPiauilino on 14/05/15.
 * Classe que cria o Fragment da View Tracar Rotas
 */
public class TraceRouteFragment extends android.support.v4.app.Fragment implements InterfaceGetGeopoints{

    /**
     * View do Trace Route Fragment
     */
    private View traceRouteView;


    /**
     * Button que dispara a acao de tracar uma rota entre dois pontos escolhidos
     */
    private Button traceRouteButton;

    private AutoCompleteTextView originEditText;
    private AutoCompleteTextView destinationEditText;


    private String[] search;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        traceRouteView = inflater.inflate(R.layout.fragment_trace_route, container, false);

        originEditText = (AutoCompleteTextView) traceRouteView.findViewById(R.id.originEditText);
        originEditText.setThreshold(1);
        destinationEditText = (AutoCompleteTextView) traceRouteView.findViewById(R.id.destinationEditText);
        destinationEditText.setThreshold(1);

        AsyncTaskGetGeopoints buscarDescricoes = new AsyncTaskGetGeopoints(this, ((MainActivity)getActivity()).getGeoPointsDatabase());
        buscarDescricoes.execute();

        return traceRouteView;
    }

    @Override
    public void devolverGeopoints(String[] geopoints) {
        search = geopoints;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, search);

        originEditText.setAdapter(adapter);
        destinationEditText.setAdapter(adapter);

        traceRouteButton = (Button) traceRouteView.findViewById(R.id.trace_route_button);
        traceRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origin = originEditText.getText().toString();
                String destination = destinationEditText.getText().toString();
                if (origin.equals("") == true) {
                    Toast.makeText(getActivity().getApplicationContext(), "Ponto de Origem não Informado!", Toast.LENGTH_SHORT).show();
                } else if (destination.equals("") == true) {
                    Toast.makeText(getActivity().getApplicationContext(), "Ponto de Destino não Informado!", Toast.LENGTH_SHORT).show();
                } else if (!((MainActivity) getActivity()).getGeoPointsDatabase().hasNode(origin)) {
                    Toast.makeText(getActivity().getApplicationContext(), "Ponto de Origem não Cadastrado!", Toast.LENGTH_SHORT).show();
                } else if (!((MainActivity) getActivity()).getGeoPointsDatabase().hasNode(destination)) {
                    Toast.makeText(getActivity().getApplicationContext(), "Ponto de Destino não Cadastrado!", Toast.LENGTH_SHORT).show();
                } else if (origin.equals(destination)) {
                    Toast.makeText(getActivity().getApplicationContext(), "Ponto de Origem é igual ao Ponto de Destino!", Toast.LENGTH_LONG).show();
                } else {
                    ((MainActivity)getActivity()).onNavigationDrawerItemSelected(2);
                    AsyncTaskTraceRoute tracarRota = new AsyncTaskTraceRoute(((MainActivity)getActivity()).getGeoPointsDatabase(), ((ApplicationObject) getActivity().getApplicationContext()).mapa);
                    tracarRota.execute(origin, destination);
                }
            }
        });

        destinationEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.v("Search", "Clicado!");
                    traceRouteButton.callOnClick();
                }
                return false;
            }
        });
    }
}