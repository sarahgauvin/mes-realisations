package com.sarah.SQLite;

/*************************************************/
// Nom du projet: GAP'Scpace
// Nom du fichier: GAPSpaceImages.java
// Version : 1.0
// Nom du programmeur: Thomas ARROYOS
// Date de création : 21 avril 2020
// Rôle du fichier: récupère les informations des champs du formulaire et les lie aux champs de la BDD Images
// Nom des composants utilises:
// Historique du fichier: 21 avril 2020 ajout de toutes les fonctions présentes
//                        13 mai 2020 suppression variables images ainsi que leur méthode get et set
//                                    ajout variable Tableau et sa méthode get et set
/*************************************************/

import java.io.Serializable;

/**
 * implémentation du nom des lignes de la base de données Images
 */
public class GAPSpaceImages implements Serializable {
    private String NomProjet;
    private String Horodatage;
    private String Image1;
    private String Image2;
    private String Image3;
    private String Image4;
    private String Image5;
    private String Image6;
    private String Image7;
    private String Image8;
    private String Image9;
    private String Image10;

    // Nom : GAPSpaceImages
    // Rôle : lie les champs de la BDD Images avec les infos récupérées dans les champs du formulaire
    // Paramètres d'entrée : NomProjet, Horodatage, tableau
    public GAPSpaceImages(String NomProjet, String Horodatage, String i1, String i2, String i3, String i4, String i5, String i6, String i7, String i8, String i9, String i10) {
        super();
        this.NomProjet = NomProjet;
        this.Horodatage = Horodatage;
        this.Image1 = i1;
        this.Image2 = i2;
        this.Image3 = i3;
        this.Image4 = i4;
        this.Image5 = i5;
        this.Image6 = i6;
        this.Image7 = i7;
        this.Image8 = i8;
        this.Image9 = i9;
        this.Image10 = i10;
    }

    // Nom : getNomProjet
    // Rôle : récupère le nom du projet
    // Valeur de retour : NomProjet
    public String getNomProjet() {
        return NomProjet;
    }

    // Nom : setNomProjet
    // Rôle : instancie le nom du projet
    // Paramètre d'entrée : nomProjet
    public void setNomProjet(String nomProjet) {
        this.NomProjet = nomProjet;
    }

    // Nom : getHorodatage
    // Rôle : récupère l'heure et la date
    // Valeur de retour : Horodatage
    public String getHorodatage() {
        return Horodatage;
    }

    // Nom : setHorodatage
    // Rôle : instancie l'heure et la date
    // Paramètre d'entrée : horodatage
    public void setHorodatage(String horodatage) {
        this.Horodatage = horodatage;
    }

    // Nom : getTableau
    // Rôle : récupère le tableau contenant les images
    // Valeur de retour : Tableau
    public String getImage1() {
        return Image1;
    }

    public String getImage2() {
        return Image2;
    }

    public String getImage3() {
        return Image3;
    }

    public String getImage4() {
        return Image4;
    }

    public String getImage5() {
        return Image5;
    }

    public String getImage6() {
        return Image6;
    }

    public String getImage7() {
        return Image7;
    }

    public String getImage8() {
        return Image8;
    }

    public String getImage9() {
        return Image9;
    }

    public String getImage10() {
        return Image10;
    }
}


