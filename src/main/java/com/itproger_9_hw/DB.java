package com.itproger_9_hw;

import java.sql.*;

public class DB {

    private final String HOST = "localhost";
    private final String PORT = "3307";
    private final String DB_NAME = "itproger-java";
    private final String LOGIN = "root";
    private final String PASS = "root";

    private  Connection dbConn = null;
    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME ;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }

    public  void isConnected() throws SQLException, ClassNotFoundException {
        dbConn = getDbConnection();
        System.out.println(dbConn.isValid(1000));
    }
    public boolean changeUser(String login, String email, String password) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `users` SET  `login`=? ,`email`=?,`password`=? WHERE `login` = \"Admin\" ";
        Statement statement = getDbConnection().createStatement();
//        ResultSet res = statement.executeQuery("SELECT * FROM `users` WHERE `login` = \""+ login +"\" LIMIT 1");
//        if(res.next())
//            return false;

        PreparedStatement prST = getDbConnection().prepareStatement(sql);
        prST.setString(1, login);
        prST.setString(2, email);
        prST.setString(3, password);
        prST.executeUpdate();
        return true;
    }
//    public boolean authUser(String login, String password) throws SQLException, ClassNotFoundException {
//        Statement statement = getDbConnection().createStatement();
//        String sql = "SELECT * FROM `users` WHERE `login` = \"" + login + "\" AND `password` = \"" + password + "\" LIMIT 1";
//        ResultSet res = statement.executeQuery(sql);
//        return res.next();
//
//    }

}


