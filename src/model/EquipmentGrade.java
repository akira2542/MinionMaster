/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author COM
 */
public enum EquipmentGrade {
    COMMON,UNCOMMON,RARE,EPIC,LEGENDARY,MYTHIC;
    
    public static double PRICE_INCREASE_PER_LEVEL_MULT = 1.2;
    
    public static int COMMON_INDEX = 1;
    public static double COMMON_MULT = 1.1;
    public static int COMMON_COST = 100;

    public static int UNCOMMON_INDEX = 2;
    public static double UNCOMMON_MULT = 1.5;
    public static int UNCOMMON_COST = 250;
    public static int UNCOMMON_TOKEN_NEEDED = 10;
    
    public static int RARE_INDEX = 3;
    public static double RARE_MULT = 1.8;
    public static int RARE_COST = 500;
    public static int RARE_TOKEN_NEEDED = 25;
    
    public static int EPIC_INDEX = 3;
    public static double EPIC_MULT = 2.5;
    public static int EPIC_COST = 850;
    public static int EPIC_TOKEN_NEEDED = 60;
        
    public static int LEGENDARY_INDEX = 4;
    public static double LEGENDARY_MULT = 3.0;
    public static int LEGENDARY_COST = 1200;
    public static int LEGENDARY_TOKEN_NEEDED = 100;
    
    public static int MYTHIC_INDEX = 4;
    public static double MYTHIC_MULT = 4.0; 
    public static int MYTHIC_COST = 2000;
    public static int MYTHIC_TOKEN_NEEDED = 200;
}
