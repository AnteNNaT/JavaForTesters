package ru.geekbrains.java;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class ConnectionToDB {

    private Connection connection;

    private MysqlDataSource createDataSource() throws SQLException {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("antenna");
        dataSource.setPassword("Kapusta33510");
        dataSource.setURL("jdbc:mysql://localhost:3306");
        dataSource.setServerTimezone("UTC");
        return dataSource;
    }

    public Connection connect() {
        if (connection == null) {
            try {
                MysqlDataSource dataSource=createDataSource();
                connection=dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
}
