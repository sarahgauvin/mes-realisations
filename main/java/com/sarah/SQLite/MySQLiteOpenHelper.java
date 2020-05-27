/*************************************************/
// Nom du projet: GAP'Scpace
// Nom du fichier: MySQLiteOpenHelper.java
// Version : 1.0
// Nom du programmeur: Thomas ARROYOS
// Date de création : 30 janvier 2020
// Rôle du fichier: ajoute une ligne aux BDDs GAPSpaceTable et Images
// Nom des composants utilises:
// Historique du fichier: 30 janvier 2020 ajout des fonctions ajout et recupDernier
//                        28 avril 2020 ajout de la fonction addInfosImages, de la variable creation2
//                                      et de la requête SQL pour supprimer la BDD Images dans la fonction onUpgrade
//                        13 mai 2020 mise à jour de la BDD GAPSpaceTable avec l'ajout de champs supplémentaires
/*************************************************/
package com.sarah.SQLite ;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    /**
     * Constructeur de la classe qui sera instancié dans formulaire.java
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    // Nom : onCreate
    // Rôle : créée les champs des BDDs GAPSpaceTable et Images
    // Paramètres de sortie : sqLiteDatabase
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String creation = "CREATE TABLE GAPSpaceTable("
                + "     NomProjet TEXT PRIMARY KEY,"
                + "     NomPyrotechnicien TEXT,"
                + "     NomMaitreDoeuvre TEXT,"
                + "     ReferencePropulseur1 TEXT,"
                + "     ReferencePropulseur2 TEXT,"
                + "     ReferencePropulseur3 TEXT,"
                + "     TypeCasing1 TEXT,"
                + "     TypeCasing2 TEXT,"
                + "     TypeCasing3 TEXT,"
                + "     NumeroIdentification1 TEXT,"
                + "     NumeroIdentification2 TEXT,"
                + "     NumeroIdentification3 TEXT,"
                + "     ReferenceLotPropergol1 TEXT,"
                + "     ReferenceLotPropergol2 TEXT,"
                + "     ReferenceLotPropergol3 TEXT,"
                + "     DateFabrication1 TEXT,"
                + "     DateFabrication2 TEXT,"
                + "     DateFabrication3 TEXT,"
                + "     NumeroIdentificationLot1 TEXT,"
                + "     NumeroIdentificationLot2 TEXT,"
                + "     NumeroIdentificationLot3 TEXT,"
                + "     MasseMatiereActive1 TEXT,"
                + "     MasseMatiereActive2 TEXT,"
                + "     MasseMatiereActive3 TEXT,"
                + "     ImpedenceInflammateur TEXT,"
                + "     ImpedenceLigne TEXT,"
                + "     DateHeureLancement TEXT,"
                + "     QualiteDuVol TEXT,"
                + "     Incident TEXT,"
                + "     Commentaires TEXT"
                + ")";
        sqLiteDatabase.execSQL(creation);
        Log.i("DATABASE","créé GAPSpaceTable");

        String creation2 = "CREATE TABLE Images("
                + "     id_Images INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "     NomProjet TEXT,"
                + "     Horodatage TEXT,"
                + "     Image1 BLOB,"
                + "     Image2 BLOB,"
                + "     Image3 BLOB,"
                + "     Image4 BLOB,"
                + "     Image5 BLOB,"
                + "     Image6 BLOB,"
                + "     Image7 BLOB,"
                + "     Image8 BLOB,"
                + "     Image9 BLOB,"
                + "     Image10 BLOB,"
                + "     FOREIGN KEY(NomProjet) REFERENCES GAPSpaceTable(NomProjet)"
                + ")";
        sqLiteDatabase.execSQL(creation2);
        Log.i("DATABASE","créé Images");
    }

    // Nom : onUpgrade
    // Rôle : supprime les BDDs GAPSpaceTable et Images si elles existent puis appelle la méthode onCreate
    // Paramètres de sortie : sqLiteDatabase, i, i1
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w(MySQLiteOpenHelper.class.getName(),"Mise à jour de la base de données version" + i + "à la version" + i1 + "");
        sqLiteDatabase.execSQL("DROP TABLE GAPSpaceTable");
        sqLiteDatabase.execSQL("DROP TABLE Images");
        onCreate(sqLiteDatabase);
        Log.i("DATABASE","invoque onUpdate");
    }

    // Nom : addInfos
    // Rôle : insert les infos dans les champs de la BDD GAPSpaceTable
    // Paramètres d'entrée : NomProjet, NomPyrotechnicien, NomMaitreDoeuvre, ReferencePropulseurx3, TypeCasingx3, NumeroIdentificationx3, ReferenceLotPropergolx3, DateFabricationx3, NumeroIdentificationLotx3, MasseMatiereActivex3, ImpedenceInflammateur, ImpedenceLigne, DateHeureLancement, QualiteDuVol, Incident, Commentaires
    public void addInfos(String nomProjet, String nomPyrotechnicien, String nomMaitreDoeuvre, String referencePropulseur1, String referencePropulseur2, String referencePropulseur3, String typeCasing1, String typeCasing2, String typeCasing3, String numeroIdentification1, String numeroIdentification2, String numeroIdentification3, String referenceLotPropergol1, String referenceLotPropergol2, String referenceLotPropergol3, String dateFabrication1, String dateFabrication2, String dateFabrication3, String numeroIdentificationLot1, String numeroIdentificationLot2, String numeroIdentificationLot3, String masseMatiereActive1, String masseMatiereActive2, String masseMatiereActive3, String impedenceInflammateur, String impedenceLigne, String dateHeureLancement, String qualiteDuVol, String incident, String commentaires) {

        String[] selection = {nomProjet};
        Cursor cursor;
        cursor = getWritableDatabase().query("GAPSpaceTable", null, "nomProjet = ?", selection, null, null, null);
        cursor.getCount();
        Log.i("DATABASE", String.valueOf(cursor.getCount()));
        if(cursor.getCount()==0){
            String sqlInsert = "INSERT INTO GAPSpaceTable (NomProjet, NomPyrotechnicien, NomMaitreDoeuvre, ReferencePropulseur1, ReferencePropulseur2, ReferencePropulseur3, TypeCasing1, TypeCasing2, TypeCasing3, NumeroIdentification1, NumeroIdentification2, NumeroIdentification3, ReferenceLotPropergol1, ReferenceLotPropergol2, ReferenceLotPropergol3, DateFabrication1, DateFabrication2, DateFabrication3, NumeroIdentificationLot1, NumeroIdentificationLot2, NumeroIdentificationLot3, MasseMatiereActive1, MasseMatiereActive2, MasseMatiereActive3, ImpedenceInflammateur, ImpedenceLigne, DateHeureLancement, QualiteDuVol, Incident, Commentaires) VALUES('"
                    + nomProjet + "','"
                    + nomPyrotechnicien + "','"
                    + nomMaitreDoeuvre + "','"
                    + referencePropulseur1 + "','"
                    + referencePropulseur2 + "','"
                    + referencePropulseur3 + "','"
                    + typeCasing1 + "','"
                    + typeCasing2 + "','"
                    + typeCasing3 + "','"
                    + numeroIdentification1 + "','"
                    + numeroIdentification2 + "','"
                    + numeroIdentification3 + "','"
                    + referenceLotPropergol1 + "','"
                    + referenceLotPropergol2 + "','"
                    + referenceLotPropergol3 + "','"
                    + dateFabrication1 + "','"
                    + dateFabrication2 + "','"
                    + dateFabrication3 + "','"
                    + numeroIdentificationLot1 + "','"
                    + numeroIdentificationLot2 + "','"
                    + numeroIdentificationLot3 + "','"
                    + masseMatiereActive1 + "','"
                    + masseMatiereActive2 + "','"
                    + masseMatiereActive3 + "','"
                    + impedenceInflammateur + "','"
                    + impedenceLigne + "','"
                    + dateHeureLancement + "','"
                    + qualiteDuVol + "','"
                    + incident + "','"
                    + commentaires + "'"
                    + ")";
            Log.i("DATABASE", sqlInsert);
            getWritableDatabase().execSQL(sqlInsert);
            Log.i("DATABASE","invoque addInfos");
        }
        if(cursor.getCount()==1){
            String sqlUpdate = "UPDATE GAPSpaceTable SET NomProjet='"+nomProjet+"',NomPyrotechnicien='"+nomPyrotechnicien+"',NomMaitreDoeuvre='"+nomMaitreDoeuvre+"',ReferencePropulseur1='"+referencePropulseur1+"',ReferencePropulseur2='"+referencePropulseur2+"',ReferencePropulseur3='"+referencePropulseur3+"',TypeCasing1='"+typeCasing1+"',TypeCasing2='"+typeCasing2+"',TypeCasing3='"+typeCasing3+"',NumeroIdentification1='"+numeroIdentification1+"',NumeroIdentification2='"+numeroIdentification2+"',NumeroIdentification3='"+numeroIdentification3+"',ReferenceLotPropergol1='"+referenceLotPropergol1+"',ReferenceLotPropergol2='"+referenceLotPropergol2+"',ReferenceLotPropergol3='"+referenceLotPropergol3+"',DateFabrication1='"+dateFabrication1+"',DateFabrication2='"+dateFabrication2+"',DateFabrication3='"+dateFabrication3+"',NumeroIdentificationLot1='"+numeroIdentificationLot1+"',NumeroIdentificationLot2='"+numeroIdentificationLot2+"',NumeroIdentificationLot3='"+numeroIdentificationLot3+"',MasseMatiereActive1='"+masseMatiereActive1+"',MasseMatiereActive2='"+masseMatiereActive2+"',MasseMatiereActive3='"+masseMatiereActive3+"',ImpedenceInflammateur='"+impedenceInflammateur+"',ImpedenceLigne='"+impedenceLigne+"',DateHeureLancement='"+dateHeureLancement+"',QualiteDuVol='"+qualiteDuVol+"',Incident='"+incident+"',Commentaires='"+commentaires+"' WHERE NomProjet='"+nomProjet+"'";
            Log.i("DATABASE", sqlUpdate);
            getWritableDatabase().execSQL(sqlUpdate);
            Log.i("DATABASE","invoque update addInfos");
        }
    }


    // Nom : addInfosImages
    // Rôle : insert les infos dans les champs de la BDD Images
    // Paramètres d'entrée : NomProjet, Horodatage, tableau
    public void addInfosImages(String nomProjet, String horodatage, String[] tableau) {

        String[] selection = {nomProjet};
        Cursor c;
        c = getWritableDatabase().query("Images", null, "nomProjet = ?", selection, null, null, null);
        c.getCount();

        Log.i("DATABASE2", String.valueOf(c.getCount()));
        Log.i("DATABASE2", "c'est" + tableau.length);

        Log.i("DATABASE2", "c'est" + tableau[0] + " "+ tableau[1] + " "+ tableau[2] +" "+  tableau[3] + " "+ tableau[4] + " "+tableau[5] + " "+ tableau[6]);




        if(c.getCount()==0){
            String sql2Insert = "INSERT INTO Images (NomProjet, Horodatage, Image1, Image2, Image3, Image4, Image5, Image6, Image7, Image8, Image9, Image10)VALUES('"
                    + nomProjet + "','"
                    + horodatage + "','"
                    + tableau[0] + "','"
                    + tableau[1] + "','"
                    + tableau[2]+ "','"
                    +tableau[3]+ "','"
                    + tableau[4]+ "','"
                    +tableau[5]+ "','"
                    +tableau[6]+ "','"
                    + tableau[7]+ "','"
                    +tableau[8]+ "','"
                    + tableau[9]+"'"
                    + ")";
            Log.i("DATABASE2", sql2Insert);
            getWritableDatabase().execSQL(sql2Insert);
            Log.i("DATABASE2","invoque addInfosImages");

        }
        if(c.getCount()==2){
            String sql2Update = "UPDATE Images SET NomProjet='"+nomProjet+"',Horodatage='"+horodatage+"',Image1='"+tableau[0]+"',Image2='"+tableau[1]+"',Image3='"+tableau[2]+"',Image4='"+tableau[3]+"',Image5='"+tableau[4]+"',Image6='"+tableau[5]+"',Image7='"+tableau[6]+"',Image8='"+tableau[7]+"',Image9='"+tableau[8]+"',Image10='"+tableau[9]+"' WHERE NomProjet='"+nomProjet+"'";
            Log.i("DATABASE2", sql2Update);
            getWritableDatabase().execSQL(sql2Update);
            Log.i("DATABASE2","invoque update addInfosImages");
            //Log.i("DATABASE2","invoque addInfosImages");
        }




    }
}