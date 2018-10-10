package it.goosegame.board.cell;

public class GooseCell extends AbstractCell {

	
	public GooseCell(int indexCell) {
		super();
		this.indexCell = indexCell;
	}


	@Override
	public String getLabel() {
		return indexCell +", the Goose";
	}

	@Override
	public int action(int diceValue) {
		return indexCell+diceValue;
	}


}
