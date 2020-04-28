/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content.playableminon;

import model.ClassIndex;
import model.LevelMultipiler;
import model.Minion;
import model.PlayableMinion;
import model.PrimaryStatus;

/**
 *
 * @author SURAPONGCHAMALAI
 */

    public class Priest extends PlayableMinion {
      
      private static final String DEFAULT_NAME = "Priest";
      private static final double BASE_HEALTPOINT = 30; 
      private static final double BASE_MANA = 30;
      private static final double BASE_AP = 15;
      private static final double BASE_ARMOR = 10;
      private static final double BASE_EVASION = 5;
      private static final double BASE_ACC = 5;
      private static final LevelMultipiler MULT = new LevelMultipiler(1.1);
      private static final int CLASS_INDEX = ClassIndex.PRIEST_INDEX;
//    skill = Heal
    public Priest() {
    super(CLASS_INDEX,DEFAULT_NAME,BASE_HEALTPOINT,BASE_MANA,BASE_AP,BASE_ARMOR,BASE_EVASION,BASE_ACC,MULT); 
    }
    
    @Override
    protected void useSkillOn(Minion minion) {
        if(this.getManapoint()>= 15){
        System.out.println(this.getName() + "had used Skill 'Heal'! on " + minion.getName());
        this.setManapoint(this.getManapoint()-15);
         minion.setHealthpoint(minion.getManapoint()+30);
         
        }
    }

   
    


    @Override
    public void actionDecider(Minion[] ourpaty, Minion[] enemyparty) {
 for (int i = 0; i < enemyparty.length; i++) {
            Minion minion = enemyparty[i];
            Minion team   =  ourpaty[i];
            if (team.getHealthpoint()<=50/100) {
              this.useSkillOn(team);
               
              break;
            }
        }    }
}
