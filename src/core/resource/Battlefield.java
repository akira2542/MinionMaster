/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.resource;

import exception.UnmatchingIndexPositionException;
import exception.UnmatchingLinkedListException;
import model.Minion;
import model.enumurator.PrimaryStatus;

/**
 *
 * @author COM
 */
public class Battlefield {
    Minion[] localParty;
    int turncounter;
        
    public Battlefield(Minion[] localParty){
         this.localParty = localParty;
    }
   
    public boolean battle(Minion[] visitingParty) {
        try {
            if (!UnmatchingIndexPositionException.isPositionMatch(visitingParty)) throw  new UnmatchingIndexPositionException();
            if (!UnmatchingLinkedListException.isLinkedListMatched(visitingParty)) throw new UnmatchingLinkedListException();
        boolean battleresult = false; // true = visiting party win
        while( !isAllDead(localParty) && !isAllDead(visitingParty)){
            this.turncounter++;
            System.out.println("Turn "+this.turncounter);
            System.out.println("visiting party turn!");
            for (Minion visitingminion : visitingParty) {
                if(visitingminion.isStunned()){System.out.println(visitingminion.getName()+" is still stunned "); continue;}
                visitingminion.actionDecider(visitingParty, localParty);
            }
            if (isAllDead(localParty)) {
                System.out.println("local minions is defeated!");
                battleresult = true;
                break;
            }
            System.out.println("local party turn!");
            for (Minion localminion : this.localParty) {
                if(localminion.isStunned()){System.out.println(localminion.getName()+" is still stunned "); continue;}
                localminion.actionDecider(localParty, visitingParty);
            }
            if (isAllDead(visitingParty)) {
                System.out.println("visiting minions is defeated!");
                battleresult = false;
                break;
            }
        }
        return battleresult;
        }catch (UnmatchingIndexPositionException | UnmatchingLinkedListException ex){
            ex.printStackTrace();
        }
        return false;
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
