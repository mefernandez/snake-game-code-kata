package org.code.kata.snake;

public interface SnakeRenderer {

	void renderHead(Coordinates headCoordinates, char direction);

	void renderBody(Coordinates coordinates);

}
