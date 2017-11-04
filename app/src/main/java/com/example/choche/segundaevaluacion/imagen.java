package com.example.choche.segundaevaluacion;

import android.graphics.Bitmap;

/**
 * Created by Choche on 4/11/2017.
 */

public class imagen {
    private Bitmap image;
    private String numero,ruta;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
