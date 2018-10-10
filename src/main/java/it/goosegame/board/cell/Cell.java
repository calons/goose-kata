package it.goosegame.board.cell;

import it.goosegame.board.player.Player;

public interface Cell {
	
	public boolean isBusy();
	
	public Player getPlayer();
	
	public void setPlayer(Player player);
	
	public void inboundPlayer(Player player);
	
	public boolean canPlayerMove();
	
	public int getIndexCell();
	
	public String getLabel();
	
	public int action(int diceValue);
	
	public void removePlayer(Player player);

}
