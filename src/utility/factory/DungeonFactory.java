/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.factory;

import utility.factory.MinionFactory;
import core.Battlefield;
import core.Dungeon;
import java.util.Random;
import model.ClassIndex;
import model.Minion;

/**
 *
 * @author COM
 */
public class DungeonFactory {
    
        public static final int EASY_INDEX = 1;
        public static final int MEDIUM_INDEX = 2;
        public static final int HARD_INDEX = 3;
        
        public static final int BASE_REWARD = 100; 
        
        private static final int RANGE_DEVIATION = 3;
        private static final int LENGTH_DEVIATION = 3;
        
        private static final int EASY_RANGE = 5;
        private static final int EASY_LENGTH = 5;
        private static final double EASY_REWARD_MULTIPILER = 1;
        private static final int MEDIUM_RANGE = 10;
        private static final int MEDIUM_LENGTH = 10;
        private static final double MEDIUM_REWARD_MULTIPILER = 1.2;
        private static final int HARD_RANGE = 25;
        private static final int HARD_LENGTH = 15;
        private static final double HARD_REWARD_MULTIPILER = 1.5;
        
        
        public static void main(String[] args) {
        Dungeon d = DungeonFactory.createDungeon(EASY_INDEX);
    }
        
        public static Dungeon createDungeon(int index) {
            switch (index) {
                case EASY_INDEX:
                    return assembleDungeon(EASY_RANGE,EASY_LENGTH,EASY_REWARD_MULTIPILER,EASY_INDEX);
                case MEDIUM_INDEX:
                    return assembleDungeon(MEDIUM_RANGE,MEDIUM_LENGTH,MEDIUM_REWARD_MULTIPILER,MEDIUM_INDEX);
                case HARD_INDEX:
                    return assembleDungeon(HARD_RANGE,HARD_LENGTH,HARD_REWARD_MULTIPILER,HARD_INDEX);
            default:
                return null;
            }
        }
        
        private static Dungeon assembleDungeon(int levelrange,int length,double rewardMult,int token) {
            int ranlength = randomWithDeviation(length,LENGTH_DEVIATION);
            Battlefield[] bats = new Battlefield[ranlength];
            for (int i = 0; i < bats.length ; i++) {
                Minion[] minions = new Minion[4];
                for (int j = 0; j < minions.length; j++) {
                   int classindex = randomBetweenNum(ClassIndex.GOBLIN_INDEX,ClassIndex.ORC_INDEX);
                   int level = randomWithDeviation(levelrange,RANGE_DEVIATION);
                   minions[j] = MinionFactory.createMinion(classindex,level);
                }
                bats[i] = new Battlefield(minions);
            }
            long reward = Math.round(ranlength*(BASE_REWARD*rewardMult));
            long score = reward;
            return new Dungeon(bats,reward,score,token);
        }
        
        private static int randomBetweenNum(int low,int high) {
            //throw exception here
             Random r = new Random();
             return r.nextInt(high-low) + low;
        }
        
        private static int randomWithDeviation(int num,int deviation) {
            return randomBetweenNum(num-deviation,num+deviation);
        }
        
        
        
}
