/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import content.Goblin;
import content.HealerUnit;
import content.TankUnit;
import model.Minion;

/**
 *
 * @author COM
 */
public class MinionFactory {
    
    public static final int TANK = 101;
    public static final int HEALER = 102;
    
    public static final int GOBLIN = 201;
    
    public static Minion createPlayableMinion(int classIndex) {
        switch(classIndex) {
            case TANK:
                return new TankUnit();
            case HEALER:
                return new HealerUnit();
            default:
                return null;
        }
    }
    
    public static Minion createMinion(int classIndex) {
                switch(classIndex) {
            case GOBLIN:
                return new Goblin();
            default:
                return null;
        }
    }
    
    public static Minion createMinion(int classIndex,int level) {
        Minion m;
            switch(classIndex) {
            case GOBLIN:
                m = MinionFactory.createMinion(classIndex);
                m.setLevel(level);
                return m;
            default:
                return null;
        }
    }

}
