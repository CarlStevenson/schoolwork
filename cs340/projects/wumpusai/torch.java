// some modification by AK Fri Nov  8 11:39:03 EST 2013

public class torch extends gameobject {

	static int count = 0;
	
	public torch()
        {
	 count = 5;
         name = "torch";
        }

	public static boolean islit()
        { // is the torch lit
	
	  boolean haslight = false;	
	
	  if(count > 0)
            {
	      count--;      // strange to only burn when asked if lit.
			    // this should be an "action" of the torch
	      haslight = true;
            }
	  else
            {
	       gameio.putln("You are out of torches!");}
               // should this procedure be printing things out?
		    
	       return haslight;
            }
			

	 public static void torchnumber(){

		count+=5;}

}
