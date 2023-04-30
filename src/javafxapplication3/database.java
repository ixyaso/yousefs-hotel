/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author youse
 */
public class database {
    
    public static Connection connectDb(){
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
         
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement","root","" );
            
            //Connection connect = DriverManager.getConnection("jdbc:sqlserver://YOUSEFS-LAPTOP\\SQLEXPRESS;databaseName=HotelManagement;user=root;password=");
            //System.out.println("connection is established with sql");
            return connect; 
             
            
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    
}
