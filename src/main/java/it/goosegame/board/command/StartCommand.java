package it.goosegame.board.command;

import java.util.List;

import it.goosegame.board.GameBoard;
import it.goosegame.board.cell.Cell;
import it.goosegame.board.exception.CommandException;
import it.goosegame.board.exception.GameBoardException;
import it.goosegame.board.player.Player;
import it.goosegame.game.StartGame;

public class StartCommand implements Command{

	@Override
	public boolean run(String[] command, GameBoard gameBoard, List<Player> playerList) throws CommandException {
		if (playerList.size()!=2){
			throw new CommandException("Players must be two! Please insert the player");
		}
		try {
			this.addUserToStartCell( gameBoard, playerList);
			StartGame sg= new StartGame();
			return sg.runGame( gameBoard,  playerList);

		} catch (GameBoardException e) {
			throw new CommandException(e.getMessage());
		}		
	}

	private GameBoard addUserToStartCell(GameBoard gameBoard, List<Player> playerList) throws GameBoardException {
		GameBoard gameBoardTmp =  gameBoard;
		Cell startCell=gameBoard.getCellByIndex(0);
		int i=0;
		for (Player player : playerList) {
			if (i==0){
				player.setPlayerTurn(true);
				i++;
			}
			startCell.setPlayer(player);
			player.setCell(startCell);
		}
		return gameBoardTmp;
	}


}
