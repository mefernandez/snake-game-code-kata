var Snake = function() {
  this.string = "ULL";
};

Snake.prototype.eat = function(apple) {
  this.string += this.string.charAt(this.string.length -1);
};

Snake.prototype.__defineGetter__("length", function(){
   return this.string.length;
});

Snake.prototype.__defineGetter__("direction", function(){
   return this.string[0];
});

Snake.prototype.down = function() {
  this.string = "D" + this.string.substr(1);
};

Snake.prototype.left = function() {
  if (this.string.charAt(1) !== "L")
    this.string = "L" + this.string.substr(1);
};

Snake.prototype.move = function() {
  var replacement;
  if (this.direction === "D")
    replacement = "U";
  else
    replacement = "D";
  this.string = this.string.replace("L", replacement);
};

Snake.prototype.up = function() {
  this.string = "U" + this.string.substr(1);
};

var Yard = function() {
  this.width = 4;
  this.height = 4;
};

