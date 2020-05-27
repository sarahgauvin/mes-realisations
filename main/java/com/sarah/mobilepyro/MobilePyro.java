package com.sarah.mobilepyro;

import android.content.Context;
import android.content.SharedPreferences;

import com.sarah.Scan.Creer_QRcode;
import com.sarah.Scan.Scanner_QRcode;

import com.sarah.photographie.Photographie;
import com.sarah.synchronisation.Api;

import com.sarah.synchronisation.Synchronisation_SCAE;

public class MobilePyro {

    public  Scanner_QRcode monobjQRcode = new Scanner_QRcode();
    public Creer_QRcode monobjCreerQRcode = new Creer_QRcode() ;
    public Synchronisation_SCAE monobjecSynchro;
    public Api monobjApi ;
    public Photographie monobjectPhoto ;

    private SharedPreferences s  ;

    public MobilePyro()
    {


    }
    public MobilePyro (Context c)
    {
        monobjectPhoto = new Photographie(c) ;
    }

    public MobilePyro (Context c , SharedPreferences s )
    {
        monobjecSynchro= new Synchronisation_SCAE(c, s) ;
    }
    public MobilePyro( SharedPreferences s)
    {

         monobjApi =  new Api(s);
    }






}
