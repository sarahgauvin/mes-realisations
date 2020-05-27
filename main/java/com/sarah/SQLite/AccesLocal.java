package com.sarah.SQLite;

/*************************************************/
// Nom du projet: GAP'Scpace
// Nom du fichier: AccesLocal.java
// Version : 1.0
// Nom du programmeur: Thomas ARROYOS
// Date de création : 30 janvier 2020
// Rôle du fichier: ajoute une ligne aux BDDs GAPSpaceTable et Images
// Nom des composants utilises:
// Historique du fichier: 30 janvier 2020 ajout des fonctions ajout et recupDernier
//                        28 avril 2020 ajout des fonctions ajoutImages et recupLeDernier
//                        13 mai 2020 ajout des champs supplémentaires de GAPSpaceTable dans méthodes ajout et recupDernier
/*************************************************/


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AccesLocal {

    private String nomBase = "BaseDeDonnees.db";
    private Integer versionBase = 9;
    private MySQLiteOpenHelper accesdb;
    private SQLiteDatabase bd;

    public AccesLocal(Context context){
        accesdb = new MySQLiteOpenHelper(context, nomBase, null, versionBase);
    }

    // Nom : ajout
    // Rôle : ajoute une ligne dans la BDD GAPSpaceTable
    // Paramètres d'entrée : gapSpace
    public void ajout(GAPSpace gapSpace){
        bd = accesdb.getWritableDatabase();
        String req = "INSERT INTO GAPSpaceTable(NomProjet, NomPyrotechnicien, NomMaitreDoeuvre, ReferencePropulseur1, ReferencePropulseur2, ReferencePropulseur3, TypeCasing1, TypeCasing2, TypeCasing3, NumeroIdentification1, NumeroIdentification2, NumeroIdentification3, ReferenceLotPropergol1, ReferenceLotPropergol2, ReferenceLotPropergol3, DateFabrication1, DateFabrication2, DateFabrication3, NumeroIdentificationLot1, NumeroIdentificationLot2, NumeroIdentificationLot3, MasseMatiereActive1, MasseMatiereActive2, MasseMatiereActive3, ImpedenceInflammateur, ImpedenceLigne, DateHeureLancement, QualiteDuVol, Incident, Commentaires) VALUES";
        req += "(\""+gapSpace.getNomProjet()+"\",\""+gapSpace.getNomPyrotechnicien()+"\",\""+gapSpace.getNomMaitreDoeuvre()+"\",\""+gapSpace.getReferencePropulseur1()+"\",\""+gapSpace.getReferencePropulseur2()+"\",\""+gapSpace.getReferencePropulseur3()+"\",\""+gapSpace.getTypeCasing1()+"\",\""+gapSpace.getTypeCasing2()+"\",\""+gapSpace.getTypeCasing3()+"\",\""+gapSpace.getNumeroIdentification1()+"\",\""+gapSpace.getNumeroIdentification2()+"\",\""+gapSpace.getNumeroIdentification3()+"\",\""+gapSpace.getReferenceLotPropergol1()+"\",\""+gapSpace.getReferenceLotPropergol2()+"\",\""+gapSpace.getReferenceLotPropergol3()+"\",\""+gapSpace.getDateFabrication1()+"\",\""+gapSpace.getDateFabrication2()+"\",\""+gapSpace.getDateFabrication3()+"\",\""+gapSpace.getNumeroIdentificationLot1()+"\",\""+gapSpace.getNumeroIdentificationLot2()+"\",\""+gapSpace.getNumeroIdentificationLot3()+"\",\""+gapSpace.getMasseMatiereActive1()+"\",\""+gapSpace.getMasseMatiereActive2()+"\",\""+gapSpace.getMasseMatiereActive3()+"\",\""+gapSpace.getImpedenceInflammateur()+"\",\""+gapSpace.getImpedenceLigne()+"\",\""+gapSpace.getDateHeureLancement()+"\",\""+gapSpace.getQualiteDuVol()+"\",\""+gapSpace.getIncident()+"\",\""+gapSpace.getCommentaires()+"\")";
        bd.execSQL(req);
    }

    // Nom : ajoutImages
    // Rôle : ajoute une ligne dans la BDD Images
    // Paramètres d'entrée : gapSpaceImages
    public void ajoutImages(GAPSpaceImages gapSpaceImages){
        bd = accesdb.getWritableDatabase();
        String req2 = "INSERT INTO Images(NomProjet, Horodatage, Image1, Image2, Image3, Image4, Image5, Image6, Image7, Image8, Image9, Image10)VALUES";
        req2 += "(\""+gapSpaceImages.getNomProjet()+"\",\""+gapSpaceImages.getHorodatage()+"\","+gapSpaceImages.getImage1()+"\","+gapSpaceImages.getImage2()+"\","+gapSpaceImages.getImage3()+"\","+gapSpaceImages.getImage4()+"\","+gapSpaceImages.getImage5()+"\","+gapSpaceImages.getImage6()+"\","+gapSpaceImages.getImage7()+"\","+gapSpaceImages.getImage8()+"\","+gapSpaceImages.getImage9()+"\","+gapSpaceImages.getImage10()+")";
        bd.execSQL(req2);
    }

    // Nom : recupDernier
    // Rôle : pointe la dernière ligne de la BDD pour ajouter chaque entrée l'une après l'autre
    // Valeur de retour : gapSpace
    public GAPSpace recupDernier(){
        bd = accesdb.getReadableDatabase();
        GAPSpace gapSpace = null;
        String req = "SELECT * FROM GAPSpaceTable";
        Cursor cursor = bd.rawQuery(req, null);
        cursor.moveToLast();
        if(!cursor.isAfterLast()){
            String NomProjet = cursor.getString(0);
            String NomPyrotechnicien = cursor.getString(1);
            String NomMaitreDoeuvre = cursor.getString(2);
            String ReferencePropulseur1 = cursor.getString(3);
            String ReferencePropulseur2 = cursor.getString(4);
            String ReferencePropulseur3 = cursor.getString(5);
            String TypeCasing1 = cursor.getString(6);
            String TypeCasing2 = cursor.getString(7);
            String TypeCasing3 = cursor.getString(8);
            String NumeroIdentification1 = cursor.getString(9);
            String NumeroIdentification2 = cursor.getString(10);
            String NumeroIdentification3 = cursor.getString(11);
            String ReferenceLotPropergol1 = cursor.getString(12);
            String ReferenceLotPropergol2 = cursor.getString(13);
            String ReferenceLotPropergol3 = cursor.getString(14);
            String DateFabrication1 = cursor.getString(15);
            String DateFabrication2 = cursor.getString(16);
            String DateFabrication3 = cursor.getString(17);
            String NumeroIdentificationLot1 = cursor.getString(18);
            String NumeroIdentificationLot2 = cursor.getString(19);
            String NumeroIdentificationLot3 = cursor.getString(20);
            String MasseMatiereActive1 = cursor.getString(21);
            String MasseMatiereActive2 = cursor.getString(22);
            String MasseMatiereActive3 = cursor.getString(23);
            String ImpedenceInflammateur = cursor.getString(24);
            String ImpedenceLigne = cursor.getString(25);
            String DateHeureLancement = cursor.getString(26);
            String QualiteDuVol = cursor.getString(27);
            String Incident = cursor.getString(28);
            String Commentaires = cursor.getString(29);
            gapSpace = new GAPSpace(NomProjet, NomPyrotechnicien, NomMaitreDoeuvre, ReferencePropulseur1, ReferencePropulseur2, ReferencePropulseur3, TypeCasing1, TypeCasing2, TypeCasing3, NumeroIdentification1, NumeroIdentification2, NumeroIdentification3, ReferenceLotPropergol1, ReferenceLotPropergol2, ReferenceLotPropergol3, DateFabrication1, DateFabrication2, DateFabrication3, NumeroIdentificationLot1, NumeroIdentificationLot2, NumeroIdentificationLot3, MasseMatiereActive1, MasseMatiereActive2, MasseMatiereActive3, ImpedenceInflammateur, ImpedenceLigne, DateHeureLancement, QualiteDuVol, Incident, Commentaires);
        }
        cursor.close();
        return gapSpace;
    }

    // Nom : recupLeDernier
    // Rôle : pointe la dernière ligne de la BDD pour ajouter chaque entrée l'une après l'autre
    // Valeur de retour : gapSpaceImages
    /*public GAPSpaceImages recupLeDernier(){
        bd = accesdb.getReadableDatabase();
        GAPSpaceImages gapSpaceImages = null;
        String req2 = "SELECT * FROM Images";
        Cursor c = bd.rawQuery(req2, null);
        c.moveToLast();
        if(!c.isAfterLast()){
            String NomProjet = c.getString(0);
            String Horodatage = c.getString(1);
            String[] Tableau = c.getString(2);
            gapSpaceImages = new GAPSpaceImages(NomProjet, Horodatage, Tableau);
        }
        c.close();
        return gapSpaceImages;
    }*/

    /*public void supprimePremier(GAPSpace gapSpace){
        bd = accesdb.getWritableDatabase();
        String req = "DELETE FROM GAPSpaceTable WHERE NomProjet = "+gapSpace.getNomProjet()+"";
        Cursor cursor = bd.rawQuery(req, null);
        cursor.moveToFirst();
        if(!cursor.isBeforeFirst()){

        }
    }*/
}

