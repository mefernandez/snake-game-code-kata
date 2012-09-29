package org.code.kata.snake;

public class MockClockProvider implements ClockProvider {
	

	private long milliseconds = System.currentTimeMillis();

	public void updateTime(long milliseconds) {
		this.milliseconds += milliseconds;
	}

	@Override
	public long getTime() {
		return this.milliseconds;
	}

}
