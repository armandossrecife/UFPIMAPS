package com.ufpimaps.interfaces;

import com.google.android.gms.maps.model.LatLng;
import com.ufpimaps.models.Node;

import java.util.List;

/**
 * Created by zenote on 29/05/2015.
 */
public interface InterfaceGetListOfGeopoints {
    void devolveListaDeGeoPoints(List<LatLng> lista, Node origem, Node destino);
}