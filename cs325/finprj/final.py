# lab9.py
# Written By Carl Stevenson
# ***************************************************************************

import MySQLdb

def main():
	menu = "Menu\n----\n1.\tList customers and their balances at bank\n2.\tFind customer by exact name for transaction\n3.\tFind customer by partial name for transaction\n4.\tCreate new customer\n5.\tExit"
	db = MySQLdb.connect(user = "stevenson", passwd = "lumber@woulds", db = "cs325fa2014")
	cursor = db.cursor(MySQLdb.cursors.DictCursor)	

	good = True
	selector = 0
	# start by getting the branch id
	bid = start()

	while good:

		print menu
		selector = eval(raw_input())
		print "You chose",selector
		if selector == 1:
			listCustomers(cursor)
			db.commit
		elif selector == 2:
			findExactCustomer(cursor)
			db.commit
		elif selector == 3:
			findPartialCustomer(cursor)
			db.commit
		elif selector == 4:
			createCustomer(cursor, bid)
			db.commit
		elif selector == 5:
			good = False

		else:
			print "That was not one of the presented options. Please try again."

	return

def createCustomer(cursor, bid):
	print "Add Customer"
	print "------------"
	print "Enter first name:",
	first_name = raw_input()
	print "\nEnter last name:",
	last_name = raw_input()
	print "Enter phone number:",
	phone_no = input()
	print "\nStarting balance :",
	balance = input()
	cursor.execute("insert into customers(first_name, last_name, phone_no, bid)(%s,%s,%d,%d);", (first_name, last_name, phone_no, bid))
	print first_name, last_name,"added to the database."

	return
def listCustomers(cursor):
	print "\nCustomers at Bank"
	print "-----------------"
	# list customers and their balances
	cursor.execute("select last_name, first_name, accountChange(cid, 0, now()) as balance from customers join transactions using(id) order by last_name;")
	for row in cursor.fetchall():
		print row['last_name']+", "+row['first_name']+"\t"+row['balance']

	return
def findExactCustomer(cursor):
	print "Customer Search by Exact Name"
	print "-----------------------------"
	print "First name:",
	first_name = raw_input()
	print "\nLast name:",
	last_name = raw_input()
	# find balance
	cursor.execute("select last_name, first_name, accountChange(cid, 0, now()) as balance from customers join transactions using(id) where last_name ==%s and first_name ==%s;",(last_name, first_name))
	for row in cursor.fetchall():
		print first_name+' '+ last_name+"'s balance is "+row['balance']
	# go forward with the deposit or withdrawal
	print "Deposit (1) or withdrawal (2)? "
	code = input()
	print "How much money? "
	diff = input()
	if diff <0:
		print "Cannot interact with negative money."
		return
	if code == 2:
		diff = diff * -1
	# perform the transaction
	cursor.execute("insert into transactions(diff) values(%d)",(diff))	

	# report back
	cursor.execute("select last_name, first_name, accountChange(cid, 0, now()) as balance from customers join transactions using(id) where last_name ==%s and first_name ==%s;",(last_name, first_name))
	for row in cursor.fetchall():
		print first_name+' '+ last_name+"'s balance is now"+row['balance']

	return

def findPartialCustomer(cursor):
	print "Customer Search by Partial Name"
	print "-------------------------------"
	print "First name:",
	first_name = raw_input()
	print "\nLast name:",
	last_name = raw_input()
	# find balance
	cursor.execute("select last_name, first_name, accountChange(cid, 0, now()) as balance from customers join transactions using(id) where last_name ==%s/% and first_name ==%s/%;",(last_name, first_name))
	print first_name+' '+ last_name+"'s balance is "+row['balance']
	# go forward with the deposit or withdrawal
	print "Deposit (1) or withdrawal (2)? "
	code = input()
	print "How much money? "
	diff = input()
	if diff <0:
		print "Cannot interact with negative money."
		return
	diff == diff * -1
	# perform the transaction

	cursor.execute("insert into transactions(diff) values(%d)",(diff))	
	cursor.execute("select last_name, first_name, accountChange(cid, 0, now()) as balance from customers join transactions using(id) where last_name ==%s and first_name ==%s;",(last_name, first_name))
	for row in cursor.fetchall():
		print first_name+' '+ last_name+"'s balance is now"+row['balance']
	

	# report back
	cursor.execute()
	print first_name+' '+ last_name+"'s balance is now"+row['balance']
	return
def start(cursor):
	print "Welcome to CS Bank"
	print "==================\n"
	print "Select a branch:"
	cursor.execute("select city, state, id from branches order by city, state;")
	for row in cursor.fetchall():
		print '  '+row['city']+', '+ row['state']+' ('+row['id']+')'

	print "\nBranch ID: "
	bid = raw_input()

	return bid


main()