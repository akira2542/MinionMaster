/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Scanner;
import model.Equipment;
import model.EquipmentGrade;
import utility.factory.DungeonFactory;
import utility.factory.MinionFactory;

/**
 *
 * @author SURAPONGCHAMALAI
 */
public class Interface {

    Scanner Scan = new Scanner(System.in);
    PlayerProfile profile;

 //  newgame loadgame
    //create profile createchar
    public void newGame(){
        
           
        System.out.println("Load or Create new save game");
        System.out.println("Press 1 to load local");
        System.out.println("Press 2 to load cloud");
        System.out.println("Press 3 to create new");
        
       int num = Scan.nextInt();
      
       if(num == 1){
           System.out.println("Select slot to load local 0 - 2");
            int load = Scan.nextInt();
           for (int i = 0; i < 3; i++) {
               if(load == i){
                   System.out.println(" Load Profile on slot" + i );
                   
               }
           }
       }  if(num == 2){
           System.out.println("Insert your Username");
           Scanner String = new Scanner(System.in);
           String user = String.nextLine();
           System.out.println("Username = " + user);
       }
       if(num == 3){
           System.out.println("Select slot to New game 0 - 2");
            int create = Scan.nextInt();
           for (int i = 0; i < 3; i++) {
               if(create == i){
                   System.out.println("Create New save game on slot " + i);
               }
           }
       }
     
    }
    public void goBattle() {

        System.out.println("Choose Difficulty");
        System.out.println("1 : Easy");
        System.out.println("2 : Medium");
        System.out.println("3 : Hard");
        int num = Scan.nextInt();

        System.out.println("Difficult = " + num);
        if (num == 1) {
            DungeonFactory.createDungeon(DungeonFactory.EASY_INDEX);
        } else if (num == 2) {
            DungeonFactory.createDungeon(DungeonFactory.MEDIUM_INDEX);
        } else if (num == 3) {
            DungeonFactory.createDungeon(DungeonFactory.HARD_INDEX);
        } else {
            System.out.println("You choose wrong difficulty");
        }

         //Dungeon dun = DungeonFactory.createDungeon(DungeonFactory.HARD_INDEX);
        //dun.enter(profile);
    }

    public void upequipmentlevel() {
        System.out.println("Select Your Equipment");
        System.out.println("1 = Weapon");
        System.out.println("2 = Armor");
        int num = Scan.nextInt();
         Equipment e = new Equipment();
            if (num == 1) {
                System.out.println("Equipment = Weapon " );
                Equipment.getIncreaseEquipmentLevelPrice(e.getWeaponlv(), e.getWeaponGrade());
                
            }if(num == 2){
                System.out.println("Equipment = Armor " );
                Equipment.getIncreaseEquipmentLevelPrice(e.getArmorlv(), e.getArmorGrade());
            }else if (num<1||num>2){
                System.out.println("Wrong input");
            }
           
            
        }
   

    public static void main(String[] args) {
        Interface n = new Interface();
       // n.localLoadSaveInterface();
       //  n.goBattle();
       n.newGame();
             // n.upequipmentlevel();
    }

    private void saveLocalProfile(int slot) {
        LocalSaveSystem.saveProfile(profile, slot);
    }

    public void localLoadSaveInterface() {

        System.out.println("Choose the Slot to Save 0 - 2");
        int num = Scan.nextInt();
        System.out.println("You save to slot " + num);
        for (int i = 0; i < 4; i++) {
            if (i == num) {
                LocalSaveSystem.saveProfile(profile, num);
            }
        }
        LocalSaveSystem savesys = new LocalSaveSystem();
        PlayerProfile[] slots = savesys.getSaveSlots();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < slots.length; i++) {
            PlayerProfile slot = slots[i];
            if (slot != null) {
                s.append("SLOT ").append(i).append(" :").append(slot.getUsername()).append("\n");
            } else {
                s.append("SLOT ").append(i).append(" :").append(" emptyslot \n");
            }
        }
        System.out.println(s);
    }

}
