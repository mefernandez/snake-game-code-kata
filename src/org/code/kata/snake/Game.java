package org.code.kata.snake;


public class Game {
	
	RandomCoordinatesGenerator randomCoordinatesGenerator;
	Yard yard;
	ClockProvider clockProvider;
	long time;
	long delay = 500;
	
	public void setRandomCoordinatesGenerator(
			RandomCoordinatesGenerator randomCoordinatesGenerator) {
		this.randomCoordinatesGenerator = randomCoordinatesGenerator;
		
	}

	public Yard getYard() {
		return yard;
	}

	public void init() {
		Coordinates initialCoordinates = this.randomCoordinatesGenerator.generate();
		Snake snake = new Snake();
		yard.put(snake, initialCoordinates);
	}

	public void setTickingClock(ClockProvider clockProvider) {
		this.clockProvider = clockProvider;
	}
	
	public void tick() throws SnakeHitYardWallException {
		long current = this.clockProvider.getTime();
		long dif = current - this.time;
		this.time = current;
		if (dif >= delay) {
				this.yard.getSnake().move();
		}
	}

	public long getDelay() {
		return this.delay;
	}

}
