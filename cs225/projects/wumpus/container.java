// Anthony Kapolka
// Fri Nov 18 12:25:56 EST 2011

//should move cave.has function into here
// Edited by Carl Stevenson for clarity
import java.util.*;

class container extends gameobject{
   
	ArrayList<gameobject> contents;

	public container(){
		contents = new ArrayList<gameobject>();
	}


	public container(int size){
		contents = new ArrayList<gameobject>(size);
	}

	public void putinto(gameobject item){
		contents.add(item);
	}

	public void remove(gameobject item){
		contents.remove(item);
	}

	public boolean has(String name){
		// loop through contents, looking for object
        for (int c = 0; c < contents.size(); c++){
           
        	gameobject t = contents.get(c);
        	if (t.name == name)
        		return true;
         	}
        return false;
    	}

	void act(){

		// ask everything in contents to act
		for (int x = 0; x < contents.size(); x++){
			gameobject tc = contents.get(x);
			tc.act();
		}
	}
}
