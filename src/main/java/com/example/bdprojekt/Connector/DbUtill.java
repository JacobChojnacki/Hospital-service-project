package com.example.bdprojekt.Connector;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class DbUtill {

    private static final String databaseName = "punkt_szczepien";
    private static final String databaseUser = "root";
    private static final String databasePassword = "7eGN7yfe4y5C#MSN5T4Nm";
    private static final String JBDC_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection = null;
    private static final String connStr = "jdbc:mysql://localhost/" + databaseName + "?"+
            "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    public Connection dbConnect() throws SQLException,ClassNotFoundException{
        try{
            Class.forName(JBDC_DRIVER);
        }catch(ClassNotFoundException e){
            System.out.println("Where is your MySql JDBC Driver");
            e.printStackTrace();
            throw e;
        }
        try{
            connection = DriverManager.getConnection(connStr,databaseUser,databasePassword);
            System.out.println("JDBC Driver has been registered !!!");
        }catch(SQLException e){
            System.out.println("Connection failed! Check output console " +e);
            throw e;
        }
        return connection;
    }
    //method for closing the connection
    public void dbDisconnect() throws SQLException{
        try{
            if(connection != null &&  !connection.isClosed()){
                System.out.println("Disconnection");
                connection.close();
            }
        }catch(Exception e) {
            throw e;
        }
    }
    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs;

        try {

            dbConnect();

            stmt = connection.prepareStatement(queryStmt);

            resultSet = stmt.executeQuery(queryStmt);

            crs = new CachedRowSetWrapper();

            crs.populate(resultSet);
        } catch (SQLException e) {
//            consoleTextArea.appendText("Problem occurred at executeQuery operation. \n");
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }

        return crs;
    }
    public  void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        try {
            dbConnect();
            stmt = connection.createStatement();
            stmt.executeUpdate(sqlStmt);

        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }
}
