// driver.java
// Uses the monkeybrain class to demonstrate Travesty.
// Written by Carl Stevenson
// Loosely based on code written in class by Anthony Kapolka
// ***********************************************************************

import java.util.*;
import java.io.*;

public class driver{

    // set up global variables to be used
    static Scanner sc = new Scanner(System.in);
    static PrintStream out = new PrintStream(System.out);

    public static void main(String[] args)throws FileNotFoundException{

        out.print("What prefix size would you like to use? : ");
        int prefix = sc.nextInt();
        out.print("What is the file the text is located in? : ");
        String filename = sc.next();
	    out.print("How much random text do you want to generate?(in words): ");
        int outSize = sc.nextInt();
    
      
    
        // generate the values in monkeybrain to be used to generate
        // random text
        // later 

        monkeybrain MB = teach(prefix,filename);
        //MB.dump(prefix);
        MB.cumulate(prefix);
        //MB.dump(1);
        // generate the random text
        for(int i=0; i<outSize;i++){
            
            out.print(MB.genWord(prefix, "").trim()+" ");
        }
        out.println();      

    }

    public static monkeybrain teach(int prefix, String filename)
                                    throws FileNotFoundException{

        // initialize the monkeybrain class
        monkeybrain MB = new monkeybrain(prefix);
        Scanner infile = new Scanner(new File(filename));
        List<String> inList= strip(infile);
        Iterator<String> iter= inList.iterator(); 
        String inWord;
        while(iter.hasNext()){

            inWord = iter.next();
            inWord = inWord.concat("     ");
            inWord = inWord.substring(0,prefix+1);
            MB.learn(inWord);           

        }
    
        return MB;
    }
    
    // strip method adapted from the codes project
    public static List<String> strip(Scanner in){

        List<String> daList = new ArrayList<String>();
        String inStr = "";
        char inChar = 65;
        // read in each character, omitting bad characters, and toggling
        // whether the characters are legal based on the '>' and '<'
        // characters
        while(in.hasNext()){


            inStr = in.next();
            inStr.toLowerCase();
            inChar = inStr.charAt(0);
            if(inChar >= 97){

                if(inStr.charAt(inStr.length()-1)<97){
                    daList.add(inStr.substring(0,inStr.length()-1));
                }
                else{
                    daList.add(inStr);
                }
            }
            
            
            
            
            }
            //if(((int)inChar == 60 || (int)inChar == 62)&& toggle == true){
            //    toggle = false;
            //    inChar = 0;
            //}
            //if(((int)inChar == 60 || (int)inChar == 62)&& toggle == false){
            //    toggle = true;
            //    inChar = 0;
            //}
        return daList;

    }

    
}
