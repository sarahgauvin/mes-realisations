package com.sarah.ihm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.sarah.mobilepyro.MobilePyro;
import com.sarah.mobilepyro.R;
import com.sarah.synchronisation.Api;


public  class Donner_api extends AppCompatActivity {

    Button btnEnreAPI ;
    EditText ETapi ;

    public SharedPreferences sharedPreferences;

    MobilePyro monObjPyro ;

// Nom :onCreate
// Rôle :onCreate est appelée au premier lancement de l'activité
// Paramètres d'entrée : un Bundle


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donner_api);



        btnEnreAPI = findViewById(R.id.btnAPI);

        btnEnreAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                //sharedPreferences = getSharedPreferences("clée api", MODE_PRIVATE);
                monObjPyro = new MobilePyro( sharedPreferences);
                ETapi =  findViewById(R.id.ETnomAPI);
                String api2  =  ETapi.getText().toString();
                monObjPyro.monobjApi.EnregistrerLaClee(api2, context);

                Intent donnerAPI = new Intent(Donner_api.this, Choix_du_projet.class);
                donnerAPI.putExtra("api", api2);
                startActivity(donnerAPI);


            }
        });


    }






}
