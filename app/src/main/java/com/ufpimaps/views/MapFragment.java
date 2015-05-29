package com.ufpimaps.views;

import android.graphics.Color;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.ufpimaps.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HugoPiauilino on 30/04/15.
 */
public class MapFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback {

    private static final LatLng ufpiLocation = new LatLng(-5.057772, -42.797009);
    private MapView mapView;
    private GoogleMap googleMap;
    private int tipoDeMapa = 0;
    private Marker marker;
    private Polyline polyline;
    private List<LatLng> list;
    private long distance;

    public static double distance(LatLng StartP, LatLng EndP) {
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return 6366000 * c;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView) view.findViewById(R.id.map);

        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        MapsInitializer.initialize(getActivity());

        if (mapView != null) {
            googleMap = mapView.getMap();

            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            googleMap.setMyLocationEnabled(true);

            googleMap.getUiSettings().setZoomControlsEnabled(true);

            try {
                tipoDeMapa = getArguments().getInt("mapType") - 2;
            } catch (NullPointerException e) {
                tipoDeMapa = 1;
            }

            switch (tipoDeMapa) {
                case 1:
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    break;
                case 2:
                    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    break;
                case 3:
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    break;
            }
        }

        list = new ArrayList<LatLng>();
        list.add(new LatLng(-5.055527, -42.788745));
        list.add(new LatLng(-5.056282, -42.78844));

        getRoute(new LatLng(-5.055527, -42.788745), new LatLng(-5.056282, -42.78844));
        //drawRoute();

        //getDistance();

        return view;

    }

    @Override
    public void onResume() {
        mapView.onResume();

        super.onResume();

        getRoute(new LatLng(-5.055527, -42.788745), new LatLng(-5.056282, -42.78844));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        mapView.onLowMemory();
    }

    /**
     * Seta uma ancora no ponto central da UFPI
     *
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(ufpiLocation)
                .title("Universidade Federal do Piauí (UFPI)"));
        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(ufpiLocation)      // Sets the center of the map to UFPI
                .zoom(15)                   // Sets the zoom
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void customAddMarker(LatLng latLng, String title, String snippet) {
        MarkerOptions options = new MarkerOptions();
        options.position(latLng).title(title).snippet(snippet).draggable(true);
        marker = googleMap.addMarker(options);
    }

    public void drawRoute() {
        PolylineOptions polylineOptions = null;

        if (polylineOptions == null) {
            polylineOptions = new PolylineOptions();
            for (int i = 0; i < list.size(); i++) {
                polylineOptions.add(list.get(i));
            }

            polylineOptions.color(Color.BLACK).width(4);

            polyline = googleMap.addPolyline(polylineOptions);
        } else {
            polyline.setPoints(list);
        }

    }

    public void getDistance() {
        double distance = 0;

        for (int i = 0, tam = list.size(); i < tam; i++) {
            if (i < tam - 1) {
                distance += distance(list.get(i), list.get(i + 1));
            }
        }

        Log.i("Distancia", String.valueOf(distance));
    }

    public void getRoute(final LatLng origin, final LatLng destination) {
        new Thread() {
            public void run() {
                        /*String url= "http://maps.googleapis.com/maps/api/directions/json?origin="
								+ origin+"&destination="
								+ destination+"&sensor=false";*/
                String url = "http://maps.googleapis.com/maps/api/directions/json?origin="
                        + origin.latitude + "," + origin.longitude + "&destination="
                        + destination.latitude + "," + destination.longitude + "&sensor=false";


                HttpResponse response;
                HttpGet request;
                AndroidHttpClient client = AndroidHttpClient.newInstance("route");

                request = new HttpGet(url);
                try {
                    response = client.execute(request);
                    final String answer = EntityUtils.toString(response.getEntity());
                    client.close();

                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                //Log.i("Script", answer);
                                list = buildJSONRoute(answer);
                                drawRoute();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public List<LatLng> buildJSONRoute(String json) throws JSONException {
        JSONObject result = new JSONObject(json);
        JSONArray routes = result.getJSONArray("routes");

        distance = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("distance").getInt("value");

        JSONArray steps = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
        List<LatLng> lines = new ArrayList<LatLng>();

        for (int i = 0; i < steps.length(); i++) {
            //Log.i("Script", "STEP: LAT: " + steps.getJSONObject(i).getJSONObject("start_location").getDouble("lat") + " | LNG: " + steps.getJSONObject(i).getJSONObject("start_location").getDouble("lng"));


            String polyline = steps.getJSONObject(i).getJSONObject("polyline").getString("points");

            for (LatLng p : decodePolyline(polyline)) {
                lines.add(p);
            }

            //Log.i("Script", "STEP: LAT: " + steps.getJSONObject(i).getJSONObject("end_location").getDouble("lat") + " | LNG: " + steps.getJSONObject(i).getJSONObject("end_location").getDouble("lng"));
        }

        return (lines);
    }

    // DECODE POLYLINE
    private List<LatLng> decodePolyline(String encoded) {

        List<LatLng> listPoints = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)), (((double) lng / 1E5)));
            Log.i("Script", "POL: LAT: " + p.latitude + " | LNG: " + p.longitude);
            listPoints.add(p);
        }
        return listPoints;
    }

}