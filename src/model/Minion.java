/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.resource.LevelMultipiler;
import core.resource.Equipment;
import model.enumurator.PrimaryStatus;
import model.enumurator.SecondaryStatus;
import exception.NullStatusException;
import java.io.Serializable;
import utility.TimeStopper;

/**
 *
 * @author COM
 */
public abstract class Minion implements Serializable {
    
    private final String name;
    private final double BASEHP;
    private final double BASEMP;
    private final double BASEAP;
    private final double BASEARMOR;
    private final double BASEEVA;
    private final double BASEACC;
    private final LevelMultipiler MULT;
    private final int CLASS_INDEX;
    
    //changeble stat
    private int position;
    private int level;
    private double maxhealthpoint;
    private double manapoint;
    private double rawattackpoint;
    private double rawarmor;
    private double evasion;
    private double accuracy;
    private PrimaryStatus primarystatus;
    private SecondaryStatus secondarystatus;
    private Equipment equipment; 
    
    //battle relatedvariable
    private double maxmanapoint;
    private double healthpoint;

    
    
    
    //contructor
        public Minion(int index,String Mname,double basehelthpoint, double basemanapoint,double baseattackpoint,double basearmor, double baseevasion,double baseaccuracy,LevelMultipiler mult) {
        
        CLASS_INDEX = index;    
        MULT = mult;
        BASEHP = basehelthpoint;
        BASEMP = basemanapoint;
        BASEAP = baseattackpoint;
        BASEARMOR = basearmor;
        BASEEVA = baseevasion;
        BASEACC = baseaccuracy;
        name = Mname;
        
        // new minnion will start with level 1 and common lv 1 equipment 
        this.level = 1;
        this.primarystatus = PrimaryStatus.ALIVE;
        this.secondarystatus = SecondaryStatus.STABLE;
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
    public abstract void actionDecider(Minion[] ourparty,Minion[] enemyparty);
    
    //preconfigured method
    protected void attackOn(Minion enemy,double skillDamage){
        if (evasionCheck(this.accuracy)) {
            double deductedDmg = enemy.armorCheck(skillDamage);
            enemy.healthpoint -= deductedDmg;
            System.out.println("lv."+this.getLevel()+" "+this.getName()+"("+this.getPosition()+")"+" attacked "+"lv."+enemy.getLevel()+" "+enemy.getName()+"("+enemy.getPosition()+")"+" Deals "+deductedDmg+" damage, " + "reduce it's health to " + enemy.getHealthpoint());
           
            if (enemy.getHealthpoint() <= 0) {
                enemy.primarystatus = PrimaryStatus.DEAD;
                System.out.println("lv."+enemy.getLevel()+" "+enemy.name+"("+enemy.getPosition()+")"+" is Dead!");
            }   
        }else {
            System.out.println("lv."+this.getLevel()+" "+this.getName()+"("+this.getPosition()+")"+" attacked "+"lv."+enemy.getLevel()+" "+enemy.getName()+"("+enemy.getPosition()+") but missed" );
            
        } 
        TimeStopper.Delay(1);
    }
    
    protected void attackOn(Minion minion) {
        attackOn(minion,this.getCalculatedAttackPoint());
    }
    
    private boolean evasionCheck(double accuracy) {
        double echance = (accuracy-this.evasion)/accuracy;
        boolean hit = false;
        for (int i = 0; i < 1; i++) {
            double rand = Math.random();
              hit = rand > echance;
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
    


    
    public void setEquipment(Equipment equipment) {
     this.equipment = equipment;   
    }    

    public void setHealthpoint(Double healthpoint) {
        this.healthpoint = healthpoint;
    }

    protected void setManapoint(Double manapoint) {
        this.manapoint = manapoint;
    }

    protected void setPrimarystatus(PrimaryStatus primarystatus) {
        this.primarystatus = primarystatus;
    }

    public void setSecondarystatus(SecondaryStatus secondarystatus) {
        this.secondarystatus = secondarystatus;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    public void refresh() {
    this.healthpoint = this.maxhealthpoint;
    this.manapoint = this.maxmanapoint;
    this.secondarystatus = SecondaryStatus.STABLE;
    this.primarystatus = PrimaryStatus.ALIVE;
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

    public Equipment getEquipment() {
        return equipment;
    }

    public int getCLASS_INDEX() {
        return CLASS_INDEX;
    }
    
    public boolean isStunned(){
        try {
        switch(this.secondarystatus) {
            case STUNNED:
                this.secondarystatus = SecondaryStatus.STABLE;
                return true;
            case STABLE:
                return false;
            default:
                throw new NullStatusException();
        }
        }catch (NullStatusException ex) {
        ex.printStackTrace();
        }
        return false;
    }
    
    

    @Override
    public String toString() {
        return "Minion{" + "name=" + name + ", position=" + position + ", level=" + level + ", maxhealthpoint=" + maxhealthpoint + ", maxmanapoint=" + maxmanapoint + ", healthpoint=" + healthpoint + ", manapoint=" + manapoint + ", rawattackpoint=" + rawattackpoint + ", rawarmor=" + rawarmor + ", evasion=" + evasion + ", accuracy=" + accuracy + ", primarystatus=" + primarystatus + ", secondarystatus=" + secondarystatus + ", equipment=" + equipment + '}';
    }
    
    
}
