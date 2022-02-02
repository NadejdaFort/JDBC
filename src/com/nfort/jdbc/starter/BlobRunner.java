package com.nfort.jdbc.starter;

import com.nfort.jdbc.starter.util.ConnectionManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.*;

public class BlobRunner {

    public static void main(String[] args) throws SQLException, IOException {
        // blob - bytea
        // clob - TEXT
//        saveImage();
        getImage();
    }

    private static void getImage() throws SQLException, IOException {
        var sql = """
                SELECT image
                FROM aircraft
                WHERE id = ?;
                """;
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, 1);
            var resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                var image = resultSet.getBytes("image");
                Files.write(Path.of("resources", "boing777_new.jpeg"), image, StandardOpenOption.CREATE);
            }
        }
    }

// так нужно делать в PostgreSQL, которая не поддерживает blob и clob.
    private static void saveImage() throws SQLException, IOException {
        var sql = """
                UPDATE aircraft
                SET image = ?
                WHERE id = 1
                """;
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBytes(1, Files.readAllBytes(Path.of("resources", "Boeing_777.jpeg")));
            preparedStatement.executeUpdate();
        }
    }

    // таким способом можно делать только в БД Oracle, т.к. в ней есть сущности blob и clob
//    private static void saveImage() throws SQLException, IOException {
//        var sql = """
//                UPDATE aircraft
//                SET image = ?
//                WHERE id = 1
//                """;
//        try (var connection = ConnectionManager.open();
//             var preparedStatement = connection.prepareStatement(sql)) {
//            connection.setAutoCommit(false);
//            var blob = connection.createBlob();
//            blob.setBytes(1, Files.readAllBytes(Path.of("resources", "Boeing_777.jpeg")));
//
//            preparedStatement.setBlob(1, blob);
//            preparedStatement.executeUpdate();
//            connection.commit();
//        }
//    }
}
