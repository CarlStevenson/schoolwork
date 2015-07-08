// monkeybrain.java 
// monkeybrain.java is a class meant to calculate letter frequencies,
// and be able to output random text based on the letter frequencies
// of text given as input.
// Adapted from code written by: Anthony Kapolka 
//                               monkeybrain class
//                               Thu Sep 26 18:35:40 EDT 2013

import java.util.*;

class monkeybrain
{
    int brainlevel;		  // counts down
    monkeybrain nextlevel [];       // index for this array level
    double letterprobabilities [];   // % next letter is...

    public monkeybrain(int bl)
    {
       brainlevel = bl;
       // System.out.println("making level "+bl);

        letterprobabilities = new double[27];
        for (int c=0; c < 27; c++)
            letterprobabilities[c] = 0.0;
        if (bl > 0)
        {
           nextlevel = new monkeybrain[27];
           for (int c=0; c < 27; c++)
           nextlevel[c] = new monkeybrain(bl-1);
        }
          
    }

    public void learn(String S)
    {
        if(S.charAt(0)<97||S.charAt(0)>122 ){
            letterprobabilities[26]+=1;
            return;
        }
        else{
            //System.out.println(S.charAt(0));
            letterprobabilities[S.charAt(0)-97]+=1;
        }
        if (S.length() > 1)
         {
           String S1 = S.substring(1,S.length());
           nextlevel[S.charAt(0)-97].learn(S1); 
         }
      
       
    }

    public void cumulate(int l){

        // makes the percentages
        double cumuNum = 0;
        double letternumber =0;
        for (int c = 0; c < 27; c++){
            letternumber+=letterprobabilities[c];
            }
        
        for (int c = 0; c < 27; c++){
            if(letterprobabilities[c]!=0){
                letterprobabilities[c]=letterprobabilities[c]/letternumber;
            }
        }
        for(int c=0; c<27;c++){
            if(letterprobabilities[c]>0){
                cumuNum+=letterprobabilities[c];
                letterprobabilities[c]=cumuNum;
            }

        }
        if (l > 0){
           for (int c = 0; c < 27; c++) 
           nextlevel[c].cumulate(l-1);
        }

    }
    public String genWord(int l, String word){

        Random r = new Random();
        int holder = 30;
        double checker = r.nextDouble();
        double closeness = 0;
        
        for(int i=0; i<27; i++){
            
            closeness = checker-letterprobabilities[i];
            if(closeness<=0){
                
                break;    
            }else if(letterprobabilities[i]>0.0){
                holder=i;
            }
        }
        
        if(holder >= 26){
            return " ";
        }

        String letter = Character.toString((char)(holder+97));
        //word = word.concat(letter);
        
        if (l > 0){
            word = word.concat(nextlevel[holder].genWord(l-1, letter));
        }
        
        return word;
    }
    public void dump(int l)
    {
        System.out.println(l);
        for (int c = 0; c < 27; c++){ 
            System.out.print(" " +letterprobabilities[c]);   
        }
        System.out.println();
        if (l > 0){
            for (int c = 0; c < 27; c++){ 
                nextlevel[c].dump(l-1);
                
            }
        } 
      
       
    }
}
