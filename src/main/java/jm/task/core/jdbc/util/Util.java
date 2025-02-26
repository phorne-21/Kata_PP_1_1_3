package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class Util {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/pp_1_1_3";
    private static final String USER = "root";
    private static final String PASSWORD = "qwerty12";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    //Hibernate
    private static Logger logger = Logger.getLogger(Util.class.getName());
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties setting = new Properties();
                setting.put(Environment.DRIVER, DRIVER);
                setting.put(Environment.URL, URL);
                setting.put(Environment.USER, USER);
                setting.put(Environment.PASS, PASSWORD);
                setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                setting.put(Environment.SHOW_SQL, "true");
                setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                configuration.setProperties(setting);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                logger.info("SessionFactory created");
            } catch (Exception e) {
                logger.severe("SessionFactory creation failed with message: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    // jdbc
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void close(Connection connection) throws SQLException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
