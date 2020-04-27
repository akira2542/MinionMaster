/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Minion;

/**
 *
 * @author COM
 */
public class LocalSaveSystem {
    
    public static final int MAX_SAVE_SLOT = 3;
    
    private PlayerProfile[] saveslots = new PlayerProfile[MAX_SAVE_SLOT];
    
    public static void main(String[] args) {
//        LocalSaveSystem sls = new LocalSaveSystem();
//        PlayerProfile test = new PlayerProfile();
//        sls.writeProfile(test);
//        PlayerProfile loaded = sls.readProfile("test");
//        System.out.println(loaded.getUsername());
//        System.out.println(loaded.getPlayerParty()[0].getName());
//        Minion testmin = loaded.getPlayerParty()[0];
//        testmin.levelUp();
//        System.out.println(testmin.getLevel());
        
        
    }
    
    public LocalSaveSystem() {
        saveSlotInitialization();
    }
    
    private boolean isSlotExist(int slot) {
        File temp;
        temp = new File("save/slot_"+slot+".save");
        boolean exist = temp.exists();
        return exist;
    }
    
    private void saveSlotInitialization() {
        for (int i = 1; i < MAX_SAVE_SLOT; i++) {
            if (isSlotExist(i)) {
                this.saveslots[i] = this.readProfileSave("slot_"+i+".save");
            }
        }
    }
    
    public PlayerProfile getSaveSlot(int slot) {
        if (slot > 0 && slot <= MAX_SAVE_SLOT) return saveslots[slot];
        return null;
    }
    
    public PlayerProfile[] getSaveSlots() {
        return saveslots;
    }
    
    public static boolean saveProfile(PlayerProfile profile,int slot) {
        if (slot > 0 && slot <= MAX_SAVE_SLOT) {
            writeProfile(profile,"slot_"+slot+".save");
            return true;
        }
            return false;
    }
             
    
    private static void writeProfile(PlayerProfile profile,String savename) {
        try (FileOutputStream fos = new FileOutputStream("save/"+savename);
               ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(profile);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static PlayerProfile loadProfile(int index) {
        if (index > 0 && index <= MAX_SAVE_SLOT) {
            return readProfileSave("slot_"+index+".save");
        }
        return null;
    }

    private static PlayerProfile readProfileSave(String savename) {
        PlayerProfile profile = null;
        try (FileInputStream fis = new FileInputStream("save/"+savename);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            while(fis.available()!=0){
               profile = (PlayerProfile) ois.readObject();
            }
           
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        }
        return profile;
        }
    }