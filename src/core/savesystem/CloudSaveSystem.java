/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.savesystem;

import core.resource.PlayerProfile;
import exception.UnmatchingIndexPositionException;
import exception.UnmatchingLinkedListException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import core.resource.Equipment;
import model.enumurator.EquipmentGrade;
import model.Minion;
import model.PlayableMinion;
import utility.JDBC.DBConnector;
import utility.factory.MinionFactory;

/**
 *
 * @author COM
 */
public class CloudSaveSystem {
    
    public static final String TABLE_NAME = "userprofile";
    
//    public static void main(String[] args) {
//       CloudSaveSystem cs = new CloudSaveSystem(); 
//        PlayerProfile p = cs.readProfile("test", "123");
//        PlayableMinion pm = (PlayableMinion) p.getPlayerParty()[0];
//        System.out.println(p);
//        p.receiveGold(500);
//        cs.updateSave(p, "123");
//         p = cs.readProfile("test", "123");
//        System.out.println(p);
//        PlayerProfile pt = new PlayerProfile();
//        
//        cs.insertProfile(p, "123");
//    }
    
    public CloudSaveSystem() {
        //create table if not exist
        try {
        createTable();
                }
         catch (SQLException ex) {
            Logger.getLogger(CloudSaveSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertProfile(PlayerProfile profile, String pwd) {
        try {
        if (!UnmatchingIndexPositionException.isPositionMatch(profile.getPlayerParty())) throw  new UnmatchingIndexPositionException();
        if (!UnmatchingLinkedListException.isLinkedListMatched(profile.getPlayerParty())) throw new UnmatchingLinkedListException();
        if (!isUsernameExist(profile.getUsername())) {
          String sql =  "INSERT INTO userprofile(usr,pwd,gold,score,token"+
                  ",char_classindex_0,char_xp_0,char_wep_lv_0,char_wep_grade_0,char_ar_lv_0,char_ar_grade_0,"+
                  "char_classindex_1,char_xp_1,char_wep_lv_1,char_wep_grade_1,char_ar_lv_1,char_ar_grade_1,"+
                  "char_classindex_2,char_xp_2,char_wep_lv_2,char_wep_grade_2,char_ar_lv_2,char_ar_grade_2,"+
                  "char_classindex_3,char_xp_3,char_wep_lv_3,char_wep_grade_3,char_ar_lv_3,char_ar_grade_3)"+
                  " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          try (Connection conn = DBConnector.getConnection();
               PreparedStatement pstm = conn.prepareStatement(sql)) {
               pstm.setString(1, profile.getUsername());
               pstm.setString(2, pwd);
               pstm.setInt(3, (int) profile.getGold());
               pstm.setInt(4, (int) profile.getScore());
               pstm.setInt(5, profile.getToken());
               Minion[] minions = profile.getPlayerParty();
               for (int i = 6; i < 30; ) {
                   for (int j = 0; j < minions.length; j++) {
                       PlayableMinion m = (PlayableMinion) minions[j];
                       pstm.setInt(i++, m.getCLASS_INDEX());
                       pstm.setInt(i++, (int) m.getXPpool());
                       pstm.setInt(i++,m.getEquipment().getWeaponlv());
                       pstm.setInt(i++,EquipmentGrade.getEquipmentGradeIndex(m.getEquipment().getWeaponGrade()));
                       pstm.setInt(i++,m.getEquipment().getArmorlv());
                       pstm.setInt(i++,EquipmentGrade.getEquipmentGradeIndex(m.getEquipment().getArmorGrade()));
                   }
              }
              pstm.executeUpdate();
            }
          catch (SQLException ex) {
            Logger.getLogger(CloudSaveSystem.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }else {
        System.out.println("Username exist, update profile");
        updateSave(profile,pwd);
        }
        }catch (UnmatchingIndexPositionException | UnmatchingLinkedListException ex){
            ex.printStackTrace();
        }   
    }
    
    public PlayerProfile queryProfile(String user,String password){
        if (!isUserPasswordCorrect(user,password)){System.out.println("incrroect username/password"); return null;}
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE usr like '"+user+"'";
        try (Connection conn = DBConnector.getConnection();
             Statement stm = conn.createStatement();) {
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                    PlayerProfile profile = new PlayerProfile(rs.getString("usr"));
                    Minion[] minions = new Minion[4];
                    profile.receiveGold(rs.getInt("gold"));
                    profile.receiveScore(rs.getInt("score"));
                    profile.receiveToken(rs.getInt("token"));
                    for (int i = 0; i < minions.length; i++) {
                        minions[i] = MinionFactory.createPlayableMinion(rs.getInt("char_classindex_"+i), rs.getInt("char_xp_"+i),rs.getInt("char_wep_lv_"+i) , EquipmentGrade.getEquipmentGrade(rs.getInt("char_wep_grade_"+i)), rs.getInt("char_ar_lv_"+i), EquipmentGrade.getEquipmentGrade(rs.getInt("char_ar_grade_"+i)));

                    }
                    MinionFactory.reassemblePlayerParty(minions);
                    profile.setPlayerParty(minions);
                    return profile;                  
            }       
        return null;
       } catch (SQLException ex) {
         Logger.getLogger(CloudSaveSystem.class.getName()).log(Level.SEVERE, null, ex);
       }
        return null;
    }
    
    private boolean isTableExist() throws SQLException{
        try (Connection conn = DBConnector.getConnection();) {
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
"                                               token int(255) NOT NULL,"+
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
    
    public boolean isUsernameExist(String username) {
        String sql = "SELECT usr FROM "+TABLE_NAME+" WHERE usr like '"+username+"'";
        try (Connection conn = DBConnector.getConnection();
             Statement stm = conn.createStatement();) {
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        }catch (SQLException ex) {
            Logger.getLogger(CloudSaveSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private boolean isUserPasswordCorrect(String username,String pwd) {
        if (isUsernameExist(username)) {
            String sql = "SELECT * FROM "+TABLE_NAME+" WHERE usr like '"+username+"'"+" && pwd like '"+pwd+"'";
            try (Connection conn = DBConnector.getConnection();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
                return rs.next();
            }catch (SQLException ex) {
                Logger.getLogger(CloudSaveSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        return false;
    }
    
    public void updateSave(PlayerProfile profile,String pwd) {
        String username = profile.getUsername();
        if (!isUserPasswordCorrect(username,pwd)){System.out.println("incorrect username/password");return;};
        String sql = "UPDATE "+TABLE_NAME+" SET gold = ?,score = ?,token = ?"+
                  ",char_classindex_0 = ?,char_xp_0 = ?,char_wep_lv_0 = ?,char_wep_grade_0 = ?,char_ar_lv_0 = ?,char_ar_grade_0 = ?,"+
                  "char_classindex_1 = ?,char_xp_1 = ?,char_wep_lv_1 = ?,char_wep_grade_1 = ?,char_ar_lv_1 = ?,char_ar_grade_1 = ?,"+
                  "char_classindex_2 = ?,char_xp_2 = ?,char_wep_lv_2 = ?,char_wep_grade_2 = ?,char_ar_lv_2 = ?,char_ar_grade_2 = ?,"+
                  "char_classindex_3 = ?,char_xp_3 = ?,char_wep_lv_3 = ?,char_wep_grade_3 = ?,char_ar_lv_3 = ?,char_ar_grade_3 = ? "+
                  "WHERE usr like '"+username+"' AND pwd like '"+pwd+"'";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);) {
             Minion[] minions = profile.getPlayerParty();
             pstm.setInt(1, (int) profile.getGold());
             pstm.setInt(2, (int) profile.getScore());
             pstm.setInt(3,profile.getToken());
             for ( int i = 4 ; i < 28;) {
                   for (int j = 0; j < minions.length; j++) {
                       PlayableMinion m = (PlayableMinion) minions[j];
                       pstm.setInt(i++, m.getCLASS_INDEX());
                       pstm.setInt(i++, (int) m.getXPpool());
                       pstm.setInt(i++,m.getEquipment().getWeaponlv());
                       pstm.setInt(i++,EquipmentGrade.getEquipmentGradeIndex(m.getEquipment().getWeaponGrade()));
                       pstm.setInt(i++,m.getEquipment().getArmorlv());
                       pstm.setInt(i++,EquipmentGrade.getEquipmentGradeIndex(m.getEquipment().getArmorGrade()));
                   }
              }
            pstm.executeUpdate();
             
        }catch (SQLException ex) {
            Logger.getLogger(CloudSaveSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public String getLeaderBoard(){
            String sql = "SELECT usr,score from userprofile ORDER by score limit 10";
            try (Connection conn = DBConnector.getConnection();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
                StringBuilder s = new StringBuilder();
                s.append("============================\n");
                s.append("         LEADERBOARD        \n");
                s.append("============================\n");
                int i = 1;
                while(rs.next()){
                    String usr = rs.getString("usr");
                    long score = rs.getInt("score");
                    String temp = i+" | USER : "+usr+" SCORE : "+score+"\n";
                    s.append(temp);
                    i++;
                }
                return s.toString();
            }catch (SQLException ex) {
                Logger.getLogger(CloudSaveSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "something wrong!";
    }
    
}
