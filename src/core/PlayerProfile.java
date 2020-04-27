package core;

import content.minion.Goblin;
import java.io.Serializable;
import model.Minion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author COM
 */
public class PlayerProfile implements Serializable {
    
    private final String Username;
    private Minion[] playerParty;
    private long gold;
    private long score;
  
    
    public PlayerProfile() {
       //test contructor autogenerated party
       this.playerParty = new Minion[4];
        for (int i = 0; i < playerParty.length; i++) {
            playerParty[i] = new Goblin();
        }
        this.Username = "test";
        this.gold = 850;
        this.score = 950;
    }
    
    public Minion[] getPlayerParty() {
    return this.playerParty;
    }
    
    public void setPlayerParty(Minion[] minions) {
    this.playerParty = minions;
    }
    
    public void receiveGold(long gold) {
        this.gold += gold;
    }
    
    public void receiveScore(long score) {
        this.score += score;
    }

    public String getUsername() {
        return Username;
    }

    public long getGold() {
        return gold;
    }

    public long getScore() {
        return score;
    }
    
    
}
