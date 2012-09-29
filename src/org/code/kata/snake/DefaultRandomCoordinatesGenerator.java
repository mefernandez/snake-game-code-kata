package org.code.kata.snake;

import java.util.Random;

public class DefaultRandomCoordinatesGenerator implements
		RandomCoordinatesGenerator {
	
	int width;
	int height;

	public DefaultRandomCoordinatesGenerator(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public Coordinates generate() {
		Random rnd = new Random();
		int x = rnd.nextInt(this.width);
		int y = rnd.nextInt(this.height);
		Coordinates c = new Coordinates(x, y);
		return c;
	}

}
