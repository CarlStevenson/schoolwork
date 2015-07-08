// InterestAccount.java extends the BasicAccount class
// Written by Carl Stevenson
//
// Interest Account - This account charges a fee of which is the smaller of 10 or 
// 10% of the balance at the end of the month. There is interest of 7% per year
// but paid monthly. There is no minimum balance required.

package AccountKinds;  

public class InterestAccount extends ProtectedAccount{

	// Constructor for InterestAccount
	public InterestAccount(String name, String pin, double initial){

		super(name, pin);
		super.setRate(.07);
		deposit(initial);
	}// end InterestAccount constructor
} // end InterestAccount class
