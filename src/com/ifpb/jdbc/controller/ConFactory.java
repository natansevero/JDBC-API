

package com.ifpb.jdbc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author natan
 */
public class ConFactory {
    public static Connection getConnection(String url, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        
        Connection conn = DriverManager.getConnection(url, props);
        
        return conn;
    }
}
