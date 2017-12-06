package com.ufpimaps.system;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.ufpimaps.interfaces.InterfaceGetListOfGeopoints;
import com.ufpimaps.models.GeoPointsDatabase;
import com.ufpimaps.models.Graph;
import com.ufpimaps.models.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe responsavel por traçar uma reta entre a origem e o destino dada a requisição do usuário
 * Created by Rafael M. Barros on 07/11/2017.
 */

public class AsyncTaskTraceFlyingRoute extends AsyncTask<String, Void, LinkedList<Node>> {

    private GeoPointsDatabase bancoDeDados;
    private InterfaceGetListOfGeopoints interfaceMapFragment;
    private Node origem;
    private Node destino;

    /**
     * Construtor da classe AsyncTaskTraceFlyingRoute
     *
     * @param bancoDeDados
     * @param interfaceMapFragment
     */
    public AsyncTaskTraceFlyingRoute(GeoPointsDatabase bancoDeDados, InterfaceGetListOfGeopoints interfaceMapFragment) {
        this.bancoDeDados = bancoDeDados;
        this.interfaceMapFragment = interfaceMapFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    /**
     * Método que recebe os pontos inicial e final e devolve uma lista contendo os geopoints da rota.
     *
     * @param descricoes Pontos inicial e final
     * @return lista com os geopoints da rota tracada
     */
    @Override
    protected LinkedList<Node> doInBackground(String... descricoes) {
        //String answer = "";


        origem = bancoDeDados.selectByName(descricoes[0]);
        destino = bancoDeDados.selectByName(descricoes[1]);
        if (origem == null && destino == null) {
            return null;
        }

        LinkedList<Node> path = new LinkedList<>();
        path.add(origem);
        path.add(destino);

        return path;
    }

    /**
     * Método que é executado quando a execução da thread assincrona termina, devolvendo o controle
     * ao fragmento do mapa
     *
     * @param answer Json com os geopoints da rota tracada
     */
    @Override
    protected void onPostExecute(LinkedList<Node> answer) {
        List<LatLng> list = null;
        if (answer!= null) {
            try {
                list = nodesToGeoPoints(answer);
                interfaceMapFragment.devolveListaDeGeoPoints(list, origem, destino);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Transforma os nos de um caminho em geopoints
     * @param path Caminho como lista ligada de nos
     * @return Retorna uma lista de posicoes de latitude e longitude (LatLng)
     */
    public List<LatLng> nodesToGeoPoints(LinkedList<Node> path){
        List<LatLng> l = new LinkedList<LatLng>();
        int i = 0;
        for(Node n:path){
            l.add(path.get(i).getLocalization());
            i++;
        }
        return l;
    }

}
