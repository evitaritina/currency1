package lv.ctco.currencyrates;

import lv.ctco.mentoring.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/*
public class CurrencyRates {
    public List<Currency> listStrings() {
    Document doc = null;
    String date, currency_name, currency_rate,  name = null;
    BigDecimal rate = null;
    try {
        doc = Jsoup.connect("https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html").get();

    } catch (IOException e) {

        e.printStackTrace();
    }


    List<Currency> listStrings = new ArrayList<Currency>();


    for (Element table : doc.select("tbody")) {
        for (Element row : table.select("tr")) {
            Elements tds = row.select("td");

            currency_name = tds.get(0).text();
            name = currency_name;
            System.out.println(" currency:" + currency_name);
            currency_rate = tds.get(2).text();
            rate = new BigDecimal(currency_rate);
            System.out.println(" rate:" + currency_rate);
            //    List<String> listStrings = new ArrayList<String>();
            listStrings.add(new Currency(currency_name, new BigDecimal(currency_rate)));

            System.out.println(listStrings);
            System.out.println(listStrings.size());

            //  System.out.println(" currency:"  + tds.get(0).text() + " rate:" + tds.get(2).text());
            //   System.out.println(" currency:"  + currency_name + " rate:" + currency_rate);

        }

        //    System.out.println(Arrays.deepToString(listStrings));

        System.out.println("listStrings1: " + listStrings);
        System.out.println(listStrings.size());

    }
    Currency c = new Currency(name, rate);
    for (Currency currency : listStrings) {
        System.out.println(c.getName());



        saveToDB(currency);
    }


    return listStrings;}

    private static void saveToDB(Currency currency) {

    }

}
*/

