package com.gms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database database = new Database();
    private Connection conn;
    private Database(){

    }

    public static Database getDatabase() {
        return database;
    }

    public Connection getConn(){
        return this.conn;
    }
    public void connect() throws SQLException {
        if(conn != null){
            return;
        }
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String username = "n01323814";
        String password = "oracle";
        String url = "jdbc:oracle:thin:@calvin.humber.ca:1521:grok";

        //load and register driver

        conn = DriverManager.getConnection(url, username, password);

    }

    public void disconnect(){
        if(conn!=null){
            try{
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        conn = null;
    }
}
