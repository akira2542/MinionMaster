/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.resource;

import java.io.Serializable;

/**
 *
 * @author COM
 */
public class LevelMultipiler implements Serializable {
    private double HP_MULT;
    private double MP_MULT;
    private double AP_MULT;
    private double ARMOR_MULT;
    private double EVA_MULT;
    private double ACC_MULT;
        
    public LevelMultipiler(double HP,double MP,double AP,double ARMOR, double EVA, double ACC) {
            HP_MULT = HP;
            MP_MULT = MP;
            AP_MULT = AP;
            ARMOR_MULT = ARMOR;
            EVA_MULT = EVA;
            ACC_MULT = ACC;
    }
    
    public LevelMultipiler(Double all) {
        this(all,all,all,all,all,all);
    }

    public double getHP_MULT() {
        return HP_MULT;
    }

    public double getMP_MULT() {
        return MP_MULT;
    }

    public double getAP_MULT() {
        return AP_MULT;
    }

    public double getARMOR_MULT() {
        return ARMOR_MULT;
    }

    public double getEVA_MULT() {
        return EVA_MULT;
    }

    public double getACC_MULT() {
        return ACC_MULT;
    }
        
        
}
