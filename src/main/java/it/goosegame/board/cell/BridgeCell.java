package it.goosegame.board.cell;

public class BridgeCell extends AbstractCell {

		
	

	public BridgeCell(int index) {
		indexCell=index;
	}

	@Override
	public String getLabel() {
		return "The Bridge";
	}
	
	@Override
	public int action(int diceValue) {
		return 12;
	}

}
