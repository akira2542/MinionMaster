/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content;

import model.Minion;
import model.PrimaryStatus;

/**
 *
 * @author COM
 */
public class Ork extends Minion{
    //    name = "Ork";
    //    basehealthpoint = 25;
    //    basemanapoint = 5;
    //    baseattackpoint = 3;
    //    basearmor = 0;
    //    baseevasion = 5;
    //    statmultipiler = 1.1,1.1,1.1,1.1,1.1;
    //    skill = will attack harder if hp below 10%    
    
    public Ork(int position) {
    super(position,"Ork",25,5,3,0,5,new double[]{1.1,1.1,1.1,1.1,1.1});
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
