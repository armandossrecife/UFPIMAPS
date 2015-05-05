package com.ufpimaps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by HugoPiauilino on 30/04/15.
 */
public class MapaNormalFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback {

    MapView mapView;

    private GoogleMap googleMap;

    private static final LatLng ufpiLocation = new LatLng(-5.057772,-42.797009);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.mapa_fragment, container, false);

        mapView = (MapView) view.findViewById(R.id.map);

        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        MapsInitializer.initialize(getActivity());

        if(mapView != null) {
            googleMap = mapView.getMap();

            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            googleMap.setMyLocationEnabled(true);

            googleMap.getUiSettings().setZoomControlsEnabled(true);

            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(ufpiLocation)      // Sets the center of the map to UFPI
                    .zoom(15)                   // Sets the zoom
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(ufpiLocation)
                .title("Universidade Federal do Piauí (UFPI)"));
    }
}