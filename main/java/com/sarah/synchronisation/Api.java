package com.sarah.synchronisation;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import static android.content.Context.MODE_PRIVATE;

/*************************************************/
// Nom du projet: MobilePyro
// Nom du fichier:Api.Java
// Version : 1.0
// Nom du programmeur: Sarah Gauvin
// Date de création :25/03/2020
// Rôle du fichier: Permet  la gestion de la clée Api permetant de récupé les informations de la base de données SCAE
/*************************************************/

public class Api {
    private String api ;
    private SharedPreferences sharedPreferences;

// Nom : Api
// Rôle : contructeur de la classe Api

    public Api (SharedPreferences s )
    {
        this.api ="clée api" ;
        this.sharedPreferences = s ;
    }
// Nom : EnregistrerLaClee
// Rôle : Permet d'enregistrer sur l'appliation la clée Api
// Paramètres d'entrée : la clée api et le context

    public void EnregistrerLaClee(String api2, Context context)
    {

        this.sharedPreferences = context.getSharedPreferences(this.api, MODE_PRIVATE);
        SharedPreferences.Editor editor = this.sharedPreferences.edit();

        editor.putString(this.api, api2);
        editor.apply();
        Toast.makeText(context.getApplicationContext(), "Clé enregistrée n°  "+ api2, Toast.LENGTH_SHORT).show();
    }
// Nom : ChercherLaClee
// Rôle : permet de chercher si une clée api est déja présente dans l'application
// Paramètres d'entrée : le context
// Valeur de retour : un boolean Vrai si il y a un clé / faux si non

    //Méthode qui permet de chercher si une clée API existe déja
    public boolean ChercherLaClee(Context context)
    {
        boolean b1 = false ;
        this.sharedPreferences = context.getSharedPreferences(api, MODE_PRIVATE);
        String clee = this.sharedPreferences.getString(api,null) ;
        if (this.sharedPreferences.contains(api))
        {
            Toast toast1 =  Toast.makeText(context.getApplicationContext(), "Enregistrement SCAE OK - clée API n° " + clee , Toast.LENGTH_SHORT);
             toast1.show();
            b1 = true ;
            //ici mettre la partie de Maxime
        }
        else {
            //si aucune clée n'est sauvegardé, on l'ajoute
            Toast.makeText(context.getApplicationContext(), "Aucune clée API trouvé", Toast.LENGTH_SHORT).show();
            b1 = false ;

        }
        return b1 ;
    }
}
