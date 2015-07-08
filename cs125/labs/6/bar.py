#File:    bar.py
#Author:  Carl Stevenson
#Date:    10/4/12
#Purpose: To graph a chart of values.

from graphics import *
from random import *

def main():
    
    #tell user what the program does
    
    print("\nThis program with generate a bar chart based on")
    print("data stored in a file.\n")

    #get the file name

    filename = input("Enter the name of the file: ")

    infile = open(filename, 'r')

    #get the axis labels

    vertAxisLabel = infile.readline()
    horizAxisLabel = infile.readline()

    #strip off the returns

    vertAxisLabel = vertAxisLabel[0:-1]
    horizAxisLabel = horizAxisLabel[0:-1]

    totalValues = 0

    #read in all the values and store in a list called values

    values = [] #empty list

    for line in infile:
        if (line != ''):
            num = int(line)
            values.append(num)
            totalValues = totalValues + 1
    infile.close()

    print(totalValues, "items read in.")

    #start setting up the bar chart

    winWidth = 600
    winHeight = 500
    graphWidth = 400
    graphHeight = 400
    topGap = 50
    leftGap = 150
    maxValue = 50 #largest bar height
    


    #create the window

    win = GraphWin("Bar Chart", winWidth, winHeight)
    win.setBackground('white')

    #create the axes

    p1 = Point(leftGap, topGap + graphHeight)
    p2 = Point(leftGap, topGap)
    p3 = Point(leftGap + graphWidth, topGap + graphHeight)

    vertAxis = Line(p1, p2)
    horizAxis = Line(p1, p3)
    vertAxis.draw(win)
    horizAxis.draw(win)
    
    #draw and label hashmarks

    hashHeight = graphHeight / 5
    totalHashes = 5
    hashWidth = 10

    myName = Text(Point((p1.getX() + p3.getX())/2, topGap/2), "Carl Stevenson")
    myName.draw(win)


    for k in range(totalHashes):
        x1Loc = leftGap - hashWidth/2
        x2Loc = x1Loc + hashWidth
        y1Loc = p1.getY() - hashHeight * (k + 1)

        hashLine = Line(Point(x1Loc, y1Loc), Point(x2Loc, y1Loc))
        hashLine.draw(win)

        #generate labels

        hashStr = str((k+1) * 10)
        hashLabel = Text(Point(x1Loc - 20, y1Loc), hashStr)
        hashLabel.draw(win)

    #generate the bars

    barSpace = graphWidth // totalValues
    barWidth = barSpace * (7/8)


    for i in range (totalValues):
        x2Loc = leftGap + (i + 1) * barSpace

        x1Loc = x2Loc - barWidth

        y2Loc = topGap + graphHeight
        scaleVal = graphHeight / maxValue
        barHeight = scaleVal * min(maxValue, values[i])

        y1Loc = y2Loc - barHeight

        bar = Rectangle(Point(x1Loc, y1Loc), Point(x2Loc, y2Loc))

        #pick a random color

        redVal = randrange(0,256)
        greenVal = randrange(0,256)
        blueVal = randrange(0,256)

        bar.setFill(color_rgb(redVal, greenVal, blueVal))
        bar.draw(win)
        







    win.getMouse()
    win.close()
    
main()
