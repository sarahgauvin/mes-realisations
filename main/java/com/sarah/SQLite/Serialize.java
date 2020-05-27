package com.sarah.SQLite;

/*************************************************/
// Nom du projet: GAP'Scpace
// Nom du fichier: Serialize.java
// Version : 1.0
// Nom du programmeur: Thomas ARROYOS
// Date de création : 30 janvier 2020
// Rôle du fichier: récupère les dernières infos renseignées dans la BDD
// Nom des composants utilises:
// Historique du fichier: 30 janvier 2020 ajout 2 fonctions présentes
/*************************************************/
;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

abstract class Serialize {

    // Nom : serialize
    // Rôle : enregistre les dernières infos renseignées dans la BDD
    // Paramètres d'entrée : filename, object, context
    public static void serialize(String filename, Object object, Context context){
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream;
            try {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(object);
                objectOutputStream.flush();
                objectOutputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    // Nom : deSerialize
    // Rôle : efface de sa mémoire les dernières infos renseignées dans la BDD
    // Paramètres d'entrée : filename, context
    // Valeur de retour : null
    public static Object deSerialize(String filename, Context context){
        try {
            FileInputStream fileInputStream = context.openFileInput(filename);
            ObjectInputStream objectInputStream;
            try {
                objectInputStream = new ObjectInputStream(fileInputStream);
                try {
                    Object object = objectInputStream.readObject();
                    objectInputStream.close();
                    return object;
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            }catch (StreamCorruptedException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}

