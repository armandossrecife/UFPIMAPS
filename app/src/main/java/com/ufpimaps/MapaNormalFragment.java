package com.ufpimaps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by HugoPiauilino on 30/04/15.
 */
public class MapaNormalFragment extends android.support.v4.app.Fragment {

    MapView mapView;

    GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.mapa_fragment, container, false);

        mapView = (MapView) view.findViewById(R.id.map);

        mapView.onCreate(savedInstanceState);

        if(mapView!=null) {
            googleMap = mapView.getMap();

            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            googleMap.setMyLocationEnabled(true);

            googleMap.getUiSettings().setZoomControlsEnabled(true);

            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            //googleMap.moveCamera(new CameraUpdateFactory(new LatLng(-5.057772, -42.797009), 14));

        }

        return view;

    }

    @Override
    public void onResume()
    {
        mapView.onResume();

        super.onResume();
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();

        mapView.onDestroy();
    }
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();

        mapView.onLowMemory();
    }
}