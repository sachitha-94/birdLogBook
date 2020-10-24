package com.birdlogbook.sajja.birdlogbook.validation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.birdlogbook.sajja.birdlogbook.R;

/**
 * Created by sajja on 1/15/2017.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    int shapes[];
    String[] shapesIDs;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, int[] flags, String[] countryNames) {
        this.context = applicationContext;
        this.shapes = flags;
        this.shapesIDs = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return shapes.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_new_log_note, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageViewSpinner);
        TextView names = (TextView) view.findViewById(R.id.textViewSpinner);
        System.out.println("****** "+shapes[i]);
        icon.setImageResource(shapes[i]);
        names.setText(shapesIDs[i]);
        return view;
    }
}
