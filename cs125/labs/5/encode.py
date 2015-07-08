#File   : encode.py
#Author : Carl Stevenson
#Date   : 9/27/2012
#Purpose: To encode a message using a simple cipher.

def main():
    #tell user what the program does and who wrote it
    print()
    print("This program will encode a message using")
    print("a Caesar cipher.")
    print("You will enter a key (number between 1 and 25).")
    print("Written by Carl Stevenson")
    print()
    
    #prompt and input the key

    key = eval(input("Enter a key (number between 1 and 25): "))
    print()
    print()
    
    #prompt and input the plaintext line

    msg = input("Enter a sentence: ")
    print()
    print()
   
    #lowercase the line

    msg = msg.lower()
    #go through the text character by character
    #convert each letter to the encoded version
    #output the encoded version

    print("With a key of", key)
    print()
    print("Original line:", msg)
    print()
    print("Encoded line:  ", end = "")
    for ch in msg:
        outChar = ch
        if ch >= "a": 
            if ch <= "z":
                num = ord(ch)
                num = num + key
                outChar = chr(num)
                if outChar > "z":
                    outChar =  chr(num - 26)
                if outChar < 'a':
                    outChar = chr(num + 26)
        print(outChar, end = "")
    print()
    print()


main()
