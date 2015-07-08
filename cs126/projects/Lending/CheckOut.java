// CheckOut.java contains the CheckOut object, used for keeping track
// of the books that have been checked out from a library.
//
// Written by Carl Stevenson
// Date: 4/22/13

public class CheckOut{

	// pID is the customer ID number
	private String pID;
	// bID is the book ID
	private String bID;
	// checkoutTime is the time at checkout
	private long checkoutTime;


	// CheckOut
	// Constructor, takes the desired customer ID and the ID of the book
	// being checked out, sets the checkout time to the System time.
	public CheckOut(String inpID, String inbID){

		pID = inpID;
		bID = inbID;
		checkoutTime = System.currentTimeMillis();
	} // end CheckOut constructor

	// CheckOut
	// Constructor, same as the other constructor, with the addition of
	// being able to specify what time the book was taken out.
	public CheckOut(String inpID, String inbID, long inCheckout){

		pID = inpID;
		bID = inbID;
		checkoutTime = inCheckout;
	} // end CheckOut constructor
	
	// getpID
	// Accessor, returns the customer ID of the given CheckOut instance.
	public String getpID(){

		return pID;
	} // end getpID


	// getbID
	// Accessor, returns the book ID of the given CheckOut instance
	public String getbID(){

		return bID;
	} // end getbID


	//getCheckoutTime
	// Accessor, returns the checkout time of the given CheckOut instance
	public long getCheckoutTime(){

		return checkoutTime;
	} // end getCheckoutTime


	// toString
	// Accessor, returns a string containing CheckOut's basic data, being pID,
	// bID, and the checkout time.
	public String toString(){

		return pID+" "+bID+" "+checkoutTime;

	} // end toString
	

	// equal()
	// Accessor, returns boolean indicating whether or not two instances
	// of CheckOut are equal or not. Does not compare checkoutTime.
	public boolean equal(CheckOut right){

		if((pID == right.getpID()) && (bID == right.getbID()))
			return true;
		else
			return false;
	} // end equal

} // end CheckOut class
