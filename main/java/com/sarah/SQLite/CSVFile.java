package com.sarah.SQLite;

/*************************************************/
// Nom du projet: GAP'Scpace
// Nom du fichier: CSVFile.java
// Version : 1.0
// Nom du programmeur: Thomas ARROYOS
// Date de création : 19 mars 2020
// Rôle du fichier: créé le fichier csv et le dossier qui le contiendra et le remplie le csv avec les infos récupérées du formulaire
// Nom des composants utilises:
// Historique du fichier: 19 mars 2020 ajout de toutes les fonctions présentes
/*************************************************/

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CSVFile {

    //static File csvFile;
    private static String separateur =",";

    // Nom : creerCSV
    // Rôle : créé le fichier csv et son dossier
    // Paramètres d'entrée : nomFichier
    // Valeur de retour : csvFile
    public static File creerCSV(String nomChemin, String nomFichier) {

        //création dossier nommé folder
        File folder = new File(nomChemin);
        //création fichier nommé csvFile
        File csvFile = new File(nomFichier);
        Log.i("CSV", "création fichier");

        if (!folder.exists()) {
            folder.mkdir();
        }

        if (!csvFile.exists()) {
            try {
                csvFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i("CSV", "creerCSV");
        return csvFile;
    }

    // Nom : remplirCSV
    // Rôle : remplie le fichier csv avec les infos récupérées des champs du formulaire
    // Paramètres d'entrée : NomProjet, NomPyrotechnicien, NomMaitreDoeuvre, ReferencePropulseurx3, TypeCasingx3, NumeroIdentificationx3, ReferenceLotPropergolx3, DateFabricationx3, NumeroIdentificationLotx3, MasseMatiereActivex3, ImpedenceInflammateur, ImpedenceLigne, DateHeureLancement, QualiteDuVol, Incident, Commentaires
    public static void remplirCSV(String NomProjet, String NomPyrotechnicien, String NomMaitreDoeuvre, String ReferencePropulseur1, String TypeCasing1, String NumeroIdentification1, String ReferenceLotPropergol1, String DateFabrication1, String NumeroIdentificationLot1, String MasseMatiereActive1, String ImpedenceInflammateur, String ImpedenceLigne, String DateHeureLancement, String QualiteDuVol, String Incident, String Commentaires) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
        String date = dateFormat.format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
        String heure = sdf.format(new Date());
        String pathName = "sdcard/fichierCSV";
        String fileName = "sdcard/fichierCSV/" + date + "_" + heure + "_" + NomProjet + "_" + NomPyrotechnicien + ".csv";
        Log.i("tag2", "La variable scae contient :" + NomProjet + NomPyrotechnicien + NomMaitreDoeuvre + ReferenceLotPropergol1);

        List<String> csvWords = Arrays.asList("NomProjet"+";"+"NomPyrotechnicien"+";"+"NomMaitreDoeuvre"+";"+"ReferencePropulseur1"+";"+"TypeCasing1"+";"+"NumeroIdentification1"+";"+"ReferenceLotPropergol1"+";"+"DateFabrication1"+";"+"NumeroIdentificationLot1"+";"+"MasseMatiereActive1"+";"+"ImpedenceInflammateur"+";"+"ImpedenceLigne"+";"+"DateHeureLancement"+";"+"QualiteDuVol"+";"+"Incident"+";"+"Commentaires");
        List<String> csvWords2 = Arrays.asList(NomProjet,";", NomPyrotechnicien,";", NomMaitreDoeuvre, ";",ReferencePropulseur1,";", TypeCasing1,";", NumeroIdentification1,";",  ReferenceLotPropergol1,";",  DateFabrication1,";", NumeroIdentificationLot1, ";",MasseMatiereActive1,";", ImpedenceInflammateur,";", ImpedenceLigne,";", DateHeureLancement,";", QualiteDuVol,";", Incident,";",Commentaires);

        //List<String> csvWords = Arrays.asList("NomProjet"+separateur+"NomPyrotechnicien"+separateur+"NomMaitreDoeuvre"+separateur+"ReferencePropulseur1"+separateur+"ReferencePropulseur2"+separateur+"ReferencePropulseur3"+separateur+"TypeCasing1"+separateur+"TypeCasing2"+separateur+"TypeCasing3"+separateur+"NumeroIdentification1"+separateur+"NumeroIdentification2"+separateur+"NumeroIdentification3"+separateur+"ReferenceLotPropergol1"+separateur+"ReferenceLotPropergol2"+separateur+"ReferenceLotPropergol3"+separateur+"DateFabrication1"+separateur+"DateFabrication2"+separateur+"DateFabrication3"+separateur+"NumeroIdentificationLot1"+separateur+"NumeroIdentificationLot2"+separateur+"NumeroIdentificationLot3"+separateur+"MasseMatiereActive1"+separateur+"MasseMatiereActive2"+separateur+"MasseMatiereActive3"+separateur+"ImpedenceInflammateur"+separateur+"ImpedenceLigne"+separateur+"DateHeureLancement"+separateur+"QualiteDuVol"+separateur+"Incident"+separateur+"Commentaires");
        //List<String> csvWords2 = Arrays.asList(NomProjet, separateur, NomPyrotechnicien, separateur, NomMaitreDoeuvre, separateur, ReferencePropulseur1, separateur, ReferencePropulseur2, separateur, ReferencePropulseur3, separateur, TypeCasing1, separateur, TypeCasing2, separateur, TypeCasing3, separateur, NumeroIdentification1, separateur, NumeroIdentification2, separateur, NumeroIdentification3, separateur, ReferenceLotPropergol1, separateur, ReferenceLotPropergol2, separateur, ReferenceLotPropergol3, separateur, DateFabrication1, separateur, DateFabrication2, separateur, DateFabrication3, separateur, NumeroIdentificationLot1, separateur, NumeroIdentificationLot2, separateur, NumeroIdentificationLot3, separateur, MasseMatiereActive1, separateur, MasseMatiereActive2, separateur, MasseMatiereActive3, separateur, ImpedenceInflammateur, separateur, ImpedenceLigne, separateur, DateHeureLancement, separateur, QualiteDuVol, separateur, Incident, separateur, Commentaires);

        File fichier = creerCSV(pathName, fileName);
        //if (fichier.getUsableSpace() < 100000) {
        try {
            FileWriter writer = new FileWriter(fichier);
            BufferedWriter bw = new BufferedWriter(writer);

            for (String word : csvWords) {
                bw.write(word);
                bw.write(separateur);
            }
            bw.newLine();
            for (String word2 : csvWords2) {
                bw.write(word2);

            }
            bw.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("CSV", "remplirCSV");
        //}
    }//else{
    //faire un toast
    //}


}