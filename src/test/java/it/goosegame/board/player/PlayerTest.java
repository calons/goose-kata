package it.goosegame.board.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.goosegame.board.exception.PlayerBuilderException;
public class PlayerTest {

	@Test
	public void testBuild() throws PlayerBuilderException {
		String nickname="akira";
		int id = 9;
		Player player = Player.builder().nickname(nickname).id(id).build();

		assertEquals(player.getNickname(),nickname);
		assertEquals(player.getId(),id);
	}

	@Test(expected = PlayerBuilderException.class) 
	public void testBuildNicknameNull() throws PlayerBuilderException {
		Player.builder().nickname(null).build();

	}

	@Test(expected = PlayerBuilderException.class) 
	public void testBuildNicknameEmpty() throws PlayerBuilderException {
		Player.builder().nickname("").build();

	}

	@Test(expected = PlayerBuilderException.class) 
	public void testBuildIdNotSet() throws PlayerBuilderException {
		Player.builder().nickname("akira").build();
	}



}
