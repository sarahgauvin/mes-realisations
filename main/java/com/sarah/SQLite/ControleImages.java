package com.sarah.SQLite;

/*************************************************/
// Nom du projet: GAP'Scpace
// Nom du fichier: ControleImages.java
// Version : 1.0
// Nom du programmeur: Thomas ARROYOS
// Date de création : 21 avril 2020
// Rôle du fichier: récupère les informations des champs via la classe GAPSpace et les implémente dans la BDD Images
// Nom des composants utilises:
// Historique du fichier: 21 avril 2020 ajout de toutes les fonctions présentes
//                        13 mai 2020 suppression des méthodes get des images et ajout méthode get du tableau d'images
/*************************************************/



import android.content.Context;

public class ControleImages {

    private static ControleImages instance = null;
    private static GAPSpaceImages gapSpaceImages;
    private static String nomFic = "sauvegarde_images";
    private static AccesLocal accesLocal;

    private ControleImages(){
        super();
    }

    // Nom : getInstance
    // Rôle : appelle le constructeur de la classe Controle et de la classe AccesLocal
    // Paramètres d'entrée : context
    // Valeur de retour : ControleImages.instance
    public static final ControleImages getInstance(Context context){
        if(ControleImages.instance == null){
            ControleImages.instance = new ControleImages();
            recupSerialize(context);
            accesLocal = new AccesLocal(context);
            //gapSpaceImages = accesLocal.recupLeDernier();
        }
        return ControleImages.instance;
    }

    public void creerImage(String NomProjet, String Horodatage, String i1, String i2, String i3, String i4, String i5, String i6, String i7, String i8, String i9, String i10, Context context){
        gapSpaceImages = new GAPSpaceImages(NomProjet, Horodatage, i1,  i2,  i3,  i4, i5, i6,  i7, i8,  i9,  i10);
        Serialize.serialize(nomFic, gapSpaceImages, context);
        accesLocal.ajoutImages(gapSpaceImages);
    }

    // Nom : recupSerialize
    // Rôle : récupère toutes les infos fournies dans le formulaire
    // Paramètres d'entrée : context
    private static void recupSerialize(Context context){
        gapSpaceImages = (GAPSpaceImages) Serialize.deSerialize(nomFic, context);
    }

    // Nom : getNomProjet
    // Rôle : récupère le nom du projet via gapSpaceImages
    // Valeur de retour : gapSpace.getNomProjet()
    public String getNomProjet(){
        return gapSpaceImages.getNomProjet();
    }

    // Nom : getHorodatage
    // Rôle : récupère l'heure et la date via gapSpaceImages
    // Valeur de retour : gapSpace.getHorodatage()
    public String getHorodatage(){
        return gapSpaceImages.getHorodatage();
    }

    // Nom : getTableau
    // Rôle : récupère le tableau contenant les images via gapSpaceImages
    // Valeur de retour : gapSpace.getTableau()
    public String getImage1(){
        return gapSpaceImages.getImage1();
    }
}
