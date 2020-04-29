/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import model.Minion;
import model.PlayableMinion;

public class UnmatchingLinkedListException extends Exception{
    private static final String MSG = "PlayableMinons in the array have unmatched linked list";
    public UnmatchingLinkedListException() {
    super(MSG);
    }
    
    public static boolean isLinkedListMatched(Minion[] minions) {
        boolean bool = true;
        PlayableMinion[] pms = new PlayableMinion[minions.length];
        for (int i = 0; i < pms.length; i++) {
            pms[i] = (PlayableMinion) minions[i];
        }
        for (int i = 0; i < pms.length; i++) {
            PlayableMinion pm = pms[i];
            int nextIndex = (i+1 < pms.length) ? i+1 : -1;
            int previousIndex = ( i-1 >= 0)? i-1 : -1;
            if (nextIndex > -1) {
                if (!pm.getNext().equals(pms[nextIndex])) {
                        bool = false;
                        break;
                    }
                }
            if (previousIndex > - 1) {
               if (!pm.getPrevious().equals(pms[previousIndex])) {
                   bool = false;
                   break;
               }
            }
        }
        return bool;
    }
}
