/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Equipment;
import model.Minion;
import model.PlayableMinion;
import utility.JDBC.DBConnector;

/**
 *
 * @author COM
 */
public class CloudSaveSystem {
    
    public static final String TABLE_NAME = "userprofile";
    
    public static void main(String[] args) {
//        CloudSaveSystem cs = new CloudSaveSystem();
        PlayerProfile p = new PlayerProfile();
//        cs.insertProfile(p, "123");
    }
    
    public CloudSaveSystem() {
        //create table if not exist
        try {
        createTable();
                }
         catch (SQLException ex) {
            Logger.getLogger(CloudSaveSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertProfile(PlayerProfile profile, String password) {
//          String hashedpwd = password.hashCode();
          String sql =  "INSERT INTO userprofile(usr,pwd,gold,score"+
                  ",char_classindex_0,char_xp_0,char_wep_lv_0,char_wep_grade_0,char_ar_lv_0,char_ar_grade_0,"+
                  "char_classindex_1,char_xp_1,char_wep_lv_1,char_wep_grade_1,char_ar_lv_1,char_ar_grade_1,"+
                  "char_classindex_2,char_xp_2,char_wep_lv_2,char_wep_grade_2,char_ar_lv_2,char_ar_grade_2,"+
                  "char_classindex_3,char_xp_3,char_wep_lv_3,char_wep_grade_3,char_ar_lv_3,char_ar_grade_3)"+
                  " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          try (Connection conn = DBConnector.getConnection();
               PreparedStatement pstm = conn.prepareStatement(sql)) {
               pstm.setString(1, profile.getUsername());
               pstm.setString(2, password);
               pstm.setInt(3, (int) profile.getGold());
               pstm.setInt(4, (int) profile.getScore());
               Minion[] minions = profile.getPlayerParty();
               for (int i = 5; i < 29; ) {
                   for (int j = 0; j < minions.length; j++) {
                       PlayableMinion m = (PlayableMinion) minions[j];
                       pstm.setInt(i++, m.getCLASS_INDEX());
                       pstm.setInt(i++, (int) m.getXPpool());
                       pstm.setInt(i++,m.getEquipment().getWeaponlv());
                       pstm.setInt(i++,Equipment.getEquipmentGradeIndex(m.getEquipment().getWeaponGrade()));
                       pstm.setInt(i++,m.getEquipment().getArmorlv());
                       pstm.setInt(i++,Equipment.getEquipmentGradeIndex(m.getEquipment().getArmorGrade()));
                   }
              }
              pstm.executeUpdate();
        }
          catch (SQLException ex) {
            Logger.getLogger(CloudSaveSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Minion readProfile(String user,String password){
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE usr like "+user;
        try (Connection conn = DBConnector.getConnection()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("pwd") == password) {
                    
                }else
            }       
        return null;
       } catch (SQLException ex) {
         Logger.getLogger(CloudSaveSystem.class.getName()).log(Level.SEVERE, null, ex);
       }
        return null;
    }
    
    private boolean isTableExist() throws SQLException{
        try (Connection conn = DBConnector.getConnection()) {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet rs = dbm.getTables(null, null, TABLE_NAME, null);
            return rs.next();
        }
    }
    
    
    private void createTable() throws SQLException {
        if (!isTableExist()) {
            try (Connection conn = DBConnector.getConnection();
             Statement stm = conn.createStatement();) {
                String sql = "CREATE TABLE "+TABLE_NAME+"(id int(255) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
"                                               usr varchar(255) NOT NULL," +
"                   				pwd varchar(255) NOT NULL," +
"                   				gold int(255) NOT NULL," +
"                   				score int(255) NOT NULL," +
"                   				char_classindex_0 SMALLINT NOT NULL," +
"                   				char_xp_0 INT(255) NOT NULL," +
"                   				char_wep_lv_0 SMALLINT NOT NULL," +
"                   				char_wep_grade_0 SMALLINT NOT NULL," +
"                   				char_ar_lv_0 SMALLINT NOT NULL," +
"                   				char_ar_grade_0 SMALLINT NOT NULL," +
"                   				char_classindex_1 SMALLINT NOT NULL," +
"                   				char_xp_1 INT(255) NOT NULL," +
"                   				char_wep_lv_1 SMALLINT NOT NULL," +
"                   				char_wep_grade_1 SMALLINT NOT NULL," +
"                   				char_ar_lv_1 SMALLINT NOT NULL," +
"                   				char_ar_grade_1 SMALLINT NOT NULL," +
"                                               char_classindex_2 SMALLINT NOT NULL," +
"                   				char_xp_2 INT(255) NOT NULL," +
"                   				char_wep_lv_2 SMALLINT NOT NULL," +
"                   				char_wep_grade_2 SMALLINT NOT NULL," +
"                   				char_ar_lv_2 SMALLINT NOT NULL," +
"                   				char_ar_grade_2 SMALLINT NOT NULL," +
"                                               char_classindex_3 SMALLINT NOT NULL," +
"                   				char_xp_3 INT(255) NOT NULL," +
"                   				char_wep_lv_3 SMALLINT NOT NULL," +
"                   				char_wep_grade_3 SMALLINT NOT NULL," +
"                   				char_ar_lv_3 SMALLINT NOT NULL," +
"                   				char_ar_grade_3 SMALLINT NOT NULL" +
"                  )";
                stm.executeUpdate(sql);
            }
        }
    }
    
    public boolean isUsernameExist() {
        return false;
    }
    
    public PlayerProfile loadProfile(String username,String password) {
        return null;
    }
    
//    public getLeaderBoard(){
//    
//    }
//    
}
