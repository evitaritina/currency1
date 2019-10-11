package connectiondb;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExampleJSoupProxy {

    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html").get();

        } catch (IOException e) {

            e.printStackTrace();
        }

        Element element = doc.getElementsByTag("h2").first();
        System.out.println("date: " + element);
        //  String title = doc.title();
        for (Element table : doc.select("tbody")) {
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td");

                System.out.println("Date: "+ element + " currency:"  + tds.get(0).text() + " rate:" + tds.get(2).text());

            }
        }
    }
}