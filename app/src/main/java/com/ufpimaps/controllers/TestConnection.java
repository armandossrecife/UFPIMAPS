package com.ufpimaps.controllers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class TestConnection {

    private Context context;

    public TestConnection (Context context){
        this.context = context;
    }

    public boolean isConnected(){
        ConnectivityManager connectivity =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null){
            NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

            if (netInfo == null){
                return false;
            }

            int netType = netInfo.getType();

            if (netType == ConnectivityManager.TYPE_MOBILE || netType == ConnectivityManager.TYPE_WIFI){
                return netInfo.isConnected();
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

}
