import time
import os
import math


class Canvas:
    def __init__(self, width, height):
        self._x = width
        self._y = height
        # using underscore to indicate these are internal variables that should not be accessed outside of canvas class
        self._canvas = [[' ' for y in range(self._y)] for x in range(self._x)]

    def hitsWall(self, point):
        return round(point[0]) < 0 or round(point[0]) >= self._x or round(point[1]) < 0 or round(point[1]) >= self._y

    def setPos(self, pos, mark):
        # updates the element at position 0,1 and then sets it to the character or the mark that gets passed in
        self._canvas[round(pos[0])][round(pos[1])] = mark
        # up

    def clear(self):
        os.system('cls' if os.name == 'nt' else 'clear')

    def print(self):
        self.clear()
        for y in range(self._y):
            print(' '.join([col[y] for col in self._canvas]))


class TerminalScribe:
    # takes in a canvas class that it's supposed to draw on
    def __init__(self, canvas):
        self.canvas = canvas
        self.pos = [0, 0]
        self.framerate = 0.08
        self.mark = '*'
        self.trail = '.'
        self.direction = [0, 1]

    def setDir(self, dir):
        h = round(math.sin((dir / 180) * math.pi), 1)
        v = round(-math.cos((dir / 180) * math.pi), 1)
        self.direction = [h, v]

    def up(self):
        pos = [self.pos[0], self.pos[1] - 1]
        if not self.canvas.hitsWall(pos):
            self.draw(pos)

    def down(self):
        pos = [self.pos[0], self.pos[1] + 1]
        if not self.canvas.hitsWall(pos):
            self.draw(pos)

    def right(self):
        pos = [self.pos[0] + 1, self.pos[1]]
        if not self.canvas.hitsWall(pos):
            self.draw(pos)

    def left(self):
        pos = [self.pos[0] - 1, self.pos[1]]
        if not self.canvas.hitsWall(pos):
            self.draw(pos)

    def draw(self, pos):
        self.canvas.setPos(self.pos, self.trail)
        self.pos = pos
        self.canvas.setPos(self.pos, self.mark)
        self.canvas.print()
        time.sleep(self.framerate)

    def drawEquiTriangle(self, size):
        # work on after challenge 2
        # starting point of triangle, prob off if it's an odd sized triangle
        midPoint = int(size / 2)
        if size % 2 == 1:
            midPoint += 1


    def forward(self):
        pos = [self.pos[0] + self.direction[0], self.pos[1] + self.direction[1]]
        if not self.canvas.hitsWall(pos):
            self.draw(pos)


    def drawSquare(self, size):
        it = 0
        while it < size:
            self.right()
            it += 1
        it = 0
        while it < size:
            self.down()
            it += 1
        it = 0
        while it < size:
            self.left()
            it += 1
        it = 0
        while it < size:
            self.up()
            it += 1



# Create a new Canvas instance that is 30 units wide by 30 units tall
canvas = Canvas(30, 30)

# Create a new scribe and give it the Canvas object
scribe = TerminalScribe(canvas)
#this only works for 135 degrees lol. Otherwise,
scribe.setDir(135)
for i in range(30):
    scribe.forward()
# Draw a small square
#scribe.drawSquare(5)
# for i in range(0, 10):
#    for j in range(0, 10):
#        scribe.draw((i, j))
# reminder: pos format is [ , ], so double parantheses here to ensure one parameter
