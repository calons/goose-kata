package it.goosegame.board.command;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.goosegame.board.GameBoard;
import it.goosegame.board.exception.CommandException;
import it.goosegame.board.player.Player;

public class StartCommandTest {

	private GameBoard gameBoard;
	private Command c ;

	@Before
	public void init(){
		this.gameBoard = GameBoard.builder().initBoard();
		this.c = new StartCommand();
	}


	@Test (expected = CommandException.class)
	public void twoPlayertest() throws CommandException {
		List<Player> playerList = new ArrayList<Player>();
		String[] command = {"start"};
		this.c.run(command, this.gameBoard, playerList);
	}

}
