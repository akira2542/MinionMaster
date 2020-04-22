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
    public double COMMON_MULT = 1.1;
    public double UNCOMMON_MULT = 1.5;
    public double RARE_MULT = 1.8;
    public double EPIC_MULT = 2.5;
    public double LEGENDARY_MULT = 3.0;
    public double MYTHIC_MULT = 4.0; 
}
