package it.goosegame.board.command;

import java.util.List;

import it.goosegame.board.GameBoard;
import it.goosegame.board.exception.CommandException;
import it.goosegame.board.player.Player;

public class AddCommand implements Command{

	@Override
	public boolean run(String[] command,GameBoard gameBoard, List<Player> playerList) throws CommandException {

		try {
			if(!this.validateAddCommand(command)){
				throw new CommandException("Command doesn't not valid");
			}

			Player player = Player.builder().nickname(command[2]).id(playerList.size()+1).build();
			if(this.playerExists(playerList,player.getNickname())){
				throw new CommandException(player.getNickname()+": already existing player");
			}
			if (playerList.size()>=2){
				throw new CommandException("Allows only two players");
			}else{
				playerList.add(player);
				System.out.println("players: "+playerList.toString());
			}
		} catch (Exception e) {
			throw new CommandException(e.getMessage());
		}
		return true;
	}

	private boolean playerExists( List<Player> playerList,String nickanme) {
		for (Player player : playerList) {
			if(player.getNickname().equalsIgnoreCase(nickanme)){
				return true;
			}
		}
		return false;
	}

	private boolean validateAddCommand(String[] command) {
		return (command.length==3) && command[1].equalsIgnoreCase("player")&& !command[2].equals("");
	}

}
