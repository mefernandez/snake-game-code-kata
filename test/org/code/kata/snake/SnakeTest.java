package org.code.kata.snake;

import static org.junit.Assert.*;

import org.junit.Test;

public class SnakeTest {

	@Test
	public void aSnakeIs4UnitsLongByDefault() {
		Snake snake = new Snake();
		int length = snake.getLength();
		assertEquals(4, length);
	}
	
	@Test
	public void canHeadUp() {
		Snake snake = new Snake();
		snake.headUp();
		String direction = snake.getCurrentDirection();
		assertEquals("U", direction);
	}

	@Test
	public void canHeadDown() {
		Snake snake = new Snake();
		snake.headDown();
		String direction = snake.getCurrentDirection();
		assertEquals("D", direction);
	}
	
	@Test
	public void canHeadRight() {
		Snake snake = new Snake();
		snake.state = "ULLL";
		snake.headRight();
		String direction = snake.getCurrentDirection();
		assertEquals("R", direction);
	}
	@Test
	public void isFullyExtendedAtTheBeginning() {
		Snake snake = new Snake();
		assertEquals("LRRR", snake.state);
	}
	@Test
	public void canMoveUp() throws SnakeHitYardWallException {
		Snake snake = new Snake();
		snake.headUp();
		snake.move();
		assertEquals("UDRR", snake.state);
	}
	@Test
	public void canMoveDown() throws SnakeHitYardWallException {
		Snake snake = new Snake();
		snake.headDown();
		snake.move();
		assertEquals("DURR", snake.state);
	}
	
	@Test
	public void canMoveRight() throws SnakeHitYardWallException {
		Snake snake = new Snake();
		snake.state = "ULLL";
		snake.headRight();
		snake.move();
		assertEquals("RLLL", snake.state);
	}
	
	@Test
	public void cannotHeadBackwards() {
		Snake snake = new Snake();
		snake.state = "RLLL";
		snake.headLeft();
		assertEquals("RLLL", snake.state);
		snake.state = "LRRR";
		snake.headRight();
		assertEquals("LRRR", snake.state);
		snake.state = "UDDD";
		snake.headDown();
		assertEquals("UDDD", snake.state);
		snake.state = "DUUU";
		snake.headDown();
		assertEquals("DUUU", snake.state);
	}
	
	@Test
	public void theHeadIsAtX0Y0CoordinatesByDefault() throws Exception {
		Snake snake = new Snake();
		Coordinates coordinates = snake.getHeadCoordinates();
		assertEquals(0, coordinates.x);
		assertEquals(0, coordinates.y);
	}
	
	@Test
	public void itChangesCoordinatesWhenItMoves() throws Exception {
		Snake snake = new Snake();
		snake.headDown();
		snake.move();
		Coordinates headCoordinates = snake.getHeadCoordinates();
		assertEquals(0, headCoordinates.x);
		assertEquals(1, headCoordinates.y);
	}
	
	@Test
	public void snakeHorizontalMatrixRepresentation() {
		Snake snake = new Snake();
		snake.state = "LRRR";
		char[][] matrix = new char[1][4];
		snake.placeIn2DMatrix(matrix);
		char[][] expected = new char[][] {"LRRR".toCharArray()};
		assertArrayEquals(expected, matrix);
	}
	
	@Test
	public void snakeVerticalMatrixRepresentation() {
		Snake snake = new Snake();
		snake.state = "UDDD";
		char[][] matrix = new char[4][1];
		snake.placeIn2DMatrix(matrix);
		char[][] expected = new char[][] {{'U'},{'D'},{'D'},{'D'}};
		assertArrayEquals(expected, matrix);
	}

	@Test
	public void snakeDiagonalMatrixRepresentation() {
		Snake snake = new Snake();
		snake.state = "UDRD";
		char[][] matrix = new char[3][3];
		snake.placeIn2DMatrix(matrix);
		char[][] expected = new char[][] {{'U',0,0},{'D','R',0},{0,'D',0}};
		assertArrayEquals(expected, matrix);
	}
	
	@Test
	public void delegateSnakeRenderingToExternalObject() {
		Snake snake = new Snake();
		snake.state = "UDRU";
		MockSnakeRenderer renderer = new MockSnakeRenderer();
		snake.render(renderer);
		assertEquals("U(0,0)(0,1)(1,1)(1,0)", renderer.sb.toString());
	}

	@Test
	public void grows1UnitWhenItEatsAndThenMoves() throws SnakeHitYardWallException {
		Snake snake = new Snake();
		snake.state = "RLLL";
		snake.setHeadCoordinates(new Coordinates(3,0));
		snake.eat();
		assertEquals(4, snake.getLength());
		snake.move();
		assertEquals(5, snake.getLength());
		assertEquals("RLLLL", snake.state);
		assertEquals(4, snake.getHeadCoordinates().x);
		snake.move();
		assertEquals(5, snake.getLength());
		assertEquals(5, snake.getHeadCoordinates().x);
	}
	
}
