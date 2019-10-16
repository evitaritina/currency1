package lv.ctco.mentoring;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        CheckDate dat = new CheckDate();
        WriteDataToDB writeData = new WriteDataToDB();

        boolean curDat = dat.compareCurrencyRatesDates();

        if (curDat == true) {
            System.out.println("Data for this date exist ");
        } else {
          WriteDataToDB.main();
        }

    }
}
