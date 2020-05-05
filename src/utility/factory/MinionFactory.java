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
import model.enumurator.ClassIndex;
import core.resource.Equipment;
import model.enumurator.EquipmentGrade;
import model.Minion;
import model.PlayableMinion;

/**
 *
 * @author CHANANAN
 */
public class MinionFactory {
    
    
    
    public static Minion createPlayableMinion(int classIndex) {
        switch(classIndex) {
            case ClassIndex.KNIGHT_INDEX:
                return new Knight();
            case ClassIndex.PRIEST_INDEX:
                return new Priest();
            case ClassIndex.DUELIST_INDEX:
                return new Duelist();
            case ClassIndex.THIEF_INDEX:
                return new Thief();
            default:
                return null;
        }
    }
    
    public static Minion createPlayableMinion(ClassIndex index) {
        return createPlayableMinion(index.getIndex());
    }
    
    public static Minion createPlayableMinion(int classIndex,long xp,int weaponlevel,EquipmentGrade weapongrade,int armorlevel,EquipmentGrade armorgrade) {
        PlayableMinion m = (PlayableMinion) createPlayableMinion(classIndex);
        int level = PlayableMinion.calculateLevelByXP(xp);
        long surplusxp = ((xp - PlayableMinion.calculateXPByLevel(level)) >= 0)? xp - PlayableMinion.calculateXPByLevel(level): 0;
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
    
    public static void reassemblePlayerParty(Minion[] minions) {
        for (int i = 0; i < minions.length; i++) {
            PlayableMinion minion = (PlayableMinion) minions[i];
            minion.setPosition(i);
            int nextIndex = (i+1 < minions.length) ? i+1 : -1;
            int previousIndex = ( i-1 >= 0)? i-1 : -1;
            if (nextIndex > -1) {
            minion.setNext(minions[nextIndex]);
            }
            if (previousIndex > -1) {
            minion.setPrevious(minions[previousIndex]);
            }
        }
    }
    
    public static void reassembleMinion(Minion[] minions) {
        for (int i = 0; i < minions.length; i++) {
            minions[i].setPosition(i);
        }
    } 
    
}
