// driver2.java
// uses the dynamic brain structure to be used for Travesty.
// Written by Carl Stevenson
// Uses some code from the monkeybrain program
// ***************************************************************

import java.util.*;
import java.io.*;

public class driver2{

    // set up global variables to be used
    static Scanner sc = new Scanner(System.in);
    static PrintStream out = new PrintStream(System.out);
    
    static travesty2 brain;
    static int prefix;

    public static void main(String[] args)throws FileNotFoundException{

        out.print("What prefix size would you like to use? : ");
        prefix = sc.nextInt();
        out.print("What is the file the text is located in? : ");
        String filename = sc.next();
	    out.print("How much random text do you want to generate?(in words): ");
        int outSize = sc.nextInt();
        
        brain = new travesty2(prefix);
        
        teach(filename);
        spew(outSize);
    }
    
    // spew outputs the random text desired
    
    public static void spew(int outSize){
    
        out.println();
        for(int i=0; i<outSize; i++){
        
            out.print(brain.genWord(prefix, "") + " ");
        }
        out.println();
    }
    
    // teach loads up the dynamic brain with words
    
    public static void teach(String inFile)throws FileNotFoundException{
    
        Scanner file = new Scanner(new FileInputStream(new File(inFile)));
        String inWord;
        while(file.hasNext()){
            inWord = file.next();
            if(inWord.charAt(0) != '<'){
                inWord = process(inWord);
                if(inWord.equals("")){}
                else 
                brain.insert(inWord);
            }
        }
    }
    
    
    // process proccesses the input from the file and returns a String to 
    // be inserted into the brain
    
    public static String process(String inWord){
       
        inWord.toLowerCase();
        for(int i = 0; i<inWord.length(); i++){
        
            if(inWord.charAt(i)<'a' || inWord.charAt(i)>'z'){
                inWord = "";
            }
        }
        return inWord;
    }
}
    
    
