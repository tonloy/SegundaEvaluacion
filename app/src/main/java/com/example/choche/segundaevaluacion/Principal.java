package com.example.choche.segundaevaluacion;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    private final int READ_EXTERNAL_STORAGE_PERMISSION_CODE=23;
    private int PICK_PHOTO_FOR_AVATAR=3;
    private Button btnAgregar;
    private ArrayList<imagen> lstImagenes;
    private AdaptadorImagen adaptadorImagen;
    private ListView lista;
    private Animation zoom_in,zoom_out;

    Boolean   es_zoomIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            //ask for permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_CODE);
            }
        }
        btnAgregar=findViewById(R.id.btnAgregar);
        lstImagenes=new ArrayList<>();
        adaptadorImagen=new AdaptadorImagen(this,lstImagenes);
        lista=findViewById(R.id.lstImagenes);

        zoom_in= AnimationUtils.loadAnimation(this,R.anim.zoom_in);
        zoom_out=AnimationUtils.loadAnimation(this,R.anim.zoom_out);

        es_zoomIN  = true;

        lista.setAdapter(adaptadorImagen);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });
        
    }

        public void pickImage() {
            Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                Bitmap bmp = null;
                try {
                    bmp = getBitmapFromUri(selectedImage);
                    if(bmp!=null) {
                        String ruta = data.getData().getPath();
                        //aqui se agrega la imagen a la lista
                        imagen item=new imagen();
                        item.setImage(bmp);
                        item.setRuta(ruta);
                        lstImagenes.add(item);
                        adaptadorImagen.notifyDataSetChanged();
                    }
                } catch (IOException e) {
                    Toast.makeText(this,"Error loading image", Toast.LENGTH_SHORT);
                    e.printStackTrace();
                }

            }
        }

        private Bitmap getBitmapFromUri(Uri uri) throws IOException {
            ParcelFileDescriptor parcelFileDescriptor =
                    getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
            return image;
        }
    }

