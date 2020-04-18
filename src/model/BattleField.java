/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import content.Goblin;
import content.Ork;
import model.PrimaryStatus;
/**
 *
 * @author COM
 */
public class BattleField {
    Minion[] localParty;
    int turncounter;
        
    public static void main(String[] args) {
        Minion[] p1 = new Minion[4];
        for (int i = 0; i < p1.length; i++) {
            p1[i] = new Goblin(i);
        }
         Minion[] p2 = new Minion[4];
        for (int i = 0; i < p2.length; i++) {
            p2[i] = new Ork(i);
        }
        BattleField b = new BattleField(p1);
        b.battle(p2);
        
    }
    
    public BattleField(Minion[] localParty){
         this.localParty = localParty;
    }
   
    public void battle(Minion[] visitingParty) {
        while( !isAllDead(localParty) && !isAllDead(visitingParty)){
            this.turncounter++;
            System.out.println("Turn "+this.turncounter);
            System.out.println("visiting party turn!");
            for (Minion visitingminion : visitingParty) {
                visitingminion.actionDecider(visitingParty, localParty);
            }
            if (isAllDead(localParty)) {
                System.out.println("local minions is defeated!");
                break;
            }
            System.out.println("local party turn!");
            for (Minion localminion : this.localParty) {
                localminion.actionDecider(localParty, visitingParty);
            }
            if (isAllDead(visitingParty)) {
                System.out.println("visiting minions is defeated!");
                break;
            }
        }
    
    }
    
    private boolean isAllDead(Minion[] party) {
        for (Minion minion : party) {
            if (minion.getPrimarystatus() == PrimaryStatus.ALIVE) {
            return false;
            }
        }
       return true;
    }
}
