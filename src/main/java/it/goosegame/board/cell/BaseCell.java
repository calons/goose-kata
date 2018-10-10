/**
 * @author fabrizio.caloni
 */
package it.goosegame.board.cell;

public class BaseCell extends AbstractCell  {

	
	public BaseCell(int index){
		indexCell=index;
	}
	

	@Override
	public String getLabel() {
		return Integer.valueOf(indexCell).toString();
	}


}
