package com.ss.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public String driverName = "com.mysql.cj.jdbc.Driver";
    public String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
    public String userName = "root";
    public String password = "Avery0077";

    public Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, userName, password);
            conn.setAutoCommit(Boolean.FALSE);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }
}
