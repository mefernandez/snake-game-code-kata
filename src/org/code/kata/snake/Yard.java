package org.code.kata.snake;

import java.util.Arrays;

public class Yard {

	private int width = 10;
	private int height = 10;
	char[][] matrix = new char[height][width];
	private Snake snake;

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Yard(int width, int height) {
		this.width = width;
		this.height = height;
		this.matrix = new char[height][width];
	}

	public Yard() {

	}

	public void put(Snake snake, Coordinates coordinates) {
		snake.headCoordinates.x = coordinates.x;
		snake.headCoordinates.y = coordinates.y;
		this.snake = snake;
	}

	public String render() {
		if (this.snake != null) {
			this.cleanMatrix();
			this.snake.placeIn2DMatrix(this.matrix);

		}

		StringBuffer sb = new StringBuffer();
		this.renderMatrix(sb);
		this.renderWalls(sb);
		return sb.toString();
	}

	private void cleanMatrix() {
		for (int j = 0; j < this.height; j++) {
			for (int i = 0; i < this.width; i++) {
				this.matrix[j][i] = 0;
			}
		}
	}

	private void renderWalls(StringBuffer sb) {
		int fromIndex = sb.indexOf("\n");
		int toIndex = 0;
		while (fromIndex > -1) {
			sb.insert(toIndex, '*');
			sb.insert(fromIndex + 1, '*');
			toIndex = fromIndex + 3;
			fromIndex = sb.indexOf("\n", fromIndex + 3);
		}
		String hWall = String
				.format(String.format("%%0%dd", this.width + 2), 0).replace(
						"0", "*");
		sb.insert(0, hWall + "\n");
		sb.append(hWall + "\n");
	}

	void renderMatrix(StringBuffer sb) {
		for (int j = 0; j < this.height; j++) {
			for (int i = 0; i < this.width; i++) {
				char c = this.matrix[j][i];
				if (c == 0)
					c = ' ';
				sb.append(c);
			}
			sb.append('\n');
		}
	}
}
