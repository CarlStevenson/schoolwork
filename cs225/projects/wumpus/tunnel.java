// Anthony Kapolka
// Fri Nov 18 12:27:30 EST 2011
// Edited by Carl Stevenson for clarity

public class tunnel 
{
   int     destination;  // where the tunnel leads
   boolean travelled;
   boolean haspit;

   public tunnel()
   {
	   destination = 0;
	   travelled   = false;
	   haspit      = false;
   }

   public void setdestination(int s)
   {
	   destination = s;
   }

   public void settravelled()
   {
	   travelled = true;
   }
}
