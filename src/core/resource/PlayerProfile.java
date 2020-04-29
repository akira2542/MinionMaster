package core.resource;

import content.minion.Goblin;
import content.playableminon.Duelist;
import content.playableminon.Knight;
import content.playableminon.Priest;
import content.playableminon.Thief;
import java.io.Serializable;
import java.util.Iterator;
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
public class PlayerProfile implements Serializable,Iterable<Minion>{
    
    private final String username;
    private Minion[] playerParty;
    private long gold;
    private long score;
    private int token;
  
    
    public PlayerProfile() {
       //test contructor autogenerated party
       this.playerParty = new Minion[4];
        for (int i = 0; i < playerParty.length; i++) {
            playerParty[i] = new Thief();
        }
        this.username = "test";
        this.gold = 850;
        this.score = 950;
        this.token = 5;
    }
    
    public PlayerProfile(String username) {
    this.username = username;
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
    
    public void receiveToken(int token) {
        this.token += token;
    }

    public String getUsername() {
        return username;
    }

    public long getGold() {
        return gold;
    }

    public long getScore() {
        return score;
    }

    public int getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "PlayerProfile{" + "username=" + username + ", gold=" + gold + ", score=" + score + ", token=" + token + '}';
    }

    @Override
    public Iterator<Minion> iterator() {
        return new Iterator() {
            int count = 0;
            @Override
            public boolean hasNext() {
                return count < playerParty.length;
            }

            @Override
            public Minion next() {
                return playerParty[count++];
            }
        };
    }
 
    
    
    
}
