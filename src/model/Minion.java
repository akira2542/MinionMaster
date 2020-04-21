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
        this.level = 1;
        this.equipmentgrade[0] = 1;
        this.equipmentgrade[1] = 1;
        this.primarystatus = PrimaryStatus.ALIVE;
        
        this.basehealthpoint = basehelthpoint;
        this.basemanapoint = basemanapoint;
        this.baseattackpoint = baseattackpoint;
        this.basearmor = basearmor;
        this.baseevasion = baseevasion;
        
        this.maxhealthpoint = basehelthpoint;
        this.maxmanapoint = basehelthpoint;
        
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
    
    protected boolean evasionCheck(double evasion,double accuracy) {
        double echance = (accuracy-evasion)/accuracy;
        boolean hit = false;
        for (int i = 0; i < 1; i++) {
            double rand = Math.random();
              String.format("value is %.1f", rand) ;  
            if(rand<=echance){
                System.out.println("attack miss");
                hit = false;
            }else {System.out.println("attack hit");
            hit = true;}
        }
        return hit;
    }
    
    
    protected double armorCheck(double attackpoint) {
    return attackpoint;
    }
    
    public void levelUp() {
        this.maxhealthpoint *= this.levelmultiplier[0];
        this.maxmanapoint *= this.levelmultiplier[1];
        this.attackpoint *= this.levelmultiplier[2];
        this.armor *= this.levelmultiplier[3];
        this.evasion *= this.levelmultiplier[4];
        this.refresh();
        this.level++;
    }
    
    public void setLevel(int level) {
        if (this.level > level) {
            System.out.println("this minion level exceed that number");
        }
        while(this.level < level) {
        this.levelUp();
        }
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
    
    public void refresh() {
    this.healthpoint = this.maxhealthpoint;
    this.manapoint = this.maxmanapoint;
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
    
}
