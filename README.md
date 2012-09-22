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
to start with something simple, like this:
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

At this point I just needed to visualize the snake. So I started to think of a Yard
holding the Snake and how to render it. I chose to render the Yard and the Snake as
Strings. This made it easy to test the renderization.

```java
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
```

An empty yard was easy, but composing the renderization of the yard with a snake in it was
a bit more difficult. The composition of the yard and the snake was too difficult to be done
on a String basis. At last, a matrix came along.

```java
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
...
}
```
 
So the Yard builds a matrix of its size and passes it to the snake. The snake takes this matrix
and places its body on it, starting at the head and moving along according to the UDLR state.

Pending challenges
------------------

To this date, these are the pending challenges:

- Actually make the game come to life. I mean, make the game tick, get keyboard input and
  watch the snake move around in the screen.
- Program the logic of the snake running into itself. 
- Make the integration with the Android SDK Snake project sample. 



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