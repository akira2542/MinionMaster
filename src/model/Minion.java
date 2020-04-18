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
    
     // final stat
    private final String name;
    private final double basehealthpoint;
    private final double basemanapoint;
    private final double baseattackpoint;
    private final double basearmor;
    private final double baseevasion;
    
    //fixed size array final
    private final double[] levelmultiplier = new double[5];
    
    //changeble stat
    private int position;
    private int level;
    private double maxhealthpoint;
    private double maxmanapoint;
    private double healthpoint;
    private double manapoint;
    private double attackpoint;
    private double armor;
    private double evasion;
    private PrimaryStatus primarystatus;
    private SecondaryStatus secondarystatus;
    
    
    //fixed size array
    private int[] equipmentgrade = new int[2]; 
    
    //contructor
        public Minion(int position,String name,double basehelthpoint, double basemanapoint,double baseattackpoint,double basearmor, double baseevasion,double[] levelmultiplier) {
        
        this.position = position;
        this.name = name;
        this.equipmentgrade = new int[2];
        this.equipmentgrade[0] = 1;
        this.equipmentgrade[1] = 1;
        this.primarystatus = PrimaryStatus.ALIVE;
        
        this.basehealthpoint = basehelthpoint;
        this.basemanapoint = basemanapoint;
        this.baseattackpoint = baseattackpoint;
        this.basearmor = basearmor;
        this.baseevasion = baseevasion;
        
        this.healthpoint = basehelthpoint;
        this.manapoint = basemanapoint;
        this.attackpoint = baseattackpoint;
        this.armor = basearmor;
        this.evasion = baseevasion;
        
        System.arraycopy(levelmultiplier, 0, this.levelmultiplier, 0, this.levelmultiplier.length);
        
    }
    
    
    //abstract method
    protected abstract void useSkillOn(Minion minion);
    public abstract void actionDecider(Minion[] ourpaty,Minion[] enemyparty);
    
    //preconfigured method
    public void attackOn(Minion minion){
        if (!minion.evasionCheck()){
               double deducteddamage = minion.armorCheck(this.attackpoint);
               minion.setHealthpoint(minion.getHealthpoint()- deducteddamage);
               System.out.println(this.name+"("+this.getPosition()+")"+" attacked "+minion.name+"("+minion.getPosition()+")"+" for "+deducteddamage+" damage");
               if (minion.getHealthpoint() <= 0) { 
                   minion.setPrimarystatus(PrimaryStatus.DEAD); 
                   System.out.println(minion.getName()+ "("+minion.getPosition()+")" + " is Dead!");
                }
        } else {
            System.out.println("Attack Missed!");
        }
    }
    
    public void attackOn(Minion minion,int SkillDamage) {
    }
    
    protected boolean evasionCheck() {
    return false;
    }
    
    protected double armorCheck(double attackpoint) {
    return attackpoint;
    }
    
    private void systemSetLevel(int amount) {
    
    }
    
    public int getPosition() {
    return position;
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

    public Double getMaxmanapoint() {
        return maxmanapoint;
    }

    public Double getHealthpoint() {
        return healthpoint;
    }

    public Double getManapoint() {
        return manapoint;
    }

    public PrimaryStatus getPrimarystatus() {
        return primarystatus;
    }

    public SecondaryStatus getSecondarystatus() {
        return secondarystatus;
    }
    public void setEquipmentgrade(int[] equipmentgrade) {
        this.equipmentgrade = equipmentgrade;
    }

    protected void setHealthpoint(Double healthpoint) {
        this.healthpoint = healthpoint;
    }

    private void setManapoint(Double manapoint) {
        this.manapoint = manapoint;
    }

    protected void setPrimarystatus(PrimaryStatus primarystatus) {
        this.primarystatus = primarystatus;
    }

    protected void setSecondarystatus(SecondaryStatus secondarystatus) {
        this.secondarystatus = secondarystatus;
    }
}
