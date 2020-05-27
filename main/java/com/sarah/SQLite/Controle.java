package com.sarah.SQLite;

/*************************************************/
// Nom du projet: GAP'Scpace
// Nom du fichier: Controle.java
// Version : 1.0
// Nom du programmeur: Thomas ARROYOS
// Date de création : 30 janvier 2020
// Rôle du fichier: récupère les informations des champs via la classe GAPSpace et les implémente dans la BDD GAPSpaceTable
// Nom des composants utilises:
// Historique du fichier: 30 janvier 2020 ajout de toutes les fonctions présentes
//                        13 mai 2020 ajout paramètres méthode creerProfil et ajout de méthodes get
/*************************************************/

import android.content.Context;

public class Controle {


    private static Controle instance = null;
    private static GAPSpace gapSpace;
    private static String nomFic = "sauvegarde_donnees";
    private static AccesLocal accesLocal;

    private Controle(){
        super();
    }

    // Nom : getInstance
    // Rôle : appelle le constructeur de la classe Controle et de la classe AccesLocal
    // Paramètres d'entrée : context
    // Valeur de retour : Controle.instance
    public static final Controle getInstance(Context context){
        if(Controle.instance == null){
            Controle.instance = new Controle();
            recupSerialize(context);
            accesLocal = new AccesLocal(context);
            //gapSpace = accesLocal.recupDernier();
        }
        return Controle.instance;
    }

    public void creerProfil(String NomProjet, String NomPyrotechnicien, String NomMaitreDoeuvre, String ReferencePropulseur1, String ReferencePropulseur2, String ReferencePropulseur3, String TypeCasing1, String TypeCasing2, String TypeCasing3, String NumeroIdentification1, String NumeroIdentification2, String NumeroIdentification3, String ReferenceLotPropergol1, String ReferenceLotPropergol2, String ReferenceLotPropergol3, String DateFabrication1, String DateFabrication2, String DateFabrication3, String NumeroIdentificationLot1, String NumeroIdentificationLot2, String NumeroIdentificationLot3, String MasseMatiereActive1, String MasseMatiereActive2, String MasseMatiereActive3, String ImpedenceInflammateur, String ImpedenceLigne, String DateHeureLancement, String QualiteDuVol, String Incident, String Commentaires, Context context){
        gapSpace = new GAPSpace(NomProjet, NomPyrotechnicien, NomMaitreDoeuvre, ReferencePropulseur1, ReferencePropulseur2, ReferencePropulseur3, TypeCasing1, TypeCasing2, TypeCasing3, NumeroIdentification1, NumeroIdentification2, NumeroIdentification3, ReferenceLotPropergol1, ReferenceLotPropergol2, ReferenceLotPropergol3, DateFabrication1, DateFabrication2, DateFabrication3, NumeroIdentificationLot1, NumeroIdentificationLot2, NumeroIdentificationLot3, MasseMatiereActive1, MasseMatiereActive2, MasseMatiereActive3, ImpedenceInflammateur, ImpedenceLigne, DateHeureLancement, QualiteDuVol, Incident, Commentaires);
        Serialize.serialize(nomFic, gapSpace, context);
        accesLocal.ajout(gapSpace);
    }

    // Nom : recupSerialize
    // Rôle : récupère toutes les infos fournies dans le formulaire
    // Paramètres d'entrée : context
    private static void recupSerialize(Context context){
        gapSpace = (GAPSpace) Serialize.deSerialize(nomFic, context);
    }


    // Nom : getNomProjet
    // Rôle : récupère le nom du projet via gapSpace
    // Valeur de retour : gapSpace.getNomProjet()
    public String getNomProjet() {
        return gapSpace.getNomProjet();
    }

    // Nom : getNomPyrotechnicien
    // Rôle : récupère le nom du pyrotechnicien via gapSpace
    // Valeur de retour : gapSpace.getNomPyrotechnicien()
    public String getNomPyrotechnicien() {
        return gapSpace.getNomPyrotechnicien();
    }

    // Nom : getNomMaitreDoeuvre
    // Rôle : récupère le nom du maître d'oeuvre via gapSpace
    // Valeur de retour : gapSpace.getNomMaitreDoeuvre()
    public String getNomMaitreDoeuvre() {
        return gapSpace.getNomMaitreDoeuvre();
    }

    // Nom : getReferencePropulseur
    // Rôle : récupère la référence du propulseur via gapSpace
    // Valeur de retour : gapSpace.getReferencePropulseur1() ou gapSpace.getReferencePropulseur2() ou gapSpace.getReferencePropulseur3()
    public String getReferencePropulseur1() {
        return gapSpace.getReferencePropulseur1();
    }
    public String getReferencePropuseur2(){ return gapSpace.getReferencePropulseur2(); }
    public String getReferencePropulseur3(){ return gapSpace. getReferencePropulseur3(); }

    // Nom : getTypeCasing
    // Rôle : récupère le type de casing via gapSpace
    // Valeur de retour : gapSpace.getTypeCasing1() ou gapSpace.getTypeCasing2() ou gapSpace.getTypeCasing3()
    public String getTypeCasing1() {
        return gapSpace.getTypeCasing1();
    }
    public String getTypeCasing2(){ return gapSpace.getTypeCasing2(); }
    public String getTypeCasing3(){ return gapSpace.getTypeCasing3(); }

    // Nom : getNumeroIdentification
    // Rôle : récupère le numéro d'identification via gapSpace
    // Valeur de retour : gapSpace.getNumeroIdentification1() ou gapSpace.getNumeroIdentification2() ou gapSpace.getNumeroIdentification3()
    public String getNumeroIdentification1() {
        return gapSpace.getNumeroIdentification1();
    }
    public String getNumeroIdentification2(){ return gapSpace.getNumeroIdentification2(); }
    public String getNumeroIdentification(){ return gapSpace.getNumeroIdentification3(); }

    // Nom : getReferenceLotPropergol
    // Rôle : récupère la référence du lot de propergol via gapSpace
    // Valeur de retour : gapSpace.getReferenceLotPropergol1() ou gapSpace.getReferenceLotPropergol2() ou gapSpace.getReferenceLotPropergol3()
    public String getReferenceLotPropergol1() {
        return gapSpace.getReferenceLotPropergol1();
    }
    public String getReferenceLotPropergol2(){ return gapSpace.getReferenceLotPropergol2(); }
    public String getReferenceLotPropergol3(){ return gapSpace.getReferenceLotPropergol3(); }

    // Nom : getDateFabrication
    // Rôle : récupère la date de fabrication via gapSpace
    // Valeur de retour : gapSpace.getDateFabrication1() ou gapSpace.getDateFabrication2() ou gapSpace.getDateFabrication3()
    public String getDateFabrication1() {
        return gapSpace.getDateFabrication1();
    }
    public String getDateFabrication2(){ return gapSpace.getDateFabrication2(); }
    public String getDateFabrication3(){ return gapSpace.getDateFabrication3(); }

    // Nom : getNumeroIdentificationLot
    // Rôle : récupère le numéro d'identification du lot via gapSpace
    // Valeur de retour : gapSpace.getNumeroIdentificationLot1() ou gapSpace.getNumeroIdentificationLot2() ou gapSpace.getNumeroIdentificationLot3()
    public String getNumeroIdentificationLot1() {
        return gapSpace.getNumeroIdentificationLot1();
    }
    public String getNumeroIdentificationLot2(){ return gapSpace.getNumeroIdentificationLot2(); }
    public String getNumeroIdentificationLot3(){ return gapSpace.getNumeroIdentificationLot3(); }

    // Nom : getMasseMatiereActive
    // Rôle : récupère la masse de matière active via gapSpace
    // Valeur de retour : gapSpace.getMasseMatiereActive1() ou gapSpace.getMasseMatiereActive2() ou gapSpace.getMasseMatiereActive3()
    public String getMasseMatiereActive1() {
        return gapSpace.getMasseMatiereActive1();
    }
    public String getMasseMatiereActive2(){ return gapSpace.getMasseMatiereActive2(); }
    public String getMasseMatiereActive3(){ return gapSpace.getMasseMatiereActive3(); }

    // Nom : getImpedenceInflammateur
    // Rôle : récupère l'impédence de l'inflammateur via gapSpace
    // Valeur de retour : gapSpace.getImpedenceInflammateur()
    public String getImpedenceInflammateur() {
        return gapSpace.getImpedenceInflammateur();
    }

    // Nom : getImpedenceLigne
    // Rôle : récupère l'impédence de la ligne via gapSpace
    // Valeur de retour : gapSpace.getImpedenceLigne()
    public String getImpedenceLigne() {
        return gapSpace.getImpedenceLigne();
    }

    // Nom : getDateHeureLancement
    // Rôle : récupère la date et l'heure du lancement via gapSpace
    // Valeur de retour : gapSpace.getDateHeureLancement()
    public String getDateHeureLancement(){
        return gapSpace.getDateHeureLancement();
    }

    // Nom : getQualiteDuVol
    // Rôle : récupère la qualité du vol via gapSpace
    // Valeur de retour : gapSpace.getQualiteDuVol()
    public String getQualiteDuVol(){
        return gapSpace.getQualiteDuVol();
    }

    // Nom : getIncident
    // Rôle : récupère l'incident via gapSpace
    // Valeur de retour : gapSpace.getIncident()
    public String getIncident(){
        return gapSpace.getIncident();
    }

    // Nom : getCommentaires
    // Rôle : récupère les commentaires via gapSpace
    // Valeur de retour : gapSpace.getCommentaires()
    public String getCommentaires(){
        return gapSpace.getCommentaires();
    }
}