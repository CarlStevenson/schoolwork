#File:    mole.py
#Author:  Carl Stevenson
#Date:    10/25/12
#Purpose: To write a program to calculate the weight of a hydrocarbon


#split into 4 functions
def printResults(weight, name, numH, numC, numO):
    print("For", name, "which has", numH, "Hydrogen,")
    print(numC, "Carbon and", numO, "Oxygen atoms, its weight")
    print("is {0:0.3f} grams/mole." .format(weight))
    print()

def calcWeight(numH, numC, numO):
    wH = numH * 1.0079
    wC = numC * 12.011
    wO = numO * 15.994
    weight = wH + wC + wO
    return weight

def intro():
    print()
    print("Program to calculate the molecular")
    print("weight of a hydrocarbon based upon")
    print("the number of hydrogen, carbon and")
    print("oxygen atoms.")
    print("Written by Carl Stevenson.")
    print()

def getInputs():
    name = input("Enter the hydrocarbon name: ")
    print()
    numH = eval(input("Enter the number of Hydrogen atoms: "))
    print()
    numC = eval(input("Enter the number of Carbon atoms:   "))
    print()
    numO = eval(input("Enter the number of Oxygen atoms:   "))
    print()
    print()
    return name, numH, numC, numO

def main():
    #call the functions
    intro()
    name, numH, numC, numO = getInputs()
    weight = calcWeight(numH, numC, numO)
    printResults(weight, name, numH, numC, numO)


main()
