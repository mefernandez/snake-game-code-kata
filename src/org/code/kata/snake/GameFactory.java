package org.code.kata.snake;

public class GameFactory {

	public static Game createGameWithYardDimensions(int width, int height) {
		Game game = new Game();
		game.yard = new Yard(width, height);
		game.randomCoordinatesGenerator = new DefaultRandomCoordinatesGenerator(width, height);
		game.clockProvider = new SystemClockProvider();
		return game;
	}

}
