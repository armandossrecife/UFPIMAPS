package com.ufpimaps.views;

import android.os.AsyncTask;

import com.ufpimaps.models.Node;

import java.util.List;

/**
 * Created by Renato on 14/07/2016.
 */
public class DownloadJsonAsyncTask extends AsyncTask<String,Void,List<Node>> {


    public interface AsyncResponse {
        void processFinish(List<Node> nodes);
    }

    public AsyncResponse delegate = null;


    //retorna os nós lidos no json a partir da url enviada
    @Override
    protected List<Node> doInBackground(String... params) {
        String url = params[0];
        JsonClass jsonClass = new JsonClass();
        return jsonClass.getNodes(url);
    }

    @Override
    protected void onPostExecute(List<Node> nodes) {
        delegate.processFinish(nodes);
    }
}
