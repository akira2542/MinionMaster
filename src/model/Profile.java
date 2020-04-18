/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author COM
 */

public abstract class Profile implements Serializable {
    // parents class for PlayerProfile
    private String username;//ชื่อไอดี
    private long   score; //คะแนนสะสม
    private Minion[] partyofminion;//current party
    
}
