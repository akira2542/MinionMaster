/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import model.Equipment;
import model.EquipmentGrade;
import utility.factory.DungeonFactory;
import utility.factory.MinionFactory;

/**
 *
 * @author SURAPONGCHAMALAI
 */
public class Interface {
    
    PlayerProfile profile;
    
    public void goBattle() {
        Dungeon dun = DungeonFactory.createDungeon(DungeonFactory.HARD_INDEX);
        dun.enter(profile);
    }
    
    public void upequipmentlevel() {

        
    }
    
    
    
    
    public static void main(String[] args) {
        Interface n = new Interface();
        n.localLoadSaveInterface();
    }
    
    private void saveLocalProfile(int slot) {        
        LocalSaveSystem.saveProfile(profile, slot);
    }
    
    public void localLoadSaveInterface() {
        LocalSaveSystem savesys = new LocalSaveSystem();
        PlayerProfile[] slots = savesys.getSaveSlots();
        StringBuilder s = new StringBuilder();
            for (int i = 0; i < slots.length; i++) {
            PlayerProfile slot = slots[i];
            if ( slot != null ) {
                s.append("SLOT ").append(i).append(" :").append(slot.getUsername()).append("\n");
            }else {
                s.append("SLOT ").append(i).append(" :").append(" emptyslot \n");
            }
        }
        System.out.println(s);
    }
     
}
