package com.nfort.jdbc.starter;

import com.nfort.jdbc.starter.util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
       // Class<Driver> driverClass = Driver.class;
        String sql;
        sql = """
               SELECT *
               FROM ticket;              
               """;

        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            System.out.println(connection.getSchema());
            System.out.println(connection.getTransactionIsolation());
//            var executeResult = statement.execute(sql);
//            System.out.println(statement.getUpdateCount());

//            var executeResult = statement.executeUpdate(sql);
            var executeResult = statement.executeQuery(sql);
            while(executeResult.next()) {
                System.out.println(executeResult.getLong("id"));
                System.out.println(executeResult.getString("passenger_no"));
                System.out.println(executeResult.getBigDecimal("cost"));
//                executeResult.updateLong("id", 1000);      // обновляет значение у текущей строки
//                executeResult.last();
                System.out.println("-----------------------");
            }
        }
    }
}
