package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String urlBD = "jdbc:postgresql://localhost:5432/db";
    private static final String userBD = "postgres";
    private static final String passwordBD = "1604";

    private static Connection conn = null;

    {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlBD,userBD,passwordBD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Util(){
    }

    public static Connection getConnection(){
        return conn;
    }
}
