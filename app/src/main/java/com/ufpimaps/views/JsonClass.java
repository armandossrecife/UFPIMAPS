package com.ufpimaps.views;

import android.os.Build;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.ufpimaps.models.Node;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renato on 14/07/2016.
 */
public class JsonClass {

    InputStream input = null;
    //JSONObject jObject = null;
    JSONArray jArray = null;
    String json = "";

    //Recebe sua url
    private void getJSONFromUrl(String url) {

        //HTTP request
        try {
            // default HttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            input = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            input.close();
            json = sb.toString();
            Log.i("JRF", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // Transforma a String de resposta em um JSonObject
        try {

            //jObject = new JSONObject(json.substring(1,json.length()-1));
            jArray = new JSONArray(json);
//            for(int i = 0;i< jArray.length();i++){
//                Log.d("ITEM"+i,String.valueOf(jArray.get(i)));
//            }
            //jObject = new JSONArray();
            //Log.d("TESTE",String.valueOf(jObject));
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
    }

    public List<Node> getNodes(String url){
        getJSONFromUrl(url);
        List<Node> nodes = null;
        //JSONArray nodesJson = null;
        try {

            //String jsonStr = jObject.toString();
            //nodesJson = new JSONArray("["+jsonStr+"]");

            JSONObject node;
            nodes = new ArrayList<>();
            for (int i = 0; i<jArray.length(); i++){

                node = new JSONObject(jArray.getString(i));
                JSONObject lcl = node.getJSONObject("localization");
                LatLng localization = new LatLng(lcl.getDouble("latitude"),lcl.getDouble("longitude"));
                Node objetoNode = new Node(node.getInt("id"),node.getString("name"),node.getString("description"),
                        node.getInt("type"),node.getString("services"),localization,node.getString("email"),
                        node.getString("website"), node.getString("phone"));
                nodes.add(objetoNode);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nodes;
    }
}
