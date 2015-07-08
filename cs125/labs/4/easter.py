# File: easter.py
# Author: Carl Stevenson
# Date: 9/20/2012
# Purpose: To determine the easter date for any given year.


#define main
def main():

    #tell user what program does
    print("This program will tell you the Easter date given a year.")
    print("Written by Carl Stevenson.")
    print()

    #ask for year

    year = eval(input("Please enter a year: "))
    print()
    print()

    #compute easter date
    A = year % 19
    B = year % 4
    C = year % 7
    D = (19 * A + 24) % 30
    E = (2 * B + 4 * C + 6 * D + 5) % 7

    easterDate = 22 + D + E

    #if easterDate is less than or equal to 31, then Easter falls on March 
    #easterDate otherwise Easter falls on April (easterDate - 31)

    if easterDate <= 31:
        easterMonth = "March"
    else:
        easterMonth = "April"
        easterDate = easterDate - 31

#output easter date
    
    print("Easter falls on", easterMonth, easterDate, "," , year , "." )
    
#call function

main()
