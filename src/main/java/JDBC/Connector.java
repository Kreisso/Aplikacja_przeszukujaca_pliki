package JDBC;

import java.sql.*;
import java.util.Properties;

public class Connector {

     public void Connector() {

        ladujSterownik();

        Connection con = getConnection("localhost", 3306);
        Statement st = createStatement(con);

        if (executeUpdate(st, "create Database wyszukiwarka;") != -1)
            System.out.println("Baza utworzona");
        else
            System.out.println("Baza nieutworzona!");
        if (executeUpdate(st, "USE wyszukiwarka;") != -1)
            System.out.println("Baza wybrana");
        else
            System.out.println("Baza niewybrana!");
        if (executeUpdate(st, "CREATE TABLE users ( id INT NOT NULL, login VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, PRIMARY KEY (id) );") != -1)
            System.out.println("Tabela utworzona");
        else
            System.out.println("Tabela nie utworzona!");
        if(executeUpdate(st, "INSERT INTO users VALUES(1, 'admin', 'admin't);") !=-1  )
            System.out.println("Admin dodany");
        else
            System.out.println("Admin nie dodany!");
    }

    public static Connection getConnection(String adres, int port) {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "lab");
        connectionProps.put("password", "lab");

        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + adres + ":" + port + "/",
                    connectionProps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to database");
        return conn;
    }

    private static Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;
        return null;
    }

    private static ResultSet executeQuery(Statement s, String sql) {
        try {
            return s.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int executeUpdate(Statement s, String sql) {
        try {
            return s.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    static boolean ladujSterownik() {
        // LADOWANIE STEROWNIKA
        System.out.print("Sprawdzanie sterownika:");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return true;
        } catch (Exception e) {
            System.out.println("Blad przy ladowaniu sterownika bazy!");
            return false;
        }
    }


}
