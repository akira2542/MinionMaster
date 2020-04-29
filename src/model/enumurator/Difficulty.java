/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.enumurator;

/**
 *
 * @author COM
 */
public enum Difficulty {
        EASY(1,5,5,1.0),MEDIUM(2,10,10,1.2),HARD(3,25,15,1.5);
    
        private final int index;
        private final int lvrange;
        private final int dungeonlength;
        private final double rewardmult;
        
        Difficulty(final int nindex,final int nlvrange,final int ndungeonlength,final double nrewardmult){
        index = nindex;
        lvrange = nlvrange;
        dungeonlength = ndungeonlength;
        rewardmult = nrewardmult;
        }

    public int getIndex() {
        return index;
    }

    public int getLvrange() {
        return lvrange;
    }

    public int getDungeonlength() {
        return dungeonlength;
    }

    public double getRewardmult() {
        return rewardmult;
    }
        
        
    
        public static final int EASY_INDEX = 1;
        public static final int MEDIUM_INDEX = 2;
        public static final int HARD_INDEX = 3;
        
        public static final int BASE_REWARD = 100; 
        
        public static final int RANGE_DEVIATION = 3;
        public static final int LENGTH_DEVIATION = 3;
        
//        private static final int EASY_RANGE = 5;
//        private static final int EASY_LENGTH = 5;
//        private static final double EASY_REWARD_MULTIPILER = 1;
//        private static final int MEDIUM_RANGE = 10;
//        private static final int MEDIUM_LENGTH = 10;
//        private static final double MEDIUM_REWARD_MULTIPILER = 1.2;
//        private static final int HARD_RANGE = 25;
//        private static final int HARD_LENGTH = 15;
//        private static final double HARD_REWARD_MULTIPILER = 1.5;
}
