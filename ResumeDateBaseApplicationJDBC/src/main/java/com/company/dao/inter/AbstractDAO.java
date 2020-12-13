package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.TimeZone;

public abstract class AbstractDAO {

    public Connection connect() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String userName = "root";
        String password = "54321";
        String url = "jdbc:mysql://localhost:3306/myresume?serverTimezone=" + TimeZone.getDefault().getID();
        Connection connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }

}
