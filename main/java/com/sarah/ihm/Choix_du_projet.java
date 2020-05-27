package com.sarah.ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sarah.mobilepyro.MobilePyro;
import com.sarah.mobilepyro.R;
import com.sarah.synchronisation.Projets;
import com.sarah.synchronisation.Synchronisation_SCAE;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Choix_du_projet extends AppCompatActivity {

   // private Synchronisation_SCAE monObjetSyn ;
    private Spinner sp1, sp2, sp3 ;
    private Button boutonChoisir, boutonSAE, boutonProjet ;
    private SharedPreferences sharedPreferences, sharedPreferences2;
    private MobilePyro monobjectPyro ;
    public ArrayList<String> s = new ArrayList<>();
    Context context;
    String un, deux,trois ;
    String URL ;

    public Choix_du_projet() {


    }

    public String getUn ()
    {
        return un ;
    }
    public String getDeux ()
    {
        return deux ;
    }
    public String getTrois ()
    {
        return this.trois ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_du_projet);
        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        sp3 = findViewById(R.id.sp3);
        boutonChoisir = findViewById((R.id.boutonChoisir));
        boutonSAE = findViewById((R.id.boutonRecupScAE));
        boutonProjet = findViewById((R.id.boutonrecupProjets));
        context = this;
        ArrayList<Projets> malistet  = new ArrayList<>();

        monobjectPyro = new MobilePyro(context, sharedPreferences);





        boutonChoisir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choixDuProjet = new Intent(Choix_du_projet.this, Formulaire.class);
                choixDuProjet .putExtra("un", un);
                choixDuProjet .putExtra("deux", deux);
                choixDuProjet .putExtra("trois", trois);
                startActivity(choixDuProjet);
            }
        });

        boutonProjet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    monobjectPyro.monobjecSynchro.recupérerSCAE();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast t2 = Toast.makeText(context.getApplicationContext(), "aucune bdd trouvée " , Toast.LENGTH_SHORT);
                    t2.show();
                }
                s = monobjectPyro.monobjecSynchro.creationPremierSpinner();
                ArrayAdapter<String> adapter1 = new ArrayAdapter(context,android.R.layout.simple_dropdown_item_1line,s);
                sp1.setAdapter(adapter1);

            }
        });

        boutonSAE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences =  context.getSharedPreferences("clée api", MODE_PRIVATE);
                monobjectPyro.monobjecSynchro = new Synchronisation_SCAE(context, sharedPreferences);
               // sharedPreferences2 = context.getSharedPreferences("clée api", MODE_PRIVATE);

                if (!sharedPreferences.contains("clée api")) {
                    Intent app = new Intent(Choix_du_projet.this, Donner_api.class);
                    startActivity(app);
                }
                else
                {
                    String clee = sharedPreferences.getString("clée api",null) ;
                    Log.i("tag3"," c'est"+ clee);
                    URL = "https://dev.scae.silica.io/index.php?p=api&key="+clee+"&fields=name,club_name,type";
                    Log.i("tag1", "L'url est : " + URL); // verifier le nombre de projet

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                // Initialiser un nouvel objet de JsonArrayRequest
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("tag3", response.toString()); // permet de voir toutes les infos dans un string
                        Log.i("tag1", "longueur " + response.length()); // verifier le nombre de projet
                        monobjectPyro.monobjecSynchro.enregistrer_SCAE(response);

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Do something when error occurred
                                Log.d("error", error.toString());
                            }
                        }

                );
                // Ajouter JsonArrayRequest à RequestQueue
                // j'ai pas tout compris à ça faudra que tu faces des recherches
                requestQueue.add(jsonArrayRequest);

            }
            }
        });


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ArrayList<String>  g = new ArrayList<>();
                un = sp1.getSelectedItem().toString();
                g.clear();
                Log.i("sp1", un);

                Log.i("sp1","taille" + monobjectPyro.monobjecSynchro.maliste.size());
                boolean vf = false;
                for (int y = 0; y < monobjectPyro.monobjecSynchro.maliste.size(); y++)
                {
                    if (monobjectPyro.monobjecSynchro.maliste.get(y).getType().equals(un))
                    {
                        for (int a = 0; a < g.size(); a++) {
                            if (monobjectPyro.monobjecSynchro.maliste.get(y).getClub().equals(g.get(a))) {
                                vf = true;
                            }
                        }
                        if (!vf)
                        {
                            g.add(monobjectPyro.monobjecSynchro.maliste.get(y).getClub());
                        }
                        else { vf=false ;}
                    }

                }
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,g);
                sp2.setAdapter(adapter2);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<String>  f = new ArrayList<>();
                f.clear();
                deux = sp2.getSelectedItem().toString();
                Log.i("sp2","le 2" + deux);
                boolean vf = false;

                for (int y = 0; y < monobjectPyro.monobjecSynchro.maliste.size(); y++)
                {

                    if (monobjectPyro.monobjecSynchro.maliste.get(y).getClub().equals(deux))
                    {
                        for (int a = 0; a < f.size(); a++) {
                            if (monobjectPyro.monobjecSynchro.maliste.get(y).getNom().equals(f.get(a))) {
                                vf = true;
                            }
                        }
                        if (!vf)
                        {
                            f.add(monobjectPyro.monobjecSynchro.maliste.get(y).getNom());
                        }
                        else { vf=false ;}
                    }

                }
                ArrayAdapter<String> adapter3 = new ArrayAdapter(context,android.R.layout.simple_dropdown_item_1line,f);
                sp3.setAdapter(adapter3);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                trois = sp3.getSelectedItem().toString();
                Log.i("sp2","le 2" + trois);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






    }



}
