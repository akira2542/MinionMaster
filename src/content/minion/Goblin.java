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
 * @author COM
 */
public class Goblin extends Minion{
    
      private static final String DEFAULT_NAME = "Goblin";
      private static final double BASE_HEALTPOINT = 15; 
      private static final double BASE_MANA = 5;
      private static final double BASE_AP = 3;
      private static final double BASE_ARMOR = 0;
      private static final double BASE_EVASION = 5;
      private static final double BASE_ACC =  10;
      private static final LevelMultipiler MULT = new LevelMultipiler(1.1);
      private static final int CLASS_INDEX = ClassIndex.GOBLIN_INDEX;
//    skill = will attack harder if hp below 10%    
    
    public Goblin() {
    super(CLASS_INDEX,DEFAULT_NAME,BASE_HEALTPOINT,BASE_MANA,BASE_AP,BASE_ARMOR,BASE_EVASION,BASE_ACC,MULT); 
    }
    
    @Override
    protected void useSkillOn(Minion minion) {
        System.out.println(this.getName() + "had used Skill 'Attack a little harder'! on " + minion.getName());
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
