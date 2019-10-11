package lv.ctco.mentoring;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class CheckDateTestDriven {
    public static void main (String[] args) throws ParseException {
        CheckDate d = new CheckDate();
        witeDataToDB w = new witeDataToDB();
JsoupRun j = new JsoupRun();


        d.DateFromURL();
        d.DateFromDB();
//        Date dateFromURL = d.getDateFromURL();
 //       Date dateFromDB = d.getDateFromDB();
        d.compareCurrencyRatesDates();
        d.convertFromJAVADateToSQLDate();
        j.main();


        java.sql.Date sqlDate1 = d.convertFromJAVADateToSQLDate();
    System.out.println("sqlData1: " + sqlDate1);
        //       Calendar c1 = Calendar.getInstance();
//        Calendar c2 = Calendar.getInstance();

//        c1.setTime(dateFromURL);
//        c2.setTime(dateFromDB);
//        System.out.println("1. " + dateFromURL);
//        System.out.println("2. " + dateFromDB);

//        System.out.println(c1.compareTo(c2) == 0);
    }
}
