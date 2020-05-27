package com.sarah.photographie;

import android.content.Context;
import android.widget.ImageView;


public class Photographie {
    Context context;
    public boolean compt1 = false;
    public  boolean compt2 = false;
    public  boolean compt3 = false;
    public  boolean compt4= false;
    public  boolean compt5= false ;
    public  boolean compt6 = false;
    public  boolean compt7 = false;
    public  boolean compt8 = false;
    public  boolean compt9= false;
    public  boolean compt10= false ;
    public  boolean compt11 = false;
    public  boolean compt22 = false;
    public  boolean compt33 = false;
    public  boolean compt44= false;
    public  boolean compt55= false ;
    public  boolean compt66 = false;
    public  boolean compt77 = false;
    public  boolean compt88 = false;
    public boolean compt99= false;
    public  boolean compt1010= false ;

    public int nbphotoprise =0;

    public Photographie(Context c) {
        this.context = c;
    }



    public void supprimerPhoto(ImageView image) {
        image.setImageBitmap(null);
        nbphotoprise = nbphotoprise - 1;
    }

    public void nePasSupprimerLaPhoto() {
        compt11 = false;
        compt22 = false;
        compt33 = false;
        compt44 = false;
        compt55 = false;
        compt66 = false;
        compt77 = false;
        compt88 = false;
        compt99 = false;
        compt1010 = false;
    }




}
