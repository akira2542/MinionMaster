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
public enum EquipmentGrade {
    COMMON(1,1.1,100,0),UNCOMMON(2,15,250,10),RARE(3,1.8,500,25),EPIC(4,2.5,850,60),LEGENDARY(5,3,1200,100),MYTHIC(6,4,2000,200);
    private final int index;
    private final double multipiler;
    private final int lvcost;
    private final int tokenneeded;
    
    public static final double PRICE_INCREASE_PER_LEVEL_MULT = 1.2;
    public static final int COMMON_INDEX = 1;
    public static final int UNCOMMON_INDEX = 2;
    public static final int RARE_INDEX = 3;
    public static final int EPIC_INDEX = 4;
    public static final int LEGENDARY_INDEX = 5;
    public static final int MYTHIC_INDEX = 6;
   
    EquipmentGrade(final int nindex,final double nmultipiler,final int nlvcost,final int ntokenneeded) {
    index = nindex;
    multipiler = nmultipiler;
    lvcost = nlvcost;
    tokenneeded = ntokenneeded;
    }

    public int getIndex() {
        return index;
    }

    public double getMultipiler() {
        return multipiler;
    }

    public int getLvcost() {
        return lvcost;
    }

    public int getTokenneeded() {
        return tokenneeded;
    }
    
    public static int getEquipmentGradeIndex(EquipmentGrade grade) {
        return grade.getIndex();
    }
    
    public static EquipmentGrade getEquipmentGrade(int index) {
                        switch(index) {
                        case COMMON_INDEX: 
                            return EquipmentGrade.COMMON;
                        case UNCOMMON_INDEX:
                            return EquipmentGrade.UNCOMMON;
                        case RARE_INDEX:
                            return EquipmentGrade.RARE;
                        case EPIC_INDEX:
                            return EquipmentGrade.EPIC;
                        case LEGENDARY_INDEX:
                            return EquipmentGrade.LEGENDARY;
                        case MYTHIC_INDEX:
                            return EquipmentGrade.MYTHIC;
                        default:
                            return null; 
                        }
    }
    

//    public static final int COMMON_INDEX = 1;
//    public static double COMMON_MULT = 1.1;
//    public static int COMMON_COST = 100;
//    public static final int UNCOMMON_INDEX = 2;
//    public static double UNCOMMON_MULT = 1.5;
//    public static int UNCOMMON_COST = 250;
//    public static int UNCOMMON_TOKEN_NEEDED = 10;
//    public static final int RARE_INDEX = 3;
//    public static double RARE_MULT = 1.8;
//    public static int RARE_COST = 500;
//    public static int RARE_TOKEN_NEEDED = 25;
//    public static final int EPIC_INDEX = 4;
//    public static double EPIC_MULT = 2.5;
//    public static int EPIC_COST = 850;
//    public static int EPIC_TOKEN_NEEDED = 60;  
//    public static final int LEGENDARY_INDEX = 5;
//    public static double LEGENDARY_MULT = 3.0;
//    public static int LEGENDARY_COST = 1200;
//    public static int LEGENDARY_TOKEN_NEEDED = 100;
//    public static final int MYTHIC_INDEX = 6;
//    public static double MYTHIC_MULT = 4.0; 
//    public static int MYTHIC_COST = 2000;
//    public static int MYTHIC_TOKEN_NEEDED = 200;
}
