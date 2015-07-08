# File: feet.py
# Author: Carl Stevenson
# Date: 9/13/2012
# Purpose: To make conversion tables of feet to meters and meters to feet.

def main():
    #introduce program
    print()
    print("Table of Feet and Meter Equivalents")
    print("Written by Carl Stevenson\n")
    #set up first chart
    print("Feet        Meters")
    print("====        ======")
    #set up conversion values in variables
    feet_over_meters = .3048
    meters_over_feet = 3.2808399
    #do calculations and print them
    for i in range(11):
        print (" ", (i * 10), "feet","  ", end= " ")
        print(((i * 10)* feet_over_meters),"meters")
    print()
    #set up second chart
    print("Meters             Feet")
    print("======             ====")
    #do calculations and print them
    for i in range(11):
        print (" ", (i * 10), "meters","       ", end= " ")
        print(((i * 10)* meters_over_feet),"feet")
    print()


main()
