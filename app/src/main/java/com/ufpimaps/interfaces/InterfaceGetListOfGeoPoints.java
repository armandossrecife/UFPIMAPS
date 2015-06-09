package com.ufpimaps.interfaces;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by zenote on 29/05/2015.
 */
public interface InterfaceGetListOfGeopoints {
    public void devolveListaDeGeoPoints(List<LatLng> lista);
}