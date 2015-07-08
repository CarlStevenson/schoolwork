#Title:   witchPic.py
#Author:  Carl Stevenson
#Date:    10/18/2012
#Purpose: To draw a crude picture of a witch.


from graphics import *

#make things WAY easier

def text(win):
    tex1 = Text(Point(160, 300), 'Carl Stevenson')
    tex1.draw(win)
    tex2 = Text(Point(160, 325), 'likes')
    tex2.draw(win)
    tex3 = Text(Point(160, 350), 'CS 125')
    tex3.draw(win)
   
def tooth(x, y, win):
    too = Polygon(Point((x-2),(y-2)), Point((x+1),(y-2)), Point((x+2),y),
                  Point(x,(y+2)))
    too.draw(win)
    too.setFill('white')
    too.setOutline('black')
   
def mouth(x, y, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, color, win):
    pol = Polygon(Point(x, y), Point(x2, y2), Point(x3, y3), Point(x4, y4),
                  Point(x5, y5), Point(x6, y6))
    pol.draw(win)
    pol.setFill(color)
    pol.setOutline(color)

def eye(x, y, radius, color, coloro, win):

    c = Circle(Point(x, y), radius)
    c.draw(win)
    c.setFill(color)
    c.setOutline(coloro)

def quad(x, y, x2, y2, x3, y3, x4, y4, color, win):
    qua = Polygon(Point(x, y), Point(x2, y2), Point(x3, y3), Point(x4, y4))
    qua.draw(win)
    qua.setFill(color)
    qua.setOutline(color)

def triangle(x, y, x2, y2, x3, y3, color, win):
    tri = Polygon(Point(x, y), Point(x2, y2), Point(x3, y3))
    tri.draw(win)
    tri.setFill(color)
    tri.setOutline(color)

def circle(x, y, radius, color, win):
    c = Circle(Point(x, y), radius)
    c.draw(win)
    c.setFill(color)
    c.setOutline(color)

def hat(win):
    #pattern follows 'x1pixel, y1pixel, x2pixel, y2pixel...color, window'
    #from the upper leftmost point, clockwise around
    triangle(15, 47, 140, 10, 96, 125, 'black', win)
    circle(70, 0, 64, 'white', win)
    triangle(45, 80, 123, 95, 58, 163, 'white', win)

    quad(110, 90, 141, 10, 188, 5, 235, 70, 'black', win)
    circle(235, 70, 47, 'black', win)
    triangle(188, 5, 280, 32, 222, 51, 'white', win)
    triangle(186, 75, 260, 100, 210, 123, 'black', win)
    quad(235, 117, 253, 101, 270, 127, 240, 140, 'black', win)
    quad(225, 152, 274, 130, 295, 184, 250, 200, 'black', win)
    quad(230, 117, 260, 103, 275, 132, 240, 145, 'black', win)
    triangle(240, 200, 263, 200, 220, 263, 'black', win)
    triangle(270, 188, 295, 183, 300, 197, 'black', win)
    triangle(250, 200, 275, 188, 263, 200, 'black', win)
    triangle(240, 200, 238, 178, 250, 200, 'black', win)
    triangle(86, 128, 102, 180, 52, 180, 'black', win)
    quad(86, 128, 120, 101, 125, 150, 101, 177, 'black', win)
    quad(95, 125, 120, 125, 125, 85, 150, 83, 'black', win)
    quad(113, 110, 130, 85, 135, 95, 110, 113, 'black', win)
   

def face(win):
    quad(100, 175, 125, 186, 118, 233, 113, 235, 'yellow', win)
    quad(100, 175, 138, 150, 170, 175, 125, 186, 'yellow', win)
    quad(138, 150, 130, 113, 173, 83, 213, 133, 'yellow', win)
    quad(138, 150, 213, 133, 216, 155, 170, 175, 'yellow', win)
    quad(170, 175, 216, 155, 230, 200, 170, 225, 'yellow', win)
    quad(170, 225, 230, 200, 175, 270, 160, 265, 'yellow', win)
    quad(216, 125, 225, 125, 230, 133, 220, 146, 'yellow', win)
    circle(166, 179, 2, 'black', win)
    quad(145, 175, 175, 170, 200, 205, 175, 250, 'yellow', win)
   
def features(win):
    eye(155, 135, 10, 'white', 'black', win)
    circle(157, 137, 3, 'black', win)
    mouth(150,196,172,183,186,168,188,190,179,212,157,222,'white',win)
    #eyebrow
    quad(130, 124, 137, 95, 140, 97, 135, 122, 'black', win)
    quad(137, 95, 188, 95, 178, 100, 140, 97, 'black', win)
   
    #teeth
    tooth(154, 196, win)
    tooth(162, 190, win)
    tooth(170, 184, win)
    tooth(178, 176, win)
    tooth(186, 171, win)
    tooth(186, 182, win)
    tooth(184, 194, win)
    tooth(180, 205, win)
    tooth(175, 213, win)
    tooth(165, 217, win)

#modular!
def main():
    #make the window
    win = GraphWin("witchPic", 335, 375)
    win.setBackground('white')
    #call each piece
    face(win)
    hat(win)
    features(win)
    text(win)
    #close the window
    win.getMouse()
    win.close()
  
main()

#happy Halloween!
