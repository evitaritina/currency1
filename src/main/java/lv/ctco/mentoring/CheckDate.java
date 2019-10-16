package lv.ctco.mentoring;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CheckDate{
    Date dateFromURL, dateFromDB;
    String element;
    String title;
    String date;
    Date urlDate, strdate;
    java.sql.Date sqlDate;


    public Date dateFromURL() {
        Document doc = null;

        try {
            doc = Jsoup.connect("https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element element = doc.getElementsByTag("h2").first();
        System.out.println("Text with date: " + element);
        title = element.toString();

        System.out.println("title:" + title);
        title = title.substring(43, title.length() - 5);
        System.out.println(title);
        String string = title;
        DateFormat format = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);

       try {
           dateFromURL = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("dateFromURL: " + dateFromURL);

        return dateFromURL;
    }

    public Date dateFromDB(){
    {
        try
        {
            // create our mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/javastudy?autoReconnect=true&useSSL=false";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "admin");

            // SQL SELECT query.
            String query = "SELECT MAX(cast(rate_date as Date)) AS rate_date FROM rates";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next())
            {
                dateFromDB = rs.getDate("rate_date");

                // print the results
                System.out.format("dateFromDB %s\n", dateFromDB);
            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
       return dateFromDB;

}
public boolean  compareCurrencyRatesDates() throws ParseException {
Date dateFromURL= dateFromURL();
Date dateFromDB = dateFromDB();

    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();

    c1.setTime(dateFromURL);
    c2.setTime(dateFromDB);
    System.out.println("1. " + dateFromURL);
    System.out.println("2. " + dateFromDB);

    boolean c3;
    if ((c1.compareTo(c2) == 0)) c3 = true;
    else c3 = false;
    System.out.println("c3 is true or false: "  +c3);
    System.out.println(c1.compareTo(c2) == 0);
    return c3;
}

    public java.sql.Date convertFromJAVADateToSQLDate(){
            java.util.Date utilDate = dateFromURL;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    final String stringDate= dateFormat.format(utilDate);
    final java.sql.Date sqlDate=  java.sql.Date.valueOf(stringDate);
        System.out.println("sqlDate" + sqlDate);
             return sqlDate;
    }

    public java.sql.Date get_convertFromJAVADateToSQLDate(){
        return this.sqlDate;
    }

    public Date get_dateFromURL(){
        return this.dateFromURL;
    }

    public Date get_DateFromDB(){
        return this.dateFromDB;
    }
}
