package com.dukatoil.transmbyvehicle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by B_Silikhin on 014 14.03.17.
 */

public class ArrayAdapterInfo extends ArrayAdapter {
    private final Context context;
    private final int resource;
    private final String[] titles;
    private final String[] values;

    public ArrayAdapterInfo(Context context, int resource, String[]titles, String[] values) {
        super(context, resource, values);
        this.context = context;
        this.resource = resource;
        this.titles = titles;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.message_title);
        TextView tvSubtitle = (TextView) convertView.findViewById(R.id.message_subtitle);

        tvTitle.setText(titles[position]);
        tvSubtitle.setText(values[position]);
        return convertView;
    }
}
