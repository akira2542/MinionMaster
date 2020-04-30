/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.resource;

import core.resource.PlayerProfile;
import model.Minion;
import model.PlayableMinion;
import model.enumurator.Difficulty;
import utility.TimeStopper;
import utility.factory.DungeonFactory;
import utility.factory.MinionFactory;

/**
 *
 * @author COM
 */
public class Dungeon {
    
  private Battlefield[]  battlefields;//ห้องที่สู้อยู่
  private long reward;//ของรางวัลเช่น จำนวนเงิน
  private long clearscore;
  private int token; 
  
  public Dungeon(Battlefield[] battleFields,long reward,long score,int token) {
      this.battlefields = battleFields;
      this.reward = reward;
      this.clearscore = score;
      this.token = token;
  }
  
  public void enter(PlayerProfile profile){
      Minion[] visitingParty = profile.getPlayerParty();
      restoreHealth(visitingParty);
      System.out.println("=========================");
      System.out.println("  WELCOME TO THE DUNGEON ");
      System.out.println("=========================");
      System.out.println("This Dungeon has '"+this.battlefields.length+"' room long");
      for (int i = 0; i < battlefields.length; i++) {
          int roomnum = i+1;
          System.out.println("Room No. "+roomnum+" Battle Begins!");
          TimeStopper.Delay();
          if (this.battlefields[i].battle(visitingParty)) {
              this.restoreHealth(visitingParty);
              System.out.println("Room No. "+roomnum+" Cleared!");
              TimeStopper.userInput();
              if ( roomnum == this.battlefields.length ) {
                  System.out.println("The dungoen is cleared");
                  System.out.println("Player recieve reward of ");
                  System.out.println("gold +"+this.reward);
                  System.out.println("clearscore +"+this.clearscore);
                  System.out.println("token +"+this.token);
                  profile.receiveGold(this.reward);
                  profile.receiveScore(this.clearscore);
                  profile.receiveToken(this.token);
                  updateLevel(profile.getPlayerParty());
              }
          }else{ 
              System.out.println("Player's Party Defeated");
              restoreHealth(visitingParty);
              break;
          }
      }
  }
  
  // restore party's helth to max
  public void restoreHealth(Minion[] party) {
      for (Minion minion : party) {
          minion.refresh();
      }
  }
  
  public void updateLevel(Minion[] m) {
      PlayableMinion[] pms = new PlayableMinion[m.length];
      for (int i = 0; i < m.length; i++) {
          pms[i] = (PlayableMinion) m[i];
      }
      for (int i = 0; i < pms.length; i++) {
          PlayableMinion pm = pms[i];
          int level = PlayableMinion.calculateLevelByXP(pm.getXPpool());
          if (level > pm.getLevel()) {
              pm.setLevel(level);
              System.out.println(pm.getName()+ " has leveled up to "+level);
          }
      }
  }
  
}
