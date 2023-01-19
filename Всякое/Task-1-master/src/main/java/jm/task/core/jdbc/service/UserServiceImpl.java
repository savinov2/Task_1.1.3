package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoHibernateImpl view = new UserDaoHibernateImpl();
    public void createUsersTable() {
        view.createUsersTable();
    }

    public void dropUsersTable() {
        view.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        view.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        view.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return view.getAllUsers();
    }

    public void cleanUsersTable() {
        view.cleanUsersTable();
    }
}
