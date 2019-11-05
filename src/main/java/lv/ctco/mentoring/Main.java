package lv.ctco.mentoring;

import lv.ctco.mentoring.service.CurrencyRateUpdateService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

//  TODO: log4j
public class Main {
    public static void main(String[] args) throws ParseException, IOException, SQLException, ClassNotFoundException {
        CurrencyRateUpdateService currencyRateUpdateService = new CurrencyRateUpdateService();
        currencyRateUpdateService.updateRates();
    }
}
