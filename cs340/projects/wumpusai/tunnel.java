// Anthony Kapolka
// made 2 way tunnels Tue Dec  4 15:07:07 EST 2012
// original code      Fri Nov 18 12:27:30 EST 2011

public class tunnel 
{
   int 	   source;       // tunnel connects source & destination
   int     destination;  // source <= destination
   boolean haspit;

   public tunnel()
   {
	destination = 0;
        haspit      = false;
   }

   public tunnel(int s)
   {
	source = s;
	destination = 0;
        haspit      = false;
   }

   public int leadsto(int s)
   {
	if (source == s)	// recall source <= destination
          return destination;
        else
          return source;

   }

   public void setdestination(int s)
   {
     destination = s;
   }

   public void sethaspit()
   {
     haspit = true;
   }
}
