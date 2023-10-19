/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author remo
 */
public class Datasource {
    
    private String url = "jdbc:mysql://localhost:3306/restoratings";
    private String username = "root";
    private String password = "";
    
    private Connection cnx;
    private static Datasource instance;
    
    private Datasource() {
        try {
            this.cnx = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected");
        } catch (SQLException ex) {
            Logger.getLogger(Datasource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public static Datasource getInstance() {
        if(instance == null){
            instance = new Datasource();
        }
        return instance;
    }
      
}
