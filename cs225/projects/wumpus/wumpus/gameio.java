
// Anthony Kapolka
// Fri Nov 18 12:26:42 EST 2011
// Edited by Carl Stevenson for clarity


// all console i/o is routed through this object
// to allow for an easy addition of a future graphical interface

import java.io.*;

class gameio
{
	static void put(String s){
		System.out.print(s);
	}

	static void putln(String s){
		System.out.println(s);
	}

	static void put(int s){
	  System.out.print(s);
  	}

	static void putln(int s){
		System.out.println(s);
	}

	static void putln(){
		System.out.println();
	}

	static String getstring(){
		String string = "";
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);

		// read in user input

		try{
			string = reader.readLine();
		}
		catch(Exception e){}

		return string;
	}

	static void sleep(int n){
		try{
			Thread.currentThread().sleep(n*1000); //sleep for n*1000 ms
		}
		catch(Exception ie){ }
	}


	static void wumpuskill(){
		putln("You throw the grenade into the tunnel.");
		sleep(1);	
		put("CHINK!... ");
		sleep(1);	
		putln("CHINK... ");
		sleep(1);	
		putln("Chink... ");
		sleep(3);	
		putln("BOOOOOM!");
		sleep(2);	
		putln("You hear a groan as the wumpus falls over. You have killed the wumpus! Whatever happened to the sanctity of all living creatures?");
		putln("Nice work! Game over.");
		putln();
	}

	static void grenadetoss(){
		putln("You throw the grenade into the tunnel.");
		sleep(1);	
		put("CHINK!... ");
		sleep(1);	
		putln("CHINK... ");
		sleep(1);	
		putln("Chink... ");
		sleep(3);	
		putln("BOOOOOM!");
		sleep(2);	
		putln("No luck in killing the wumpus. Try again next time.");
		putln();
	}
// Cheap ASC graphic effects
// Anthony Kapolka
// Tue Nov 28 00:34:44 EST 1991

	static void wumpuseffect(){
    
		putln("Hmm... it stinks all of a sudden...");
		sleep(1);	
		put("chomp... ");
		sleep(2);	
		putln("CHOMP... ");
		sleep(2);	
		put("munch... ");
		sleep(2);	
		putln("MUNCH... ");
		sleep(2);	
		put("S "); 
		sleep(1);	
		put("C ");
		sleep(1);	
		put("R ");
		sleep(1);	
		put("U ");
		sleep(1);	
		put("N ");
		sleep(1);	
		put("C ");
		sleep(1);	
		putln("H ");
		putln();
		sleep(1);	
		putln("Even the wumpus needs to eat lunch!");
		putln();
	}


	static void bateffect(){
		for (int b=1; b<10; b++){
			for (int sp = 1; 
					(sp < (game.R.nextInt(80))); sp++)
				put(" ");

			putln("FLAP"); 
			sleep(1);	
		}

		putln();
		putln();
		putln("S U P E R   B A T   S N A T C H"); 
		putln();
		putln();
	}


	static void piteffect(){
		for (int b=1; b<30; b++){
			for (int sp = 1; sp < b; sp++)
				put(' ');
			putln("E");
        }

		putln();
		putln();
		putln("... that last step is was doozie."); 
		putln();
		putln();
	}

}
