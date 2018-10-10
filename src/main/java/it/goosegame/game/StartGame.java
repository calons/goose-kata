package it.goosegame.game;

import java.util.List;
import java.util.Scanner;

import it.goosegame.board.GameBoard;
import it.goosegame.board.command.Command;
import it.goosegame.board.command.CommandReader;
import it.goosegame.board.command.MoveCommand;
import it.goosegame.board.player.Player;

public class StartGame {

	public boolean runGame(GameBoard gameBoard, List<Player> playerList){

		System.out.println("Start Game");
		System.out.println(playerList.get(0).getNickname()+" move first");

		Scanner scanner = new Scanner(System.in);
		boolean gaming = true;
		while (gaming) {

			String[] command = CommandReader.readCommand(scanner);
			Command c ;
			if (command[0].equalsIgnoreCase("move")){

				c=new MoveCommand();
				try {
					gaming = c.run(command, gameBoard, playerList);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}else{
				System.out.println("Invalid command");
			}

		}
		System.out.println("End Game");
		scanner.close();
		return false;
	}

}
