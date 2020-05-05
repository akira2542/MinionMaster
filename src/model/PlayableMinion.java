/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.resource.LevelMultipiler;
import model.enumurator.PrimaryStatus;

/**
 *
 * @author RUANGRIT
 */
public abstract class PlayableMinion extends Minion{
    
    private static final long BASE_REQUIRED_EXP = 500;
    private static final long PERCENT_INCREASE_PER_LEVEL = 40;
    
    
    private long XPpool;
    private Minion next;
    private Minion previous;

    public PlayableMinion(int index,String Mname, double basehealthpoint, double basemanapoint, double baseattackpoint, double basearmor, double baseevasion, double baseaccuracy, LevelMultipiler mult) {
        super(index,Mname, basehealthpoint, basemanapoint, baseattackpoint, basearmor, baseevasion, baseaccuracy, mult);
    }
    
    //คำนวณ exp
    public static int calculateLevelByXP(long XP){
        //รับค่า exp มา
     
        int levelbyXP = 1;
      long exppool = BASE_REQUIRED_EXP;
      // exppool = (exppool * 40)/100 + exppool;
        for  (long maximumXP = exppool; maximumXP < XP; maximumXP += maximumXP*PERCENT_INCREASE_PER_LEVEL/100)
            if( XP <= BASE_REQUIRED_EXP ){
                levelbyXP = 1;
            }else if(maximumXP>XP){
                levelbyXP = levelbyXP-1;
            }else{
                levelbyXP++;
            }
        return levelbyXP;
    }
    
    public static long calculateXPByLevel(int level) {
        if (level == 1) return BASE_REQUIRED_EXP;
        long XP = BASE_REQUIRED_EXP;
        for (int i = 1 ; i < level; i++) {
            XP += (XP*PERCENT_INCREASE_PER_LEVEL/100); 
        }
        return XP;
    }

    public long getXPpool() {
        return XPpool;
    }

    public Minion getNext() {
        return next;
    }

    public Minion getPrevious() {
        return previous;
    }
    
    
    
    //share xp function using linked list recursively
    private void shareXP(long xp) {
        long distributedXP = (long) Math.round((xp * 0.005));
        this.XPpool += distributedXP;
        if (this.next instanceof PlayableMinion && this.next != null) {
            PlayableMinion nextminion = (PlayableMinion) this.next;
            nextminion.shareXPNext(distributedXP);
        }
        if (this.previous instanceof PlayableMinion && this.previous != null) {
            PlayableMinion nextminion = (PlayableMinion) this.previous;
            nextminion.shareXPNext(distributedXP);
        }
    }
    
    public void shareXPNext(long xp) {
        this.XPpool += xp;
        if (this.next instanceof PlayableMinion  && this.next != null) {
            PlayableMinion nextminion = (PlayableMinion) this.next;
            nextminion.shareXPNext(xp);
        }
    }
    
    public void shareXPPrevious(long xp) {
        this.XPpool += xp;
        if (this.previous instanceof PlayableMinion  && this.previous != null) {
            PlayableMinion previousminion = (PlayableMinion) this.previous;
            previousminion.shareXPNext(xp);
        }
    }
    
    @Override
    public void attackOn(Minion enemy) {
    super.attackOn(enemy);
        if (enemy.getPrimarystatus() == PrimaryStatus.DEAD) {
        }
        //share xp to party member
        this.shareXP(PlayableMinion.calculateXPByLevel(enemy.getLevel()));
    }

    public void setNext(Minion next) {
        this.next = next;
    }

    public void setPrevious(Minion previous) {
        this.previous = previous;
    }    
    
    public void receiveXP(long xp) {
    this.XPpool += xp;
    }
    
    
    }

