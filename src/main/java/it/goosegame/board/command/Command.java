package it.goosegame.board.command;

import java.util.List;

import it.goosegame.board.GameBoard;
import it.goosegame.board.exception.CommandException;
import it.goosegame.board.player.Player;

public interface Command {

	public boolean run (String[] command, GameBoard gameBoard, List<Player> playerList) throws CommandException;

}
