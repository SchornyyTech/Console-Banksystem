package MySQL;


import java.sql.*;

public class MySQL {

    public static Connection con;

    public static void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankaccounts", "root", "root");
            System.out.println("[MySQL.MySQL] Connect to Database.");
            createTable();
        } catch (SQLException e) {
            System.out.println("[MySQL.MySQL] cant connect to Database Error: " + e.getMessage());
        }

    }

    public static void close() {
        try {
            if(con != null) {
                con.close();
                System.out.println("[MySQL.MySQL] Die Verbindung zur MySQL.MySQL Datenbank wurde erfolgreich beendet.");
            }

        } catch (SQLException e) {
            System.out.println("[MySQL.MySQL] Fehler beim beenden der Verbindung zur MySQL.MySQL Datenbase: " + e.getMessage());
        }
    }

    private static void createTable() {

        update("CREATE TABLE IF NOT  EXISTS BankAccount(UUID VARCHAR(64)" +
                ", Name VARCHAR(64)" +
                ", Password VARCHAR(328)" +
                ", Money INT(64));");
    }

    public static void update(String qry) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }

    }

    public static ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);

        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;

    }

    public boolean isConnected() {
        return con != null;
    }


}
