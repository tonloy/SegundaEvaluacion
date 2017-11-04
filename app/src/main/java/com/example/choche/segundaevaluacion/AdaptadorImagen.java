package com.example.choche.segundaevaluacion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Choche on 4/11/2017.
 */

public class AdaptadorImagen extends ArrayAdapter<imagen> {

    public AdaptadorImagen(@NonNull Context context, @NonNull List<imagen> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        imagen img=getItem(position);
        int cont=position+1;

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_lista, parent, false);
        }

        TextView lblContador=convertView.findViewById(R.id.txtContador);
        TextView lblRuta=convertView.findViewById(R.id.txtRuta);
        ImageView imgen=convertView.findViewById(R.id.img);

        lblContador.setText(String.valueOf(cont));
        lblRuta.setText(img.getRuta());
        imgen.setImageBitmap(img.getImage());

        return convertView;
    }
}
