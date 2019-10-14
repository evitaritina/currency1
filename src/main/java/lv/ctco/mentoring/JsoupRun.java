package lv.ctco.mentoring;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//Read Data from URL

public class JsoupRun {

    private static void saveToDB(Currency currency) {

    }

    public List<Currency> main() {
        Document doc = null;
        String date, currency_name, currency_rate, name = null;
        BigDecimal rate = null;
        try {
            doc = Jsoup.connect("https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html").get();

        } catch (IOException e) {

            e.printStackTrace();
        }


        Element element = doc.getElementsByTag("h2").first();
        System.out.println("Text with date: " + element);
        //       String title = doc.title();
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
                listStrings.add(new Currency(currency_name, rate));

                System.out.println(listStrings);
                System.out.println(listStrings.size());

            }


            System.out.println("listStrings1: " + listStrings);
            System.out.println(listStrings.size());

        }

        return listStrings;
    }

}