package lv.ctco.mentoring;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Calendar;
import java.util.List;


public class witeDataToDB {

    public static void main(String[] args) {
        //   public void writeTo(){
        {
            try {
                // create a mysql database connection
                String myDriver = "org.gjt.mm.mysql.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/javastudy?autoReconnect=true&useSSL=false";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "admin");

                // create a sql date object so we can use it in our INSERT statement
                Calendar calendar = Calendar.getInstance();
                java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

                // the mysql insert statement
                String query = " insert into rates (rate_date, currency, rate, upload_date)"
                        + " values (?, ?, ?, ?)";


                CheckDate sqlDate = new CheckDate();
                java.sql.Date sqlData1;
                sqlDate.DateFromURL();
                Date sqlDate1 = sqlDate.convertFromJAVADateToSQLDate();
                System.out.println("sqlData1: " + sqlDate1);


                JsoupRun jsoupRun = new JsoupRun();
                List<Currency> currencyList = jsoupRun.main();


                // create the mysql insert preparedstatement
                for (Currency currency : currencyList) {
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setDate(1, sqlDate1);
                    preparedStmt.setString(2, currency.getName());
                    preparedStmt.setBigDecimal(3, currency.getRate());
                    preparedStmt.setDate(4, startDate);


                    // execute the preparedstatement
                    preparedStmt.execute();

                }
                conn.close();
            } catch (Exception e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

        }
    }


}

