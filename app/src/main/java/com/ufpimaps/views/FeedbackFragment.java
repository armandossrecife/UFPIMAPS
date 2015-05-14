package com.ufpimaps.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ufpimaps.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

/**
 * Created by HugoPiauilino on 14/05/15.
 */
public class FeedbackFragment extends android.support.v4.app.Fragment {

    Button sendFeedback;
    EditText feedback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View feedbackView = inflater.inflate(R.layout.fragment_feedback, container, false);

        sendFeedback = (Button) feedbackView.findViewById(R.id.sendFeedback);
        feedback = (EditText)  feedbackView.findViewById(R.id.editText1);

        sendFeedback.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String s = feedback .getText().toString();
                String aux = s.replace(' ', '$');
                aux = aux.replace('?', '+');
                AdministrativeNodeClient a = new AdministrativeNodeClient(feedbackView.getContext());
                a.execute("http://10.20.15.29:8080/StructureUFPIMaps/feedback/inserir/" + aux);
            }
        });

        return feedbackView;
    }

    private class AdministrativeNodeClient extends
            AsyncTask<String, Void, String> {
        private static final String TAG = "PostFetcher";
        private ProgressDialog progress;
        private Context context;

        public AdministrativeNodeClient(Context contexto) {
            this.context = contexto;
        }

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(context);
            progress.setMessage("CARREGANDO....");
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                // Create an HTTP client
                HttpClient client = new DefaultHttpClient();
                HttpPost get = new HttpPost(params[0]);
                // Perform the request and check the status code
                HttpResponse response = client.execute(get);
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();

                    try {
                        content.close();

                    } catch (Exception ex) {
                        Log.e(TAG, "Failed to parse JSON due to: " + ex);
                    }
                } else {
                    Log.e(TAG, "Server responded with status code: "
                            + statusLine.getStatusCode());
                }
            } catch (Exception ex) {
                Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String a) {
            progress.dismiss();
            Toast toast = Toast.makeText(context, "Sua avaliacao foi registrada com sucesso.", Toast.LENGTH_LONG);
            toast.show();

        }
    }

}