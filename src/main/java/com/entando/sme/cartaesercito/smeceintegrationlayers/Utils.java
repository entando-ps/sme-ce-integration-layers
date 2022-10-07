package com.entando.sme.cartaesercito.smeceintegrationlayers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static Date parseString(String dateInString){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
        try {
            return new Date(formatter.parse(dateInString).getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
