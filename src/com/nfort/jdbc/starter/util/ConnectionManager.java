package com.nfort.jdbc.starter.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/flight_repository";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "";

// до Java1.8 нужно было прописывать статический блок инициализации
    static{
    loadDriver();
}

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionManager() {
    }

    public static Connection open() {
        try {
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
