/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import model.Minion;

/**
 *
 * @author CHANANAN
 */
public class UnmatchingIndexPositionException extends Exception {
    private static final String MSG = "Position inside minion do not match Party index position";
    public UnmatchingIndexPositionException() {
        super(MSG);
    }
    
    
    public static boolean isPositionMatch(Minion[] m) {
    boolean bool = true;
        for (int i = 0; i < m.length; i++) {
            Minion minion = m[i];
            if (minion.getPosition() != i) {
            bool = false;
                    }
        }
        return bool;
    }
}
