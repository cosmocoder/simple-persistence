package org.simplepersistence.utils;

import java.sql.*;
import java.util.Arrays;

public class DatabaseTestUtils {
    public static Connection newConnection() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            return DriverManager.getConnection("jdbc:hsqldb:mem:database", "SA", "");
        } catch (SQLException e) {
            throw new RuntimeException("Could not create connection", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not locate database driver", e);
        }
    }

    private static PreparedStatement prepareStatement(Connection connection, String statementString, Object[] objects) throws SQLException {
        System.out.println("\nStatement = " + statementString);
        if(objects.length > 0)
        System.out.println("Bindings = " + Arrays.toString(objects));
        PreparedStatement preparedStatement = connection.prepareStatement(statementString);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }

    public static boolean executeAtDB(Connection connection, String statementString, Object... objects) throws SQLException {
        try {
            return prepareStatement(connection, statementString, objects).execute();
        } catch (SQLException sqlException) {
            connection.close();
            throw (sqlException);
        }
    }

    public static ResultSet queryFromDB(Connection connection, String statementString, Object... objects) throws SQLException {
        try {
            return prepareStatement(connection, statementString, objects).executeQuery();
        } catch (SQLException sqlException) {
            connection.close();
            throw (sqlException);
        }
    }

}