/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content.minion;

import model.enumurator.ClassIndex;
import core.resource.LevelMultipiler;
import model.Minion;
import model.enumurator.PrimaryStatus;

/**
 *
 * @author SURAPONGCHAMALAI
 */
public class Orc extends Minion{
        
      private static final String DEFAULT_NAME = "Orc";
      private static final double BASE_HEALTPOINT = 20; 
      private static final double BASE_MANA = 5;
      private static final double BASE_AP = 3;
      private static final double BASE_ARMOR = 5;
      private static final double BASE_EVASION = 0;
      private static final double BASE_ACC = 15;
      private static final LevelMultipiler MULT = new LevelMultipiler(1.1);
      private static final int CLASS_INDEX = ClassIndex.ORC_INDEX;
//    skill = will attack harder if hp below 10%    
    
    public Orc() {
    super(CLASS_INDEX,DEFAULT_NAME,BASE_HEALTPOINT,BASE_MANA,BASE_AP,BASE_ARMOR,BASE_EVASION,BASE_ACC,MULT); 
    }
    
    @Override
    protected void useSkillOn(Minion minion) {
        double healamount = this.getMaxhealthpoint()*0.2;
        this.setHealthpoint(this.getHealthpoint()+healamount);
        System.out.println(this.getName() + "had used Skill 'Self Heal' for "+healamount+" hp !");
    }

    @Override
    public void actionDecider(Minion[] ourpaty, Minion[] enemyparty) {
       int rand = (int) Math.round(Math.random()*100);
       boolean lowhp = this.getHealthpoint() <= this.getMaxhealthpoint()*0.2;
       if (rand <= 50 && lowhp) {
       useSkillOn(this);
       }else{
            for (int i = 0; i < enemyparty.length; i++) {
                Minion minion = enemyparty[i];
                if (minion.getPrimarystatus() == PrimaryStatus.ALIVE) {
                    this.attackOn(minion);
                    break;
                }
            }
        }
    }
    
}
