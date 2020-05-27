package com.sarah.synchronisation;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.ArrayList;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import static android.content.Context.MODE_PRIVATE;

public class Synchronisation_SCAE {


    private Context mContext;
    public  ArrayList<Projets> maliste = new ArrayList<>(); // la liste de tous les projets
    SharedPreferences sharedPreferences;

    public Synchronisation_SCAE(Context c, SharedPreferences s ) {
        this.mContext = c;
        this.sharedPreferences = s;


    }



    public void enregistrer_SCAE (JSONArray s )

    {
        sharedPreferences = mContext.getSharedPreferences("SCAE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("SCAE", s.toString());
        editor.apply();
        Toast t =Toast.makeText(mContext.getApplicationContext(), "SCAE enregistrée, nombre de projets :  = " + s.length(), Toast.LENGTH_SHORT);
        t.show();

    }

    public void recupérerSCAE () throws JSONException {

        sharedPreferences = mContext.getSharedPreferences("SCAE", MODE_PRIVATE);

        if (sharedPreferences.contains("SCAE"))
        {
            String scae = sharedPreferences.getString("SCAE", null);
            Log.i("tag2", "La variable scae contient :" + scae);

             Toast t2 = Toast.makeText(mContext.getApplicationContext(), "Projet récupérés " , Toast.LENGTH_SHORT);
             t2.show();
            JSONArray jsonArr = new JSONArray(scae);
            construireListeProjet(jsonArr);
        }
        else
        {
            Toast t2 = Toast.makeText(mContext.getApplicationContext(), "aucune bdd tourvée " , Toast.LENGTH_SHORT);
            t2.show();

        }


    }

    private void construireListeProjet (JSONArray jsonArr)
    {
        try {
            // Loop through the array elements
            for (int i = 0; i < jsonArr.length(); i++) {
                // création d'autant de JsonObjet que de projet
                JSONObject jsonObject = jsonArr.getJSONObject(i);
                // ajout des trois infos au JsonObjet
                String id = jsonObject.getString("name");
                String club = jsonObject.getString("club_name");
                String type = jsonObject.getString("type");

                // création d'autant d'objet de type projet que d'objet JsonObjet
                Projets p = new Projets(type, club, id);
                // les ajouter dans un Arraylist (comme un tableau)
                maliste.add(p);


                // verification en affichant l'Arraylist
                Log.i("tag2", maliste.get(i).getNom() + " " + maliste.get(i).getClub() + " " + maliste.get(i).getType());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> creationPremierSpinner ()
    {
        ArrayList<String> s = new ArrayList<>();

        boolean vf = false ;

        Log.i("tag5", "taille du tableau maliste : " + maliste.size());
        for (int i = 0; i < maliste.size(); i++)
        {
            Log.i("tag5", "taille du tableau s : " + s.size());
            for (int a = 0; a < s.size(); a++)
            {
                if (s.get(a).equals(maliste.get(i).getType()))
                {
                    vf = true ;
                }
            }

            if (!vf)
            {
                s.add(maliste.get(i).getType());

            }
            else { vf=false ;}
        }
      return s ;
    }


}
