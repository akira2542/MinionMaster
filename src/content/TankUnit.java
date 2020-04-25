/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content;

import model.LevelMultipiler;
import model.Minion;
import model.PlayableMinion;
import model.PrimaryStatus;

/**
 *
 * @author SURAPONGCHAMALAI
 */
public class TankUnit extends PlayableMinion {
      
      private static final String DEFAULT_NAME = "Tanker";
      private static final double BASE_HEALTPOINT = 40; 
      private static final double BASE_MANA = 20;
      private static final double BASE_AP = 20;
      private static final double BASE_ARMOR = 10;
      private static final double BASE_EVASION = 5;
      private static final double BASE_ACC = 5;
      private static final LevelMultipiler MULT = new LevelMultipiler(1.1);
//    skill = Stun
    public TankUnit(int position) {
    super(position,DEFAULT_NAME,BASE_HEALTPOINT,BASE_MANA,BASE_AP,BASE_ARMOR,BASE_EVASION,BASE_ACC,MULT); 
    }
    
    @Override
    protected void useSkillOn(Minion minion) {
        Minion tanker = new TankUnit(1) ;
          if(this.getManapoint()>= 10){
           System.out.println(this.getName() + "had used Skill 'Stun'! on " + minion.getName());
            
        }
    }

    @Override
    public void actionDecider(Minion[] ourpaty, Minion[] enemyparty) {
        for (int i = 0; i < enemyparty.length; i++) {
            Minion minion = enemyparty[i];
            if (minion.getPrimarystatus() == PrimaryStatus.ALIVE) {
              this.attackOn(minion);
              break;
            }
        }
    }
    
}
