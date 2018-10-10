package it.goosegame.board.command;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.goosegame.board.GameBoard;
import it.goosegame.board.exception.CommandException;
import it.goosegame.board.exception.PlayerBuilderException;
import it.goosegame.board.player.Player;

public class MoveCommandTest {

	private GameBoard gameBoard;
	private Command c ;

	@Before
	public void init(){
		this.gameBoard = GameBoard.builder().initBoard();
		this.c = new MoveCommand();
	}
	@Test (expected=CommandException.class)
	public void validateCommandTest() throws CommandException {
		List<Player> playerList = new ArrayList<Player>();
		String[] command = {"move"};
		this.c.run(command, this.gameBoard, playerList);
	}

	@Test (expected=CommandException.class)
	public void userNotExistsTest() throws CommandException, PlayerBuilderException {
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(Player.builder().nickname("Player_1").id(1).build());
		playerList.add(Player.builder().nickname("Player_2").id(2).build());
		String[] command = {"move","Player_3","1,1"};
		this.c.run(command, this.gameBoard, playerList);
	}

	@Test (expected=CommandException.class)
	public void userTurnWrongTest() throws PlayerBuilderException, CommandException {
		List<Player> playerList = new ArrayList<Player>();
		Player player1=Player.builder().nickname("Player_1").id(1).build();
		//set turn player 1
		player1.setPlayerTurn(true);

		Player player2=Player.builder().nickname("Player_2").id(2).build();

		playerList.add(player1);
		playerList.add(player2);

		String[] command = {"move","Player_2"};

		this.c.run(command, this.gameBoard, playerList);
	}

}
