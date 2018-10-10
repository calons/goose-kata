package it.goosegame.board;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.goosegame.board.cell.BaseCell;
import it.goosegame.board.cell.BridgeCell;
import it.goosegame.board.cell.GooseCell;
import it.goosegame.board.cell.StartCell;
import it.goosegame.board.exception.GameBoardException;

public class GameBoardTest {

	private GameBoard gameBoard;
	@Before
	public void init(){
		this.gameBoard = GameBoard.builder().initBoard();
	}


	@Test
	public void startCellZeroIndexTest() throws GameBoardException {
		assertEquals(this.gameBoard.getCellByIndex(0).getClass(),StartCell.class);
	}

	@Test
	public void bridgeCell6IndexTest() throws GameBoardException {
		assertEquals(this.gameBoard.getCellByIndex(6).getClass(),BridgeCell.class);
	}

	@Test
	public void gooseCellesTest() throws GameBoardException {
		assertEquals(this.gameBoard.getCellByIndex(5).getClass(),GooseCell.class);
		assertEquals(this.gameBoard.getCellByIndex(9).getClass(),GooseCell.class);
		assertEquals(this.gameBoard.getCellByIndex(14).getClass(),GooseCell.class);
		assertEquals(this.gameBoard.getCellByIndex(18).getClass(),GooseCell.class);
		assertEquals(this.gameBoard.getCellByIndex(23).getClass(),GooseCell.class);
		assertEquals(this.gameBoard.getCellByIndex(27).getClass(),GooseCell.class);
	}

	@Test
	public void baseCell1IndexTest() throws GameBoardException {
		assertEquals(this.gameBoard.getCellByIndex(1).getClass(),BaseCell.class);
	}


}
