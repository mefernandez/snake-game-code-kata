package org.code.kata.snake;

public class MockSnakeRenderer implements SnakeRenderer {
	
	StringBuffer sb = new StringBuffer();

	@Override
	public void renderHead(Coordinates headCoordinates, char direction) {
		sb.append(direction);
		sb.append(headCoordinates);
	}

	@Override
	public void renderBody(Coordinates coordinates) {
		sb.append(coordinates);
	}

}
