// dictionary.java
// dictionary.java is a trie structure used to store a dictionary to
// be checked against as a spellchecker.
// Code adapted from Travesty 2.
// Edited by Carl Stevenson
// *********************************************************************

import java.util.*;	// for Scanner

public class dictionary{

    boolean [] nextletter;        // the probabilities at this level
    dictionary [] next;    		  // links to deeper structure

    public dictionary(){
    
    }

   private int toIndex(char c){
   
      // no safety - must be lower case a..z or space
      if (c == ' ') return 26;
        else return (c - 'a');
   }

   private char toCharacter(int i){
      
      // no safety - must be lower case a..z or space
      if (i == 26) return ' ';
        else return (char)(((int)'a') + i);
   }

    public void insert(String S){
    
        // S must be lower case a..z

        if (S.length() > 1) // we are in the prefix
        {

            // peel off first char and recurse 
            if (next == null)
               next = new dictionary[27];
            if (next[toIndex(S.charAt(0))] == null)
               next[toIndex(S.charAt(0))] = new dictionary();

            next[toIndex(S.charAt(0))].insert(S.substring(1)); 

        }else if (S.length() == 1) // S.length() == 1
        {

            if (nextletter == null){
                nextletter = new boolean [27];
                Arrays.fill(nextletter, false);
            }
            nextletter[toIndex(S.charAt(0))] = true; 
        }
    }
   
    // check is the function to compare a word against the dictionary.
    // returns true if the word is present in the dictionary,
    // returns false if the word is not present in the dictionary.
    // based on the insert function
    
    public boolean check(String inWord){
   
        boolean isGood = false;
        
        if(inWord.length() > 1){
        
            if(next[toIndex(inWord.charAt(0))] == null){
                // returns false at the end, no more letters in that trie
                // branch
            }else if(
                next[toIndex(inWord.charAt(0))].check(inWord.substring(1))){
                isGood = true;
                // everything from deeper in the word is good!
        }
        }else if(inWord.length() == 1){
            if(nextletter == null){
                // returns false at the end, a dictionary word hadn't
                // terminated at this point
            }
            
            else if(nextletter[toIndex(inWord.charAt(0))]){
                isGood = true;
                // word checked successfully!
            }
        }
        return isGood;
    }
}
