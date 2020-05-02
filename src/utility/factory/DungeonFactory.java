/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.factory;

import utility.factory.MinionFactory;
import core.resource.Battlefield;
import core.resource.Dungeon;
import java.util.Random;
import model.enumurator.ClassIndex;
import model.enumurator.Difficulty;
import model.Minion;

/**
 *
 * @author COM
 */
public class DungeonFactory {
    
        
        
        public static Dungeon createDungeon(int index) {
            switch (index) {
                case Difficulty.EASY_INDEX:
                    return assembleDungeon(Difficulty.EASY.getLvrange(),Difficulty.EASY.getDungeonlength(),Difficulty.EASY.getRewardmult(),Difficulty.EASY.getIndex());
                case Difficulty.MEDIUM_INDEX:
                    return assembleDungeon(Difficulty.MEDIUM.getLvrange(),Difficulty.MEDIUM.getDungeonlength(),Difficulty.MEDIUM.getRewardmult(),Difficulty.MEDIUM.getIndex());
                case Difficulty.HARD_INDEX:
                    return assembleDungeon(Difficulty.HARD.getLvrange(),Difficulty.HARD.getDungeonlength(),Difficulty.HARD.getRewardmult(),Difficulty.HARD.getIndex());
            default:
                return null;
            }
        }
        public static Dungeon createDungeon(Difficulty difficulty) {
        return assembleDungeon(difficulty.getLvrange(),difficulty.getDungeonlength(),difficulty.getRewardmult(),difficulty.getIndex());
        }
        
        private static Dungeon assembleDungeon(int levelrange,int length,double rewardMult,int token) {
            int ranlength = randomWithDeviation(length,Difficulty.LENGTH_DEVIATION);
            Battlefield[] bats = new Battlefield[ranlength];
            for (int i = 0; i < bats.length ; i++) {
                Minion[] minions = new Minion[4];
                for (int j = 0; j < minions.length; j++) {
                   int classindex = randomBetweenNum(ClassIndex.GOBLIN_INDEX,ClassIndex.ORC_INDEX);
                   int level = randomWithDeviation(levelrange,Difficulty.RANGE_DEVIATION);
                   minions[j] = MinionFactory.createMinion(classindex,level);
                }
                MinionFactory.reassembleMinion(minions);
                bats[i] = new Battlefield(minions);
            }
            long reward = Math.round(ranlength*(Difficulty.BASE_REWARD*rewardMult));
            long score = reward;
            return new Dungeon(bats,reward,score,token);
        }
        
        private static int randomBetweenNum(int low,int high) {
             high++;
             Random r = new Random();
             return r.nextInt(high-low) + low;
        }
        
        private static int randomWithDeviation(int num,int deviation) {
            return randomBetweenNum(num-deviation,num+deviation);
        }
        
}
