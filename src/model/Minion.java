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
public abstract class Minion implements Serializable {
    
    private String name;
    
    private int level;
    private int[] equipmentgrade; 
    
    private Double maxhealthpoint;
    private Double maxmagicpoint;
    private Double healthpoint;
    private Double magicpoint;
    private Double[] statusmultiplier;
    
    private PrimaryStatus primarystatus;
    private SecondaryStatus secondarystatus;
    
    abstract void attack();
    
    public Minion(String name) {
    this.name = name;
    this.equipmentgrade = new int[2];
    this.equipmentgrade[0] = 1;
    this.equipmentgrade[1] = 1;
    }
    
    public void levelUp() {    
    }
    
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int[] getEquipmentgrade() {
        return equipmentgrade;
    }

    public Double getMaxhealthpoint() {
        return maxhealthpoint;
    }

    public Double getMaxmagicpoint() {
        return maxmagicpoint;
    }

    public Double getHealthpoint() {
        return healthpoint;
    }

    public Double getMagicpoint() {
        return magicpoint;
    }

    public PrimaryStatus getPrimarystatus() {
        return primarystatus;
    }

    public SecondaryStatus getSecondarystatus() {
        return secondarystatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEquipmentgrade(int[] equipmentgrade) {
        this.equipmentgrade = equipmentgrade;
    }

    public void setHealthpoint(Double healthpoint) {
        this.healthpoint = healthpoint;
    }

    public void setMagicpoint(Double magicpoint) {
        this.magicpoint = magicpoint;
    }

    public void setPrimarystatus(PrimaryStatus primarystatus) {
        this.primarystatus = primarystatus;
    }

    public void setSecondarystatus(SecondaryStatus secondarystatus) {
        this.secondarystatus = secondarystatus;
    }
}
