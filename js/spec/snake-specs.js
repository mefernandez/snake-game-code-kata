describe('A Snake',function(){

var snake;
beforeEach(function(){
    snake = new Snake();

});
    
    it('is 3 units long by default',function(){
    expect(snake.length).toBe(3);
    });
  
  it('heads up at the beggining',function(){
    // xxo
    expect(snake.direction).toBe("U");
  });
  
  it('grows when it eats an apple',function(){
    // xxo
    var apple = {};
    snake.eat(apple);
    // xxxo
    expect(snake.length).toBe(4);
  });
  
  it('is represented by a String (LRUD)',function(){
    // xxo
    expect(snake.string).toBe("ULL");
  });
  
  it('can head down',function(){
    snake.down();
    // xxo
    expect(snake.string).toBe("DLL");
    snake.move();
    // xx
    //  o
    expect(snake.string).toBe("DUL");
    snake.move();
    //  x
    //  x
    //  o
    expect(snake.string).toBe("DUU");
  });
  
  it('can head up',function(){
    snake.up();
    // xxo
    expect(snake.string).toBe("ULL");
    snake.move();
    //  o
    // xx
    expect(snake.string).toBe("UDL");
    snake.move();
    //  o
    //  x
    //  x
    expect(snake.string).toBe("UDD");
  });

  it('does not change to contrary direction',function(){
    snake.left();
    // xxo
    expect(snake.string).toBe("ULL");
  });

});


describe('A Yard',function(){
it('is a 4x4 square',function(){
  var yard = new Yard();
  expect(yard.width).toBe(4);
  expect(yard.height).toBe(4);
  });
});