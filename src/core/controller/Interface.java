/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controller;

import core.resource.Dungeon;
import core.resource.PlayerProfile;
import core.savesystem.LocalSaveSystem;
import java.util.Scanner;
import core.resource.Equipment;
import core.savesystem.CloudSaveSystem;
import model.Minion;
import model.enumurator.ClassIndex;
import utility.factory.DungeonFactory;
import model.enumurator.Difficulty;
import model.enumurator.EquipmentGrade;
import utility.TimeStopper;
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
        System.out.println("=========================");
        System.out.println("WELCOME TO MINION MASTER©");
        System.out.println("=========================");
        System.out.println("Load or Create new save game || creating new profile and cloud sve required database connection");
        System.out.println("1 | Load local save");
        System.out.println("2 | Load cloud save");
        System.out.println("3 | Create new profile");
        System.out.print("your option : ");
        boolean inputcheck = true; 
            while(inputcheck){
                    int input = scn.nextInt();
                    switch(input){
                        case 1:
                            inputcheck = false;
                            localLoadInterface(scn);
                            break;
                        case 2:
                            inputcheck = false;
                            cloudLoadInterface(scn);
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
        System.out.print("username : ");
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
                System.out.print("your option : ");
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
        }
        baseInterface(scn);    
        
    }


    private void baseInterface(Scanner scn) {
            System.out.println("=======================");
            System.out.println("     Base Interface    ");
            System.out.println("=======================");
            System.out.println("welcome '"+this.sessionprofile.getUsername()+"' to the base! here you can choose to");
            System.out.println("1 | Go to battle with your minions");
            System.out.println("2 | Manage your minion equipments");
            System.out.println("3 | Check your treasury and tokens");
            System.out.println("4 | Check LeaderBoard and score");
            System.out.println("5 | Save Online Profile");
            System.out.println("6 | Save Local Profile");
            System.out.println("7 | exit (without saving)");
            System.out.print("your option: ");
            int num = scn.nextInt();
            switch(num){
                case 1 :
                      goBattle(scn);
                    break;
                case 2 :
                     upequipmentInterface(scn);
                    break;
                case 3 :
                    System.out.println("=======================");
                    System.out.println("        Treasury       ");
                    System.out.println("=======================");
                    System.out.println("Gold use to advance minion's weapon levels, Token use for advance minion's weapon grads!");
                    System.out.println("Gold   : "+this.sessionprofile.getGold());
                    System.out.println("Tokens : "+this.sessionprofile.getToken());
                    TimeStopper.userInput();
                    baseInterface(scn);
                    break;
                case 4 :
                    CloudSaveSystem cs = new CloudSaveSystem();
                    System.out.println(cs.getLeaderBoard());
                    System.out.println("=======================");
                    System.out.println("your score :"+ this.sessionprofile.getScore());
                    System.out.println("=======================");
                    TimeStopper.userInput();
                    baseInterface(scn);
                    break;
                case 5 :
                    cloudSaveInterface(scn);
                    break;
                case 6:
                    localSaveInterface(scn);
                    break;
                case 7:
                    System.out.println("Exiting game");
                    TimeStopper.Delay();
                    System.out.println("Good Bye! :)");
                    break;
                default :
                    System.out.println("invalid input, try again!");
                    baseInterface(scn);
                    break;
            }



    }

    private void goBattle(Scanner scn) {
        boolean inputcheck = true;
        Dungeon dungeon = null;
        while (inputcheck) {
        System.out.println("===================");
        System.out.println("    DUNGEON RAID   ");
        System.out.println("===================");
        System.out.println("Choose Difficulty");
        System.out.println("1 | Easy");
        System.out.println("2 | Medium");
        System.out.println("3 | Hard");
        System.out.println("your option : ");
        int num = scn.nextInt();
            switch(num){
                case 1 :
                    inputcheck = false;
                    System.out.println("You have Chosen Dungeon in EASY difficulty!");
                    dungeon = DungeonFactory.createDungeon(Difficulty.EASY.getIndex());
                    break;
                case 2 :
                    inputcheck = false;
                    System.out.println("You have Chosen Dungeon in MEDIUM difficulty!");
                    dungeon = DungeonFactory.createDungeon(Difficulty.MEDIUM.getIndex());
                    break;
                case 3 :
                    inputcheck = false;
                    System.out.println("You have Chosen Dungeon in HARD difficulty!");
                    dungeon =DungeonFactory.createDungeon(Difficulty.HARD.getIndex());
                    break;
                default : System.out.println("invalid option, try agian");
                    break;
            }
            TimeStopper.userInput();
            dungeon.enter(sessionprofile);
            inputcheck = false;
            baseInterface(scn);
        }
        
    }

    private void upequipmentInterface(Scanner scn) {
        //upgrade minion's equipment
        boolean inputcheck = true;
        while(inputcheck) {
        System.out.println("===================");
        System.out.println(" EQUIPMENT UPGRADE ");
        System.out.println("===================");
        System.out.println("Which Character would you like to upgrade their eqipment");
            int i = 1;
            for (Minion minion : sessionprofile) {
                System.out.println(i+" | lv."+minion.getLevel()+" "+minion.getName()+" [WEPLV: "+minion.getEquipment().getWeaponlv() + ", WEPGRADE: "+minion.getEquipment().getWeaponGrade()+", ARLV: "+minion.getEquipment().getArmorlv() + ", ARGRADE: "+minion.getEquipment().getArmorGrade()+"]");
                i++;
            }
        System.out.println("5 | back to base");
        System.out.print("your option : ");
        int num = scn.nextInt();
            if (num > 0 && num <= this.sessionprofile.getPlayerParty().length ) {
                num--;
                characterEquipment(scn,this.sessionprofile.getPlayerParty()[num]);
                
            }else if (num == 5){
                inputcheck = false;
                baseInterface(scn);
            }
            else {
            System.out.println("invalid option, try agian");
            }
        }
    }
    
    private void characterEquipment(Scanner scn,Minion m) {
        boolean inputcheck = true;
        Equipment e = m.getEquipment();
        while (inputcheck) {
        long wplvcost = Equipment.getIncreaseEquipmentLevelPrice(e.getWeaponlv(), e.getWeaponGrade());
        long arlvcost = Equipment.getIncreaseEquipmentLevelPrice(e.getArmorlv(), e.getArmorGrade());
        int wpgradecost = Equipment.getTokenUpgradePrice(e.getWeaponGrade());
        int argradecost = Equipment.getTokenUpgradePrice(e.getArmorGrade());
        System.out.println("=========================================================");
        System.out.println(" EQUIPMENT UPGRADE FOR LV."+m.getLevel()+" "+m.getName());
        System.out.println("=========================================================");
        System.out.println(" [WEPLV: "+e.getWeaponlv() + ", WEPGRADE: "+e.getWeaponGrade()+", ARLV: "+e.getArmorlv() + ", ARGRADE: "+e.getArmorGrade());
        System.out.println("1 | Level up your weapon using gold         | COST: "+wplvcost+" GOLD");
        System.out.println("2 | Level up your armor using gold          | COST: "+arlvcost+" GOLD");
        System.out.println("3 | Advance your weapon grade using tokens  | COST: "+wpgradecost+" TOKENS");
        System.out.println("4 | Advance your armor grade using tokens   | COST: "+argradecost+" TOKENS");
        System.out.println("5 | Back");
        System.out.println("Available Gold :"+this.sessionprofile.getGold()+", Available Tokens "+this.sessionprofile.getToken());
        System.out.print("Your option : ");
        int input = scn.nextInt();
            System.out.println("\n\n");
        switch (input) {
            case 1:
                    if (wplvcost > 0){
                        if (this.sessionprofile.spendGold(wplvcost)) {
                            e.increaseEquiptmentLevel(Equipment.WEAPON_INDEX);
                            System.out.println("minions weapon level has been advance to lv."+e.getWeaponlv());
                            TimeStopper.userInput();
                        }else{
                            System.out.println("Not enough gold!");
                            TimeStopper.userInput();
                        }    
                    }else{
                    System.out.println("Your minion's weapon reached max level and can not be upgraded further!");
                    TimeStopper.userInput();
                    }
                break;
            case 2:
                    if (arlvcost > 0){
                        if (this.sessionprofile.spendGold(arlvcost)) {
                            e.increaseEquiptmentLevel(Equipment.WEAPON_INDEX);
                            System.out.println("minion's armor level has been advance to lv."+e.getArmorlv());
                            TimeStopper.userInput();
                        }else{
                            System.out.println("Not enough gold!");
                            TimeStopper.userInput();
                        }    
                    }else{
                    System.out.println("Your minon's armor reached max level and can not be upgraded further!");
                    TimeStopper.userInput();
                    }
                break;
            case 3:
                    if (wpgradecost > 0){                
                        if (this.sessionprofile.spendToken(wpgradecost)) {
                            e.advanceUquipmentGrade(Equipment.WEAPON_INDEX);
                            System.out.println("minion's weapon grade has been advance to "+e.getWeaponGrade());
                            TimeStopper.userInput();
                        }else{
                            System.out.println("Not enough gold!");
                            TimeStopper.userInput();
                        }    
                    }else{
                    System.out.println("Your minon's weapon reached max grade and can not be upgraded further!");
                    TimeStopper.userInput();
                    }
                break;
            case 4:
                    if (argradecost > 0){                                
                        if (this.sessionprofile.spendToken(argradecost)) {
                            e.advanceUquipmentGrade(Equipment.ARMOR_INDEX);
                            System.out.println("minion's armor grade has been advance to "+e.getArmorGrade());
                            TimeStopper.userInput();
                        }else{
                            System.out.println("Not enough gold!");
                            TimeStopper.userInput();
                        }    
                    }else{
                    System.out.println("Your minon's armor reached max grade and can not be upgraded further!");
                    TimeStopper.userInput();
                    }
                break;
            case 5:
                inputcheck = false;
                break;
            default:
                System.out.println("invalid option, try agian");            
                break;
        }
        }
    }
    
    private void localSaveInterface(Scanner scn) {
        boolean inputcheck = true;
        while (inputcheck) {
        System.out.println("=======================");
        System.out.println("       SAVE LOCAL      ");
        System.out.println("=======================");
        System.out.println("Choose slot to save. slot you choose will overite existing file");
        PlayerProfile[] slots = getLocalSaveProfiles();
        System.out.print("save in slot:");
        int input = scn.nextInt();
            if (input >= 0 && input <=3) {
                LocalSaveSystem.saveProfile(sessionprofile, input);
                inputcheck = false;
                baseInterface(scn);
            }else{
                System.out.println("Invalid input, try agian");
            }       
        }
    }
    
    private void localLoadInterface(Scanner scn) {
        boolean inputcheck = true;
        while (inputcheck) {
        System.out.println("=======================");
        System.out.println("       LOAD LOCAL      ");
        System.out.println("=======================");
        System.out.println("Choose save slot to load.");
        PlayerProfile[] slots = getLocalSaveProfiles();
        System.out.print("load slot:");
        int input = scn.nextInt();
            if (input >= 0 && input <=3) {
                if (slots[input] != null) {
                this.sessionprofile = slots[input];
                inputcheck = false;
                baseInterface(scn);
                }else {
                    System.out.println("you loaded empty slot! try again.");
                }
            }else{
                System.out.println("Invalid input, try agian");
            }       
        }
        
    }
    
    private PlayerProfile[] getLocalSaveProfiles() {
            LocalSaveSystem savesys = new LocalSaveSystem();
            PlayerProfile[] slots = savesys.getSaveSlots();
            StringBuilder s = new StringBuilder();
                    for (int i = 0; i < slots.length; i++) {
            PlayerProfile slot = slots[i];
            if (slot != null) {
                s.append("[ SLOT ").append(i).append(" : ").append(slot.getUsername()).append(" ]\n");
            } else {
                s.append("[ SLOT ").append(i).append(" : ").append(" empty ]\n");
            }
        }
        System.out.println(s);
        return slots;
    }
    
    private void cloudSaveInterface(Scanner scn) {
        //establish connection by building an object
        CloudSaveSystem cs = new CloudSaveSystem();
        System.out.println("=======================");
        System.out.println("       SAVE CLOUD      ");
        System.out.println("=======================");
        System.out.println("Enter password to use cloud save (if you never use cloud save before this will be your password)");
        System.out.println("Your password (case sensitive) : ");
        String password = scn.next();
        cs.insertProfile(sessionprofile, password);
        baseInterface(scn);
    }
    
    private void cloudLoadInterface(Scanner scn) {
        boolean inputcheck = true;
        while(inputcheck) {
        CloudSaveSystem cs = new CloudSaveSystem();
            System.out.println("=======================");
            System.out.println("       LOAD CLOUD      ");
            System.out.println("=======================");
            System.out.println("Enter Username and Password to load Cloud Save");
            System.out.print("Username : ");
            String usr = scn.next();
            System.out.print("\nPassword : ");
            String pwd = scn.next();
            PlayerProfile temp = cs.queryProfile(usr, pwd);
            if (temp != null) {
                System.out.println("Save loaded successfully");
                System.out.println("Welcome '"+temp.getUsername()+"'");
                TimeStopper.Delay();
                inputcheck = false;
                this.sessionprofile = temp;
                baseInterface(scn);
            }else {
                System.out.println("invalid username/password");
            }
        }
        
    }
}
