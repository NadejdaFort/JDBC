package com.nfort.jdbc.starter;

import com.nfort.jdbc.starter.util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
        String flightId = "2 OR 1 = 1; DROP TABLE info;";  // "2 OR ''=''"
        var result = getTicketsByFlightId(flightId);
        System.out.println(result);
    }

    public static List<Long> getTicketsByFlightId(String flightId) throws SQLException {
        String sql = """
                SELECT id
                FROM ticket
                WHERE flight_id = %s
                """.formatted(flightId);
        List<Long> result = new ArrayList<>();
        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
//                result.add(resultSet.getLong("id"));  // возвращает примитивный тип, не может принять значение null
                result.add(resultSet.getObject("id", Long.class));  // в данной ситуации null-значение возможно
            }
        }
        return result;
    }
}
