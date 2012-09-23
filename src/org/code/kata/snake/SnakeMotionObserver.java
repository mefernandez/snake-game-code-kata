package org.code.kata.snake;

public interface SnakeMotionObserver {

	public void updateSnakePosition(Coordinates headCoordinates) throws SnakeHitYardWallException;

}
