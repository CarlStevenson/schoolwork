// Banking.java drives the AccountKinds package
// Written by Carl Stevenson
//
// Date: 4/15/13
//

import AccountKinds.*; // allows access to the account types

public class Banking{

	public static void main(String[] args){

		int months = 0;
		// intro
		System.out.println("\nWelcome to The Bank!");
		System.out.println("This automated transaction is written by"
							+ " Carl Stevenson.\n");
		// end intro
		// make an interest account for the hulk		
		InterestAccount hulk = new InterestAccount("The Incredible Hulk",
													"8765", 50.00);
		
		// make a CD for nemo
		CDAccount nemo = new CDAccount("Nemo", "3456", 10000);

		// do 3 monthly updates, printing out the amounts in each account
		for(int i =0 ; i<3; i++){

			hulk.monthly_update();
			nemo.monthly_update();
			System.out.print("Status after month "+(i+1)+": ");
			System.out.println(hulk.toString());
			System.out.println("                      "+nemo.toString());
			System.out.println();
		} // end for loop

		// deposit 2K into the hulk's account
		hulk.deposit(2000, "8765");
		System.out.println("\nDepositing into The Hulk's account.");

		// withdraw 3K from Nemo's account (prematurely!)
		nemo.withdraw(3000, "3456");
		System.out.println("\nWithdrawing from Nemo's CD.\n");

		// do 10 more monthly updates, printing the amounts in each account
		for(int i = 3; i<14; i++){

			hulk.monthly_update();
			nemo.monthly_update();
			System.out.print("Status after month "+(i+1)+": ");
			System.out.println(hulk.toString());
			System.out.println("                      "+nemo.toString());
			System.out.println();
		} // end for loop

	} // end main

} // end Banking class
