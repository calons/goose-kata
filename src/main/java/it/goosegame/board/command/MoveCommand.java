package it.goosegame.board.command;

import java.util.List;

import it.goosegame.board.GameBoard;
import it.goosegame.board.cell.Cell;
import it.goosegame.board.cell.GooseCell;
import it.goosegame.board.exception.CommandException;
import it.goosegame.board.exception.DiceException;
import it.goosegame.board.exception.GameBoardException;
import it.goosegame.board.exception.MoveCommandExceptionException;
import it.goosegame.board.player.Player;
import it.goosegame.utility.Dice;

public class MoveCommand implements Command{


	@Override
	public boolean run(String[] command, GameBoard gameBoard, List<Player> playerList) throws CommandException {
		try {

			StringBuilder message = new StringBuilder();
			String[] commandValidated=this.validateMoveCommand(playerList,command);
			Player playerTurn= this.retrievePlayer(commandValidated, playerList);

			int diceValue=this.retrieveDiceValue(commandValidated);
			message.append(playerTurn.getNickname()+" rolls "+commandValidated[2]+". ");

			Cell currentPlayerCell= playerTurn.getCell();
			if (currentPlayerCell.canPlayerMove()){
				int nextIndex=this.calculateIndextDestinationCell(gameBoard, currentPlayerCell.getIndexCell(),diceValue);
				message.append(playerTurn.getNickname()+" moves from "+currentPlayerCell.getIndexCell()
				+" to "+this.destinationString(gameBoard, diceValue, currentPlayerCell)+". ");

				Cell destinationCell=this.getNextCell(gameBoard,nextIndex);
				if(destinationCell.isBusy()){
					message.append("On "+this.writeCell(destinationCell)
					+" there is " + destinationCell.getPlayer().getNickname()+",  who returns to "+currentPlayerCell.getLabel());

					currentPlayerCell.setPlayer(destinationCell.getPlayer());
					this.getPlayerFromList(playerList,destinationCell.getPlayer()).setCell(currentPlayerCell);
				}else{
					currentPlayerCell.removePlayer(playerTurn);
				}

				int nextIndexTmp=destinationCell.action( diceValue);

				if (destinationCell instanceof GooseCell){
					do {
						message =message.append(playerTurn.getNickname()+ " moves again and goes to "+nextIndexTmp+". ");
						destinationCell=gameBoard.getCellByIndex(nextIndexTmp);
						nextIndexTmp=destinationCell.action( diceValue);

					} while (destinationCell.getIndexCell()!=nextIndexTmp);
				}
				destinationCell=this.getNextCell(gameBoard,nextIndexTmp);
				destinationCell.inboundPlayer(playerTurn);
				playerTurn.setCell(destinationCell);


				if(destinationCell.getIndexCell()==(GameBoard.TOT_CELL_NUMBER-1)){
					message = message.append(playerTurn.getNickname()+ " Wins!!");
					System.out.println(message);
					return false;
				}
				else if ((diceValue+currentPlayerCell.getIndexCell()) >=GameBoard.TOT_CELL_NUMBER){
					message.append(playerTurn.getNickname() + " bounces! "+playerTurn.getNickname()+" returns to "
							+nextIndex);
				}
				else if(destinationCell.getIndexCell()==12){
					message.append(playerTurn.getNickname()+" jumps to 12");

				}
				System.out.println(message);
				this.changePlayerTurn(playerList);

			}else{
				System.out.println(playerTurn.getNickname() + " skip turn");
			}
		} catch (Exception e) {
			throw new CommandException(e.getMessage());
		}
		return true;
	}

	private void changePlayerTurn(List<Player> playerList) {
		for (Player player : playerList) {
			player.setPlayerTurn(!player.isPlayerTurn());
		}

	}

	private String[] validateMoveCommand(List<Player> playerList,String[] command ) throws MoveCommandExceptionException {
		String[] commandTmp ;
		if (command.length==2){
			commandTmp= new String[3];
			for (int i = 0; i < command.length; i++) {
				commandTmp[i]=command[i];
			}
			commandTmp[command.length]=Dice.throwDice()+","+Dice.throwDice();
		}else{
			commandTmp=command;
		}

		if(!this.playerExists( playerList,commandTmp[1])){
			throw new MoveCommandExceptionException("Invalid command, player missing");
		}

		return commandTmp;
	}
	private int retrieveDiceValue(String[] command) throws MoveCommandExceptionException,DiceException {
		String[] diceValue= command[2].split(",");
		if(diceValue.length!=2){
			throw new MoveCommandExceptionException("The command param '"+command[2]+"' is invalid, It must be number,number");
		}
		int tot=0;
		try {
			for (int i = 0; i < diceValue.length; i++) {
				int dice=Integer.valueOf(diceValue[i]);
				if ((dice>0) && (dice <7)){
					tot+= Integer.valueOf(diceValue[i]);
				}else{
					throw new DiceException("Value of "+(i+1)+ "° dice is invalid, it must be between 1 and 6");
				}
			}
		} catch (NumberFormatException e) {
			throw new MoveCommandExceptionException("The command param '"+command[2]+"' is invalid");
		}
		return tot;
	}

	private boolean playerExists(List<Player> playerList,String nickanme) {
		for (Player player : playerList) {
			if(player.getNickname().equalsIgnoreCase(nickanme)){
				return true;
			}
		}
		return false;
	}

	private Player retrievePlayer(String[] command, List<Player> playerList) throws MoveCommandExceptionException {
		String nickname= command[1];
		Player playerTmp=null;

		for (Player player : playerList) {
			if (player.isPlayerTurn()){
				playerTmp=player;
				break;
			}
		}

		if (playerTmp!= null){
			if(playerTmp.getNickname().equalsIgnoreCase(nickname)){
				return playerTmp;
			}else{
				throw new MoveCommandExceptionException("Change player, '"+nickname +
						"' isn't your turn.");
			}
		}else{
			throw new MoveCommandExceptionException("Player, "+nickname +
					" out of game");
		}
	}

	private String destinationString(GameBoard gameBoard,int diceValue, Cell currentPlayerCell) throws GameBoardException {
		return ((currentPlayerCell.getIndexCell()+diceValue)>(GameBoard.TOT_CELL_NUMBER-1)?
				Integer.valueOf(GameBoard.TOT_CELL_NUMBER-1).toString():
					gameBoard.getCellByIndex(currentPlayerCell.getIndexCell()+diceValue).getLabel());
	}

	private Player getPlayerFromList(List<Player> playerList,Player player) {
		for (Player p : playerList) {
			if (p.getNickname().equalsIgnoreCase(player.getNickname())){
				return p;
			}
		}
		return null;

	}

	private int calculateIndextDestinationCell(GameBoard gameBoard,int diceValue, int playerCurrentIndex) {
		if((diceValue+playerCurrentIndex)<(GameBoard.TOT_CELL_NUMBER)){
			return diceValue+playerCurrentIndex;
		}else{
			return this.calculateIndexOverBoardLimit (GameBoard.TOT_CELL_NUMBER,playerCurrentIndex, diceValue );
		}
	}

	private int calculateIndexOverBoardLimit(int totCellNumber, int playerCurrentIndex, int diceValue) {
		return totCellNumber-1 - (diceValue - (totCellNumber-1 - playerCurrentIndex));
	}

	private String writeCell(Cell cell) {
		return cell.getLabel();
	}

	private Cell getNextCell(GameBoard gameBoard,int i) throws GameBoardException {
		return gameBoard.getCellByIndex(i);
	}

}
