package it.goosegame.board.cell;

import it.goosegame.board.exception.RemovePlayerFromCellException;
import it.goosegame.board.player.Player;

public abstract class AbstractCell implements Cell {

	protected int indexCell;
	protected Player player;
	
	@Override
	public boolean isBusy() {
		return player!= null;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(Player player) {
		this.player=player;
	}



	@Override
	public boolean canPlayerMove() {
		return true;
	}

	@Override
	public int getIndexCell() {
		return indexCell;
	}


	@Override
	public int action(int diceValue) {
		return indexCell;
	}
	
	@Override
	public void inboundPlayer(Player player) {
		this.player=player;
	}
	
	@Override
	public void removePlayer(Player player) {
		if(this.player.getNickname().equalsIgnoreCase(player.getNickname())){
			this.player=null;
		}else{
			new RemovePlayerFromCellException("Player '"+player.getNickname()+"' doesn't not in this Cell");
		}
		
	}

}
