# File:    pumpWeights.py
# Author:  Carl Stevenson
# Date:    11/1/2012
# Purpose: To classify pumpkins based on weight, and give the 
#          average, maximum, and minimum weight.

#1
def intro():
    print()
    print("Program to calculate statistics of a")
    print("group of pumpkin weights.")
    print("You will be asked to enter the number of")
    print("pumpkins, followed by each pumpkin weight.")
    print("Written by Carl Stevenson.")
    print()

#2
def cl():
    pumpnum = int(input("Enter the number of pumpkins: "))
    print() #for turnin
    pumpl = []
    num = 1
    print()
    for i in range (pumpnum):
        weight = eval(input("Enter the weight for pumpkin " + str(num) +  ": "))
        print() #for turnin
        if weight >= 1500:
            print("{0:0.2f} is humongous".format(weight))
        elif weight >= 500:
            print("{0:0.2f} is huge".format(weight))
        elif weight >= 300:
            print("{0:0.2f} is heavy".format(weight))
        elif weight >= 100:
            print("{0:0.2f} is medium".format(weight))
        elif weight >= 50:
            print("{0:0.2f} is featherweight".format(weight))
        else:
            print("{0:0.2f} is light".format(weight))
        num = num + 1
        pumpl.append(weight) 
    
    print()
    return pumpnum, pumpl

#3
def output(pumpnum, pumpl):
    print("Number of pumpkins:", pumpnum)
    avg(pumpnum, pumpl)
    print("The maximum weight is  {0:0.4f}" .format(max(pumpl)))
    print("The minimum weight is  {0:0.4f}" .format(min(pumpl)))
    print()

#4
def avg(pumpnum, pumpl):
    tot = 0 
    for l in pumpl:
        tot = tot + l
    average = tot / pumpnum
    print("The average weight is {0:0.4f}" .format(average))


#5
def main():
    #intro
    intro()
    #classification & input
    pumpnum, pumpl = cl()
    #output
    #average
    #max
    #min
    output(pumpnum, pumpl)


main()
