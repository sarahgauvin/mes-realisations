package com.sarah.synchronisation;

public class Projets {

    private String type;
    private String club;
    private String nom;

        public Projets(String t, String c, String n){
            this.type = t;
            this.club=c;
            this.nom=n;

        }
        public String getNom() {
            return nom;
        }

        public String getClub() {
            return club;
        }

        public String getType() {
            return type;
        }
    }


