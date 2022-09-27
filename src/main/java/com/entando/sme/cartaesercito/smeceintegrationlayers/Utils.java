package com.entando.sme.cartaesercito.smeceintegrationlayers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public static List<String[]> readCsv(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String fileLine = " ";
            List<String[]> csvLine = new ArrayList<>();
            boolean isHeader = true;
            while ((fileLine = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                csvLine.add(fileLine.split(","));

            }
            br.close();
            return csvLine;
        } catch (Exception ioe) {
            throw new RuntimeException(ioe);
        }
    }


}
