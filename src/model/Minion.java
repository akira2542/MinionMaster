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
    private static String name;
    private static double BASEHP;
    private static double BASEMP;
    private static double BASEAP;
    private static double BASEARMOR;
    private static double BASEEVA;
    private static double BASEACC;
    private static LevelMultipiler MULT;
    
    //changeble stat
    private int position;
    private int level;
    private double maxhealthpoint;
    private double maxmanapoint;
    private double healthpoint;
    private double manapoint;
    private double rawattackpoint;
    private double rawarmor;
    private double evasion;
    private double accuracy;
    private PrimaryStatus primarystatus;
    private SecondaryStatus secondarystatus;
    private Equipment equipment; 
    
    
    
    //contructor
        public Minion(int position,String Mname,double basehelthpoint, double basemanapoint,double baseattackpoint,double basearmor, double baseevasion,double baseaccuracy,LevelMultipiler mult) {
        
        MULT = mult;
        BASEHP = basehelthpoint;
        BASEMP = basemanapoint;
        BASEAP = baseattackpoint;
        BASEARMOR = basearmor;
        BASEEVA = baseevasion;
        BASEACC = baseaccuracy;
        name = Mname;
        
        // new minnion will start with level 1 and common lv 1 equipment 
        this.position = position;
        this.level = 1;
        this.primarystatus = PrimaryStatus.ALIVE;
        this.equipment = new Equipment();
        
        this.maxhealthpoint = basehelthpoint;
        this.maxmanapoint = basehelthpoint;
        
        this.healthpoint = basehelthpoint;
        this.manapoint = basemanapoint;
        this.rawattackpoint = baseattackpoint;
        this.rawarmor = basearmor;
        this.evasion = baseevasion;
        
    }
    
    
    //abstract method
    protected abstract void useSkillOn(Minion minion);
    public abstract void actionDecider(Minion[] ourpaty,Minion[] enemyparty);
    
    //preconfigured method
    protected void attackOn(Minion enemie){
        if (evasionCheck(this.accuracy)) {
            double deductedDmg = enemie.armorCheck(enemie.getCalculatedAttackPoint());
            enemie.healthpoint -= deductedDmg;
            System.out.println(this.getName()+"("+this.getPosition()+")"+" attacked "+enemie.getName()+"("+enemie.getPosition()+")"+" Deals "+deductedDmg+" damage" + "reduce it helth to " + enemie.getHealthpoint());
            if (enemie.getHealthpoint() <= 0) {
                enemie.primarystatus = PrimaryStatus.DEAD;
                System.out.println(enemie.name+"("+this.getPosition()+")"+" is Dead!");
            }   
        }else {
            System.out.println("Attack Missed!");
        }
    }
    
    protected void attackOn(Minion minion,int SkillDamage) {
    }
    
    private boolean evasionCheck(double accuracy) {
        double echance = (accuracy-this.evasion)/accuracy;
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
    
    
    private double armorCheck(double OpponentAP) {
        return Math.round(OpponentAP - ((OpponentAP/100)*this.getCalculatedArmor()));
    }
    
    public void levelUp() {
        this.maxhealthpoint *= MULT.getHP_MULT();
        this.maxmanapoint *= MULT.getMP_MULT();
        this.rawattackpoint *= MULT.getAP_MULT();
        this.rawarmor *= MULT.getARMOR_MULT();
        this.evasion *= MULT.getEVA_MULT();
        this.accuracy *= MULT.getACC_MULT();
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
    

    protected void setHealthpoint(Double healthpoint) {
        this.healthpoint = healthpoint;
    }

    protected void setManapoint(Double manapoint) {
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

    public double getAccuracy() {
        return accuracy;
    }
    
    
    
    public double getCalculatedAttackPoint() {
        //rawap*weapongrademultipiler
    return this.rawattackpoint * (this.equipment.getEquipmentMultipiler(Equipment.WEAPON_INDEX));
    }
    
    public double getCalculatedArmor() {
        //rawarmor*armorgrade
        return this.rawarmor* (this.equipment.getEquipmentMultipiler(Equipment.ARMOR_INDEX));
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
