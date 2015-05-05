package com.ufpimaps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.zip.Inflater;

/**
 * Created by HugoPiauilino on 01/05/15.
 */
public class MapaHibridoFragment extends SupportMapFragment {

    View mapaView;
    GoogleMap mapa;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mapaView = inflater.inflate(R.layout.mapa_fragment, container, false);
        return mapaView;
    }

}
