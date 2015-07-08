
// Anthony Kapolka
// Wed Nov 13 11:26:42 EST 2013

class quitcommand extends playercommand 
{

   public quitcommand()
   {
     commandletter = 'q';
   }

   public void docommand()
   {
       System.exit(1);
   }

}
