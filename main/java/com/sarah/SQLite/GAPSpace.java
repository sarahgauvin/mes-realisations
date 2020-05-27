

/*************************************************/
// Nom du projet: GAP'Scpace
// Nom du fichier: GAPSpace.java
// Version : 1.0
// Nom du programmeur: Thomas ARROYOS
// Date de création : 30 janvier 2020
// Rôle du fichier: récupère les informations des champs du formulaire et les lie aux champs de la BDD GAPSpaceTable
// Nom des composants utilises:
// Historique du fichier: 30 janvier 2020 ajout de toutes les fonctions présentes
//                        13 mai 2020 ajout des nouvelles variables de la maj de GAPSpaceTable et des méthodes get et set
/*************************************************/
package com.sarah.SQLite;

import java.io.Serializable;

/**
 * implémentation du nom des lignes de la base de données GAPSpaceTable
 */
public class GAPSpace implements Serializable {
    private String NomProjet;
    private String NomPyrotechnicien;
    private String NomMaitreDoeuvre;
    private String ReferencePropulseur1;
    private String ReferencePropulseur2;
    private String ReferencePropulseur3;
    private String TypeCasing1;
    private String TypeCasing2;
    private String TypeCasing3;
    private String NumeroIdentification1;
    private String NumeroIdentification2;
    private String NumeroIdentification3;
    private String ReferenceLotPropergol1;
    private String ReferenceLotPropergol2;
    private String ReferenceLotPropergol3;
    private String DateFabrication1;
    private String DateFabrication2;
    private String DateFabrication3;
    private String NumeroIdentificationLot1;
    private String NumeroIdentificationLot2;
    private String NumeroIdentificationLot3;
    private String MasseMatiereActive1;
    private String MasseMatiereActive2;
    private String MasseMatiereActive3;
    private String ImpedenceInflammateur;
    private String ImpedenceLigne;
    private String DateHeureLancement;
    private String QualiteDuVol;
    private String Incident;
    private String Commentaires;


    // Nom : GAPSpace
    // Rôle : lie les champs de la BDD GAPSpaceTable avec les infos récupérées dans les champs du formulaire
    // Paramètres d'entrée : NomProjet, NomPyrotechnicien, NomMaitreDoeuvre, ReferencePropulseurx3, TypeCasingx3, NumeroIdentificationx3, ReferenceLotPropergolx3, DateFabricationx3, NumeroIdentificationLotx3, MasseMatiereActivex3, ImpedenceInflammateur, ImpedenceLigne, DateHeureLancement, QualiteDuVol, Incident, Commentaires
    public GAPSpace(String NomProjet, String NomPyrotechnicien, String NomMaitreDoeuvre, String ReferencePropulseur1, String ReferencePropulseur2, String ReferencePropulseur3, String TypeCasing1, String TypeCasing2, String TypeCasing3, String NumeroIdentification1, String NumeroIdentification2, String NumeroIdentification3, String ReferenceLotPropergol1, String ReferenceLotPropergol2, String ReferenceLotPropergol3, String DateFabrication1, String DateFabrication2, String DateFabrication3, String NumeroIdentificationLot1, String NumeroIdentificationLot2, String NumeroIdentificationLot3, String MasseMatiereActive1, String MasseMatiereActive2, String MasseMatiereActive3, String ImpedenceInflammateur, String ImpedenceLigne, String DateHeureLancement, String QualiteDuVol, String Incident, String Commentaires) {
        super();
        this.NomProjet = NomProjet;
        this.NomPyrotechnicien = NomPyrotechnicien;
        this.NomMaitreDoeuvre = NomMaitreDoeuvre;
        this.ReferencePropulseur1 = ReferencePropulseur1;
        this.ReferencePropulseur2 = ReferencePropulseur2;
        this.ReferencePropulseur3 = ReferencePropulseur3;
        this.TypeCasing1 = TypeCasing1;
        this.TypeCasing2 = TypeCasing2;
        this.TypeCasing3 = TypeCasing3;
        this.NumeroIdentification1 = NumeroIdentification1;
        this.NumeroIdentification2 = NumeroIdentification2;
        this.NumeroIdentification3 = NumeroIdentification3;
        this.ReferenceLotPropergol1 = ReferenceLotPropergol1;
        this.ReferenceLotPropergol2 = ReferenceLotPropergol2;
        this.ReferenceLotPropergol3 = ReferenceLotPropergol3;
        this.DateFabrication1 = DateFabrication1;
        this.DateFabrication2 = DateFabrication2;
        this.DateFabrication3 = DateFabrication3;
        this.NumeroIdentificationLot1 = NumeroIdentificationLot1;
        this.NumeroIdentificationLot2 = NumeroIdentificationLot2;
        this.NumeroIdentificationLot3 = NumeroIdentificationLot3;
        this.MasseMatiereActive1 = MasseMatiereActive1;
        this.MasseMatiereActive2 = MasseMatiereActive2;
        this.MasseMatiereActive3 = MasseMatiereActive3;
        this.ImpedenceInflammateur = ImpedenceInflammateur;
        this.ImpedenceLigne = ImpedenceLigne;
        this.DateHeureLancement = DateHeureLancement;
        this.QualiteDuVol = QualiteDuVol;
        this.Incident = Incident;
        this.Commentaires = Commentaires;
    }

    // Nom : getNomProjet
    // Rôle : récupère le nom du projet
    // Valeur de retour : NomProjet
    public String getNomProjet() {
        return NomProjet;
    }
    // Nom : setNomProjet
    // Rôle : instancie le nom du projet
    // Paramètre d'entrée : NomProjet
    public void setNomProjet(String NomProjet) {
        this.NomProjet = NomProjet;
    }

    // Nom : getNomPyrotechnicien
    // Rôle : récupère le nom du pyrotechnicien
    // Valeur de retour : NomPyrotechnicien
    public String getNomPyrotechnicien() {
        return NomPyrotechnicien;
    }
    // Nom : setNomPyrotechnicien
    // Rôle : instancie le nom du pyrotechnicien
    // Paramètre d'entrée : NomPyrotechnicien
    public void setNomPyrotechnicien(String NomPyrotechnicien) {
        this.NomPyrotechnicien = NomPyrotechnicien;
    }

    // Nom : getNomMaitreDoeuvre
    // Rôle : récupère le nom du maître d'oeuvre
    // Valeur de retour : NomMaitreDoeuvre
    public String getNomMaitreDoeuvre() {
        return NomMaitreDoeuvre;
    }
    // Nom : setNomMaitreDoeuvre
    // Rôle : instancie le nom du maitre d'oeuvre
    // Paramètre d'entrée : NomMaitreDoeuvre
    public void setNomMaitreDoeuvre(String NomMaitreDoeuvre) {
        this.NomMaitreDoeuvre = NomMaitreDoeuvre;
    }

    public String getReferencePropulseur1() {
        return ReferencePropulseur1;
    }
    public void setReferencePropulseur1(String referencePropulseur1) {
        ReferencePropulseur1 = referencePropulseur1;
    }

    public String getReferencePropulseur2() {
        return ReferencePropulseur2;
    }
    public void setReferencePropulseur2(String referencePropulseur2) {
        ReferencePropulseur2 = referencePropulseur2;
    }

    public String getReferencePropulseur3() {
        return ReferencePropulseur3;
    }
    public void setReferencePropulseur3(String referencePropulseur3) {
        ReferencePropulseur3 = referencePropulseur3;
    }

    public String getTypeCasing1() {
        return TypeCasing1;
    }
    public void setTypeCasing1(String typeCasing1) {
        TypeCasing1 = typeCasing1;
    }

    public String getTypeCasing2() {
        return TypeCasing2;
    }
    public void setTypeCasing2(String typeCasing2) {
        TypeCasing2 = typeCasing2;
    }

    public String getTypeCasing3() {
        return TypeCasing3;
    }
    public void setTypeCasing3(String typeCasing3) {
        TypeCasing3 = typeCasing3;
    }

    public String getNumeroIdentification1() {
        return NumeroIdentification1;
    }
    public void setNumeroIdentification1(String numeroIdentification1) {
        NumeroIdentification1 = numeroIdentification1;
    }

    public String getNumeroIdentification2() {
        return NumeroIdentification2;
    }
    public void setNumeroIdentification2(String numeroIdentification2) {
        NumeroIdentification2 = numeroIdentification2;
    }

    public String getNumeroIdentification3() {
        return NumeroIdentification3;
    }
    public void setNumeroIdentification3(String numeroIdentification3) {
        NumeroIdentification3 = numeroIdentification3;
    }

    public String getReferenceLotPropergol1() {
        return ReferenceLotPropergol1;
    }
    public void setReferenceLotPropergol1(String referenceLotPropergol1) {
        ReferenceLotPropergol1 = referenceLotPropergol1;
    }

    public String getReferenceLotPropergol2() {
        return ReferenceLotPropergol2;
    }
    public void setReferenceLotPropergol2(String referenceLotPropergol2) {
        ReferenceLotPropergol2 = referenceLotPropergol2;
    }

    public String getReferenceLotPropergol3() {
        return ReferenceLotPropergol3;
    }
    public void setReferenceLotPropergol3(String referenceLotPropergol3) {
        ReferenceLotPropergol3 = referenceLotPropergol3;
    }

    public String getDateFabrication1() {
        return DateFabrication1;
    }
    public void setDateFabrication1(String dateFabrication1) {
        DateFabrication1 = dateFabrication1;
    }

    public String getDateFabrication2() {
        return DateFabrication2;
    }
    public void setDateFabrication2(String dateFabrication2) {
        DateFabrication2 = dateFabrication2;
    }

    public String getDateFabrication3() {
        return DateFabrication3;
    }
    public void setDateFabrication3(String dateFabrication3) {
        DateFabrication3 = dateFabrication3;
    }

    public String getNumeroIdentificationLot1() {
        return NumeroIdentificationLot1;
    }
    public void setNumeroIdentificationLot1(String numeroIdentificationLot1) {
        NumeroIdentificationLot1 = numeroIdentificationLot1;
    }

    public String getNumeroIdentificationLot2() {
        return NumeroIdentificationLot2;
    }
    public void setNumeroIdentificationLot2(String numeroIdentificationLot2) {
        NumeroIdentificationLot2 = numeroIdentificationLot2;
    }

    public String getNumeroIdentificationLot3() {
        return NumeroIdentificationLot3;
    }
    public void setNumeroIdentificationLot3(String numeroIdentificationLot3) {
        NumeroIdentificationLot3 = numeroIdentificationLot3;
    }

    public String getMasseMatiereActive1() {
        return MasseMatiereActive1;
    }
    public void setMasseMatiereActive1(String masseMatiereActive1) {
        MasseMatiereActive1 = masseMatiereActive1;
    }

    public String getMasseMatiereActive2() {
        return MasseMatiereActive2;
    }
    public void setMasseMatiereActive2(String masseMatiereActive2) {
        MasseMatiereActive2 = masseMatiereActive2;
    }

    public String getMasseMatiereActive3() {
        return MasseMatiereActive3;
    }
    public void setMasseMatiereActive3(String masseMatiereActive3) {
        MasseMatiereActive3 = masseMatiereActive3;
    }

    public String getImpedenceInflammateur() {
        return ImpedenceInflammateur;
    }
    public void setImpedenceInflammateur(String ImpedenceInflammateur) {
        this.ImpedenceInflammateur = ImpedenceInflammateur;
    }

    public String getImpedenceLigne() {
        return ImpedenceLigne;
    }
    public void setImpedenceLigne(String ImpedenceLigne) {
        this.ImpedenceLigne = ImpedenceLigne;
    }

    public String getDateHeureLancement(){
        return DateHeureLancement;
    }
    public void setDateHeureLancement(String DateHeureLancement){
        this.DateHeureLancement = DateHeureLancement;
    }

    public String getQualiteDuVol(){
        return QualiteDuVol;
    }
    public void setQualiteDuVol(String QualiteDuVol){
        this.QualiteDuVol = QualiteDuVol;
    }

    public String getIncident(){
        return Incident;
    }
    public void setIncident(String Incident){
        this.Incident = Incident;
    }

    public String getCommentaires(){
        return Commentaires;
    }
    public void setCommentaires(String commentaires) {
        this.Commentaires = commentaires;
    }
}

