/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author SURAPONGCHAMALAI
 */
public abstract class PlayableMinion extends Minion{
    
    private static final long BASE_REQUIRED_EXP = 500;
    private static final long PERCENT_INCREASE_PER_LEVEL = 40;
    
    private long XPpool;

    public PlayableMinion(int position, String Mname, double basehelthpoint, double basemanapoint, double baseattackpoint, double basearmor, double baseevasion, double baseaccuracy, LevelMultipiler mult) {
        super(position, Mname, basehelthpoint, basemanapoint, baseattackpoint, basearmor, baseevasion, baseaccuracy, mult);
    }
    
    //คำนวณ exp
    public int calculateLevelByXP(long XP){
        //รับค่า exp มา
     
        int levelbyXP = 1;
      long exppool = BASE_REQUIRED_EXP;
      // exppool = (exppool * 40)/100 + exppool;
        for  (long minimumXP = exppool; minimumXP < XP; minimumXP += minimumXP*PERCENT_INCREASE_PER_LEVEL/100)
            if( XP <= BASE_REQUIRED_EXP ){
                levelbyXP = 1;
            }else if(minimumXP>XP){
                levelbyXP = levelbyXP-1;
            }else{
                levelbyXP++;
                System.out.println(levelbyXP);
                System.out.println(minimumXP);
            }
        return levelbyXP;
    }
    
    }

