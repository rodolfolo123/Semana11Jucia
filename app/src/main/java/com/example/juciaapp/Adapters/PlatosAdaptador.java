package com.example.juciaapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.juciaapp.Models.Platos;
import com.example.juciaapp.R;

import java.util.List;

public class PlatosAdaptador extends ArrayAdapter<Platos> {
    Context context;
    ImageLoader queue;

    private class ViewHolder {
        NetworkImageView image;
        TextView nombre;
        TextView descripcion;


        private ViewHolder() {
        }
    }
    public PlatosAdaptador(Context context, List<Platos> items, ImageLoader _queue) {
        super(context, 0, items);
        this.context = context;
        this.queue = _queue;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Platos rowItem = (Platos) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.platos, null);
            holder = new ViewHolder();
            holder.nombre = (TextView) convertView.findViewById(R.id.Nombre);
            holder.descripcion = (TextView) convertView.findViewById(R.id.Descripcion);
            holder.image = (NetworkImageView)convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nombre.setText(rowItem.Nombre);
        holder.descripcion.setText(rowItem.Descripcion);

        if ( rowItem.getSmallImage() != null ) { //<----- El modelo debe poseer esta function
            holder.image.setImageUrl(rowItem.getSmallImage(), queue);
        }
        return convertView;
    }
}
