/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.JDBC.DBConnector;

/**
 *
 * @author COM
 */
public class CloudSaveSystem {
    
    public static final String TABLE_NAME = "USERPROFILE";
    
    public CloudSaveSystem() {
        //create table if not exist
        try {
        createTable();
                }
         catch (SQLException ex) {
            Logger.getLogger(CloudSaveSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertProfile(PlayerProfile profile, String Password) {
    }
    
    private boolean isTableExist() throws SQLException{
        try (Connection conn = DBConnector.getConnection();
             Statement stm = conn.createStatement();   ) {
            String sql = "SELECT * FROM "+TABLE_NAME;
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        }
    }
    
    private void createTable() throws SQLException {
        if (!isTableExist()) {
            try (Connection conn = DBConnector.getConnection();
             Statement stm = conn.createStatement();) {
                String sql = "CREATE TABLE "+TABLE_NAME+" (usr varchar(255))";
                stm.executeUpdate(sql);
            }
        }
    }
    
    public boolean isProfileExist() {
        return false;
    }
    
    public PlayerProfile loadProfile(String username,String password) {
        return null;
    }
    
    public getLeaderBoard(){
    
    }
    
}
