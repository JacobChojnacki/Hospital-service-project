package com.example.bdprojekt.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "useraccounts";
        String databaseUser = "root";
        String databasePassword = "7eGN7yfe4y5C#MSN5T4Nm";
        String url = "jdbc:mysql://localhost/" + databaseName + "?"+
                "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
