// Lending.java is a program to keep track of library books using the
// CheckOut class. Reads in a list of checked out books, then outputs
// the updated list when the program closes.
//
// Written by Carl Stevenson
// Date: 4/22/13

import java.util.*; // For Scanner
import java.io.*;   // For PrintStream
 
public class Lending{

	// global scanner for ease
	static Scanner sc = new Scanner(System.in);
	// global PrintStream for ease
	static PrintStream out = new PrintStream(System.out);
	// global menu
	static String menu = "Please enter a number:"
					+"\n        1, for checking a book out;"
					+"\n        2, for returning a book;"
					+"\n        0, to exit and print list;"
					+"\n--> ";
	// global checked out books List
	static List<CheckOut> checked;
	// global array to keep track of users' number of books
	static int[] users = new int[1000];

	public static void main(String[] args) throws FileNotFoundException{

		// Print the introductory text
		intro(); 
		
		// get the input file name for the list of already checked out books,
		// and to overwrite the file at the end.
		out.print("Enter name of input file: ");
		String file = sc.next();
		out.println("\n");
		
		// Take the name of the file and make it into a Scanner.
		Scanner in = makeInput(file);

		// Take that input file and read all of the checked out books in it,
		// returns that data as a List.
		checked = getList(in);
		goUI();
		outputs(file);
		
	} // end main

	// intro prints the introductory text for the program
	public static void intro(){

		out.println("\nProgram to keep track of library books.");
		out.println("You will enter the name of a file.");
		out.println("The program will read this file as the list");
		out.println("of books already checked out. you will then enter");
		out.println("a lender code followed by a book description to");
		out.println("check out or return a book.");
		out.println("When the program is done, it will write out the");
		out.println("current list of checked out books to the original file");
		out.println("and print a list of checked out books.");
		out.println("Written by Carl Stevenson.\n");
	
	} // end intro

	// makeInput takes the name of a file and returns a Scanner of that file.
	public static Scanner makeInput(String filename) 
									throws FileNotFoundException{

		
		Scanner in = new Scanner(new File(filename));
		return in;
	} // end makeInput

	// getList makes a List of CheckOut objects from customer ID, book ID,
	// and checkout time.
	public static List<CheckOut> getList(Scanner in){

		// fill users with initial values
		Arrays.fill(users, 0);
		// initialize some variables used in the loop
		String pID;
		String bID;
		long checkoutTime;
		checked = new ArrayList<CheckOut>();
		while(in.hasNext()){

			pID = in.next();
			bID = in.next();
			if(bID.length() == 10){

				checkoutTime = in.nextLong();
				checked.add(new CheckOut(pID, bID, checkoutTime));
				users[Integer.valueOf(pID)]++;
			} // end if
		} // end while

		return checked;
	} // end getList

	// goUI goes into the interactive prompts with the user to make
	// changes to the list of checked out books.
	public static void goUI(){

		boolean go = true;
		while(go){

			out.print(menu);
			if(!sc.hasNextInt()){
				String junk = sc.next();
				out.println("\nYou must pick one of the options <0-2>, not "
							+ junk + "\n\n\n\n");
			} // end if
			else{
				
				int choice = sc.nextInt();
				switch(choice){

					case 1: takeOut();
							break;
					case 2: giveBack();
							break;
					case 0: go = false;
							break;
					default: out.println("\nYou must pick one of the options "
										+"<0-2>, not "+choice+"\n\n\n\n");
				} // end switch
			} // end else
				
		} // end while
	} // end goUI


	// takeOut prompts the user, creates a CheckOut object based on their 
	// input, and adds it to the list of checked out books.
	public static void takeOut(){

		out.print("\n\n\nEnter a 3 digit patron code: ");
		String pID = sc.next();
		out.println();
		if(pID.length() != 3){
			out.println("You must enter a 3 digit patron code, not "+pID
						+". Re-enter your choice and try again.\n\n\n\n");
			return;
		} // end if
		if(users[Integer.valueOf(pID)]>=3){
			out.println("This patron cannot take out any more books!\n\n\n\n");
			return;
		} // end if
		out.print("Enter 10 letter book description: ");
		out.println();
		String bID = sc.next();
		out.println("\n\n\n\n");
		if(bID.length() < 10){
			out.println("You must enter a 10 character book description, not "+bID
						+". Re-enter your choice and try again.\n\n\n\n\n");
			return;
		} // end if
		for(int i=0;i<checked.size();i++){
			if(bID == checked.get(i).getbID()){
				out.println("That book has already been taken out!");
				return;
			} // end if
		} // end for
		checked.add(new CheckOut(pID, bID));
		users[Integer.valueOf(pID)]++; // record the additional book out
		return;

	} // end takeOut

	// giveBack updates the checked list to reflect the return of a book.
	public static void giveBack(){

		out.print("\n\n\nEnter a 3 digit patron code: ");
		String pID = sc.next();
		out.println();
		if(pID.length() != 3){
			out.println("You must enter a 3 digit patron code, not "+pID
						+". Re-enter your choice and try again.\n\n\n\n");
			return;
		} // end if
		out.print("Enter 10 letter book description: ");
		out.println("\n\n\n\n");
		String bID = sc.next();
		out.println();
		if(bID.length() != 10){
			out.println("You must enter a 10 character book description, not "+bID
						+". Re-enter your choice and try again.\n\n\n\n");
			return;
		} // end if
		CheckOut temp = new CheckOut(pID, bID);
		int tempIndex = checked.indexOf(temp);		
		if(tempIndex == -1){
			out.println("There is no such book out currently!\n\n\n\n");
			return;
		} // end if
		else
			checked.remove(tempIndex);
			
		users[Integer.valueOf(pID)]--; // record the book returned
		return;
	} // end giveBack

	// outputs outputs the updated list of checked out books to the
	// screen, as well as updating the the original input file
	// by overwriting it with the updated information
	public static void outputs(String file) throws FileNotFoundException{

		PrintStream output = new PrintStream(new File(file));
		out.println("\n\nHere is the current list of checked out books:\n");
		out.println("Lender  Book Desc.  Time");
		out.println("======  ==========  ====");
		for(int i=0;i<checked.size();i++){
			CheckOut temp = checked.get(i);
			output.println(temp.toString());
			out.print(" "+temp.getpID()
						+"    "+temp.getbID()
						+"  "+temp.getCheckoutTime());
			out.println();
		} // end for
	} // end outputs



} // end Lending class
