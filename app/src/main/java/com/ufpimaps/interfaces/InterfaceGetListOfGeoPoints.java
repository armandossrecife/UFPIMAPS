package com.ufpimaps.interfaces;

import com.google.android.gms.maps.model.LatLng;
import com.ufpimaps.models.Node;

import java.util.List;

/**
 * Interface criada para devolver uma lista de geopoints para a classe que a implementa.
 * Created by zenote on 29/05/2015.
 */
public interface InterfaceGetListOfGeopoints {
    /**
     *
     * @param lista Lista de
     * @param origem
     * @param destino
     */
    void devolveListaDeGeoPoints(List<LatLng> lista, Node origem, Node destino);
}