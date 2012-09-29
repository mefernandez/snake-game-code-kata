package org.code.kata.snake;

public class SystemClockProvider implements ClockProvider {
	
	@Override
	public long getTime() {
		return System.currentTimeMillis();
	}

}
