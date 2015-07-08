
// CS126 - Anthony Kapolka
// Tue Nov 12 15:16:15 EST 2013

// struct to contain letter/method pairs

// import java.lang.reflect.Method; // for Method 

class playercommand 
{
    char commandletter;  
          // single letter for command, e.g. 'n' for go north

    // Method commandfunction;
    // Method called when command is executed.

    public playercommand()
    {
    }

    public void docommand()
    {
      // subclasses implement their own command 
      System.out.println("You've invoked the playercommand do function.");
    }
}
