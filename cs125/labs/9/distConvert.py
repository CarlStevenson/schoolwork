#PROGRAM:      distConvert.py
#
# AUTHOR:      Matt Zukoski
# MODIFIED BY: Carl Stevenson
#
# DATE:        Oct 21, 2008
#
# FOR:         CS 125 - Wilkes University
#
# PURPOSE:     Demonstrate the Tkinter library
#              for building GUI applications. This one
#              converts between Fahrenheit and Celsius
#              temperatures.
 
from tkinter import *
 
def main():
    global root, mTxt, kTxt
 
    # create a main root window (default size of 200 x 200)
    root = Tk()
 
    # the GUI will have a label and entry box on the top
    # for Fahrenheit, then a label and entry box below that
    # for Celsius, then a Convert button and Quit button
    # below that. We'll use the pack manager, so Frames
    # must be used to get the desired layout.
 
    # the Fahrenheit label and entry box go in f1
    f1= Frame(root)
    f1.pack(side=TOP)
 
    # the Celsius label and entry box go in f2
    f2= Frame(root)
    f2.pack(side=TOP)
 
    # the Convert and Quit buttons go in f3
    f3= Frame(root)
    f3.pack(side=TOP)
 
    Label(f1, text="Miles:").pack(side=LEFT)
    mTxt = Entry(f1)
    mTxt.pack(side=LEFT)
    
    Label(f2, text="Kilometers:").pack(side=LEFT)
    kTxt = Entry(f2)
    kTxt.pack(side=LEFT)
 
    convertBtn = Button(f3, text="Convert", background="darkgreen")
    convertBtn.pack(side=LEFT)
 
    # associate the convertClick event handler to the
    # left-click event on the convertBtn button
    convertBtn.bind("<Button-1>", convertClick)
    
    quitBtn = Button(f3, text="Quit", background="green")
    quitBtn.pack(side=LEFT)
 
    # associate the quitClick event handler to the
    # left-click event on the quitBtn button
    quitBtn.bind("<Button-1>", quitClick)
 
    # the mainloop method is doing all the work for displaying
    # the window, detecting events, and calling event handlers
    root.mainloop()
 
# convertClick is the event handler for the convertBtn
# it's called when the user left-clicks in the button
 
def convertClick(event):
    # check which field is empty. If mTxt is empty, then
    # convert the kTxt entry to Fahrenheit. If the kTxt is
    # empty, then convert the mTxt entry to Celsius
 
    mDistStr = mTxt.get()
    kDistStr = kTxt.get()
 
    if mDistStr == "":
        # convert kilometers to miles
        kDist = float(kDistStr)
        mDist = kDist * .621371192
        mTxt.insert(0, str(mDist))
    else:
        # if there is something in mTxt, convert miles to kilometers
        # (even if there is something in kTxt)
        mDist = float(mDistStr)
        kDist = mDist / .621371192 
        kTxt.delete(0, END)
        kTxt.insert(0, str(kDist))
 
def quitClick(event):
    global root
 
    # kill the main root window and all of it's descendants
    # i.e. all the widgets on it
    root.destroy()
 
main()
