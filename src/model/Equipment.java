/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
            double mult = 1;
           switch(grade) {
                        case COMMON: 
                          mult = EquipmentGrade.COMMON.getMultipiler();
                        break;
                        case UNCOMMON:
                          mult = EquipmentGrade.UNCOMMON.getMultipiler();  
                        break;
                        case RARE:
                          mult = EquipmentGrade.RARE.getMultipiler();  
                        break;
                        case EPIC:
                          mult = EquipmentGrade.EPIC.getMultipiler();  
                        break;
                        case LEGENDARY:
                          mult = EquipmentGrade.LEGENDARY.getMultipiler();  
                        break;
                        case MYTHIC:
                          mult = EquipmentGrade.MYTHIC.getMultipiler();  
                        break;
            }
           return mult;
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
            switch(grade) {
                        case COMMON: 
                          incrementedgrade = EquipmentGrade.UNCOMMON;
                        break;
                        case UNCOMMON:
                          incrementedgrade = EquipmentGrade.RARE;  
                        break;
                        case RARE:
                          incrementedgrade = EquipmentGrade.EPIC;  
                        break;
                        case EPIC:
                          incrementedgrade = EquipmentGrade.LEGENDARY;  
                        break;
                        case LEGENDARY:
                          incrementedgrade = EquipmentGrade.MYTHIC;  
                        break;
                        case MYTHIC:
                          incrementedgrade = EquipmentGrade.MYTHIC;  
                        break;
            }
            return incrementedgrade;
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

    public static int getEquipmentGradeIndex(EquipmentGrade grade) {
                        switch(grade) {
                        case COMMON: 
                            return EquipmentGrade.COMMON_INDEX;
                        case UNCOMMON:
                            return EquipmentGrade.UNCOMMON_INDEX;
                        case RARE:
                            return EquipmentGrade.RARE_INDEX;
                        case EPIC:
                            return EquipmentGrade.EPIC_INDEX;
                        case LEGENDARY:
                            return EquipmentGrade.LEGENDARY_INDEX;
                        case MYTHIC:
                            return EquipmentGrade.MYTHIC_INDEX;
                        default:
                            return -1;    
            }
    }
    
        public static EquipmentGrade getEquipmentGrade(int index) {
                        switch(index) {
                        case 1: 
                            return EquipmentGrade.COMMON;
                        case 2:
                            return EquipmentGrade.UNCOMMON;
                        case 3:
                            return EquipmentGrade.RARE;
                        case 4:
                            return EquipmentGrade.EPIC;
                        case 5:
                            return EquipmentGrade.LEGENDARY;
                        case 6:
                            return EquipmentGrade.MYTHIC;
                        default:
                            return null;    
            }
    }
               
    public static long getIncreaseEquipmentLevelPrice(int equipmentlevel,EquipmentGrade grade) {
        long price = 0;
    if (equipmentlevel < MAX_EQUIPMENT_LEVEL) {
            switch(grade) {
                        case COMMON: 
                          price = Equipment.calculateUpgradePrice(equipmentlevel, EquipmentGrade.COMMON.getLvcost(), EquipmentGrade.PRICE_INCREASE_PER_LEVEL_MULT);
                        break;
                        case UNCOMMON:
                          price = Equipment.calculateUpgradePrice(equipmentlevel, EquipmentGrade.UNCOMMON.getLvcost(), EquipmentGrade.PRICE_INCREASE_PER_LEVEL_MULT);  
                        break;
                        case RARE:
                          price = Equipment.calculateUpgradePrice(equipmentlevel, EquipmentGrade.RARE.getLvcost(), EquipmentGrade.PRICE_INCREASE_PER_LEVEL_MULT);  
                        break;
                        case EPIC:
                          price = Equipment.calculateUpgradePrice(equipmentlevel, EquipmentGrade.EPIC.getLvcost(), EquipmentGrade.PRICE_INCREASE_PER_LEVEL_MULT);
                        break;
                        case LEGENDARY:
                          price = Equipment.calculateUpgradePrice(equipmentlevel, EquipmentGrade.LEGENDARY.getLvcost(), EquipmentGrade.PRICE_INCREASE_PER_LEVEL_MULT);
                        break;
                        case MYTHIC:
                          price = Equipment.calculateUpgradePrice(equipmentlevel, EquipmentGrade.MYTHIC.getLvcost(), EquipmentGrade.PRICE_INCREASE_PER_LEVEL_MULT);
                        break;
            }
        }
    return price;
    }
    
    private static long calculateUpgradePrice(int equipmentlevel,int gradeCost,double multipiler) {
        double price = (double) gradeCost;
        for (int i = equipmentlevel; i > 1; i--) {
            price *= multipiler;
        }
        return (long) Math.round(price);
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
    
    
}
