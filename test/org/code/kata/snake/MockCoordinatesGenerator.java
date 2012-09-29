package org.code.kata.snake;

public class MockCoordinatesGenerator implements RandomCoordinatesGenerator {
	
	Coordinates coordinates;

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public Coordinates generate() {
		return this.coordinates;
	}

}
