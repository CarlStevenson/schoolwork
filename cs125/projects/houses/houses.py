# File:    houses.py
# Author:  Carl Stevenson
# Date:    11/29/2012
# Purpose: Take input from a file and output an organized chart of
#          house sales.


# top-down design
def main():
    intro()
    infile, filename = getFile()
    l = manData(infile, filename)
    outputChart(l, filename)


def intro():
    print()
    print("Program to output a yearly report of house sales.")
    print("Written by Carl Stevenson.")
    print()

def getFile():
    filename = input("Enter the file name: ")
    print() #for turnin
    print()
    infile = open(filename,'r')
    return infile, filename

def manData(infile, filename):
    # initialize array
    l = []
    # make a 1D array out of each line and append it to a larger list, 
    # making it a 2D array
    for line in infile:
        initrow = []
        stringlist = line.split()
        for val in stringlist:
            stringnum = eval(val)
            initrow.append(stringnum)
        l.append(initrow)
    return l

def outputChart(l, filename):
    # intro to chart
    print("For the file {0}: " .format(filename))
    print()
    # break up chart into 4 main sections
    # define variables used in them
    colnum = int(len(l[1]))
    rownum = int(len(l))
    header(l, colnum)
    column(l, colnum)
    body(l, colnum)
    tail(l, colnum, rownum)

def header(l, colnum):
    # left group spaces
    print((10 * " "), end="")
    # center group, left spaces before header
    print(int((((colnum*4)/2)-6)) * " ", end='')
    print("Salesperson")

def column(l, colnum):
    # left group head
    print("  Model  :", end = '')
    # head for each salesperson
    for x in range(colnum):
        print("{0:4}".format(x+1), end='')
    print("  ", end='')
    # right group head
    print(":  Avg Totals")
    
def body(l, colnum):
    # line
    print((((colnum*4)+2) * "-") + (23 * "-")) 
    for x in range(len(l)):
        lineOut(l, x, colnum)
    # line
    print((((colnum*4)+2) * "-") + (23 * "-"))

def lineOut(l, x, colnum):
    # row number
    print("{0:^9}:".format(x+1), end="")
    # initialize values for later
    rowsum = 0
    rowavg = 0
    # print each salesperson's sales
    for y in range(colnum):
        print("{0:4}".format(l[x][y]), end="")
        rowsum = rowsum + l[x][y]
    print("  :", end="")
    # calculate the average from the sum gotten previously
    rowavg = (rowsum / colnum)
    # print the month's average
    print("{0:5.1f}".format(rowavg), end="")
    # print the month's sum
    print("{0:5}  ".format(rowsum))

def tail(l, colnum, rownum):
    # initialize needed values, consolidate retrieving values into 
    # 1 loop, putting max and totals into lists to be displayed
    # later. As optimized as I can think to make it, albeit messy.
    saleavg = 0
    salesum = 0
    salemax = 0
    maxL = []
    totalsL = []
    saletotals = 0
    # left group label
    print("Average  :", end="")
    # combining a loop to get values and print the first line
    for col in range(colnum):
        for row in range(rownum):
            salesum = salesum + l[row][col]
            if l[row][col] > salemax:
                salemax = l[row][col]
        maxL.append(salemax)
        totalsL.append(salesum)
        saletotals = saletotals + salesum
        saleavg = (salesum / rownum)
        salesum = 0
        salemax = 0
        print("{0:4.1f}" .format(saleavg), end="")
    print()  # next line
    # print the maximums
    print("Maximum  :", end="")
    for x in range(colnum):
        print("{0:4}".format(maxL[x]), end="")
    print()
    # print the totals
    print(" Totals  :", end="")
    for x in range(colnum):
        print("{0:4}" .format(totalsL[x]), end="")
    print("  :{0:10}" .format(saletotals))
    print()
    
    
main()
