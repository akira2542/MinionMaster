/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author SURAPONGCHAMALAI
 */
public class TimeStopper {

    
    public void Delay() {
        try {

            System.out.println("Delay 1 sec");

            TimeUnit.SECONDS.sleep(1);
            System.out.println("Finish...");

        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

    public void userInput() {
        Scanner uInput = new Scanner(System.in);
        String enterkey = "Press Enter";
        System.out.println(enterkey);
        enterkey = uInput.nextLine();
        System.out.println(enterkey);
        if (enterkey == "") {
            System.out.println("Done");
        }
    }
}
