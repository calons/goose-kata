package it.goosegame.board.command;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.goosegame.board.GameBoard;
import it.goosegame.board.exception.CommandException;
import it.goosegame.board.player.Player;

public class AddCommandTest {

	private GameBoard gameBoard;
	private Command c ;

	@Before
	public void init(){
		this.gameBoard = GameBoard.builder().initBoard();
		this.c = new AddCommand();
	}


	@Test
	public void addPlayerTest() throws CommandException {
		List<Player> playerList = new ArrayList<Player>();
		String[] command = {"add","player","fca"};
		this.c.run(command, this.gameBoard, playerList);
		assertEquals(playerList.size(),1);

	}


	@Test (expected = CommandException.class)
	public void addExistsPlayerTest() throws CommandException {
		List<Player> playerList = new ArrayList<Player>();
		String[] command = {"add","player","fca"};
		this.c.run(command, this.gameBoard, playerList);
		this.c.run(command, this.gameBoard, playerList);

	}


}
