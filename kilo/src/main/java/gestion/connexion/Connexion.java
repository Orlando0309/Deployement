/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.connexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {
    public static Connection getConnexion () {
        Connection conn=null;
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("postgres://rocinnee:***@rogue.db.elephantsql.com/rocinnee","rocinnee","CMAjXsi_vBp9O1Si50QoO8tgBA9go5Af");
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
           catch (SQLException e) {
            e.printStackTrace();
          }
          return conn;  
    }    
}
