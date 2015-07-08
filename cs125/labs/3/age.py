# File: age.py
# Author: Carl Stevenson
# Date: 9/13/12
# Purpose: To calculate a person's age.

def main():
    
    #tell user what the program does and who wrote it
    print()
    print("This is a program to calculate your age.")
    print("Written by Carl Stevenson.")
    print()
    #prompt and input the user's month, day and year of birth
    month = int(input("Enter the month of your birth (1-12): "))
    print() #for turnin
    day = int(input("Enter the day of the month of your birth (1-31): "))
    print() #for turnin
    year = int(input("Enter the year of your birth: ")) 
    print() #for turnin
    #enter today's date
    today_month = 9
    today_day = 13
    today_year = 2012

    #compute age
    age = today_year - year
    if (month > today_month):
        age = age - 1
    if (month == today_month):
        if ( day > today_day):
            age = age - 1
    
    #output today's date in the specified format

    print("Today's date is", today_month, "/", today_day, end= " ")
    print("/", today_year)

    #output the birthdate in the specified format
    print("Your birthdate is on", month, "/", day, "/", year)
    #output the age
    print("Your age is", age, "years old.")
    print()


main()
