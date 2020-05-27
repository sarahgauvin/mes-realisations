package com.sarah.ihm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sarah.mobilepyro.R;

public class Page_de_garde extends AppCompatActivity {
    Button boutonDemarrer ;
    ImageView photoDeFusee ;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_de_garde);
        photoDeFusee =  findViewById(R.id.photoDuCnes) ;
        boutonDemarrer =  findViewById(R.id.BoutonDemarrer);
        requestPermissions(new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.INTERNET,Manifest.permission.CAMERA }, 2);



        boutonDemarrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageDeGarde = new Intent(Page_de_garde.this, Choix_du_projet.class);
                startActivity(pageDeGarde);
            }
        });
    }


}
