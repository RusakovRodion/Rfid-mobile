package com.example.rfid_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ObjectAdapter extends ArrayAdapter<ObjectClass> {

    private LayoutInflater inflater;
    private int layout;
    private List<ObjectClass> objects;

    public ObjectAdapter(Context context, int resource, List<ObjectClass> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        TextView nameView = view.findViewById(R.id.nameObject);
        TextView descView = view.findViewById(R.id.descObject);
        TextView statusView = view.findViewById(R.id.statusObject);
        TextView categoryView = view.findViewById(R.id.categoryObject);

        ObjectClass object = objects.get(position);

        nameView.setText(object.name);
        descView.setText(object.description);
        categoryView.setText(object.category);
        if (!object.status) {
            statusView.setText("Статус: на складе");
        } else {
            statusView.setText("Статус: арендован");
        }

        return view;
    }
}
