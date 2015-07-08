// sc2.java
// sc2.java is an alternate kind of spell schecker that uses a trie
// to store a dictionary.
// Code adapted from the Travesty 2 & the Spellchecker 1 programs.
// Edited by Carl Stevenson
// *********************************************************************

import java.io.*;
import java.util.*;

public class sc2{

    // global Scanner and PrintStream for ease
    static Scanner sc = new Scanner(System.in);
    static PrintStream out = new PrintStream(System.out);
    // set up global dictionary for ease
    static dictionary dict;
    
    public static void main(String[] args)throws FileNotFoundException{
    /* /home/mathcs/staff/kapolka/webdocs/cs225/hash/words */
    
        dict = new dictionary();
        String inDict = "/home/mathcs/staff/kapolka/webdocs/cs225/hash/words";
        
        String inFile = "datafile";
        teach(inDict);
        List<String> badWords = checkWords(inFile);
        output(badWords);
        //dict.braindump();
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
    
    // check compares the words in datafile with the dictionary
    // returns a list of the incorrect words in datafile

    public static List<String> checkWords(String inFile)
                        throws FileNotFoundException{
    
        Scanner file = new Scanner(new FileInputStream(new File(inFile)));
        file.useDelimiter("[^'a-zA-Z]");
        String inWord;
        List<String> badWords = new ArrayList<String>();
        while(file.hasNext()){
        
            inWord = file.next();
            inWord = process(inWord);
            if(!dict.check(inWord)){
                if(inWord.equals("\n") || inWord.equals("")){}
                //else if(inWord.contains(new CharSequence(' '))){}
                
                else badWords.add(inWord);
            }
        }
        return badWords;    
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
    
    // output prints to the screen and the
    public static void output(List<String> badWords)
                                throws FileNotFoundException{
    
        PrintStream output = new PrintStream(new File("sc2.results"));
        
        int misspelled = badWords.size();
        out.println("Total misspelled words: "+misspelled);
        output.println("Total misspelled words: "+misspelled);
        out.println("Mispelled words: \n");
        output.println("Mispelled words: \n");
        
        for(int i=0; i<badWords.size(); i++){
            out.println(badWords.get(i));
            output.println(badWords.get(i));
        }
        out.println();
        

    }
}
