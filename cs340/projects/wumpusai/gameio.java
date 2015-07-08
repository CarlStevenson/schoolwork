
// Anthony Kapolka
// Fri Nov 18 12:26:42 EST 2011

// all console i/o is routed through this object
// to allow for an easy addition of a future graphical interface

import java.io.*;

class gameio
{

  static void put(char s)
  {
    System.out.print(s);
  }

  static void putln(char s)
  {
    System.out.println(s);
  }

  static void put(String s)
  {
    System.out.print(s);
  }

  static void putln(String s)
  {
    System.out.println(s);
  }

  static void put(int s)
  {
    System.out.print(s);
  }

  static void putln(int s)
  {
    System.out.println(s);
  }

  static void putln()
  {
    System.out.println();
  }

  static String getstring()
    {
      String string = "";
      InputStreamReader input = new InputStreamReader(System.in);
      BufferedReader reader = new BufferedReader(input);

      // read in user input

      try
      {
        string = reader.readLine();
      }
      catch(Exception e){}

      return string;
    }

  static void sleep(int n)
  {
    try{
       Thread.currentThread().sleep(n*100); //sleep for n*100 ms
       }
    catch(Exception ie){ }
  }


// Cheap ASC graphic effects
// Anthony Kapolka
// Tue Nov 28 00:34:44 EST 1991

static void wumpuseffect()
{
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


static void bateffect()
{
  for (int b=1; b<10; b++)
   {
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


static void piteffect()
{
     for (int b=1; b<30; b++)
	{
	   for (int sp = 1; sp < b; sp++)
		put(' ');
           putln("E");
           sleep(1);	
        }

   putln();
   putln();
   putln("... that last step is was doozie."); 
   putln();
   putln();
}

static void grenadeeffect()
// by Michael Bates, Fall 2012
{
   putln();
   putln("You chuck the grenade into the cave...");
   putln();
   sleep(1);
   putln("BOOOOOOM!");
   sleep(1);
}

static void arroweffect()
{
   putln();
   put("You shoot an arrow...");
   putln();
   sleep(1);
   put(">>====>     ");
   sleep(1);
   put(">>====>      ");
   sleep(1);
   put(">>====>");
   putln();
   sleep(1);
}

}
