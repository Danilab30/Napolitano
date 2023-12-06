/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.edu.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author migue
 */
public class MySQLConnection {
     public static Connection get(){
       Connection connection = null; 
       try{
          // connection = DriverManager.getConnection("jdbc:mysql://localhost/gansitodb?user=root&password=apachillo21");
           connection = DriverManager.getConnection("jdbc:mysql://localhost/cinedb?user=root&password=apachillo21");
       }catch(Exception ex){
           System.err.print("Error: " + ex.getMessage());
       }
       return connection;
   } 
}

