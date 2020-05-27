package com.sarah.Scan;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.sarah.mobilepyro.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Creer_QRcode {

    public final static int QRcodeWidth = 350 ;


    public Creer_QRcode()
    {

    }

    public Bitmap TextToImageEncode(String Value, Context c) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(Value, BarcodeFormat.DATA_MATRIX.QR_CODE, QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        c.getResources().getColor(R.color.QRCodeBlackColor):c.getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 350, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }



    public void writeToFile(String data, Context c) {

        try {
            // Instantiate a Date object
            Date dte = new Date();
            String lignes = "";
            File chemin = c.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            File fichier = new File(chemin, "sondage.txt");
            if (!fichier.exists())
            {
                FileWriter filewriter = new FileWriter(fichier, true);
                lignes = "Date" + ";" + "Autre";
                filewriter.write(lignes);
                lignes = dte.toString() + ";" + "test";
                filewriter.write(lignes);
                filewriter.close();
            }
            else
            {
                FileWriter filewriter = new FileWriter(fichier, true);
                lignes = dte.toString() + ";"  ;
                filewriter.write(lignes);
                filewriter.close();
            }

            Toast msg = Toast.makeText(c, chemin.toString(), Toast.LENGTH_LONG);

            msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);

            msg.show();

        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }
}
