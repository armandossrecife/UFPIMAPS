package com.ufpimaps.system;

import android.graphics.Color;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.ufpimaps.interfaces.InterfaceGetListOfGeoPoints;
import com.ufpimaps.models.GeoPointsDatabase;
import com.ufpimaps.models.Node;
import com.ufpimaps.views.MainActivity;

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
 * Created by zenote on 29/05/2015.
 */
public class AsyncTaskTraceRoute extends AsyncTask<String, Void, String>{

    private long distance;
    private GoogleMap googleMap;
    private GeoPointsDatabase bancoDeDados;
    private InterfaceGetListOfGeoPoints interfaceMapFragment;

    public AsyncTaskTraceRoute(GeoPointsDatabase bancoDeDados, InterfaceGetListOfGeoPoints interfaceMapFragment){
        this.bancoDeDados = bancoDeDados;
        this.interfaceMapFragment = interfaceMapFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... descricoes) {
        String answer = "";

        final Node origem = bancoDeDados.selectByDescription(descricoes[0]);
        final Node destino = bancoDeDados.selectByDescription(descricoes[1]);
        if(origem == null && destino == null){
            return answer;
        }
        String url = "http://maps.googleapis.com/maps/api/directions/json?origin="
                + origem.getLocalization().getLatitude() + "," + origem.getLocalization().getLongitude() + "&destination="
                + destino.getLocalization().getLatitude() + "," + destino.getLocalization().getLongitude() + "&sensor=false";


        HttpResponse response;
        HttpGet request;
        AndroidHttpClient client = AndroidHttpClient.newInstance("route");

        request = new HttpGet(url);
        try {
            response = client.execute(request);
            answer = EntityUtils.toString(response.getEntity());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    protected void onPostExecute(String answer) {
        List<LatLng> list = null;
        if(answer.equals("") == false){
            try {
                list = buildJSONRoute(answer);
                interfaceMapFragment.devolveListaDeGeoPoints(list);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    /*
    public double distance(LatLng StartP, LatLng EndP) {
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
    */

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
