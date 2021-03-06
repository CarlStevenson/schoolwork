// code2.java
// code2.java finds a target word in a text, and displays it to the screen.
// adapted from codes.java, written previously in class
// adds functionality to find additional words in close proximity,
// as well as hebrew language support
// Written by Carl Stevenson

// edited for codes2 12/8/13 by Carl Stevenson
// currently incomplete; searched for words in the vicinity of the found
// word in English, but it's buggy in Hebrew
// ***********************************************************************

// for Collections
import java.util.*;
// for File
 import java.io.*;

public class code2{

    static dictionary dict;
    
	public static void main(String[] args)throws FileNotFoundException{

		// receive the file, convert to a text file to be processed
		
        Scanner sc = new Scanner(System.in);
        System.out.println("Is this file English or Hebrew?");
        
        // 
        // hebrewwords.txt
		if(sc.next().toLowerCase().charAt(0) == 'e'){processEng(sc);}
		else{processHeb(sc);}


	}

    public static void processEng(Scanner sc)throws FileNotFoundException{
    
        Scanner in = new Scanner(new FileInputStream(
		                                new File("mobydick.html")));
		in.useDelimiter("");
        List<String> raw = strip(in);
		dict = new dictionary();
		String inDict = "/home/mathcs/staff/kapolka/webdocs/cs225/hash/words";
		//String inDict = "words";
		teach(inDict);
		System.out.println();
		System.out.println("What word would you like to find in this text?");
		String theWord = sc.next();
		theWord = theWord.toLowerCase();

		// find the word!
		// start at 100 skip distance, go to 1000
		// returns an arraylist containing position of the first letter,
		// and the skip distance		
		List<Integer> key = wordFinder(raw, theWord);

		// output the '2Darray' with target word & adjacent words in uppercase
		theOutput(raw, key, theWord); 
    
    
    }
    
    public static void processHeb(Scanner sc)throws FileNotFoundException{
    
        Scanner in = new Scanner(new FileInputStream(
		                                new File("torah.html")));
		in.useDelimiter("");
        List<String> raw = strip2(in);
        dict = new dictionary();
        String inDict = "hebrewwords.txt";
        teach(inDict);
        // get the word!
		// needs to be transformed into the Hebrew equivalent
		
		System.out.println();
		System.out.println("What word would you like to find in this text?");
		String theWord = sc.next();
		

		// find the word!
		// start at 100 skip distance, go to 1000
		// returns an arraylist containing position of the first letter,
		// and the skip distance		
		List<Integer> key = wordFinder(raw, theWord);
		
		// output the 2Darray with target word & adjacent words in uppercase
		theOutputHeb(raw, key, theWord); 
    
    
    }


  	public static List<String> strip2(Scanner in){

		List<String> daList = new ArrayList<String>();
		String inStr = "";
		char inChar = 65;
		boolean toggle = true;
		// read in each character, omitting bad characters, and toggling
		// whether the characters are legal based on the '>' and '<'
		// characters
		while(in.hasNext()){


            inStr = in.next();
            inChar = inStr.charAt(0);
            if(toggle){
            if((((int)inChar) >1487 && ((int)inChar) <1514)){

                daList.add(Character.toString(inChar));
                
				}
            }else{				
            if(((int)inChar == 60 || (int)inChar == 62)&& toggle == true){
				toggle = false;
				inChar = 0;	
			}
			if(((int)inChar == 60 || (int)inChar == 62)&& toggle == false){
				toggle = true;
				inChar = 0;
			}
			}
		}
		return daList;

	}
	
    // teach loads up the dynamic brain with words
    // re-used from travesty2.
    
    public static void teach(String inDict)throws FileNotFoundException{
    
        Scanner file = new Scanner(new FileInputStream(new File(inDict)));
        //file.useDelimiter("[^a-zA-Z]");
        String inWord;
        // insert 'a', because the dictionary doens't have it
        dict.insert("a");
        while(file.hasNext()){
            inWord = file.next();
            inWord = process(inWord);
            dict.insert(inWord);
           
        }
    }
    
    // process proccesses the input from the file and returns a String to 
    // be inserted into the dictionary
    // taken from Travesty2
    
    public static String process(String inWord){
       
        inWord.toLowerCase();
        for(int i = 0; i<inWord.length(); i++){
        
            if(inWord.charAt(i)<'a' || inWord.charAt(i)>'z'){
                inWord = inWord.substring(0, i);
                break;
            }
        }
        return inWord;
    }
    
	public static List<String> strip(Scanner in){

		List<String> daList = new ArrayList<String>();
		String inStr = "";
		char inChar = 65;
		boolean toggle = true;
		// read in each character, omitting bad characters, and toggling
		// whether the characters are legal based on the '>' and '<'
		// characters
		while(in.hasNext()){


            inStr = in.next();
            inChar = inStr.charAt(0);
            if(toggle){
            if((((int)inChar) >63 && ((int)inChar) <91) || (((int)inChar) >96
                    && ((int)inChar) < 123)){

                daList.add(Character.toString(inChar).toLowerCase());
                
				}
            }else{				
            if(((int)inChar == 60 || (int)inChar == 62)&& toggle == true){
				toggle = false;
				inChar = 0;	
			}
			if(((int)inChar == 60 || (int)inChar == 62)&& toggle == false){
				toggle = true;
				inChar = 0;
			}
			}
		}
		return daList;

	}
	
	public static List<Integer> wordFinder(List<String> raw, String theWord){

		int pos = 0;
		int wordPos = 0;
		int find = 0;
		int skip = 0;
		boolean found = false;
		String check = "";
		Iterator<String> itr = raw.iterator();
		boolean lalala = true;
		// start by reading for the first character		
		while(itr.hasNext()){
			wordPos = 0;
			check = itr.next();
			
			// if the character read in and the character we want match,
			// start matching, trying with skip distances from 50 to 10,000
			if(check.equals(String.valueOf(theWord.charAt(wordPos)))){
				for(skip=50; skip<=10000; skip++){ 
					wordPos = 1;				
					find = pos + skip;

					// used to make sure that we don't go past the end of
					// the text
					try{
						raw.get(find);
						theWord.charAt(wordPos);
					}catch(StringIndexOutOfBoundsException e) {
						break;
					}catch(IndexOutOfBoundsException e) {
						break;
					}
					
					// if the second character matches, take the distance
					// between it and the first character and see if
					// the correct letters line up. Break if you go past 
					// the end of the text or the character doesn't match.
					if(raw.get(find).equals(String.valueOf(
						theWord.charAt(wordPos)))){
						
						while(wordPos < theWord.length()){
							wordPos++;
							find = find + skip;
							try{
								raw.get(find);
								theWord.charAt(wordPos);
							}catch(StringIndexOutOfBoundsException e) {
								break;
							}catch(IndexOutOfBoundsException e) {
								break;
							}
							if(raw.get(find).equals(
								String.valueOf(theWord.charAt(wordPos)))&&
								wordPos == theWord.length()-1){

								found = true;
								break;
							}else if(raw.get(find)
									.equals(String.valueOf(
									theWord.charAt(wordPos))));
							else break;


						}

					} 
					
					if(found)break;				
				}

			}
			
			if(found)break;
				
			pos++;
		}
		// if we've gone through all that trouble for nothing, boolean found
		// will still be false. so, output a nice message to indicate that
		// word was, in fact, not found.
		if(!found){
			System.out.println("Word not found.");
			System.exit(0);
		}
		// return the starting position and the skip distance to be
		// used in theOutput
		List<Integer> key = new ArrayList<Integer>(2);
		key.add(pos);
		key.add(skip);
		return key;
		
	}
	
	public static List<Integer> wordFinderHeb(List<String> raw, String theWord){
	
	    List<Integer> key = new ArrayList<Integer>(2);
	    return key;
	}

	public static void theOutput(List<String> raw, List<Integer> key, 	
									String theWord){
        
        char[][] block = new char[11][theWord.length()+10];
		int find = key.get(0);
		int skip = key.get(1);
		List<Character> daList = new ArrayList<Character>();
		
		System.out.println();
		int marker=5;
		for(int i =0; i<5; i++){
		
		    find = find - skip;
		    if(find<0){
		        find = find + skip;
		        break;
		    }
		    marker--;
		}
		
		for(int i=marker; i<theWord.length()+10;i++){

			for(int j=0;j<11;j++){

				if(j==5 && (i>4 && i<theWord.length()+5)){
					//System.out.print(raw.get(find).toUpperCase());
					block[j][i] = raw.get(find).toUpperCase().charAt(0);
					daList.add(Character.toLowerCase(block[j][i]));
			    }
				else{
					//System.out.print(raw.get(find+j));
					block[j][i] = raw.get(find+(j-5)).charAt(0);
					daList.add(block[j][i]);
			    }
			}
			//System.out.println();
			find +=skip;
		}
		
		block = findAdjacent(block, daList);
		for(int i=0; i<theWord.length()+10;i++){
		
		    for(int j=0;j<11;j++){
		    
		        System.out.print(block[j][i]);
		    }
		    System.out.println();
		}

    
	}
	
	public static char[][] findAdjacent(char[][] block, List<Character> daList){
	
	    int skip = 0;
	    int find = 0;
	    int space = 0;
	    String builder = "";
	    for(find = 0; find<(block.length * block[0].length); find++){
	         
	         space = 0;
	         builder = "";
	         try{
	            builder = builder.concat(daList.get(find).toString());
	         }catch(IndexOutOfBoundsException e){
	            break;
	         }
	         for(skip = 11; skip<(block.length * block[0].length); skip++){
	            try{
	                builder = builder.concat(daList.get(find+skip).toString());
	                space = 1;
	            }catch(IndexOutOfBoundsException e){
	            
	                break;
	            }
	            while(true){

	                if(dict.check(builder)){
	                    int marker = find;
	                    block[marker%11][(int)Math.floor(marker/11.0)] = 
	                        Character.toUpperCase(block
	                            [marker%11][(int)Math.floor(marker/11.0)]);
	                    for(int i = space; i>0; i--){
	                        marker +=skip;
	                        block[marker%11][(int)Math.floor(marker/11.0)] = 
	                            Character.toUpperCase(block
	                                [marker%11][(int)Math.floor(marker/11.0)]);
	                    }
	                }
	                space++;
	                try{
	                    builder = builder.concat(
	                            daList.get(find+(skip*space)).toString());
	                }catch(IndexOutOfBoundsException e){
	                    break;
	                }
	            }
	            builder = daList.get(find).toString();
	         }

	    }
	    return block; 
	}
	public static void theOutputHeb(List<String> raw, List<Integer> key, 	
									String theWord){
        
        char[][] block = new char[11][theWord.length()+10];
		int find = key.get(0);
		int skip = key.get(1);
		List<Character> daList = new ArrayList<Character>();
		
		System.out.println();
		int marker=5;
		for(int i =0; i<5; i++){
		
		    find = find - skip;
		    if(find<0){
		        find = find + skip;
		        break;
		    }
		    marker--;
		}
		
		for(int i=marker; i<theWord.length()+10;i++){

			for(int j=0;j<11;j++){

				if(j==5 && (i>4 && i<theWord.length()+5)){
					//System.out.print(raw.get(find).toUpperCase());
					block[j][i] = raw.get(find)/*.toUpperCase()*/.charAt(0);
					daList.add(/*Character.toLowerCase*/(block[j][i]));
			    }
				else{
					//System.out.print(raw.get(find+j));
					block[j][i] = raw.get(find+(j-5)).charAt(0);
					daList.add(block[j][i]);
			    }
			}
			//System.out.println();
			find +=skip;
		}
		
		block = findAdjacentHeb(block, daList);
		for(int i=0; i<theWord.length()+10;i++){
		
		    for(int j=0;j<11;j++){
		    
		        System.out.print(block[j][i]);
		    }
		    System.out.println();
		}

    
	}
	
	public static char[][] findAdjacentHeb(char[][] block, List<Character> daList){
	
	    int skip = 0;
	    int find = 0;
	    int space = 0;
	    String builder = "";
	    for(find = 0; find<(block.length * block[0].length); find++){
	         
	         space = 0;
	         builder = "";
	         try{
	            builder = builder.concat(daList.get(find).toString());
	         }catch(IndexOutOfBoundsException e){
	            break;
	         }
	         for(skip = 11; skip<(block.length * block[0].length); skip++){
	            try{
	                builder = builder.concat(daList.get(find+skip).toString());
	                space = 1;
	            }catch(IndexOutOfBoundsException e){
	            
	                break;
	            }
	            while(true){

	                if(dict.check(builder)){
	                    int marker = find;
	                    block[marker%11][(int)Math.floor(marker/11.0)] = 
	                        /*Character.toUpperCase*/(block
	                            [marker%11][(int)Math.floor(marker/11.0)]);
	                    for(int i = space; i>0; i--){
	                        marker +=skip;
	                        block[marker%11][(int)Math.floor(marker/11.0)] = 
	                            /*Character.toUpperCase*/(block
	                                [marker%11][(int)Math.floor(marker/11.0)]);
	                    }
	                }
	                space++;
	                try{
	                    builder = builder.concat(
	                            daList.get(find+(skip*space)).toString());
	                }catch(IndexOutOfBoundsException e){
	                    break;
	                }
	            }
	            builder = daList.get(find).toString();
	         }

	    }
	    return block; 
	}

}
