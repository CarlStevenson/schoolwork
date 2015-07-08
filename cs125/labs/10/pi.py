#File:    pi.py
#Author:  Carl Stevenson
#Date:    11/8/12
#Purpose: To calculate the value of pi through two methods.

def intro():
    print()
    print("Program to calculate pi by 2 methods")
    print("Written by Carl Stevenson")
    print()

def calc():
    iteration = int(input("How many terms would you like? "))
    print()#for turnin
    print()
    print("Pi by alternating sums    Pi by odd products")
    sumalt = 0
    sumodd = 0
    diffalt = 0
    diffodd = 0
    pi = 3.14159265359
    
    for i in range (iteration):
        #alternating sum
        if (i % 2) == 0:
            sign = 1
        else:
            sign = -1
        sumalt = sumalt +  (4.0 * (sign * (1.0/((i * 2) + 1))))
        #odd products
        sumodd = sumodd + (8.0 * (1.0/(((i * 4) + 1) * ((i * 4) + 3))))
        print("  {0:0.6f}                   {1:0.6f}".format(sumalt, sumodd))
        #get the difference
        if i == (iteration - 1):
            print("For", iteration, "terms the difference is:")
            diffalt = sumalt - pi
            diffodd = sumodd - pi
            print(" {0:0.6f}                  {1:0.6f}".format(diffalt,diffodd))
    print("The actual value of pi is", pi)
    print()

def main():
    #intro
    intro()
    #calculations
    calc() #includes the output

main()
