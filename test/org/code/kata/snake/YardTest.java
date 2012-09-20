package org.code.kata.snake;

import static org.junit.Assert.*;

import org.junit.Test;

public class YardTest {
	
	@Test
	public void aYardIs10x10ByDefault() throws Exception {
		Yard yard = new Yard();
		assertEquals(10, yard.getWidth());
		assertEquals(10, yard.getHeight());
	}
	
	

	@Test
	public void renderAnEmptyDefaultYard() {
		Yard yard = new Yard();
		String emptyYard = 	"************" + "\n" + 
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"************" + "\n";
		String rendering = yard.render();
		assertEquals(emptyYard, rendering);
	}

	@Test
	public void renderAYardWithASnake() {
		Yard yard = new Yard();
		Snake snake = new Snake();
		yard.put(snake, new Coordinates(0,0));
		String emptyYard = 	"************" + "\n" + 
							"*LRRR      *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"*          *" + "\n" +
							"************" + "\n";
		String rendering = yard.render();
		assertEquals(emptyYard, rendering);
	}

	@Test
	public void renderAMovingSnake() {
		Yard yard = new Yard();
		Snake snake = new Snake();
		yard.put(snake, new Coordinates(0,0));
		snake.headDown();
		snake.move();
		
		String view = 	"************" + "\n" + 
						"*URR       *" + "\n" +
						"*D         *" + "\n" +
						"*          *" + "\n" +
						"*          *" + "\n" +
						"*          *" + "\n" +
						"*          *" + "\n" +
						"*          *" + "\n" +
						"*          *" + "\n" +
						"*          *" + "\n" +
						"*          *" + "\n" +
						"************" + "\n";
		String rendering = yard.render();
		assertEquals(view, rendering);
	}
	
	@Test
	public void renderSnakeMovingInCirclesCounterClockWise() {
		Yard yard = new Yard(4,4);
		Snake snake = new Snake();
		yard.put(snake, new Coordinates(0,0));
		
		String view = 	"******" + "\n" + 
						"*LRRR*" + "\n" +
						"*    *" + "\n" +
						"*    *" + "\n" +
						"*    *" + "\n" +
						"******" + "\n";
		String rendering = yard.render();
		assertEquals(view, rendering);

		snake.headDown();
		snake.move();
		rendering = yard.render();
		view = 	"******" + "\n" + 
				"*URR *" + "\n" +
				"*D   *" + "\n" +
				"*    *" + "\n" +
				"*    *" + "\n" +
				"******" + "\n";
		assertEquals(view, rendering);

		snake.move();
		rendering = yard.render();
		view = 	"******" + "\n" + 
				"*UR  *" + "\n" +
				"*U   *" + "\n" +
				"*D   *" + "\n" +
				"*    *" + "\n" +
				"******" + "\n";
		assertEquals(view, rendering);

		snake.move();
		rendering = yard.render();
		view = 	"******" + "\n" + 
				"*U   *" + "\n" +
				"*U   *" + "\n" +
				"*U   *" + "\n" +
				"*D   *" + "\n" +
				"******" + "\n";
		assertEquals(view, rendering);

		snake.headRight();
		snake.move();
		rendering = yard.render();
		view = 	"******" + "\n" + 
				"*    *" + "\n" +
				"*U   *" + "\n" +
				"*U   *" + "\n" +
				"*LR  *" + "\n" +
				"******" + "\n";
		assertEquals(view, rendering);

		snake.move();
		rendering = yard.render();
		view = 	"******" + "\n" + 
				"*    *" + "\n" +
				"*    *" + "\n" +
				"*U   *" + "\n" +
				"*LLR *" + "\n" +
				"******" + "\n";
		assertEquals(view, rendering);

		snake.move();
		rendering = yard.render();
		view = 	"******" + "\n" + 
				"*    *" + "\n" +
				"*    *" + "\n" +
				"*    *" + "\n" +
				"*LLLR*" + "\n" +
				"******" + "\n";
		assertEquals(view, rendering);

		snake.headUp();
		snake.move();
		rendering = yard.render();
		view = 	"******" + "\n" + 
				"*    *" + "\n" +
				"*    *" + "\n" +
				"*   U*" + "\n" +
				"* LLD*" + "\n" +
				"******" + "\n";
		assertEquals(view, rendering);

		snake.move();
		rendering = yard.render();
		view = 	"******" + "\n" + 
				"*    *" + "\n" +
				"*   U*" + "\n" +
				"*   D*" + "\n" +
				"*  LD*" + "\n" +
				"******" + "\n";
		assertEquals(view, rendering);
	
		snake.move();
		rendering = yard.render();
		view = 	"******" + "\n" + 
				"*   U*" + "\n" +
				"*   D*" + "\n" +
				"*   D*" + "\n" +
				"*   D*" + "\n" +
				"******" + "\n";
		assertEquals(view, rendering);
	
		snake.headLeft();
		snake.move();
		rendering = yard.render();
		view = 	"******" + "\n" + 
				"*  LR*" + "\n" +
				"*   D*" + "\n" +
				"*   D*" + "\n" +
				"*    *" + "\n" +
				"******" + "\n";
		assertEquals(view, rendering);

		snake.headLeft();
		snake.move();
		rendering = yard.render();
		view = 	"******" + "\n" + 
				"* LRR*" + "\n" +
				"*   D*" + "\n" +
				"*    *" + "\n" +
				"*    *" + "\n" +
				"******" + "\n";
		assertEquals(view, rendering);	
	
		snake.headLeft();
		snake.move();
		rendering = yard.render();
		view = 	"******" + "\n" + 
				"*LRRR*" + "\n" +
				"*    *" + "\n" +
				"*    *" + "\n" +
				"*    *" + "\n" +
				"******" + "\n";
		assertEquals(view, rendering);	
	
	
	}
}
