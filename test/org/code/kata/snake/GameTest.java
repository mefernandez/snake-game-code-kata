package org.code.kata.snake;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;

import org.junit.Before;
import org.junit.Test;

public class GameTest {
	
	Game game;
	MockCoordinatesGenerator randomCoordinatesGenerator;
	MockClockProvider clockProvider;

	@Before
	public void setUp() {
		game = new Game();
		game.yard = new Yard();
		randomCoordinatesGenerator = new MockCoordinatesGenerator();
		Coordinates coordinates = new Coordinates(1,0);
		randomCoordinatesGenerator.setCoordinates(coordinates);
		game.setRandomCoordinatesGenerator(randomCoordinatesGenerator);
		clockProvider = new MockClockProvider();
		game.setTickingClock(clockProvider);
		game.init();		
	}
	

	@Test
	public void theSnakeMovesWhenTheGameStartsTicking() throws SnakeHitYardWallException {
		Yard yard = game.getYard();
		long delay = game.getDelay();
		Snake snake = yard.getSnake();
		clockProvider.updateTime(delay);
		game.tick();
		Coordinates coordinates = snake.getHeadCoordinates();
		assertEquals(0, coordinates.x);
	}
	
	@Test
	public void createNewGameWithYardDimensions() {
		int width = 10;
		int height = 10;
		Game game = GameFactory.createGameWithYardDimensions(width, height);
		Yard yard = game.getYard();
		assertNotNull(yard);
		assertEquals(width, yard.getWidth());
		assertEquals(height, yard.getHeight());
		assertNotNull(game.clockProvider);
		assertNotNull(game.randomCoordinatesGenerator);
	}
}
