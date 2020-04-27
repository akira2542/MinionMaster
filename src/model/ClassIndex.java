/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author COM
 */
public enum ClassIndex {
    KNIGHT(101),PRIEST(102),DUELIST(103),THIEF(104),GOBLIN(201),ORC(202);
    
    private final int index;
    public static final int KNIGHT_INDEX = 101;
    public static final int PREIST_INDEX = 102;
    public static final int DUELIST_INDEX = 103;
    public static final int THIEF_INDEX = 104;
   
    public static final int GOBLIN_INDEX = 201;
    public static final int ORC_INDEX = 202;
    
    ClassIndex(final int newindex){
    index = newindex;
    }
    public int getIndex() {
    return index;
    }
}
