/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import content.Goblin;
import model.Minion;

/**
 *
 * @author COM
 */
public class Dungeon {
    
  private  BattleField[]  battleFields;//ห้องที่สู้อยู่
  private  long reward;//ของรางวัลเช่น จำนวนเงิน
  
      public static void main(String[] args) {
        Minion[] p1 = new Minion[4];
        for (int i = 0; i < p1.length; i++) {
            p1[i] = new Goblin(i);
        }
         Minion[] p2 = new Minion[4];
        for (int i = 0; i < p2.length; i++) {
            p2[i] = new Goblin(i);
        }
        BattleField[] test = new BattleField[2];
        test[0] = new BattleField(p1);
        test[1] = new BattleField(p2);
        PlayerProfile player = new PlayerProfile();
        Dungeon dun = new Dungeon(test,5000);
        dun.enter(player);
        
    }
  
  public Dungeon(BattleField[] battleFields,long reward) {
      this.battleFields = battleFields;
      this.reward = reward;
  }
  
  public void enter(PlayerProfile profile){
      Minion[] visitingParty = profile.getPlayerParty();
      Boolean isCleared = false;
      for (int i = 0; i < battleFields.length; i++) {
          int roomnum = i+1;
          System.out.println("Room No. "+roomnum+" Battle Begins!");
          if (this.battleFields[i].battle(visitingParty)) {
              this.restoreHelth(visitingParty);
              System.out.println("Room No. "+roomnum+" Cleared!");
              if ( roomnum == this.battleFields.length ) {
                  System.out.println("The dungoen is cleared");
                  System.out.println("Player recieve reward of " +this.reward+ " gold!");
                  profile.receiveGold(this.reward);
              }
          }else{ 
              System.out.println("Player's Party Defeated");
              break;
          }
      }
  }
  
  // restore party's helth to max
  public void restoreHelth(Minion[] party) {
      for (Minion minion : party) {
          minion.refresh();
      }
  }
  
  public void expEarn(){
      //currentexp + expfromclear
  }
  
}
