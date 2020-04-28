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
public class Thief extends PlayableMinion {

    private static final String DEFAULT_NAME = "Theif";
    private static final double BASE_HEALTPOINT = 25;
    private static final double BASE_MANA = 15;
    private static final double BASE_AP = 35;
    private static final double BASE_ARMOR = 5;
    private static final double BASE_EVASION = 20;
    private static final double BASE_ACC = 20;
    private static final LevelMultipiler MULT = new LevelMultipiler(1.1);
    private static final int CLASS_INDEX = ClassIndex.THIEF_INDEX;
//    skill = ??

    public Thief() {
        super(CLASS_INDEX,DEFAULT_NAME, BASE_HEALTPOINT, BASE_MANA, BASE_AP, BASE_ARMOR, BASE_EVASION, BASE_ACC, MULT);
    }

    @Override
    protected void useSkillOn(Minion minion) {
        if (this.getManapoint() >= 20) {
            System.out.println(this.getName() + "had used Skill 'Increase Atk + 40 ap'! on " + minion.getName());
            this.setManapoint(this.getManapoint() - 20);

        }
    }

    @Override
    public void actionDecider(Minion[] ourpaty, Minion[] enemyparty) {
        for (int i = 0; i < enemyparty.length; i++) {
            Minion minion = enemyparty[i];
            if (minion.getPrimarystatus() == PrimaryStatus.ALIVE) {
                for (int j = 0; j < enemyparty.length; j++) {
                    if (j == enemyparty.length);//ตีตัวหลัง
                    this.attackOn(minion);
                    break;
                }

            }
        }
    }

}
