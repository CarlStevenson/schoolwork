// sc.java
// sc.java is a spell checker
// Written by: Carl Stevenson
// ****************************************************************************

import java.util.*;
import java.io.*;

public class sc{

    // global printstream for ease
    public static PrintStream out = new PrintStream(System.out);
    // global scanner for ease
    public static Scanner keyboard = new Scanner(System.in);


    public static void main(String[] args)throws FileNotFoundException{


        // get the desired hash functions
        boolean[] theFunctions = getTheFuncts();       
        
        // get the desired array size
        int arraySize = 0;
        out.println("What should the hash table array size be?");
        arraySize = keyboard.nextInt();

        // load the dictionary in, set up the hash table(s), check words
        checkIt(theFunctions, arraySize);
        
        
    } // end main
    // the hash functions that were provided.
    static int hash1 (String S)
    {
        return S.hashCode();
    }

    static int hash2 (String S)
    {
        int hashval = 0;

        for (int i = 0; i < S.length(); i++)
           hashval = (hashval << 3) + S.charAt(i);

        return hashval;

    }

    static int hash3 (String S)
    {
        int hashval = 0;
        int g;

        for (int i = 0; i < S.length(); i++)
            hashval = (hashval << 4) + S.charAt(i);

        if ( (g = hashval & 0xf0000000) == 0 )
        {  
            hashval ^= g >> 24;
            hashval ^= g;
        }

        return hashval;
    }
    // my hash function
    static int hash4(String S){

        int hashval = 0;
        int inc = 1;
        S = S.toLowerCase();
        for(int i=0; i<S.length();i++){

            hashval+=((((int)S.charAt(i))-64)*inc);
            inc= inc*10;

        }
        return hashval;

    }



    public static boolean[] getTheFuncts(){

        int choice = 5;
        boolean[] theFunctions = new boolean[4];

        while (choice!=-1){

            out.print("\nWhich hash functions do you want to use? <1-4, 0 done> ");
            choice = keyboard.nextInt()-1;
            if(choice<-1 || choice>3){
                out.println("Please choose an appropriate hash function.");
            }else if(choice == -1) break;
            else theFunctions[choice] = true;

        }
        return theFunctions;

    } // end getTheFuncts
    
    // load in dictionary, set up the required hash tables, check the text
    public static void checkIt(boolean[] functs, int arraySize)
                                            throws FileNotFoundException{
        // set up our boolean dictionary!

        // setting up the arrays on an as-needed basis.        
        boolean[] hArray1 = new boolean[1];
        if(functs[0]){hArray1 = new boolean[arraySize];}
        boolean[] hArray2 = new boolean[1];
        if(functs[1]){hArray2 = new boolean[arraySize];}
        boolean[] hArray3 = new boolean[1];
        if(functs[2]){hArray3 = new boolean[arraySize];}
        boolean[] hArray4 = new boolean[1];
        if(functs[3]){hArray4 = new boolean[arraySize];}

        Scanner dictFile = new Scanner(new File(
                    "/home/mathcs/staff/kapolka/webdocs/cs225/hash/words"));
                
        dictFile.useDelimiter("[^a-zA-Z]");

        String word = "";
        //int falsePosCount = 0;
        // go through the dictionary and put it in, including 'I' and 'a'
        // which were not provided in the dictionary
        while(dictFile.hasNext()){

            word=dictFile.next().toLowerCase();
            if(functs[0]){
                hArray1[Math.abs((hash1(word)%arraySize))] = true;
                hArray1[Math.abs((hash1("i")%arraySize))] = true;
                hArray1[Math.abs((hash1("a")%arraySize))] = true;
            }
            if(functs[1]){
                hArray2[Math.abs((hash2(word)%arraySize))] = true;
                hArray2[Math.abs((hash2("i")%arraySize))] = true;
                hArray2[Math.abs((hash2("a")%arraySize))] = true;
            }
            if(functs[2]){
                hArray3[Math.abs((hash3(word)%arraySize))] = true;
                hArray3[Math.abs((hash3("i")%arraySize))] = true;
                hArray3[Math.abs((hash3("a")%arraySize))] = true;
            }
            if(functs[3]){
                hArray4[Math.abs((hash4(word)%arraySize))] = true;
                hArray4[Math.abs((hash4("i")%arraySize))] = true;
                hArray4[Math.abs((hash4("a")%arraySize))] = true;
            }
            //falsePosCount++;
        } // end while
        
        // check to see the false positive chance
        double[] falsePos = new double[4];
        int[] falsePosCount = new int[4];
        for(int i =0; i<arraySize;i++){

            if(functs[0]){                
                if(hArray1[i]){
                    falsePosCount[0]++;
                }
            }
            if(functs[1]){                
                if(hArray2[i]){
                    falsePosCount[1]++;
                }
            }
            if(functs[2]){                
                if(hArray3[i]){
                    falsePosCount[2]++;
                }
            }
            if(functs[3]){                
                if(hArray4[i]){
                    falsePosCount[3]++;
                }
            }        
        }
        
                
        
        Scanner inFile = new Scanner(new File("datafile"));
        inFile.useDelimiter("[^'a-zA-Z]");
        String inWord = "";
        boolean checker = true;
        int misspelled = 0;
        PrintStream output = new PrintStream(new File("sc.results"));
        out.println("\nMispelled words:\n");
        output.println("\nMispelled words:\n");
        while(inFile.hasNext()){

            inWord=inFile.next().toLowerCase();
            if(functs[0]){
                if(!hArray1[Math.abs((hash1(inWord)%arraySize))]){
                    checker = false;
                }
            }
            if(functs[1]){
                if(!hArray2[Math.abs((hash2(inWord)%arraySize))]){
                    checker = false;
                }
            }
            if(functs[2]){
                if(!hArray3[Math.abs((hash3(inWord)%arraySize))]){
                    checker = false;
                }
            }
            if(functs[3]){
                if(!hArray4[Math.abs((hash4(inWord)%arraySize))]){
                    checker = false;
                }
            }
            if(!checker){

                misspelled++;
                out.println(inWord);
                output.println(inWord);
            }
            // reset the checker
            checker = true;
        } // end while

        // output misc. data
        out.println("Total misspelled words: "+misspelled);
        output.println("Total misspelled words: "+misspelled);
        out.println("\nMispelled words based on the dictionary given.\n");
        output.println("\nMispelled words based on the dictionary given.\n");
        out.println("False positive chances given per hash function,"+
                    " not combined.");
        output.println("False positive chances given per hash function,"+
                    " not combined.");
        
        if(functs[0]){
            falsePos[0] = (double)falsePosCount[0]/(double)arraySize;
            out.println("False positive chance for hash1: "+falsePos[0]);
            output.println("False positive chance for hash1: "+falsePos[0]);
        }
        if(functs[1]){
            falsePos[1] = (double)falsePosCount[1]/(double)arraySize;
            out.println("False positive chance for hash2: "+falsePos[1]);
            output.println("False positive chance for hash2: "+falsePos[1]);
        }
        if(functs[2]){
            falsePos[2] = (double)falsePosCount[2]/(double)arraySize;
            out.println("False positive chance for hash3: "+falsePos[2]);
            output.println("False positive chance for hash3: "+falsePos[2]);
        }
        if(functs[3]){
            falsePos[3] = (double)falsePosCount[3]/(double)arraySize;
            out.println("False positive chance for hash4: "+falsePos[3]);
            output.println("False positive chance for hash4: "+falsePos[3]);
        }
        
        
    } // end checkIt
} // end class
