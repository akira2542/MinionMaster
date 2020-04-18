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
public abstract class Dungeon {
    
  private  BattleField[]  waveofdungeon;//ห้องที่สู้อยู่
  private  long reward;//ของรางวัลเช่น จำนวนเงิน
  private  long expfromclear;//ค่าexpที่ได้จากการลงดันนี้
  
  
  public void expEarn(){
      //currentexp + expfromclear
  }
  
}
