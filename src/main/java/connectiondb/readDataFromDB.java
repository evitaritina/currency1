package connectiondb;
import javax.print.attribute.standard.DateTimeAtCreation;
import java.math.BigDecimal;
import java.sql.*;

/**
 * A Java MySQL SELECT statement example.
 * Demonstrates the use of a SQL SELECT statement against a
 * MySQL database, called from a Java program.
 *
 * Created by Alvin Alexander, http://alvinalexander.com
 */
public class readDataFromDB
{

    public static void main(String[] args)
    {
        try
        {
            // create our mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/javastudy?autoReconnect=true&useSSL=false";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "admin");

            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM rates";
         //   String query = "SELECT MAX(rate_date) FROM rates; ";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next())
            {
                int id = rs.getInt("id");
                Date rate_date = rs.getDate("rate_date");
                String currency = rs.getString("currency");
                BigDecimal rate = rs.getBigDecimal("rate");
                Date upload_date = rs.getDate("upload_date");


                // print the results
                System.out.format("%s, %s, %s, %s, %s\n", id, rate_date, currency, rate, upload_date);
            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
