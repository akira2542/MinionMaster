/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import model.Minion;
import model.PrimaryStatus;

/**
 *
 * @author COM
 */
public class BattleField {
    Minion[] localParty;
    int turncounter;
        
    public BattleField(Minion[] localParty){
         this.localParty = localParty;
    }
   
    public boolean battle(Minion[] visitingParty) {
        boolean battleresult = false; // true = visiting party win
        while( !isAllDead(localParty) && !isAllDead(visitingParty)){
            this.turncounter++;
            System.out.println("Turn "+this.turncounter);
            System.out.println("visiting party turn!");
            for (Minion visitingminion : visitingParty) {
                visitingminion.actionDecider(visitingParty, localParty);
            }
            if (isAllDead(localParty)) {
                System.out.println("local minions is defeated!");
                battleresult = true;
                break;
            }
            System.out.println("local party turn!");
            for (Minion localminion : this.localParty) {
                localminion.actionDecider(localParty, visitingParty);
            }
            if (isAllDead(visitingParty)) {
                System.out.println("visiting minions is defeated!");
                battleresult = false;
                break;
            }
        }
        return battleresult;
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
