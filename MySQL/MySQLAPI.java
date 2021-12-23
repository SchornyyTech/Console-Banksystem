package MySQL;

import user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.UUID;

public class MySQLAPI {

    public static void addPlayerToDatabase(User user) {
        MySQL.update("INSERT INTO BankAccount (UUID" +
                ", Name" +
                ", Password" +
                ", Money) VALUES ('"
                + UUID.randomUUID().toString() + "', '"
                + user.getName() + "', '"
                + user.getPassword() + "'," +
                " '" +  0 + "');");
    }

    public static boolean userExistsInDatabase(String userName) {
        ResultSet resultSet = MySQL.query("SELECT * FROM BankAccount WHERE Name ='" + userName + "'");
        try {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void setIntegerAtDatabase(User user, int money) {
        MySQL.update("UPDATE BankAccount SET Money ='" + money + "' WHERE Name='" + user.getName() + "'");
    }

    public static void setStringAtDatabase(User user, String input) {
        MySQL.update("UPDATE BankAccount SET " + "Password" + "='" + Base64.getDecoder().decode(input) + "' WHERE Name='" + user.getName()+ "'");
    }

    public static int getIntegerFromDatabase(User user, String type) {
        ResultSet resultSet = MySQL.query("SELECT * FROM BankAccount WHERE Name='" + user.getName() + "'");
        try {
            while(resultSet.next()) {
                return resultSet.getInt(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static String getStringFromDataBase(User user) {
        ResultSet resultSet = MySQL.query("SELECT * FROM BankAccount WHERE Name='" + user.getName() + "'");
        try {
            while(resultSet.next()) {
                return resultSet.getString("Password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }
}
