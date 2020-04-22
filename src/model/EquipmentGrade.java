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
    
    public double PRICE_INCREASE_PER_LEVEL_MULT = 1.2;
    
    public double COMMON_MULT = 1.1;
    public int COMMON_COST = 100;
    
    public double UNCOMMON_MULT = 1.5;
    public int UNCOMMON_COST = 250;
    
    public double RARE_MULT = 1.8;
    public int RARE_COST = 500;
    
    public double EPIC_MULT = 2.5;
    public int EPIC_COST = 850;
        
    public double LEGENDARY_MULT = 3.0;
    public int LEGENDARY_COST = 1200;
    
    public double MYTHIC_MULT = 4.0; 
    public int MYTHIC_COST = 2000;
}
