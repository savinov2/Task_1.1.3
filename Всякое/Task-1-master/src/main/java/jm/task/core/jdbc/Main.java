package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Vady","Kola", (byte) 34);
        userService.saveUser("Alex","Kola", (byte) 42);
        userService.saveUser("Anton","Malayko", (byte) 11);
        userService.saveUser("Leonid","Savinov", (byte) 22);
        System.out.println(userService.getAllUsers().get(0));

    }
}
