/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content.playableminon;

import model.enumurator.ClassIndex;
import core.resource.LevelMultipiler;
import model.Minion;
import model.PlayableMinion;
import model.enumurator.PrimaryStatus;

/**
 *
 * @author SURAPONGCHAMALAI
 */

    public class Priest extends PlayableMinion {
      
      private static final String DEFAULT_NAME = "Priest";
      private static final double BASE_HEALTPOINT = 20; 
      private static final double BASE_MANA = 15;
      private static final double BASE_AP = 5;
      private static final double BASE_ARMOR = 3;
      private static final double BASE_EVASION = 3;
      private static final double BASE_ACC = 7;
      private static final LevelMultipiler MULT = new LevelMultipiler(2.0,1.8,1.1,1.2,1.1,1.3);
      private static final int CLASS_INDEX = ClassIndex.PRIEST_INDEX;
//    skill = Heal
    public Priest() {
    super(CLASS_INDEX,DEFAULT_NAME,BASE_HEALTPOINT,BASE_MANA,BASE_AP,BASE_ARMOR,BASE_EVASION,BASE_ACC,MULT); 
    }
    
    @Override
    protected void useSkillOn(Minion minion) {
        this.setManapoint(this.getManapoint()-5);
        double healamout = minion.getHealthpoint()*0.2;
        minion.setHealthpoint(minion.getHealthpoint()+healamout);
        System.out.println("lv."+this.getLevel()+" "+this.getName()+"("+this.getPosition()+") Healed lv."+minion.getLevel()+" "+minion.getName()+"("+minion.getPosition()+")"+" for "+healamout);
    }

   

    

@Override
    public void actionDecider(Minion[] ourparty, Minion[] enemyparty) {
        boolean healed = false;
        for (int i = 0; i < ourparty.length; i++) {
            Minion minion = ourparty[i];
            boolean lowhp = minion.getHealthpoint() < minion.getMaxhealthpoint()*0.6;
            if (minion.getPrimarystatus() == PrimaryStatus.ALIVE && lowhp && this.getManapoint() > 5) {
              useSkillOn(minion);
              healed = true;
              break;
            }
        }
        if (!healed) {
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
