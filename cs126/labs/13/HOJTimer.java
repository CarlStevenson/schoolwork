// HOJTimer.java provides a simple 'stopwatch' HOJTimer for timing execution.
//  Author:  Joel C. Adams
//  Version: For Java: An Introduction to Computing 1/e.  All Rights Reserved.
//

public class HOJTimer extends Object
{
 public HOJTimer()
 {
   running = false;
 }

 public void start()
 {
   if (!running)
   {
     running = true;
     startTime = System.currentTimeMillis();
   }
 }

 public void stop()
 {
   if (running)
   {
     stopTime = System.currentTimeMillis();
     running = false;
   }
 }

 public void reset()
 {
   startTime = System.currentTimeMillis();
 }

 public double getTime()
 {
   if (running)
     return (double)(System.currentTimeMillis() - startTime) / 1000;
   else
     return (double)(stopTime - startTime) / 1000;
 }

 public String toString()
 {
   return Double.toString( getTime() );
 }

 boolean running;    // true iff I am running
 long    startTime,  // time when start() msg received
         stopTime;   // time when stop() msg received
}
