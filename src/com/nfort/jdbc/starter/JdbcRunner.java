package com.nfort.jdbc.starter;

import com.nfort.jdbc.starter.util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
       // Class<Driver> driverClass = Driver.class;
        String sql;
        sql = """
               UPDATE info
               SET data = 'TestTest'
               WHERE id = 5
               RETURNING *;              
                """;   // в этом запросе возвращается ResultSet, а не как ожидается - int,
                        // это происходит из-за RETURNING, без него вернется int

        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement()) {
            System.out.println(connection.getSchema());
            System.out.println(connection.getTransactionIsolation());
//            var executeResult = statement.execute(sql);
//            System.out.println(statement.getUpdateCount());
            var executeResult = statement.executeUpdate(sql);
            System.out.println(executeResult);
        }
    }
}
