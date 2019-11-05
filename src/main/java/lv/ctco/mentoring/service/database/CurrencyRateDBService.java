package lv.ctco.mentoring.service.database;

import lv.ctco.mentoring.entity.Currency;

import java.sql.*;
import java.util.Date;
import java.util.List;


public class CurrencyRateDBService {

    private static final String INSERT_RATE_QUERY = " insert into rates (rate_date, currency, rate, upload_date)" +
            " values (?, ?, ?, ?)";
    private static final String LAST_DATA_UPDATE_DATE_QUERY = "SELECT MAX(cast(rate_date as Date)) AS rate_date FROM rates";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/javastudy?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public CurrencyRateDBService() throws ClassNotFoundException {
        Class.forName("org.gjt.mm.mysql.Driver");
    }

    public Date getLastUpdateDate() throws SQLException {
        Date dateFromDB = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            try (Statement st = conn.createStatement()) {
                ResultSet rs = st.executeQuery(LAST_DATA_UPDATE_DATE_QUERY);

                if (rs.next()) {
                    dateFromDB = rs.getDate("rate_date");
                }
            }
        }

        return dateFromDB;
    }

    public void saveNewRates(List<Currency> rates, Date ratesDate) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            for (Currency rate : rates) {
                saveNewRate(rate, ratesDate, conn);
            }
        }
    }

    private void saveNewRate(Currency rate, Date rateDate, Connection conn) throws SQLException {
        try (PreparedStatement preparedStmt = conn.prepareStatement(INSERT_RATE_QUERY)) {
            preparedStmt.setDate(1, new java.sql.Date(rateDate.getTime()));
            preparedStmt.setString(2, rate.getName());
            preparedStmt.setBigDecimal(3, rate.getRate());
            preparedStmt.setDate(4, new java.sql.Date(new Date().getTime()));

            preparedStmt.execute();
        }
    }

}


