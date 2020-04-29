/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.resource;

import model.enumurator.EquipmentGrade;
import java.io.Serializable;

/**
 *
 * @author COM
 */
public class Equipment implements Serializable {
    
    public static final int MAX_EQUIPMENT_LEVEL = 10;
    public static final int WEAPON_INDEX = 0;
    public static final int ARMOR_INDEX = 1;
    
    private EquipmentGrade weaponGrade;
    private EquipmentGrade armorGrade;
    private int weaponlv;
    private int armorlv;
    
    
    public Equipment() {
        this.weaponGrade = EquipmentGrade.COMMON;
        this.armorGrade = EquipmentGrade.COMMON;
        this.weaponlv = 1;
        this.armorlv = 1;
    }
    
    public Equipment(int weaponlevel,EquipmentGrade weapongrade,int armorlevel,EquipmentGrade armorgrade) {
        this.weaponGrade = weapongrade;
        this.armorGrade = armorgrade;
        this.weaponlv = weaponlevel;
        this.armorlv = armorlevel;
    }
    
    public double getEquipmentMultipiler(int index) {
        double mult = 1;
        if (index == WEAPON_INDEX) {
             mult = this.findEquipmentGradeMult(weaponGrade)*this.weaponlv;
        } else if (index == ARMOR_INDEX) {
             mult = this.findEquipmentGradeMult(armorGrade)*this.armorlv;
        }    
        return mult;
    }
    
    private double findEquipmentGradeMult(EquipmentGrade grade) {
        return grade.getMultipiler();
    }
    
    public void increaseEquiptmentLevel(int index) {
        if (index == WEAPON_INDEX) {
            this.weaponlv++;
        } else if (index == ARMOR_INDEX){
            this.armorGrade = incrementGrade(armorGrade);
            this.armorlv++;
        }
    
    }
    
    public void advanceUquipmentGrade(int index) {
        //if the grade is advanced weapon/armor level will be reset to 1
        if (index == WEAPON_INDEX) {
            this.weaponGrade = incrementGrade(weaponGrade);
            this.weaponlv = 1;
        } else if (index == ARMOR_INDEX){
            this.armorGrade = incrementGrade(armorGrade);
            this.armorlv = 1;
        }
    }
 
    private EquipmentGrade incrementGrade(EquipmentGrade grade) {
            EquipmentGrade incrementedgrade = grade;
            int nextindex = grade.getIndex() + 1;
            if (nextindex <= EquipmentGrade.MYTHIC.getIndex()) {
            return EquipmentGrade.getEquipmentGrade(nextindex);
            }
            System.out.println("Error incrementing grade");
            return null;
    }

    public static int getTokenUpgradePrice(EquipmentGrade grade) {
            switch(grade) {
                        case COMMON: 
                            return EquipmentGrade.UNCOMMON.getTokenneeded();
                        case UNCOMMON:
                            return EquipmentGrade.RARE.getTokenneeded();
                        case RARE:
                            return EquipmentGrade.EPIC.getTokenneeded();
                        case EPIC:
                            return EquipmentGrade.LEGENDARY.getTokenneeded();
                        case LEGENDARY:
                            return EquipmentGrade.LEGENDARY.getTokenneeded();
                        case MYTHIC:
                            return 0;
                        default:
                            return -1;    
            }
    }
    
               
    public static long getIncreaseEquipmentLevelPrice(int equipmentlevel,EquipmentGrade grade) {
    if (equipmentlevel < MAX_EQUIPMENT_LEVEL) {
        double price = (double) grade.getLvcost();
        for (int i = equipmentlevel; i > 1; i--) {
            price *= EquipmentGrade.PRICE_INCREASE_PER_LEVEL_MULT;
        }
        return (long) Math.round(price);
        }
    return 0;
    }
   
    
    public boolean upgradeArmor() {
    if (this.armorlv <= MAX_EQUIPMENT_LEVEL) {
        this.armorlv++;
        return true;
        }
    return false;
    }
    
    public boolean upgradeWeapon() {
        if (this.weaponlv <= MAX_EQUIPMENT_LEVEL) {
        this.weaponlv++;
        return true;
        }
    return false;
    }

    public EquipmentGrade getArmorGrade() {
        return armorGrade;
    }

    public EquipmentGrade getWeaponGrade() {
        return weaponGrade;
    }

    public int getWeaponlv() {
        return weaponlv;
    }

    public int getArmorlv() {
        return armorlv;
    }

    @Override
    public String toString() {
        return "Equipment{" + "weaponGrade=" + weaponGrade + ", armorGrade=" + armorGrade + ", weaponlv=" + weaponlv + ", armorlv=" + armorlv + '}';
    }
    
    
}
