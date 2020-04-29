/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author COM
 */
public enum SecondaryStatus {
     STUNNED(1),STABLE(0);
     final int turntime;
     SecondaryStatus(final int newturntime) {
     turntime = newturntime;
     }
    public int getTurntime() {
        return turntime;
    }
     
     
}
