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
        self.direction = [0, 0]
        self.scribe_list = [["Square", 5], ["Sideways Triangle", [0, 0], 135], ["Bottom Right Line", [0, 0], 135],
                  ["Staircase", [0, 0], 120], ["Horizontal Line", [0, 0], 0], ["Vertical Line", [0, 0], 180]]

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

    def drawSideTriangle(self, steps):
        for i in range(steps):
            self.forward()
        for i in range(steps):
            self.backward()
        # need to fix range later. It goes too far
        for i in range(steps * 2):
            self.up()

    def forward(self):
        # towards quadrant 4
        pos = [self.pos[0] + self.direction[0], self.pos[1] + self.direction[1]]
        if not self.canvas.hitsWall(pos):
            self.draw(pos)

    def backward(self):
        # towards quadrant 3
        pos = [self.pos[0] - self.direction[0], self.pos[1] + self.direction[1]]
        if not self.canvas.hitsWall(pos):
            self.draw(pos)

    def drawSquare(self, size):
        for i in range(size):
            self.right()
        for i in range(size):
            self.down()
        for i in range(size):
            self.left()
        for i in range(size):
            self.up()

    def execute(self, num):
        match num:
            case 1:
                self.drawSquare(self.scribe_list[[0][1]])
            case 2:
                self.drawSideTriangle(self.scribe_list[0][2])
            case 3:
                self.direction = 135
                var = [self.forward() in range(8)]
            # case 4:
                # SKIP 4 NOW (pun intended)
            case 5:
                var = [self.right() in range(8)]
            case 6:
                car = [self.down() in range(8)]


# Create a new Canvas instance that is 30 units wide by 30 units tall
canvas = Canvas(30, 30)
# Create a new scribe and give it the Canvas object
scribe = TerminalScribe(canvas)
# order for lists in scribe: name, position, direction
print(
    "Choose the shape number from the below options:\n1: Square\n2: Sideways Triangle\n3: Bottom Right Line\n"
    "4: Staircase\n5: Horizontal Line\n6: Vertical Line")
try:
    choice = int(input("Number Choice: "))

except:
    print("Please input a number")
    choice = int(input("Number Choice: "))

scribe.execute(choice)
# this only works for 135 degrees lol. Otherwise, it makes a staircase... could be another shape easily
# scribe.setDir(135)
# scribe.drawTriangle(10)
# Draw a small square
# scribe.drawSquare(5)
# for i in range(0, 10):
#    for j in range(0, 10):
#        scribe.draw((i, j))
# reminder: pos format is [ , ], so double parantheses here to ensure one parameter
