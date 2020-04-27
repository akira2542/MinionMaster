package utility.factory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import content.playableminon.Duelist;
import content.minion.Goblin;
import content.minion.Orc;
import content.playableminon.Knight;
import content.playableminon.Preist;
import content.playableminon.Thief;
import model.Equipment;
import model.EquipmentGrade;
import model.Minion;
import model.PlayableMinion;

/**
 *
 * @author COM
 */
public class MinionFactory {
    
    public static final int KNIGHT = 101;
    public static final int PREIST = 102;
    public static final int DUELIST = 103;
    public static final int THIEF = 104;
    
    
    public static final int GOBLIN = 201;
    public static final int ORC = 202;
    
    public static Minion createPlayableMinion(int classIndex) {
        switch(classIndex) {
            case KNIGHT:
                return new Knight();
            case PREIST:
                return new Preist();
            case DUELIST:
                return new Duelist();
            case THIEF:
                return new Thief();
            default:
                return null;
        }
    }
    
    public static Minion createPlayableMinion(int classIndex,long xp,int weaponlevel,EquipmentGrade weapongrade,int armorlevel,EquipmentGrade armorgrade) {
        PlayableMinion m = (PlayableMinion) createPlayableMinion(classIndex);
        int level = PlayableMinion.calculateLevelByXP(xp);
        long surplusxp = xp - PlayableMinion.calculateXPByLevel(level);
        m.setLevel(level);
        m.receiveXP(surplusxp);
        m.setEquipment(new Equipment(weaponlevel,weapongrade,armorlevel,armorgrade));
        return m;
    }
    
    public static Minion createMinion(int classIndex) {
                switch(classIndex) {
            case GOBLIN:
                return new Goblin();
            case ORC:
                return new Orc();
            default:
                return null;
        }
    }
    
    public static Minion createMinion(int classIndex,int level) {
        Minion m = MinionFactory.createMinion(classIndex);
        m.setLevel(level);
        return m;
    }
    
    

}
