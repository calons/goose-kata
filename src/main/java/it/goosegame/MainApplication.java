/**
 * @author fabrizio.caloni
 */
package it.goosegame;

import it.goosegame.game.Game;

public class MainApplication {
	public static void main(String[] args) {
		System.out.println("Start Game");
		
		Game game=  new Game();
		game.run();
	}
}
