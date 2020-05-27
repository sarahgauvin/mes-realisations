package com.sarah.Scan;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class Scanner_QRcode {

    private String ResulatDuQRcode;
    private String[] parts ;


public Scanner_QRcode()
{

}
public String[] getParts() {
    return parts;
}

public void scanner( IntentIntegrator i)
{
    /// fonction de ihm
    i.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
    i.setPrompt("Scan");
    i.setCameraId(0);
    i.setBeepEnabled(false);
    i.setBarcodeImageEnabled(false);
    i.initiateScan();

}
public void traitementDuQRcode(IntentResult result)
{
    //on recupère la valeur du QRcode
    ResulatDuQRcode = result.getContents();
    // on coupe en plusieurs string à chaque ";"
     parts = ResulatDuQRcode.split(";");
}

}
