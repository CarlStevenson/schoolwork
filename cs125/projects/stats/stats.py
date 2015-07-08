#File   : stats.py
#Author : Carl Stevenson
#Date   : 9/27/2012
#Purpose: to count specific characters in a string.


def main():
    #introduce and explain program
    print()
    print("Program to calculate some statistics")
    print("for a line entered by the user.")
    print("Written by Carl Stevenson")
    print()
    #get message
    line = input("Enter the original line: ")
    print("\n")
    
    #echo message
    print("Original line:",line)
    #count total characters
    total = len(line)
    
    
    #count capital letters
    capital = 0
    for ch in line:
        if ch >= "A":
            if ch <= "Z":
                capital = capital + 1
                
    #count lowercase letters: 
    lower = 0 
    for ch in line:
        if ch >= "a":
            if ch <= "z":
                lower = lower + 1
    #count digits
    digit = 0
    for ch in line:
        if ch >= "0":
            if ch <= "9":
               digit = digit + 1 
    #count other characters
    other = total - (capital + lower + digit)

    #print results
    
    print("Number of characters:       ", total)
    print("Number of capital letters:  ", capital)
    print("Number of lowercase letters:", lower)
    print("Number of digits:           ", digit)
    print("number of other characters: ", other)
    print()


main()
