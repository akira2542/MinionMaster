/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author SURAPONGCHAMALAI
 */
public abstract class PlayableMinion {
    private long exppool;
    
    //คำนวณ exp
    public int returnlvl(long exp){
        //รับค่า exp มา
     
        int lvl = 1;
      long exppool = 500;
     long i ;
      // exppool = (exppool * 40)/100 + exppool;
        for  (i = exppool; i < exp; i += i*40/100)
          
            if(exp<=500){
                lvl = 1;
            }else if(i>exp){
                lvl = lvl-1;
            }else{
                lvl = lvl+1;
                System.out.println(lvl);
                System.out.println(i);
            }
        return lvl;
    }
    }

