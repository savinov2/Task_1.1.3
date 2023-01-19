package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
    public static Connection getConnection(){
        return conn;
    }

    public class HibernateUtil{
        private static SessionFactory sessionFactory;
        public static SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration();

                    // Hibernate settings equivalent to hibernate.cfg.xml's properties
                    Properties settings = new Properties();
                    settings.put(Environment.DRIVER, "org.postgresql.Driver");
                    settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/db");
                    settings.put(Environment.USER, "postgres");
                    settings.put(Environment.PASS, "1604");
                    // settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                    settings.put(Environment.SHOW_SQL, "true");



                    settings.put(Environment.HBM2DDL_AUTO, "update");

                    configuration.addProperties(settings);

                    configuration.addAnnotatedClass(User.class);



                    sessionFactory = configuration.buildSessionFactory();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return sessionFactory;
        }

    }

}
