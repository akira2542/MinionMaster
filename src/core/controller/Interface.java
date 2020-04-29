/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controller;

import core.resource.PlayerProfile;
import core.savesystem.LocalSaveSystem;
import java.util.Scanner;
import core.resource.Equipment;
import core.savesystem.CloudSaveSystem;
import model.Minion;
import model.enumurator.ClassIndex;
import utility.factory.DungeonFactory;
import model.enumurator.Difficulty;
import utility.factory.MinionFactory;

/**
 *
 * @author SURAPONGCHAMALAI
 */
public class Interface {

    PlayerProfile sessionprofile;

    public Interface() {
        try(Scanner scn = new Scanner(System.in);) {
            newGame(scn);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    //  newgame loadgame
    //create profile createchar
    private void newGame(Scanner scn){
        System.out.println("WELCOME TO MINION MASTER©");
        System.out.println("[ Load or Create new save game ]");
        System.out.println("Press 1 to load local");
        System.out.println("Press 2 to load cloud");
        System.out.println("Press 3 to create new");
        boolean inputcheck = true; 
            while(inputcheck){
                    int input = scn.nextInt();
                    switch(input){
                        case 1:
                            inputcheck = false;
                            break;
                        case 2:
                            inputcheck = false;
                            break;
                        case 3:                            
                            inputcheck = false;
                            createNewProfileInterface(scn);
                            break;
                        default:
                            System.out.println("incorrect input, try again");
                            break;
                            
                    }    
            }
    }
    
    private void createNewProfileInterface(Scanner scn) {
        CloudSaveSystem cs = new CloudSaveSystem();
        boolean inputcheck = true;
        System.out.println("Insert Username");
        System.out.println("#it can't not be changed later!");
        while (inputcheck) {
            String input = scn.next();
            if (cs.isUsernameExist(input)) {
                System.out.println("this name has been taken try another name");
            } else {
            inputcheck = false;
                System.out.println("Username '"+input+"' has been created!");
                this.sessionprofile = new PlayerProfile(input);
                partyCreator(scn);
            }
        }
    }
    
    private void partyCreator(Scanner scn) {
        Minion[] minions = new Minion[4];
        System.out.println("Now, It's time to assenple a new adventurer party!");
        for (int i = 0; i < minions.length; i++) {
            boolean inputcheck = true;
            while(inputcheck) {
                int position = i+1;
                System.out.println("Choose your mionion to be in position "+position+" of your party");
                System.out.println("1. Knight  : good at being vangard recommended to be fist inline");
                System.out.println("2. Priest  : can heal fellow minions weares little armor");
                System.out.println("3. Duelist : powerful attack and agile but can't take too much beating");
                System.out.println("4. Thief   : decent backline attack can't take any beating");
                System.out.print("input :");
                int input = scn.nextInt();
                switch(input) {
                    case 1:
                        inputcheck = false;
                        minions[i] = MinionFactory.createPlayableMinion(ClassIndex.KNIGHT.getIndex());
                        System.out.println(minions[i]);
                        break;
                    case 2:
                        inputcheck = false;
                        minions[i] = MinionFactory.createPlayableMinion(ClassIndex.PRIEST.getIndex());
                        break;
                    case 3:
                        inputcheck = false;
                        minions[i] = MinionFactory.createPlayableMinion(ClassIndex.DUELIST.getIndex());
                        break;
                    case 4:
                        inputcheck = false;
                        minions[i] = MinionFactory.createPlayableMinion(ClassIndex.THIEF.getIndex());
                        break;
                    default:
                        System.out.println("inccorrect input, try again.");
                        break;
                }
            }
        }

        MinionFactory.reassemblePlayerParty(minions);
        this.sessionprofile.setPlayerParty(minions);
        System.out.println("Party has been assembled here's an overview");
        for (Minion p : sessionprofile) {
        System.out.println(p);
        menu(scn);    
        }
        
    }


    public void menu(Scanner scn) {
            System.out.println("=======================");
            System.out.println("     Base Interface    ");
            System.out.println("=======================");
            System.out.println("welcome "+this.sessionprofile.getUsername()+" to the base! here you can choose to");
            System.out.println("1. Go to battle with your minions");
            System.out.println("2. Mange your minion equipments");
            System.out.println("3. Save Online Profile");
            System.out.println("4. Save Local Profile");
           //  System.out.println(profile.getGold());
          //  System.out.println(profile.getUsername());
            int num = scn.nextInt();
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
                DungeonFactory.createDungeon(Difficulty.EASY_INDEX);
                break;
            case 2 :
                DungeonFactory.createDungeon(Difficulty.MEDIUM_INDEX);
                break;
            case 3 :
                DungeonFactory.createDungeon(Difficulty.HARD_INDEX);
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
        LocalSaveSystem.saveProfile(this.sessionprofile, slot);
    }

    public void localLoadSaveInterface() {
        Scanner Save = new Scanner(System.in);
        System.out.println("Choose the Slot to Save 0 - 2");
        int num = Save.nextInt();
        System.out.println("You save to slot " + num);
        for (int i = 0; i < 4; i++) {
            if (i == num) {
                LocalSaveSystem.saveProfile(this.sessionprofile, num);

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
//       n.newGame();
         //n.upequipmentlevel();

    }
}
