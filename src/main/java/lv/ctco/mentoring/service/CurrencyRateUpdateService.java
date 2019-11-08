package lv.ctco.mentoring.service;

import lv.ctco.mentoring.service.database.CurrencyRateDBService;
import lv.ctco.mentoring.service.ecb.ECBService;
import lv.ctco.mentoring.entity.Currency;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class CurrencyRateUpdateService {
    private static ECBService ecbService = new ECBService();
    private static CurrencyRateDBService currencyRateDBService;

    {
        currencyRateDBService = new CurrencyRateDBService();
    }

    public CurrencyRateUpdateService() throws ClassNotFoundException {
    }

    public void updateRates() throws IOException, ParseException, SQLException {
        Date lastEcbUpdate = ecbService.getLastUpdateDate();
        Date lastDbUpdate = currencyRateDBService.getLastUpdateDate();

        if (!lastDbUpdate.equals(lastEcbUpdate)) {
            List<Currency> rates = ecbService.getRates();
            currencyRateDBService.saveNewRates(rates, lastEcbUpdate);
        }
    }

}
