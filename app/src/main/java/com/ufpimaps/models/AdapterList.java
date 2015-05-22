package com.ufpimaps.models;

/**
 * Created by HugoPiauilino on 12/05/15.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ufpimaps.R;

import java.util.List;

public class AdapterList extends ArrayAdapter {

    private Context context;
    private TextView titleText;

    public AdapterList(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemList item = (ItemList) getItem(position);
        View viewToUse;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewToUse = mInflater.inflate(R.layout.list_item, null);
            titleText = (TextView) viewToUse.findViewById(R.id.titleTextView);
            viewToUse.setTag(titleText);
        } else {
            viewToUse = convertView;
            titleText = (TextView) viewToUse.getTag();
        }

        titleText.setText(item.getItemTitle());
        return viewToUse;
    }
}
