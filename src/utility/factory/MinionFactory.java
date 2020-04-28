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
import content.playableminon.Priest;
import content.playableminon.Thief;
import java.util.Objects;
import model.ClassIndex;
import model.Equipment;
import model.EquipmentGrade;
import model.Minion;
import model.PlayableMinion;

/**
 *
 * @author COM
 */
public class MinionFactory {
    
    
    
    public static Minion createPlayableMinion(int classIndex) {
        switch(classIndex) {
            case ClassIndex.KNIGHT_INDEX:
                return new Knight();
            case ClassIndex.PREIST_INDEX:
                return new Priest();
            case ClassIndex.DUELIST_INDEX:
                return new Duelist();
            case ClassIndex.THIEF_INDEX:
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
            case ClassIndex.GOBLIN_INDEX:
                return new Goblin();
            case ClassIndex.ORC_INDEX:
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
