package it.goosegame.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.goosegame.board.GameBoard;
import it.goosegame.board.command.AddCommand;
import it.goosegame.board.command.Command;
import it.goosegame.board.command.CommandReader;
import it.goosegame.board.command.StartCommand;
import it.goosegame.board.exception.CommandException;
import it.goosegame.board.player.Player;

public class Game {

	private GameBoard gameboard;
	private List<Player> playerList= new ArrayList<Player>();


	public void run() {
		this.init();	

		boolean execution = true;
		Scanner scanner = new Scanner(System.in);
		while (execution) {

			try {
				String[] command = CommandReader.readCommand(scanner);
				Command c=null;
				switch (command[0].toLowerCase()) {
				case "add":
					c = new AddCommand();
					break;
				case "start":
					c = new StartCommand();
					break;
				case "move":
					throw new CommandException("Before use 'add player' and 'start' command");
				default:
					throw new CommandException("No command recognized");
				}

				execution= c.run(command,this.gameboard, this.playerList);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Game Completed");
		scanner.close();
	}


	private void init(){
		System.out.println("Init board");
		this.gameboard= GameBoard.builder().initBoard();
	}


}
