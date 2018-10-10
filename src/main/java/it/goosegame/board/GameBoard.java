package it.goosegame.board;

import it.goosegame.board.cell.BaseCell;
import it.goosegame.board.cell.BridgeCell;
import it.goosegame.board.cell.Cell;
import it.goosegame.board.cell.GooseCell;
import it.goosegame.board.cell.StartCell;
import it.goosegame.board.exception.GameBoardException;

public class GameBoard {

	public final static int TOT_CELL_NUMBER=64;
	private Cell[] cellBoardList = new Cell[64];


	public GameBoard(Cell[] cellBoardList) {
		super();
		this.cellBoardList = cellBoardList;
	}

	public Cell getCellByIndex(int index) throws GameBoardException{

		if (index>TOT_CELL_NUMBER){
			throw new GameBoardException("The index "+index+" out of range");
		}
		return this.cellBoardList[index];

	}


	public static GameBoard.Builder builder() {
		return new GameBoard.Builder();
	}

	public static final class Builder {
		private Cell[] cellBoardListTmp=new Cell[GameBoard.TOT_CELL_NUMBER]; ;
		private Builder(){
		}

		public GameBoard initBoard()  {

			for (int i = 0; i < GameBoard.TOT_CELL_NUMBER; i++) {
				if(i==0){
					this.cellBoardListTmp[i]=new StartCell();
				}else if(i==6){
					this.cellBoardListTmp[i]= new BridgeCell(i);
				}else if ((i==5) || (i==9) || (i==14) ||(i==18) ||(i==23) ||(i==27)){
					this.cellBoardListTmp[i]=new GooseCell(i);
				}else{
					this.cellBoardListTmp[i]=new BaseCell(i);
				}
			}
			return new GameBoard(this.cellBoardListTmp);
		}

	}

}
