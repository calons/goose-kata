/**
 * @author fabrizio.caloni
 */
package it.goosegame.board.player;

import it.goosegame.board.cell.Cell;
import it.goosegame.board.exception.PlayerBuilderException;

public class Player {
	private String nickname;
	private int id;
	private Cell cell;
	private boolean playerTurn=false;



	public Player(int id,String nickname ) {
		super();
		this.nickname = nickname;
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public static Player.Builder builder() {
		return new Player.Builder();
	}

	public boolean isPlayerTurn() {
		return this.playerTurn;
	}

	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}

	public Cell getCell() {
		return this.cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	@Override
	public String toString() {
		return this.nickname ;
	}


	public static final class Builder {
		private String nickname;
		private int id;
		private Builder(){
		}

		public Player build() throws PlayerBuilderException {
			if ((this.nickname== null) || this.nickname.trim().equals("")){
				throw new PlayerBuilderException("'Nickname' must not null or empty");
			}
			if (this.id <= 0){
				throw new PlayerBuilderException("'id' must be > 0");
			}
			return new Player(this.id, this.nickname);
		}

		public Builder nickname(String nickname){
			this.nickname=nickname;
			return this;
		}

		public Builder id(int id){
			this.id=id;
			return this;
		}

	}


}
