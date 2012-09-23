package org.code.kata.snake;

public class Snake {
	
	/* The initial state reads:
	 * The snake is moving Left,
	 * and the 3 remaining parts of the body are placed to its Right.
	 */
	String state = "LRRR";
	/*
	 * The default coordinates (0,0) makes the initial state
	 * a valid one to be represented in a 1x4 matrix.
	 * [L,R,R,R], which is also visually correct.
	 */
	Coordinates headCoordinates = new Coordinates(0,0);
	SnakeMotionObserver snakeMotionObserver = new SnakeMotionObserver() {
		
		@Override
		public void updateSnakePosition(Coordinates headCoordinates)
				throws SnakeHitYardWallException {
			// NullObjectPattern, don't track the snake's position
			
		}
	};
	

	public SnakeMotionObserver getSnakeMotionObserver() {
		return snakeMotionObserver;
	}

	public void setSnakeMotionObserver(SnakeMotionObserver snakeMotionObserver) {
		this.snakeMotionObserver = snakeMotionObserver;
	}

	public int getLength() {
		return this.state.length();
	}

	public void headUp() {
		if (!"D".equals(this.getCurrentDirection()))
		this.state = "U" + this.state.substring(1);;
	}

	public String getCurrentDirection() {
		return "" + this.state.charAt(0);
	}

	public void headDown() {
		if (!"U".equals(this.getCurrentDirection()))
		this.state = "D" + this.state.substring(1);
	}

	public void headLeft() {
		if (!"R".equals(this.getCurrentDirection()))
		this.state = "L"  + this.state.substring(1);
	}

	public void headRight() {
		if (!"L".equals(this.getCurrentDirection()))
		this.state = "R" + this.state.substring(1);
	}

	public void move() throws SnakeHitYardWallException {
		String tail = this.state.substring(1, this.state.length()-1);
		String head = this.getCurrentDirection();
		String neck = "D";
		if ("D".equals(head)) {
			neck = "U";
			this.headCoordinates.y++;
		} else if ("U".equals(head)) {
			neck = "D";
			this.headCoordinates.y--;
		} else if ("R".equals(head)) {
			neck = "L";
			this.headCoordinates.x++;
		} else if ("L".equals(head)) {
			neck = "R";
			this.headCoordinates.x--;
		}
		this.state = head + neck + tail;
		this.snakeMotionObserver.updateSnakePosition(this.headCoordinates);
	}

	public void setHeadCoordinates(Coordinates coordinates) {
		this.headCoordinates = coordinates;
	}

	public Coordinates getHeadCoordinates() {
		return this.headCoordinates;
	}

	/**
	 * 
	 * @param matrix, in (row, col) zero-based index fashion.
	 * e.g. In the following matrix of size 2x3 (2 rows, 3 cols)
	 * the number 1 is at (1,2):
	 * 0 0 0
	 * 0 0 1
	 * Thus, the (0,0) coordinates is at top-left.
	 */
	public void placeIn2DMatrix(char[][] matrix) {
		int hx = this.headCoordinates.x;
		int hy = this.headCoordinates.y;
		matrix[hy][hx] = this.state.charAt(0);
		int x = hx;
		int y = hy;
		for (int i=1; i<this.state.length(); i++) {
			char c = this.state.charAt(i);
			switch (c) {
			case 'U':
				y--;
				break;
			case 'D':
				y++;
				break;
			case 'R':
				x++;
				break;
			case 'L':
				x--; 
				break;
			}
			if (x>=0 && y>=0 && y<matrix.length && x<matrix[0].length)
				matrix[y][x] = c;
		}
		
	}

	
}
