# lab9.py
# Written By Carl Stevenson
# ***************************************************************************

import MySQLdb
db = MySQLdb.connect(user = "stevenson", passwd = "lumber@woulds", db = "stevenson")
cursor = db.cursor(MySQLdb.cursors.DictCursor)	
def main():
	menu = "\nMenu\n----\n1.\tAdd employee\n2.\tList employees currently working\n3.\tFind employees' shifts worked\n4.\tExit\n"


	good = True
	selector = 0
	print "\nEmployee Database"
	print "=================\n"
	while good:

		print menu
		selector = eval(raw_input())
		print "You chose",selector
		if selector == 1:
			addEmployee()
		elif selector == 2:
			listEmployees()
		elif selector == 3:
			findShifts()
		elif selector == 4:
			good = False
		else:
			print "That was not one of the presented options. Please try again."

	return

def addEmployee():
	print "Add employee"
	print "------------"
	print "First name:",
	first_name = raw_input()
	print "\nLast name:",
	last_name = raw_input()
	print "\nStarting salary:",
	salary = raw_input()
	if first_name == "" or last_name == "" or salary == "":
		print "No employee added. Missing one or more fields."
		return
	cursor.execute("insert into employees(first_name, last_name, salary)values(%s,%s,%s);", [first_name, last_name, salary])
	db.commit()
	return
def listEmployees():
	print "\nEmployees currently working"
	print "---------------------------"
	cursor.execute("select last_name, first_name from employees join employeeshifts using(id) where shift_begin <= now() and shift_end < shift_begin order by last_name;")
	for row in cursor.fetchall():
		print row['last_name']+", "+row['first_name']

	return
def findShifts():
	print "Find shifts for employee"
	print "------------------------"
	print "First name:",
	first_name = raw_input()
	print "\nLast name:",
	last_name = raw_input()
	cursor.execute("select * from employees where first_name = %s and last_name = %s;", [first_name,last_name])
	good = False
	if cursor.rowcount>0:
		good = True
	if good:
		cursor.execute("select * from showallshifts where first_name = %s and last_name = %s and shift_end>=shift_begin order by shift_begin;",[first_name, last_name])

		print "Shifts worked by",first_name,last_name,"are..."
		for row in cursor.fetchall():
			print row['shift_begin'],"to",row['shift_end']
	else:
		print "No employees found with the name",first_name,last_name+"."	
	return

main()