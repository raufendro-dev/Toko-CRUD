package com.toko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
 
public class SQLConnector {
    public static Connection con=null;
    public static Statement stm;
    public static Connection dbconnector(){
        try {
            String url ="jdbc:mysql://localhost:3306/toko";
            String user="root";
            String pass="bismillah";
           
            con =DriverManager.getConnection(url,user,pass);
            stm = con.createStatement();
            //System.out.println("Database Terhubung");
            return con;
        } catch (Exception e) {
            //System.err.println("Koneksi ke Database gagal" +e.getMessage());
            return null;
            
        }
    }
    
}
