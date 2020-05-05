/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author CHANANAN
 */
public class NullStatusException extends Exception{
    private static final String MSG = "Statuses is not suppose to be null"; 
    public NullStatusException()
   {
      super (MSG);
   }
}
