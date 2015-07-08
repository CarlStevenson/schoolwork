# File:    computable.py
# Author:  Carl Stevenson
# Date:    2/4/2013
# Purpose: To calculate a chosen digit for a decimal expansion.

def main():
    # retrieve the values needed for the calculation
    top, bot, pres, text = intro()
    # do the calculation and return the desired decimal point
    dec = calc(top, bot, pres)
    # output results to screen
    out(dec, pres, text)

def intro():
    print()
    text = input("Enter a rational number fraction in the interval (0,1): ")
    # split up the numerator and the denominator into something usable
    top = int(text[0:text.find("/")])
    bot = int(text[(text.find("/")+1):len(text)])
    print()
    pres = int(input("Which digit do you want? "))
    return top, bot, pres, text

def calc(top, bot, pres):
    # long division
    # holder is what is divided into, gives the value of the next place 
    holder = top * 10
    dec = 0
    # do the long division
    for i in range(pres):
        dec = holder//bot
        holder = holder%bot
        holder = holder * 10
    return dec

def out(dec, pres, text):
    print()
    print("The " + str(pres) + "th digit in the decimal expansion", end ="")
    print(" of", text, "is", dec)
    print()

main()
