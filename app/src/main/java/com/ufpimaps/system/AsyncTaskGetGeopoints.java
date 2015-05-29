package com.ufpimaps.system;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ufpimaps.interfaces.InterfaceGetGeopoints;
import com.ufpimaps.models.GeoPointsDatabase;
import java.util.ArrayList;

/**
 * Created by zenote on 29/05/2015.
 */
public class AsyncTaskGetGeopoints extends AsyncTask<Void, Void, String[]> {

    private InterfaceGetGeopoints interfaceComTraceRouteFragment;
    private GeoPointsDatabase bancoDeDados;

    public AsyncTaskGetGeopoints(InterfaceGetGeopoints interfaceComTraceRouteFragment, GeoPointsDatabase bancoDeDados){
        this.interfaceComTraceRouteFragment = interfaceComTraceRouteFragment;
        this.bancoDeDados = bancoDeDados;
    }

    @Override
    protected String[] doInBackground(Void... params) {
        String[] listAux;
        ArrayList<String> descricoes = bancoDeDados.getNodesDescriptions();
        listAux = new String[descricoes.size()];
        listAux = descricoes.toArray(listAux);
        return listAux;
    }

    @Override
    protected void onPostExecute(String[] geopoints) {
        interfaceComTraceRouteFragment.devolverGeopoints(geopoints);
    }
}
