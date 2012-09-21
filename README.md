Snake game code kata
====================

Kata objective
--------------

Write the classic Snake game TDD style.
The game description can be found here:
http://en.wikipedia.org/wiki/Snake_(video_game)

The goal is to be able to play the game using the keyboard.
The graphics rendering can be as simple as just text output in the system console, or whatever
canvas you find easier to draw in your programming language of choice.


Walkthrough
-----------

The obvious approach is to think of the snake object, what properties does it have, and
how does it behave.

Properties seem to be easier to code into tests, for instance:

	@Test
	public void aSnakeIs4UnitsLongByDefault() {
		Snake snake = new Snake();
		int length = snake.getLength();
		assertEquals(4, length);
	}

The behaviour of a snake is mostly about how it moves or where it's going to. I preferred
to start with something simple, list this:

	@Test
	public void canHeadUp() {
		Snake snake = new Snake();
		snake.headUp();
		String direction = snake.getCurrentDirection();
		assertEquals("U", direction);
	}
	


Some personal background
------------------------

Way back in 1990's my parents got me a x286, my first computer. I was only 13, but I soon
grasped some very basic skills with QBASIC to program a few very simple games.

QBASIC included one game I found awesome: Nibbles. 

http://en.wikipedia.org/wiki/Nibbles_(video_game)

Nibbles is the classic game of a snake that crawls around the screen eating apples 
and growing longer. The objective of the game is that the snake doesn't run into itself 
as it grows longer.

Back to the present. I recently downloaded the Android SDK to give it a try. 
There are a number of sample projects included in it. One of these is the classic snake game.
You can find a fork of the source code here:

https://github.com/mefernandez/android-snake-example

I modified it to accept touch screens instead of UDLR keys to change the snake's direction.
Looking at the code I wondered if I could detach the game logic from the Android stuff.
So I engaged in developing the game TDD style independently from the presentation and platform
technologies. The next challenge is to integrate this with the Android project and make it
work.