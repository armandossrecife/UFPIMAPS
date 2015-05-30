package com.ufpimaps.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.ufpimaps.R;
import com.ufpimaps.interfaces.InterfaceGetGeopoints;
import com.ufpimaps.system.AsyncTaskGetGeopoints;

/**
 * Created by HugoPiauilino on 14/05/15.
 */
public class TraceRouteFragment extends android.support.v4.app.Fragment implements InterfaceGetGeopoints{

    /**
     * Botao trace route
     */
    private Button traceRouteButton;

    private AutoCompleteTextView originEditText;
    private AutoCompleteTextView destinationEditText;
    private String[] search;
    private View traceRouteView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        traceRouteView = inflater.inflate(R.layout.fragment_trace_route, container, false);

        originEditText = (AutoCompleteTextView) traceRouteView.findViewById(R.id.originEditText);
        originEditText.setTextColor(Color.BLACK);
        originEditText.setThreshold(1);
        destinationEditText = (AutoCompleteTextView) traceRouteView.findViewById(R.id.destinationEditText);
        destinationEditText.setTextColor(Color.BLACK);
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
                }else{
                    Bundle argumentos = new Bundle();
                    argumentos.putString("origem", origin);
                    argumentos.putString("destino", destination);
                    MapFragment mapa = new MapFragment();
                    ((MainActivity)getActivity()).setMainFragment(mapa);
                    mapa.setArguments(argumentos);
                }
            }
        });
    }
}