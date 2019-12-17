package lv.ctco.mentoring.service;

import lv.ctco.mentoring.entity.Currency;
import lv.ctco.mentoring.service.database.CurrencyRateDBService;
import lv.ctco.mentoring.service.ecb.ECBService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class CurrencyRateUpdateService {
    private static final Logger log = Logger.getLogger(CurrencyRateUpdateService.class);
    public static void updateRates() throws ClassNotFoundException, IOException, ParseException, SQLException {
        Date lastEcbUpdate = ECBService.getLastUpdateDate();
        Date lastDbUpdate = CurrencyRateDBService.getLastUpdateDate();


        if (!lastDbUpdate.equals(lastEcbUpdate)) {
            // логируем инфо
            log.info("Info: !lastDbUpdate.equals(lastEcbUpdate)");
            List<Currency> rates = ECBService.getRates();
            // логируем инфо
            log.info("Info: ECBService.getRates()");
            CurrencyRateDBService.saveNewRates(rates, lastEcbUpdate);
            // логируем инфо
            log.info("Info:  CurrencyRateDBService.saveNewRates");
        } else {log.error("lastDbUpdate == lastEcbUpdate)"); }
    }
}
