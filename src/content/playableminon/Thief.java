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
public class Thief extends PlayableMinion {

    private static final String DEFAULT_NAME = "Theif";
    private static final double BASE_HEALTPOINT = 15;
    private static final double BASE_MANA = 12;
    private static final double BASE_AP = 8;
    private static final double BASE_ARMOR = 3;
    private static final double BASE_EVASION = 7;
    private static final double BASE_ACC = 8;
    private static final LevelMultipiler MULT = new LevelMultipiler(1.4,1.2,2.2,1.1,2.0,1.5);
    private static final int CLASS_INDEX = ClassIndex.THIEF_INDEX;
//    skill = ??

    public Thief() {
        super(CLASS_INDEX,DEFAULT_NAME, BASE_HEALTPOINT, BASE_MANA, BASE_AP, BASE_ARMOR, BASE_EVASION, BASE_ACC, MULT);
    }

    @Override
    protected void useSkillOn(Minion minion) {
            System.out.println(this.getName() + " had used Skill 'Back Stab'! on " + minion.getName());
            this.setManapoint(this.getManapoint() - 5);
            double criticaldmg = this.getCalculatedAttackPoint()*2;
            this.attackOn(minion,criticaldmg);
    }

   @Override
    public void actionDecider(Minion[] ourparty, Minion[] enemyparty) {
        for ( int lastindex = enemyparty.length - 1; lastindex > 0; lastindex--) {
            Minion minion = enemyparty[lastindex];
            if (minion.getPrimarystatus() == PrimaryStatus.ALIVE) {
                int rand = (int) Math.round(Math.random()*100);
                if (rand < 50 && this.getManapoint() > 5){
                    this.useSkillOn(minion);
                    break;
                }else{
                    this.attackOn(minion);
                    break;
                }
            }
        }
    }

}
