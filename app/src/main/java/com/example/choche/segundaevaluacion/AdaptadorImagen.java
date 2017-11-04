package com.example.choche.segundaevaluacion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Choche on 4/11/2017.
 */

public class AdaptadorImagen extends ArrayAdapter<imagen> {
    private Animation zoom_in,zoom_out;
    Boolean   es_zoomIN;

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

        zoom_in= AnimationUtils.loadAnimation(getContext(),R.anim.zoom_in);
        zoom_in.setFillAfter(true);
        zoom_out=AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
        zoom_out.setFillAfter(true);

        es_zoomIN  = true;

        lblContador.setText(String.valueOf(cont));
        lblRuta.setText(img.getRuta());
        imgen.setImageBitmap(img.getImage());

        imgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(es_zoomIN) {
                    view.startAnimation(zoom_in);
                }else{
                    view.startAnimation(zoom_out);
                }
                es_zoomIN=!es_zoomIN;
            }
        });

        return convertView;
    }
}
