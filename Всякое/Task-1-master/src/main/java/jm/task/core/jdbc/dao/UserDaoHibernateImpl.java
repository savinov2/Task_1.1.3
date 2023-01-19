package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory factory = Util.HibernateUtil.getSessionFactory();
    private String SQLCREATE = "CREATE TABLE IF NOT EXISTS users (\n" +
            "    id SERIAL PRIMARY KEY,\n" +
            "    name VARCHAR(255),\n" +
            "    last_name VARCHAR(255), \n" +
            "    age NUMERIC);";
    private String SQLDROP = "DROP TABLE IF EXISTS Users";
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try(Session session = factory.openSession();) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(SQLCREATE);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = factory.openSession();) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(SQLDROP);
            query.executeUpdate();
            transaction.commit();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(new User(name, lastName, age));
//        Query query = session.createSQLQuery(String.format("INSERT into Users(name,lastname,age) Values(\'%s\', \'%s\', %d)",name,lastName,age));
//        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = factory.openSession();) {
            Transaction transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            session.delete(user);
//            Query query = session.createSQLQuery(String.format("DELETE From Users where users.id = %d", id)).addEntity(User.class);
//            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = factory.openSession();) {
            Transaction transaction = session.beginTransaction();
            List<User> result = session.createQuery("from User", User.class).list();
//            List users = session.createSQLQuery("SELECT name,lastname,age From Users").list();
//            for (int i = 0; i < users.size(); i++) {
//                String name = String.valueOf(((Object[])users.get(i))[0]);
//                String lastname = String.valueOf(((Object[])users.get(i))[1]);
//                byte age = Byte.parseByte(String.valueOf(((Object[])users.get(i))[2]));
//                result.add(new User(name,lastname,age));
//            }
            transaction.commit();
            return result;
        }
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = factory.openSession();) {
            Transaction transaction = session.beginTransaction();
            List<User> users = session.createQuery("from User", User.class).list();
            for(User user : users){
                session.delete(user);
            }
//            Query query = session.createSQLQuery("DELETE FROM users").addEntity(User.class);
//            query.executeUpdate();
            transaction.commit();
        }
    }
}
