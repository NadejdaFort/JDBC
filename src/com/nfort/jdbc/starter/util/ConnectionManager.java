package com.nfort.jdbc.starter.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USER_NAME_KEY = "db.userName";
    private static final String PASSWORD_KEY = "db.password";

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
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USER_NAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
