package lv.ctco.mentoring.service.ecb;

import lv.ctco.mentoring.entity.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class ECBService {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
    private static final String ECB_URL = "https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html";

    public static Date getLastUpdateDate() throws ParseException, IOException {
        Document doc = Jsoup.connect(ECB_URL).get();
        return extractDate(doc);
    }

    private static Date extractDate(Document doc) throws ParseException {
        Element h2WithDateElement = doc.getElementsByTag("h2").first();

        String h2WithDate = h2WithDateElement.toString();
        h2WithDate = h2WithDate.substring(43, h2WithDate.length() - 5);

        return DATE_FORMAT.parse(h2WithDate);
    }


    public static List<Currency> getRates() throws IOException {
        List<Currency> rates = new ArrayList<>();

        Document doc = Jsoup.connect(ECB_URL).get();

        Element tbody = doc.select("tbody").first();

        for (Element row : tbody.select("tr")) {
            Currency rate = extractRate(row);
            rates.add(rate);
        }

        return rates;
    }

    private static Currency extractRate(Element row) {
        Elements tds = row.select("td");

        String currency_name = tds.get(0).text();
        BigDecimal rate = new BigDecimal(tds.get(2).text());

        return new Currency(currency_name, rate);
    }
}
