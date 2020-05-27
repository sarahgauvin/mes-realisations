package com.sarah.ihm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.sarah.SQLite.CSVFile;
import com.sarah.SQLite.Controle;
import com.sarah.SQLite.ControleImages;
import com.sarah.SQLite.MySQLiteOpenHelper;
import com.sarah.mobilepyro.MobilePyro;
import com.sarah.mobilepyro.R;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Formulaire extends AppCompatActivity {
    FloatingActionButton  btnScanQRcode, btnEnregistrerbdd, btnPrendreUnePhoto ;
    SharedPreferences sharedPreferences;
    MobilePyro monObjMobilePyro,monObjMobilePyro2 ;
    Spinner qualiteDuVol, Incident;
    Controle controle;
    ControleImages controleImages;
    Context context;
    String[] tabForm = new String[30];

    EditText nomDuProjet, nomDuPyrotechnicien, nomDuMaitreDoeuvre, ReferencePropulseur, ReferencePropulseur2, ReferencePropulseur3,
            typeDeCasing, typeDeCasing2, typeDeCasing3, numeroId, numeroId2, numeroId3, ReferenceProgergol, ReferenceProgergol2, ReferenceProgergol3,
            dateFabrication, dateFabrication2, dateFabrication3, nimeroIdCp, nimeroIdCp2, nimeroIdCp3, masseMatiereActive, masseMatiereActive2,
            masseMatiereActive3, impedanceInflammateur, impedanceLigne, Commentaire, editTextQR;
    ImageView imageView;
    Button btnCreerQR,btnSynchronisation, btnProjet,  btnDateEtHeure= null;
    String ChaineDeString="proj";
    Bitmap bitmap;
    private  ImageView img, img2, img3 ,img4, img5, img6, img7, img8, img9, img10;
    private  final int DIALOG_ALERT = 10;


    //partie de thomas
    MySQLiteOpenHelper mySQLiteOpenHelper;
    String nomBase = "BaseDeDonnees.db";
    Integer version = 9;
    ImageView[] tableauDesImages = new ImageView[10];


    public Formulaire() {

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulaire);
        // forcer l'application à être en mode portrait (souhait client)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        context = this;

        monObjMobilePyro =new MobilePyro(context);
        monObjMobilePyro2 =new MobilePyro(sharedPreferences);

        img = findViewById(R.id.imgAfficherPhoto1);
        img2 = findViewById(R.id.imgAfficherPhoto2);
        img3 = findViewById(R.id.imgAfficherPhoto3);
        img4 = findViewById(R.id.imgAfficherPhoto4);
        img5 = findViewById(R.id.imgAfficherPhoto5);
        img6 = findViewById(R.id.imgAfficherPhoto6);
        img7 = findViewById(R.id.imgAfficherPhoto7);
        img8 = findViewById(R.id.imgAfficherPhoto8);
        img9 = findViewById(R.id.imgAfficherPhoto9);
        btnPrendreUnePhoto = findViewById(R.id.btnPhoto);
        img10 = findViewById(R.id.imgAfficherPhoto10);
        btnProjet = findViewById(R.id.changerProjet);
        btnSynchronisation = findViewById(R.id.btnSyncro);
        btnScanQRcode = findViewById(R.id.btnQrCodee);
        btnCreerQR = findViewById(R.id.btnCreerQRcode);
        btnDateEtHeure = findViewById(R.id.btnLancement);
        btnEnregistrerbdd = findViewById(R.id.btnEnregistrer);
        imageView = findViewById(R.id.imageView);
        init();

        Intent intent = getIntent();
        if (intent != null) {
            String un = "";
            String deux = "";
            String trois = "";
            String api ;
            if (intent.hasExtra("un")) {
                un = intent.getStringExtra("un");
            }
            if (intent.hasExtra("deux")) {
                deux = intent.getStringExtra("deux");
            }
            if (intent.hasExtra("trois")) {
                trois = intent.getStringExtra("trois");
                nomDuProjet.setText(trois);
            }
            if (intent.hasExtra("api")) {
                api = intent.getStringExtra("api");
                Log.e("3", api);
            }

        }

        appuieLong() ;


        btnScanQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator integrator = new IntentIntegrator(Formulaire.this);
                monObjMobilePyro2.monobjQRcode.scanner(integrator);
            }
        });

        btnProjet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choixDuProjet = new Intent(Formulaire.this, Choix_du_projet.class);
                startActivity(choixDuProjet);
            }
        });


        btnSynchronisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Context c = getApplicationContext();
                if (!monObjMobilePyro2.monobjApi.ChercherLaClee(context)) {
                    Intent app = new Intent(Formulaire.this, Donner_api.class);
                    startActivity(app);

                }
            }
        });

        btnPrendreUnePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

            }
        });

        btnDateEtHeure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabForm[26] = donneLaDateEtLheure();

            }
        });

        btnCreerQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //concaténation de string
                init();
                for (int i =0 ; i<30 ; i++) {
                    ChaineDeString = ChaineDeString + ";" +tabForm[i];
                }

                monObjMobilePyro.monobjCreerQRcode.writeToFile(ChaineDeString, context);

                try {
                    bitmap = monObjMobilePyro.monobjCreerQRcode.TextToImageEncode(ChaineDeString, context);

                    imageView.setImageBitmap(bitmap);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        btnEnregistrerbdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableauDesImages[0] = img;
                tableauDesImages[1] = img2;
                tableauDesImages[2] = img3;
                tableauDesImages[3] = img4;
                tableauDesImages[4] = img5;
                tableauDesImages[5] = img6;
                tableauDesImages[6] = img7;
                tableauDesImages[7] = img8;
                tableauDesImages[8] = img9;
                tableauDesImages[9] = img10;
                mySQLiteOpenHelper = new MySQLiteOpenHelper(context, nomBase, null, version);

                Toast t = Toast.makeText(getApplicationContext(), "Données sauvegardées", Toast.LENGTH_LONG);
                t.show();
                init();
                enleverLeNull();
                mySQLiteOpenHelper.addInfos(tabForm[0], tabForm[1], tabForm[2], tabForm[3], tabForm[4], tabForm[5], tabForm[6], tabForm[7], tabForm[8], tabForm[9], tabForm[10], tabForm[11], tabForm[12], tabForm[13], tabForm[14], tabForm[15], tabForm[16], tabForm[17], tabForm[18],
                        tabForm[19], tabForm[20], tabForm[21], tabForm[22], tabForm[23], tabForm[24], tabForm[25], tabForm[26], tabForm[27], tabForm[28], tabForm[29]);
                //CSVFile.remplirCSV(e1, e2, e3, e41, e42, e43, e51, e52, e53, e61, e62, e63, e71, e72, e73, e81, e82, e83, e91, e92, e93, e101, e102, e103, e11, e12, e13, e14, e15, e16);
                CSVFile.remplirCSV(tabForm[0], tabForm[1], tabForm[2], tabForm[3], tabForm[4], tabForm[5], tabForm[6], tabForm[7], tabForm[8], tabForm[9], tabForm[24], tabForm[25], tabForm[26], tabForm[27], tabForm[28], tabForm[29]);

                String[] tabImages = new String[10];
                for(int i=0; i<monObjMobilePyro.monobjectPhoto.nbphotoprise; i++){
                    tabImages[i] = recupImages(tableauDesImages[i]);
                    Log.i("2", "->"+ tabImages[i]);

                }
                for (int i =0 ; i<10 ; i++) {
                    if (tabImages[i] == null)
                    {
                        tabImages[i] = "";
                    }

                }


                String horodatage = donneLaDateEtLheure();
                mySQLiteOpenHelper.addInfosImages(tabForm[0], horodatage, tabImages);
                mySQLiteOpenHelper.close();

            }
        });
    }


private void enleverLeNull( )
{
    for (int i =0 ; i<30 ; i++) {
        if (tabForm[i] == null)
        {
            tabForm[i] = "";
        }

    }
}


    private void majFormulaire(String[] parts) {
        init();
        //on stipule à l'utilisateur que le flash du QRcode est ok
        Toast.makeText(this, " QRcode ok ", Toast.LENGTH_LONG).show();
        // si le QR code est un QRcode contenant plus de 4 ";"
        // c'est donc un QRcode de tous les champs du formulaire
        if (parts[0].equals("proj")) {
            nomDuProjet.setText(parts[1]);
            nomDuPyrotechnicien.setText(parts[2]);
            nomDuMaitreDoeuvre.setText(parts[3]);
            ReferencePropulseur.setText(parts[4]);
            typeDeCasing.setText(parts[5]);
            numeroId.setText(parts[6]);
            ReferenceProgergol.setText(parts[7]);
            dateFabrication.setText(parts[8]);
            nimeroIdCp.setText(parts[9]);
            masseMatiereActive.setText(parts[10]);
            ReferencePropulseur2.setText(parts[11]);
            typeDeCasing2.setText(parts[12]);
            numeroId2.setText(parts[13]);
            ReferenceProgergol2.setText(parts[14]);
            dateFabrication2.setText(parts[15]);
            nimeroIdCp2.setText(parts[16]);
            masseMatiereActive2.setText(parts[17]);
            ReferencePropulseur3.setText(parts[18]);
            typeDeCasing3.setText(parts[19]);
            numeroId3.setText(parts[20]);
            ReferenceProgergol3.setText(parts[21]);
            dateFabrication3.setText(parts[22]);
            nimeroIdCp3.setText(parts[23]);
            masseMatiereActive3.setText(parts[24]);
            impedanceInflammateur.setText(parts[25]);
            impedanceLigne.setText(parts[26]);
            tabForm[26] = parts[27];
            Commentaire.setText(parts[30]);

            switch (parts[28]) {
                case "Nominale":
                    qualiteDuVol.setSelection(0);
                    break;
                case "Balistique":
                    qualiteDuVol.setSelection(1);
                    break;
                case "Torche":
                    qualiteDuVol.setSelection(2);
                    break;

            }
            switch (parts[2]) {
                case "sans incident":
                    Incident.setSelection(0);
                    break;
                case "long feux":
                    Incident.setSelection(1);
                    break;
                case "non feux":
                    Incident.setSelection(2);
                    break;

            }

        } else {
            if (parts[0].equals("prop")) {
                if (tabForm[10].equals("") || tabForm[10].equals(parts[0])) {
                    numeroId.setText(parts[1]);
                    dateFabrication.setText(parts[2]);
                    ReferencePropulseur.setText(parts[3]);
                    masseMatiereActive.setText(parts[4]);
                } else {
                    if (tabForm[11].equals("") || tabForm[11].equals(parts[0])) {
                        numeroId2.setText(parts[1]);
                        dateFabrication2.setText(parts[2]);
                        ReferencePropulseur2.setText(parts[3]);
                        masseMatiereActive2.setText(parts[4]);
                    } else {
                        if (tabForm[12].equals("") || tabForm[12].equals(parts[0])) {
                            numeroId3.setText(parts[1]);
                            dateFabrication3.setText(parts[2]);
                            ReferencePropulseur3.setText(parts[3]);
                            masseMatiereActive3.setText(parts[4]);
                        } else {
                            Toast.makeText(this, "Vous avez déja enregistré 3 QRcode différents", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            } else {
                if (parts[0].equals("cas")) {
                    if (tabForm[10].equals("") ) {
                        typeDeCasing.setText(parts[1]);
                        nimeroIdCp.setText(parts[2]);
                    } else {
                        if (tabForm[11].equals("") || tabForm[11].equals(parts[0])) {
                            typeDeCasing2.setText(parts[1]);
                            nimeroIdCp2.setText(parts[2]);
                        } else {
                            if (tabForm[12].equals("") || tabForm[12].equals(parts[0])) {
                                typeDeCasing3.setText(parts[1]);
                                nimeroIdCp3.setText(parts[2]);
                            } else {
                                Toast.makeText(this, "Vous avez déja enregistré 3 QRcode Casing différents", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        }

    }

    protected void init() {
        nomDuProjet = findViewById(R.id.ETnomDuProjet);
        nomDuPyrotechnicien = findViewById(R.id.ETnomDuPyrotechnicien);
        nomDuMaitreDoeuvre = findViewById(R.id.ETnomDuMaitreDoeuvre);
        ReferencePropulseur = findViewById(R.id.ETReferencePropulseur);
        ReferencePropulseur2 = findViewById(R.id.ETReferencePropulseur2);
        ReferencePropulseur3 = findViewById(R.id.ETReferencePropulseur3);
        typeDeCasing = findViewById(R.id.ETtypeDeCasing);
        typeDeCasing2 = findViewById(R.id.ETtypeDeCasing2);
        typeDeCasing3 = findViewById(R.id.ETtypeDeCasing3);
        numeroId = findViewById(R.id.ETnumeroId);
        numeroId2 = findViewById(R.id.ETnumeroId2);
        numeroId3 = findViewById(R.id.ETnumeroId3);
        ReferenceProgergol = findViewById(R.id.ETReferenceProgergol);
        ReferenceProgergol2 = findViewById(R.id.ETReferenceProgergol2);
        ReferenceProgergol3 = findViewById(R.id.ETReferenceProgergol3);
        dateFabrication = findViewById(R.id.ETdateFabrication);
        dateFabrication2 = findViewById(R.id.ETdateFabrication2);
        dateFabrication3 = findViewById(R.id.ETdateFabrication3);
        nimeroIdCp = findViewById(R.id.ETnimeroIdCp);
        nimeroIdCp2 = findViewById(R.id.ETnimeroIdCp2);
        nimeroIdCp3 = findViewById(R.id.ETnimeroIdCp3);
        masseMatiereActive = findViewById(R.id.ETmasseMatiereActive);
        masseMatiereActive2 = findViewById(R.id.ETmasseMatiereActive2);
        masseMatiereActive3 = findViewById(R.id.ETmasseMatiereActive3);
        impedanceInflammateur = findViewById(R.id.ETimpedanceInflammateur);
        impedanceLigne = findViewById(R.id.ETimpédanceLigne);
        qualiteDuVol = findViewById(R.id.deroulanQualité);
        Incident = findViewById(R.id.derouleIncident);
        Commentaire = findViewById(R.id.ETCommentaire);
        controle = Controle.getInstance(this);
        controleImages = ControleImages.getInstance(this);
        recupProfil();
    }

    protected void recupProfil() {
        tabForm[0] = nomDuProjet.getText().toString();
        tabForm[1] = nomDuPyrotechnicien.getText().toString();
        tabForm[2] = nomDuMaitreDoeuvre.getText().toString();
        tabForm[3] = ReferencePropulseur.getText().toString();
        tabForm[4] = typeDeCasing.getText().toString();
        tabForm[5]= numeroId.getText().toString();
        tabForm[6] = ReferenceProgergol.getText().toString();
        tabForm[7] = dateFabrication.getText().toString();
        tabForm[8] = nimeroIdCp.getText().toString();
        tabForm[9]= masseMatiereActive.getText().toString();
        tabForm[10] = ReferencePropulseur2.getText().toString();
        tabForm[11]= typeDeCasing2.getText().toString();
        tabForm[12]= numeroId2.getText().toString();
        tabForm[13] = ReferenceProgergol2.getText().toString();
        tabForm[14] = dateFabrication2.getText().toString();
        tabForm[15] = nimeroIdCp2.getText().toString();
        tabForm[16] = masseMatiereActive2.getText().toString();
        tabForm[17] = ReferencePropulseur3.getText().toString();
        tabForm[18] = typeDeCasing3.getText().toString();
        tabForm[19] = numeroId3.getText().toString();
        tabForm[20] = ReferenceProgergol3.getText().toString();
        tabForm[21] = dateFabrication3.getText().toString();
        tabForm[22] = nimeroIdCp3.getText().toString();
        tabForm[23] = masseMatiereActive3.getText().toString();
        tabForm[24] = impedanceInflammateur.getText().toString();
        tabForm[25]= impedanceLigne.getText().toString();

        tabForm[27] = qualiteDuVol.getSelectedItem().toString();
        tabForm[28] = Incident.getSelectedItem().toString();
        tabForm[29] = Commentaire.getText().toString();
    }

    @Override
    public Dialog onCreateDialog(int id) {
        // Crée un AlertDialog - le pop up
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Voulez-vous supprimer cette photo ? ");
        builder.setCancelable(true);
        builder.setPositiveButton("Oui", new CliqueSurOui());
        builder.setNegativeButton("non", new CliqueSurNon());
        AlertDialog dialog = builder.create();
        dialog.show();
        return super.onCreateDialog(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (monObjMobilePyro.monobjectPhoto.nbphotoprise < 10) {
            if (!monObjMobilePyro.monobjectPhoto.compt1) {
                Bitmap bit = (Bitmap) data.getExtras().get("data");
                img.setImageBitmap(bit);
                MediaStore.Images.Media.insertImage(getContentResolver(), bit, "monImage", "imagePyro");
                monObjMobilePyro.monobjectPhoto.compt1 = true;
                monObjMobilePyro.monobjectPhoto.nbphotoprise++;
            } else {
                if (!monObjMobilePyro.monobjectPhoto.compt2) {
                    Bitmap bit2 = (Bitmap) data.getExtras().get("data");
                    img2.setImageBitmap(bit2);
                    MediaStore.Images.Media.insertImage(getContentResolver(), bit2, "monImage", "imagePyro");
                    monObjMobilePyro.monobjectPhoto.compt2 = true;
                    monObjMobilePyro.monobjectPhoto.nbphotoprise++;
                } else {
                    if (!monObjMobilePyro.monobjectPhoto.compt3) {
                        Bitmap bit3 = (Bitmap) data.getExtras().get("data");
                        img3.setImageBitmap(bit3);
                        MediaStore.Images.Media.insertImage(getContentResolver(), bit3, "monImage", "imagePyro");
                        monObjMobilePyro.monobjectPhoto.compt3 = true;
                        monObjMobilePyro.monobjectPhoto.nbphotoprise++;
                    } else {
                        if (!monObjMobilePyro.monobjectPhoto.compt4) {
                            Bitmap bit4 = (Bitmap) data.getExtras().get("data");
                            img4.setImageBitmap(bit4);
                            MediaStore.Images.Media.insertImage(getContentResolver(), bit4, "monImage", "imagePyro");
                            monObjMobilePyro.monobjectPhoto.compt4 = true;
                            monObjMobilePyro.monobjectPhoto.nbphotoprise++;
                        } else {
                            if (!monObjMobilePyro.monobjectPhoto.compt5) {
                                Bitmap bit5 = (Bitmap) data.getExtras().get("data");
                                img5.setImageBitmap(bit5);
                                MediaStore.Images.Media.insertImage(getContentResolver(), bit5, "monImage", "imagePyro");
                                monObjMobilePyro.monobjectPhoto.compt5 = true;
                                monObjMobilePyro.monobjectPhoto.nbphotoprise++;
                            } else {
                                if (!monObjMobilePyro.monobjectPhoto.compt6) {
                                    Bitmap bit6 = (Bitmap) data.getExtras().get("data");
                                    img6.setImageBitmap(bit6);
                                    MediaStore.Images.Media.insertImage(getContentResolver(), bit6, "monImage", "imagePyro");
                                    monObjMobilePyro.monobjectPhoto.compt6 = true;
                                    monObjMobilePyro.monobjectPhoto.nbphotoprise++;
                                } else {
                                    if (!monObjMobilePyro.monobjectPhoto.compt7) {
                                        Bitmap bit7 = (Bitmap) data.getExtras().get("data");
                                        img7.setImageBitmap(bit7);
                                        MediaStore.Images.Media.insertImage(getContentResolver(), bit7, "monImage", "imagePyro");
                                        monObjMobilePyro.monobjectPhoto.compt7 = true;
                                        monObjMobilePyro.monobjectPhoto.nbphotoprise++;
                                    } else {
                                        if (!monObjMobilePyro.monobjectPhoto.compt8) {
                                            Bitmap bit8 = (Bitmap) data.getExtras().get("data");
                                            img8.setImageBitmap(bit8);
                                            MediaStore.Images.Media.insertImage(getContentResolver(), bit8, "monImage", "imagePyro");
                                            monObjMobilePyro.monobjectPhoto.compt8 = true;
                                            monObjMobilePyro.monobjectPhoto.nbphotoprise++;
                                        } else {
                                            if (!monObjMobilePyro.monobjectPhoto.compt9) {
                                                Bitmap bit9 = (Bitmap) data.getExtras().get("data");
                                                img9.setImageBitmap(bit9);
                                                MediaStore.Images.Media.insertImage(getContentResolver(), bit9, "monImage", "imagePyro");
                                                monObjMobilePyro.monobjectPhoto.compt9 = true;
                                                monObjMobilePyro.monobjectPhoto.nbphotoprise++;
                                            } else {
                                                if (!monObjMobilePyro.monobjectPhoto.compt10) {
                                                    Bitmap bit10 = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                                                    img10.setImageBitmap(bit10);
                                                    MediaStore.Images.Media.insertImage(getContentResolver(), bit10, "monImage", "imagePyro");
                                                    monObjMobilePyro.monobjectPhoto.compt10 = true;
                                                    monObjMobilePyro.monobjectPhoto.nbphotoprise++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            // Si il y a deja 10 photo prises :
            Toast toast = Toast.makeText(getApplicationContext(), "Maximum de 10 photos", Toast.LENGTH_SHORT);
            toast.show();
        }

        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Log.e("Scan*******", "Cancelled scan");

            } else {
                monObjMobilePyro.monobjQRcode.traitementDuQRcode(result); // resuly.getconetent
                String[] parts = monObjMobilePyro.monobjQRcode.getParts();
                majFormulaire(parts);

            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public final class CliqueSurNon implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {

            monObjMobilePyro.monobjectPhoto.nePasSupprimerLaPhoto();

        }
    }


    public final class CliqueSurOui implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(context, "photo supprimée", Toast.LENGTH_LONG).show();

            if (monObjMobilePyro.monobjectPhoto.compt11) {
                monObjMobilePyro.monobjectPhoto.supprimerPhoto(img);
                monObjMobilePyro.monobjectPhoto.compt11 = false;
                monObjMobilePyro.monobjectPhoto.compt1 = false;
            } else {
                if (monObjMobilePyro.monobjectPhoto.compt22) {
                    monObjMobilePyro.monobjectPhoto.supprimerPhoto(img2);
                    monObjMobilePyro.monobjectPhoto.compt22 = false;
                    monObjMobilePyro.monobjectPhoto.compt2 = false;
                } else {
                    if (monObjMobilePyro.monobjectPhoto.compt33) {
                        monObjMobilePyro.monobjectPhoto.supprimerPhoto(img3);
                        monObjMobilePyro.monobjectPhoto.compt33 = false;
                        monObjMobilePyro.monobjectPhoto.compt3 = false;
                    } else {
                        if (monObjMobilePyro.monobjectPhoto.compt44) {
                            monObjMobilePyro.monobjectPhoto.supprimerPhoto(img4);
                            monObjMobilePyro.monobjectPhoto.compt44 = false;
                            monObjMobilePyro.monobjectPhoto.compt4 = false;
                        } else {
                            if (monObjMobilePyro.monobjectPhoto.compt55) {
                                monObjMobilePyro.monobjectPhoto.supprimerPhoto(img5);
                                monObjMobilePyro.monobjectPhoto.compt55 = false;
                                monObjMobilePyro.monobjectPhoto.compt5 = false;
                            } else {
                                if (monObjMobilePyro.monobjectPhoto.compt66) {
                                    monObjMobilePyro.monobjectPhoto.supprimerPhoto(img6);
                                    monObjMobilePyro.monobjectPhoto.compt66 = false;
                                    monObjMobilePyro.monobjectPhoto.compt6 = false;
                                } else {
                                    if (monObjMobilePyro.monobjectPhoto.compt77) {
                                        monObjMobilePyro.monobjectPhoto.supprimerPhoto(img7);
                                        monObjMobilePyro.monobjectPhoto.compt77 = false;
                                        monObjMobilePyro.monobjectPhoto.compt7 = false;
                                    } else if (monObjMobilePyro.monobjectPhoto.compt88) {
                                        monObjMobilePyro.monobjectPhoto.supprimerPhoto(img8);
                                        monObjMobilePyro.monobjectPhoto.compt88 = false;
                                        monObjMobilePyro.monobjectPhoto.compt8 = false;
                                    } else {
                                        if (monObjMobilePyro.monobjectPhoto.compt99) {
                                            monObjMobilePyro.monobjectPhoto.supprimerPhoto(img9);
                                            monObjMobilePyro.monobjectPhoto.compt99 = false;
                                            monObjMobilePyro.monobjectPhoto.compt9 = false;
                                        } else {
                                            if (monObjMobilePyro.monobjectPhoto.compt1010) {
                                                monObjMobilePyro.monobjectPhoto.supprimerPhoto(img10);
                                                monObjMobilePyro.monobjectPhoto.compt1010 = false;
                                                monObjMobilePyro.monobjectPhoto.compt10 = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void appuieLong() {
            img.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    monObjMobilePyro.monobjectPhoto.compt11 = true;

                    onCreateDialog(10);
                    return false;
                }
            });
            img2.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    monObjMobilePyro.monobjectPhoto.compt22 = true;
                    onCreateDialog(10);
                    return false;
                }
            });

            img3.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    monObjMobilePyro.monobjectPhoto.compt33 = true;
                    onCreateDialog(10);

                    return false;
                }
            });
            img4.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    monObjMobilePyro. monobjectPhoto.compt44 = true;
                    onCreateDialog(10);
                    return false;
                }
            });
            img5.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    monObjMobilePyro.monobjectPhoto.compt55 = true;
                    onCreateDialog(10);
                    return false;
                }
            });

            img6.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    monObjMobilePyro.monobjectPhoto.compt66 = true;
                    onCreateDialog(10);
                    return false;
                }
            });
            img7.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    monObjMobilePyro.monobjectPhoto.compt77 = true;
                    onCreateDialog(10);
                    return false;
                }
            });

            img8.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    monObjMobilePyro.monobjectPhoto.compt88 = true;
                    onCreateDialog(10);
                    return false;
                }
            });

            img9.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    monObjMobilePyro.monobjectPhoto.compt99 = true;
                    onCreateDialog(10);
                    return false;
                }
            });

            img10.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    monObjMobilePyro.monobjectPhoto.compt1010 = true;
                    onCreateDialog(10);
                    return false;
                }
            });


        }
public String donneLaDateEtLheure()
{
    String deh ;
    Date now = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY HH:mm");
    deh = formatter.format(now);
    return  deh ;
}

    protected String recupImages(ImageView i){

        return String.valueOf(i);
    }





}

