package com.example.bdprojekt.Connector;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DbUtill {

    private static final String databaseName = "punkt_szczepien";
    private static final String databaseUser = "root";
    private static final String databasePassword = "7eGN7yfe4y5C#MSN5T4Nm";
    private static final String JBDC_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection = null;
    private static final String connStr = "jdbc:mysql://localhost/" + databaseName + "?"+
            "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static void dbConnect() throws SQLException,ClassNotFoundException{
        try{
            Class.forName(JBDC_DRIVER);
        }catch(ClassNotFoundException e){
            System.out.println("Where is your MySql JDBC Driver");
            e.printStackTrace();
            throw e;
        }

        System.out.println("JDBC Driver has been registered !!!");

        try{
            connection = DriverManager.getConnection(connStr,databaseUser,databasePassword);
        }catch(SQLException e){
            System.out.println("Connection failed! Check output console " +e);
            throw e;
        }
    }
    //method for closing the connection
    public static void dbDisconnect() throws SQLException{
        try{
            if(connection != null &&  !connection.isClosed()){
                connection.close();
            }
        }catch(Exception e) {
            throw e;
        }
    }
    //this is for insert / delete / update operation
    public static void dbExecuteQuery(String sqlStmt)throws SQLException, ClassNotFoundException{
        Statement stmt = null;
        try{
            dbConnect();
            stmt=connection.createStatement();
            stmt.executeUpdate(sqlStmt);
        }catch(SQLException e){
            System.out.println("Problem occured at dbExecuteQuery operation" + e);
            throw e;
        }
        finally{
            if(stmt!=null){
                stmt.close();
            }

            dbDisconnect();
        }
    }
    //this is for retriving data from database
    public static ResultSet dbExecute(String sqlQuery) throws ClassNotFoundException,SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;

        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery(sqlQuery);
            crs= new CachedRowSetImpl();
            crs.populate(rs);
        }catch(SQLException e){
            System.out.println("Error occured in dbExecute operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }
    //gets id int value  of user based on login (pesel)
    public static int getUserId(String login)throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM punkt_szczepien.pacjenci WHERE pacjenci.pesel IN('"+login+"')");
            while(rs.next()){
                id=rs.getInt("user_id");
                System.out.println(id);
            }
            // id=rs.getInt("id");


        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getUserId) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }
        return id;
    }
    public static int[] getAllIds( )throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        int id = 0;
        int iterator=0;
        int tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM pacjenci ");
            //first iteration to get size od a table
            while(rs.next()){
                id=rs.getInt("user_id");
                iterator++;
            }

            rs=stmt.executeQuery("SELECT * FROM pacjenci ");
            tab= new int[iterator];
            iterator =0;
            // second iteration to fill table with ids
            while(rs.next()){
                id=rs.getInt("user_id");
                tab[iterator]=id;
                iterator++;
            }

        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllIds) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return tab;
    }
    public static String[] getAllLogins( )throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String login = null;
        int iterator=0;
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM pacjenci ");
            //first iteration to get size od a table
            while(rs.next()){
                login=rs.getString("user_login");
                iterator++;
            }

            rs=stmt.executeQuery("SELECT * FROM pacjenci ");
            tab= new String[iterator];
            iterator =0;
            // second iteration to fill table with ids
            while(rs.next()){
                login=rs.getString("user_login");
                tab[iterator]=login;
                iterator++;
            }

        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllLogs) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return tab;
    }
    public static String[] getAllPasswords( )throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String password = null;
        int iterator=0;
        String tab[];
        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM pacjenci ");
            //first iteration to get size od a table
            while(rs.next()){
                password=rs.getString("user_password");
                iterator++;
            }

            rs=stmt.executeQuery("SELECT * FROM pacjenci ");
            tab= new String[iterator];
            iterator =0;
            // second iteration to fill table with ids
            while(rs.next()){
                password=rs.getString("user_password");
                tab[iterator]=password;
                iterator++;
            }

        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getAllPassword) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }

        return tab;
    }
    public static String getUserType(int id) throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String type=null;

        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM pacjenci WHERE ID_p IN('"+id+"')");
            while(rs.next()){
                type=rs.getString("user_type");
                System.out.println(id);
            }
            // id=rs.getInt("id");


        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getUserId) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }
        return type;
    }
    public static String getUserPassword(int id) throws ClassNotFoundException,SQLException{
        //   String tab[] = new String
        Statement stmt = null;
        ResultSet rs = null;
        String password=null;

        try{
            dbConnect();
            stmt=connection.createStatement();
            rs=stmt.executeQuery("SELECT * FROM pacjenci WHERE ID_p IN('"+id+"')");
            while(rs.next()){
                password=rs.getString("user_password");
                System.out.println(id);
            }
            // id=rs.getInt("id");


        }catch(SQLException e){
            System.out.println("Error occured in dbExecute(getUserId) operation " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if (stmt != null ){
                stmt.close();
            }
            dbDisconnect();
        }
        return password;
    }

}
