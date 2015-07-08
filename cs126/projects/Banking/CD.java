// CDAccount.java extends the BasicAccount class
// Written by Carl Stevenson
//
// CD Account - This account charges a fee of which is the smaller of 10 or 10%
// of the balance at the end of the month. There is interest of 15% paid yearly.
// There is no minimum balance required, but if there is a withdrawal before 12
// months have gone by there will be a penalty of 20% of the current balance.

package AccountKinds;

import AccountKinds.AccountBalanceException;

public class CDAccount extends ProtectedAccount{

	private int monthsHad;
	private String pin;
	// Constructor for CDAccount	
	public CDAccount(String name, String pin, double principle){

		super(name, pin);
		super.deposit(principle);
		super.setRate(.15);
		monthsHad = 0;
		this.pin = pin;
		
	} // end CDAccount constructor
	
	// deposit method to prevent user from depositing into their CD,
	// overrides ProtectedAccount's method
	public void deposit(double amount, String pin){

		throw new AccountBalanceException("Cannot withdraw from an"
											+ " established CD Account!");
	} // end deposit
	
	// withdraw method to allow withdrawal, but with a steep fee
	// overrides ProtectedAccount's method
	public void withdraw(double amount, String pin){

		super.withdraw(amount, pin);
		if(monthsHad<12)
			super.withdraw((getBalance() *.2), pin);
	} // end withdraw

	// monthly_update charges the monthly fee, as well as adds interest
	// every 12 months. Overrides BasicAccount's method to account for
	// the yearly pay of the CD Account
	// derived from BasicAccount's method
	public void monthly_update(){
		// the use of the super.deposit and super.withdraw is safe because
		// the input will always be positive
		monthsHad++;
		if (monthsHad%12 == 0){
			double adjustBy = (computeInterest()*12) - computeFees();
			super.deposit(adjustBy, pin);
		}
		else
			super.withdraw(computeFees(), pin);
		
		
	} // end monthly_update

} // end CDAccount class
