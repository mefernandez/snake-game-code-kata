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

The obvious approach is to think of the snake object, its *properties* and its *behaviour*.

Properties seem to be easier to code into tests, for instance:
```java
@Test
public void aSnakeIs4UnitsLongByDefault() {
	Snake snake = new Snake();
	int length = snake.getLength();
	assertEquals(4, length);
}
```

The behaviour of a snake is mostly about how it moves or where it's going to. I preferred
to start with something simple, list this:
```java
@Test
public void canHeadUp() {
	Snake snake = new Snake();
	snake.headUp();
	String direction = snake.getCurrentDirection();
	assertEquals("U", direction);
}
```
	
Moving the snake led me to include coordinates in the snake object.

```java
@Test
public void itChangesCoordinatesWhenItMoves() throws Exception {
	Snake snake = new Snake();
	snake.headDown();
	snake.move();
	Coordinates headCoordinates = snake.getHeadCoordinates();
	assertEquals(0, headCoordinates.x);
	assertEquals(1, headCoordinates.y);
}
```

This test also starts showing some choices on where's the origin of the coordinates.
So far so good for the head's position. But how about the rest of the body?
How do I check it's following the head?

I decided to hold the state of the snake as a String attribute. To make assertions on the
attribute, I set a default visibility on it, so JUnit tests on the same package could just 
access the state.

```java
@Test
public void isFullyExtendedAtTheBeginning() {
	Snake snake = new Snake();
	assertEquals("LRRR", snake.state);
}
```

The downside is that the tests depend upon the internal state representation of the snake.
In this case, I chose to hold the state as a UDLR (Up Down Left Right) string. If at any
time I needed to move on to another representation, like a bitmap matrix, all of these tests 
would just go to the trash bin.

I think this matter comes close to the (Test vs. Behaviour) Driven Development discussion. 
For instance, let's say that testing the length of the snake and how it increases size 
when it eats and apple is closer to BDD, and that testing for a specific state after the snake
moves is closer to TDD, for it makes assertions on the object's internals.

Must I say, testing for specific UDLR patterns has been crucial to make the snake move around
as it is supposed to.

```java
@Test
public void canMoveUp() {
	Snake snake = new Snake();
	snake.headUp();
	snake.move();
	assertEquals("UDRR", snake.state);
}
```

Here the snake object starts with a "LRRR" state, that is heading left, completely horizontal.
Visually, it should be rendered exactly as it reads in the string.

```
LRRR
```

Then I tell the snake to head up and move in that direction. So the string should now be
"UDRR", which renders as follows:

```
U
DRR
```


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