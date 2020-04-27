/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author COM
 */
public class DBConnector {
    
        private static final String URI = "jdbc:mysql://localhost/minionmaster";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "";
        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
        
    
        public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn=DriverManager.getConnection(URI, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
        return conn;
    }
}
