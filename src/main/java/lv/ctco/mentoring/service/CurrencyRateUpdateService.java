package lv.ctco.mentoring.service;

import lv.ctco.mentoring.entity.Currency;
import lv.ctco.mentoring.service.database.CurrencyRateDBService;
import lv.ctco.mentoring.service.ecb.ECBService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class CurrencyRateUpdateService {
    public static void updateRates() throws ClassNotFoundException, IOException, ParseException, SQLException {
        Date lastEcbUpdate = ECBService.getLastUpdateDate();
        Date lastDbUpdate = CurrencyRateDBService.getLastUpdateDate();

        if (!lastDbUpdate.equals(lastEcbUpdate)) {
            List<Currency> rates = ECBService.getRates();
            CurrencyRateDBService.saveNewRates(rates, lastEcbUpdate);
        }
    }
}
