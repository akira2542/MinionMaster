/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author SURAPONGCHAMALAI
 */
public class TimeStopper {

    
    
    public static void Delay(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);     
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }

    }
    
    public static void Delay() {
        Delay(1);
    }
   

    public static void userInput() {
        Scanner uInput = new Scanner(System.in);
        String enterkey = "Press {Enter} to continue";
        System.out.println(enterkey);
        enterkey = uInput.nextLine();
        System.out.println(enterkey);
        if (enterkey == "") {
            System.out.println("Done");
        }
    }
}
