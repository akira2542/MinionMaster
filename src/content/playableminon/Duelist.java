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
public class Duelist extends PlayableMinion {
    private static final String DEFAULT_NAME = "Duelist";
      private static final double BASE_HEALTPOINT = 15; 
      private static final double BASE_MANA = 10;
      private static final double BASE_AP = 10;
      private static final double BASE_ARMOR = 7;
      private static final double BASE_EVASION = 5;
      private static final double BASE_ACC =  10;
      private static final LevelMultipiler MULT = new LevelMultipiler(1.5,1.1,2.0,1.2,1.3,1.5);
      private static final int CLASS_INDEX = ClassIndex.DUELIST_INDEX;
//    skill = Stun
    public Duelist() {
    super(CLASS_INDEX,DEFAULT_NAME,BASE_HEALTPOINT,BASE_MANA,BASE_AP,BASE_ARMOR,BASE_EVASION,BASE_ACC,MULT); 
    }
    
    @Override
    protected void useSkillOn(Minion minion) {
         if(this.getManapoint()>= 10){
        System.out.println(this.getName() + "had used Skill 'Deal 80%of atk to all enemy'! on " + minion.getName());
         this.setManapoint(this.getManapoint()-15);
         }
    }

   @Override
    public void actionDecider(Minion[] ourparty, Minion[] enemyparty) {
        for (int i = 0; i < enemyparty.length; i++) {
            Minion minion = enemyparty[i];
            if (minion.getPrimarystatus() == PrimaryStatus.ALIVE) {
              this.attackOn(minion);
              break;
            }
        }
    }
    
}


