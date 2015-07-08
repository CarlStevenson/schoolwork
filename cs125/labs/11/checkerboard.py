#Title:   checkerboard.py
#Author:  Carl Stevenson
#Date:    11/15/2012
#Purpose: To display a checkerboard based on input.

def writeRowIndented(row, col, size, check):
    line = ""
    for i in range(1, col + 1):
        if i % 2 == 0:
            line = line + (size * check)
        elif i % 2 != 0:
            line = line + (size * " ")
    for i in range(size):
        
        print(line)

def writeRow(row, col, size, check):
    line = ""
    for i in range(1, col + 1):  
        if i % 2 != 0:
            line = line + (size * check)
        elif i % 2 == 0:
            line = line + (size * " ")
    for i in range (size):

        print(line)

def checkerboard(row, col, size, check):
    #print each row for how many row you need
    for i in range(1, row+1):
        if i % 2 != 0:
            writeRow(row, col, size, check)
        elif i % 2 == 0:
            writeRowIndented(row, col, size, check)
    
def values():
    row = int(input("How many rows? "))
    print()
    col = int(input("How many columns? "))
    print()
    size = int(input("What is the block size? "))
    print()
    check = input("What is the checkerboard character? ")
    print()
    check = check[0]
    return row, col, size, check

def main():
    print()
    print("This program will display a checkerboard.")
    print("You will enter the number of rows, columns,")
    print("block size and the checkerboard character.")
    print("Written by carl Stevenson.")
    print()
    row, col, size, check = values()
    checkerboard(row, col, size, check)


main()
