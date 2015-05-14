package com.ufpimaps.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ufpimaps.R;

/**
 * Created by HugoPiauilino on 14/05/15.
 */
public class TraceRouteFragment extends android.support.v4.app.Fragment {

    /**
     * Botao trace route
     */
    private Button traceRouteButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View traceRouteView = inflater.inflate(R.layout.fragment_trace_route, container, false);
        traceRouteButton = (Button) traceRouteView.findViewById(R.id.trace_route_button);
        traceRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), CollaboratorsActivity.class);
                //startActivity(intent);
            }
        });
        return traceRouteView;
    }
}