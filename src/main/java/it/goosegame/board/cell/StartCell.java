/**
 * @author fabrizio.caloni
 */
package it.goosegame.board.cell;

import java.util.ArrayList;
import java.util.List;

import it.goosegame.board.exception.RemovePlayerFromCellException;
import it.goosegame.board.player.Player;

public class StartCell extends AbstractCell {

	private int indexCell;

	private List<Player> startListPlayer = new ArrayList<>();

	@Override
	public boolean isBusy() {
		return false;
	}

	@Override
	public Player getPlayer() {
		return null;
	}

	@Override
	public void setPlayer(Player player) {
		startListPlayer.add(player);

	}

	@Override
	public boolean canPlayerMove() {
		return true;
	}

	@Override
	public String getLabel() {
		return "Start";
	}

	@Override
	public int action(int diceValue) {
		return indexCell;
	}

	@Override
	public void removePlayer(Player player) {
		for (Player playerFromList : startListPlayer) {
			if(playerFromList.getNickname().equalsIgnoreCase(player.getNickname())){
				startListPlayer.remove(playerFromList);
				return;
			}
		}
		new RemovePlayerFromCellException("Player '"+player.getNickname()+"' doesn't not in this Cell");

	}

}
