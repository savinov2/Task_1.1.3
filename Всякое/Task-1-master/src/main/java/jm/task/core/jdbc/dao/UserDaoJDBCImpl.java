package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.security.PrivateKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();
    private final Connection connection = util.getConnection();

    private Statement getStatament(){
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void createUsersTable() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS Users (\n" +
                "    id SERIAL,\n" +
                "    name VARCHAR(255) NOT NULL,\n" +
                "    lastname VARCHAR(255), \n" +
                "    age INT DEFAULT 18 );";
        try {
            getStatament().executeUpdate(createUsersTable);
            System.out.println("Created tabel Users");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String dropUsersTable = "DROP table Users";
        try {
            getStatament().executeUpdate(dropUsersTable);
            System.out.println("Droped tabel Users");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String addUserTable = String.format("INSERT into Users(name,lastname,age) Values(\'%s\', \'%s\', %d)",name,lastName,age);
        try {
            getStatament().executeUpdate(addUserTable);
            System.out.println("Add User "+ name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String addUserTable = String.format("DELETE From Users where users.id = %d", id);
        try{
            getStatament().executeUpdate(addUserTable);
            System.out.println("delete User "+ id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> usersList = new ArrayList<User>();
        String getAll = String.format("SELECT name,lastname,age From Users ");
        try{
            ResultSet resultSet = getStatament().executeQuery(getAll);
            while(resultSet.next()) {
                String name  = resultSet.getString(1);
                String lastname = resultSet.getString(2);
                byte age = resultSet.getByte(3);
                usersList.add(new User(name, lastname, age));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return usersList;
    }

    public void cleanUsersTable() {
        String clearBD = String.format("DELETE From Users");
        try{
            getStatament().executeUpdate(clearBD);
            System.out.println("bd cleared");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
