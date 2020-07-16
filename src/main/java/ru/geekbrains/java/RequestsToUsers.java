package ru.geekbrains.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ru.geekbrains.java.ColumnList.*;

public class RequestsToUsers {

    private PreparedStatement preStatement = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void showAllUsers() {
        ConnectionToDB mySqlConnect = new ConnectionToDB();
        try {
            statement = mySqlConnect.connect().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM geekbrains.users");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("name")
                        + " " + resultSet.getString("age") + " " + resultSet.getString("email"));
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            mySqlConnect.disconnect();
        }
    }

    public void showUsersByAge(int min, int max) {
        ConnectionToDB mySqlConnect = new ConnectionToDB();
        try {
            preStatement = mySqlConnect.connect().prepareStatement("SELECT * FROM geekbrains.users WHERE age BETWEEN ? and ?");
            preStatement.setInt(1, min);
            preStatement.setInt(2, max);
            resultSet = preStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("name")
                        + " " + resultSet.getString("age") + " " + resultSet.getString("email"));
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            mySqlConnect.disconnect();
        }
    }

    public void deleteByName(String name) {
        ConnectionToDB mySqlConnect = new ConnectionToDB();
        try {
            preStatement = mySqlConnect.connect().prepareStatement("DELETE FROM geekbrains.users WHERE name=?");
            preStatement.setString(1, name);
            int resultSet = preStatement.executeUpdate();
            if (resultSet > 0) {
                System.out.println("User deleted successfully");
            } else System.out.println("Nothing to delete");
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            mySqlConnect.disconnect();
        }
    }

    public void insertUser(String userString) {

        String[] splitStr = userString.split("\\s+");
        ConnectionToDB mySqlConnect = new ConnectionToDB();
        try {
            preStatement = mySqlConnect.connect().prepareStatement(
                    "insert into geekbrains.users (name, age, email) values (?,?,?)"
            );
            preStatement.setString(1, splitStr[0]);
            preStatement.setInt(2, Integer.parseInt(splitStr[1]));
            preStatement.setString(3, splitStr[2]);

            int resultSet = preStatement.executeUpdate();
            if (resultSet > 0) {
                System.out.println("User inserted successfully.");
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            mySqlConnect.disconnect();
        }


    }

    public int returnCountOfRows() {
        ConnectionToDB mySqlConnect = new ConnectionToDB();
        int countOfRows = 0;
        try {
            statement = mySqlConnect.connect().createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) as rowcount FROM geekbrains.users");
            resultSet.next();
            countOfRows = resultSet.getInt("rowcount");

        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            mySqlConnect.disconnect();
        }
        return countOfRows;
    }

    public List<User> getResultByParameter(ColumnList parameter, String parameterValue) {
        ConnectionToDB mySqlConnect = new ConnectionToDB();
        List<User> userList = null;
        try {

            preStatement = mySqlConnect.connect().prepareStatement("SELECT * FROM geekbrains.users WHERE "+parameter.name()+"= ?");
            //preStatement.setString(1, parameter.name());
            if (parameter == AGE) {
                preStatement.setInt(1, Integer.parseInt(parameterValue));
            } else if (parameter == ID) {
                preStatement.setLong(1, Long.parseLong(parameterValue));
            } else {
                preStatement.setString(1, parameterValue);
            }
            resultSet = preStatement.executeQuery();
            userList=new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                user.setEmail(resultSet.getString("email"));
                userList.add(user);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            mySqlConnect.disconnect();
        }
    return userList;
    }


}
