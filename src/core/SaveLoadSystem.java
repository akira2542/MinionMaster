/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

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
public class SaveLoadSystem {
    
    public static void main(String[] args) {
        SaveLoadSystem sls = new SaveLoadSystem();
        PlayerProfile test = new PlayerProfile();
        sls.writeProfile(test);
        PlayerProfile loaded = sls.readProfile("test");
        System.out.println(loaded.getUsername());
        System.out.println(loaded.getPlayerParty()[0].getName());
        Minion testmin = loaded.getPlayerParty()[0];
        testmin.levelUp();
        System.out.println(testmin.getLevel());
        
        
    }
    
    
    public void writeProfile(PlayerProfile profile) {
        try (FileOutputStream fos = new FileOutputStream("save/"+profile.getUsername()+".save");
               ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(profile);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public PlayerProfile readProfile(String profilename) {
        PlayerProfile profile = null;
        try (FileInputStream fis = new FileInputStream("save/"+profilename+".save");
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
