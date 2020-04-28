/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Scanner;
import model.ClassIndex;
import model.Equipment;
import model.EquipmentGrade;
import model.Minion;
import utility.factory.DungeonFactory;
import utility.factory.MinionFactory;

/**
 *
 * @author SURAPONGCHAMALAI
 */
public class Interface {

    PlayerProfile profile;

    //  newgame loadgame
    //create profile createchar
    public void newGame() {
        //start game
        try (Scanner Scan = new Scanner(System.in)) {
            System.out.println("Load or Create new save game");
            System.out.println("Press 1 to load local");
            System.out.println("Press 2 to load cloud");
            System.out.println("Press 3 to create new");

            int num = Scan.nextInt();
           switch(num){
               case 1 :
                   System.out.println("Select slot to load local 0 - 2");
                   int load = Scan.nextInt();
                   for (int i = 0; i < 3; i++) {
                       if(load == i){
                           System.out.println("Load Profile on slot " + i);
                           LocalSaveSystem.loadProfile(i);
                       }

                   }menu();
                   break;
               case 2 :
                   System.out.println("Insert your Username ");
                   Scanner String = new Scanner(System.in);
                   String user = String.nextLine();
                   System.out.println("Username = " + user);
                   createProfile(user);
                    break;
               case 3 : 
                    System.out.println("Select slot to Create new save 0 - 2");    
                    int create = Scan.nextInt();
                    for (int i = 0; i < 3; i++) {
                       if (create == i){
                           System.out.println("Create new save game on slot " + i);
                       }
                   }menu();
                   break;
                   default : System.out.println("invalid num ");
                   break;
                 
           }
          
           
         
          

        }
    }
    public void createProfile(String user){
        PlayerProfile profile = new PlayerProfile(user) ;
       Minion[] minions = new Minion[4];
        for (int i = 0; i < minions.length; i++) {
            System.out.println("Choose your class for position " + i );
            System.out.println("1 : Knight ");
            System.out.println("2 : Priest ");
            System.out.println("3 : Duelist ");
            System.out.println("4 : Thief ");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            
            switch(num){
               case 1 :
                    minions[i] = MinionFactory.createMinion(ClassIndex.KNIGHT_INDEX);
                    break;
                case 2 :
                    minions[i] = MinionFactory.createMinion(ClassIndex.PRIEST_INDEX);
                    break;
                case 3 :
                    minions[i] = MinionFactory.createMinion(ClassIndex.DUELIST_INDEX);
                    break;
                case 4 :
                    minions[i] = MinionFactory.createMinion(ClassIndex.THIEF_INDEX);
                    break;
                   
                default: System.out.println("Invalid num");
                 break;
                
            }
          
        }
        menu();
        
    }
    

    public void menu() {
        Scanner Menu = new Scanner(System.in); 
            System.out.println("Choose 1 or 2");
            System.out.println("Press 1 : gobattle");
            System.out.println("Press 2 : upEquipment");
           //  System.out.println(profile.getGold());
          //  System.out.println(profile.getUsername());
            int num = Menu.nextInt();
            switch(num){
                case 1 :
                    goBattle();
                    break;
                case 2 : 
                    upequipmentlevel();
                    break;
                default :
                    System.out.println("invalid num");
                    break;
            }
           

      
    }

    public void goBattle() {
//go dungeon
        Scanner Battle = new Scanner(System.in);
        System.out.println("Choose Difficulty");
        System.out.println("1 : Easy");
        System.out.println("2 : Medium");
        System.out.println("3 : Hard");
        int num = Battle.nextInt();

        
        switch(num){
            case 1 :
                DungeonFactory.createDungeon(DungeonFactory.EASY_INDEX);
                break;
            case 2 :
                DungeonFactory.createDungeon(DungeonFactory.MEDIUM_INDEX);
                break;
            case 3 :
                DungeonFactory.createDungeon(DungeonFactory.HARD_INDEX);
                break;
            default : System.out.println("invalid num");
            break;
        }
      
      
        //Dungeon dun = DungeonFactory.createDungeon(DungeonFactory.HARD_INDEX);
        //dun.enter(profile);
      
    }

    public void upequipmentlevel() {
        //up equipment
        Scanner Equip = new Scanner(System.in);
        System.out.println("Select Your Equipment");
        System.out.println("1 = Weapon");
        System.out.println("2 = Armor");
        int num = Equip.nextInt();
        Equipment e = new Equipment();
        switch(num){
            case 1 :
            System.out.println("Equipment = Weapon ");
            Equipment.getIncreaseEquipmentLevelPrice(e.getWeaponlv(), e.getWeaponGrade());
            break;
            case 2 :
                System.out.println("Equipment = Armor ");
                 Equipment.getIncreaseEquipmentLevelPrice(e.getArmorlv(), e.getArmorGrade());
            break;
            default :
                System.out.println("Wrong Input");
                break;
        }
       
    }

    private void saveLocalProfile(int slot) {
        LocalSaveSystem.saveProfile(profile, slot);
    }

    public void localLoadSaveInterface() {
        Scanner Save = new Scanner(System.in);
        System.out.println("Choose the Slot to Save 0 - 2");
        int num = Save.nextInt();
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

    public static void main(String[] args) {
        Interface n = new Interface();
        // n.localLoadSaveInterface();
         //n.goBattle();
       n.newGame();
         //n.upequipmentlevel();
         
    }
}
