
// Written in CS 126
// Mon Nov 25 11:40:17 EST 2013

public class threestateobject extends gameobject
{
 int state;  // 0, 1, 2 - which of the three states obj is in

 // array holding the default names
 String[] statename = {"stateone", "statetwo", "statethree"};

 public threestateobject()
 {
    state = 0;

    name = statename[state];
 }

}
