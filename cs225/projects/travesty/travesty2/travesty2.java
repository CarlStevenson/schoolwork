// travesty2.java
// travesty2 expands on the code written for a dynamic brain in class
// in order to demonstrate travesty.
// Code adapted from Anthony Kapolka
// Edited by: Carl Stevenson
// ********************************************************************

import java.util.*;	// for Scanner

public class travesty2
{

    int prefixlvl;			      // how deep in the brain am I?
    float [] nextprob;   		  // the probabilities at this level
    float [] letterprobs;         // used for generating words
    travesty2 [] next;    		  // links to deeper structure
    Random r = new Random();      // used for generating words

    public travesty2()
    {
    }

    public travesty2(int n)
    {
        prefixlvl = n;
    }


   private int toIndex(char c)
   {
      // no safety - must be lower case a..z or space
      if (c == ' ') return 26;
        else return (c - 'a');
   }

   private char toCharacter(int i)
   {
      // no safety - must be lower case a..z or space
      if (i == 26) return ' ';
        else return (char)(((int)'a') + i);
   }

   public void braindump()
   {
        // does not indent subtrees nicely when printing...
	if (prefixlvl > 0)
        {
         if (next != null)
           for (int i=0; i< 27; i++)
             if (next[i] != null)
               { System.out.print((char)('a' + i) + "->");
                 next[i].braindump();
               }
        }
        else // prefixlvl == 0
         {
          if (nextprob != null)
           for (int i=0; i< 27; i++)
             if (nextprob[i] > 0)
               System.out.print(toCharacter(i) + "=" + nextprob[i] + " ");
          System.out.println();
         }

   }

   public void insert(String S)
   { 
     // Going to increase probability having seen S
     // S = prefix + next character
     // S must be lower case a..z or space

     if (S.length() > 1) // we are in the prefix
      {

        // peel off first char and recurse 
        if (next == null)
           next = new travesty2[27];
        if (next[toIndex(S.charAt(0))] == null)
           next[toIndex(S.charAt(0))] = new travesty2(prefixlvl-1);

        next[toIndex(S.charAt(0))].insert(S.substring(1)); 

      }
     else // S.length() == 1
      {

        if (nextprob == null){
            nextprob = new float [27];   // the probabilities at this level
        }
        nextprob[toIndex(S.charAt(0))]++;   //tick my probability
      }
   }

   // genWord adapted from travesty 1
   // genWord generates one word
   
    public String genWord(int l, String word){
        
        String letter = "";
        
        if (l > 0 && next!=null){
            // crawl down the travesty trie
            int holder = 0;
        
            for(int i=0; i<27; i++){
                if(next[i] != null){
                    holder++;
                }
            }
            //chooses which branch to follow
            
            int chooser = r.nextInt(holder);
            int index = 30;
        
            for(int i=0; i<27; i++){
                if(next[i] != null){
                    if(chooser == 0){
                        letter = Character.toString(toCharacter(i));
                        index = i;
                        break;
                    }
                    chooser--;
                }
            }
            
            // append our found letter and append another one recursively
            // based on prefix length
        
            try{
            word = word.concat(next[index].genWord(l-1, letter));
            }
            catch(NullPointerException e){
            
                return word; 
            }
        }else{
        
            // find the letter based on the probability charts if it's the
            // last letter   
            int total = 0;
            
                if(letterprobs == null){
                
                    letterprobs = new float[27];
                    for(int i=0; i<27; i++){
                        total += nextprob[i];
                        letterprobs[i] = total;
                    }
                }
                for(int i=0; i<27; i++){
                    total+=nextprob[i];
                }
            
                int checkRand = r.nextInt(total);
                for(int i=0; i<27; i++){
                        if(nextprob[i] >0){
                        word = word.concat(Character.toString(toCharacter(i)));
                        return word;
                        }
                }
            word = word.concat(letter); 
            return word;
 
            
        }
        return word;
 
        
        
    }
}
