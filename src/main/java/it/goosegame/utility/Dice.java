/**
 * @author fabrizio.caloni
 */
package it.goosegame.utility;

public class Dice {

	public static int throwDoubleDice(){
		int diceOne=throwDice();
		int diceTwo=throwDice();
		System.out.println(diceOne + ","+diceTwo);
		return diceOne+diceTwo;
	}
	
	public static int throwDice(){
		return ((int) (Math.random()*90000) % 6)+ 1; 
	}
}
