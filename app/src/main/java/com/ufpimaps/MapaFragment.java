package com.ufpimaps;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by HugoPiauilino on 30/04/15.
 */
public class MapaFragment extends android.support.v4.app.Fragment {

    View mapaView;
    GoogleMap mapa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mapaView = inflater.inflate(R.layout.mapa_fragment, container, false);
        return mapaView;
    }
}
