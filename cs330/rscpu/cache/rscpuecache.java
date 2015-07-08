// rscpuecache.java
// Relatively Simple CPU
// Program to simulate a cpu in order to demonstrate the inner functionality
// of a cpu
// Written by: Carl Stevenson
// 4/20/15
// rscpue adds cached memory
// *************************************************************************


import java.io.*;
import java.util.*;

public class rscpuecache
{
  // Registers to be used in the CPU

  // 16 bit accumulator
  public static short AC;
  // 16 bit general purpose register
  public static short R;
  // 4 bit Flag: Z C V N
  public static boolean[] flag = new boolean[4];
  // 16 bit address register
  public static short AR;
  // 16 bit program counter
  public static short PC;
  // 8 bit data register
  public static byte DR;
  // 8 bit instruction register
  public static byte IR;
  // 8 bit temporary register
  public static byte TR;

  // Memory to be used
  public static byte[] M = new byte[65536];

  // blocks for the cache
  static class block
  {
    // tag is a 4 bit value
    byte tag;
    byte[] f = new byte[4];
  }
  public static block[] cache = new block[1024];
  public static int hits;
  public static int misses;
  // global PrintStream for ease
  public static PrintStream out = System.out;




  // main
  // Executes the operations required to operate the simulated CPU 
  public static void main(String args[]) throws FileNotFoundException
  {
    // Print the intro
    out.println("\nCarl Stevenson");
    out.println("RSCPU");
    out.println("This program accepts the title of a file from the");
    out.println("user, opens the file, then reads in lines of ");
    out.println("hexadecimal bytes, and executes them in the ");
    out.println("simulated CPU.\n");
    // initialize our global registers & memory
    // FLAG & M are initialized to false & 0 by default Java 
    // behavior
    // ********************************************************
    AC=0; 
    R=0;
    AR=0;
    PC=0; 
    DR=0; 
    IR=0; 
    TR=0;

    // initialize our cache
    initcache();
    // get the file name
    Scanner sc = new Scanner(System.in);
    String inName;
    out.print("Enter the name of the file containing the program: ");
    inName = sc.next();
    out.println("\nYou chose: "+inName);
    out.println();
    Scanner in = new Scanner(new File(inName));
    

    // Print out the starting values of all the registers
    out.println("Initial register values: ");
    regStat();
    out.println("\tIR = "+(0xFF&IR)+", TR = "+(0xFF&TR)+"\n");

    // load in the program to memory
    load(in);

    boolean run = true;
    while(run)
    {
      fetch();
      run = decode();
      regStat();
    }
    out.println("Cache hits = "+hits+" Cache misses = "+misses);
  }

  // initcache
  // initializes the cache for use by the program
  public static void initcache()
  {
    hits = 0;
    // set each tag to an invalid value
    // set each data value to 0
    for(int i = 0; i<1024; i++)
    {
      cache[i] = new block();
      cache[i].tag = (byte)255;
      cache[i].f[0] = 0;
      cache[i].f[1] = 0;
      cache[i].f[2] = 0;
      cache[i].f[3] = 0;
    }

  }

  // readMemory
  // reads the desired address from the cache, if it's not in cache,
  // it loads it in, then returns the desired value
  public static byte readMemory(short address)
  {
    // 4096
    byte tag;
    short line;
    short blocknum;
    short cacheline;
    byte word;
    // determine cache, line, and word values
    tag = (byte)((0xF000&address)/4096);
    line = (short)(0x0FFF&address);
    word = (byte)(0x0003&address);
    // determine what line in cache the desired address would be in
    // direct mapping
    // i = j%m
    //
    // i = cache line number
    // j = main memory block number
    // m = number of lines in the cache
    // determine block number
    blocknum = (short)((0xFFFC&address)/4);
    // determine what line in cache it should be in
    cacheline = (short)(blocknum % 1024);
    // check if the tags match up
    mAccessPrint(address, blocknum, cacheline, line, tag);
    if(tag == cache[cacheline].tag)
    {
      // if they do, we already have the block in the cache
      out.println("Cache hit");
      hits++;
      out.println("result: cache line = "+(0xFFFF&line)+" tag = "+tag);
      // return the desired value
      return cache[cacheline].f[word];
    } 
    // else, we need to load the block into the cache and return the
    // desired value
    out.println("Cache miss");
    misses++;
    loadBlock(address, cacheline, tag);

    out.println("result: cache line = "+(0xFFFF&line)+" tag = "+tag);
    return cache[cacheline].f[word];

  }

  // writeMemory
  // writes the byte to the given memory location, through the cache
  // if the desired memory location is not in cache, it will load it,
  // modify it, and write it through to main memory
  public static void writeMemory(short address, byte data)
  {
    byte tag;
    short line;
    short blocknum;
    short cacheline;
    byte word;
    // determine cache, line, and word values
    tag = (byte)((0xF000&address)/4096);
    line = (short)(0x0FFF&address);
    word = (byte)(0x0003&address);
    // determine what line in cache the desired address would be in
    // direct mapping
    // i = j%m
    //
    // i = cache line number
    // j = main memory block number
    // m = number of lines in the cache
    // determine block number
    blocknum = (short)((0xFFFC&address)/4);
    // determine what line in cache it should be in
    cacheline = (short)(blocknum % 1024);
    // check if the tags match up
    mAccessPrint(address, blocknum, cacheline, line, tag);
    if(tag == cache[cacheline].tag)
    {
      // if they do, we already have the block in the cache
      out.println("Cache hit");
      hits++;
      out.println("result: cache line = "+(0xFFFF&line)+" tag = "+tag);
      // change the desired address location to the desired value
      cache[cacheline].f[word]= data;
      // write through to main memory
      M[0xFFFF&address] =data;
      return;
    } 
    // else, we need to load the block into the cache and return the
    // desired value
    out.println("Cache miss");
    misses++;
    loadBlock(address, cacheline, tag);
    // write to cache
    cache[cacheline].f[word]= data;
    // write through to main memory
    M[0xFFFF&address] =data;

    out.println("result: cache line = "+(0xFFFF&line)+" tag = "+tag);
  }

  // loadBlock
  // loads a block from main memory into the appropriate cache line
  public static void loadBlock(short address, short cacheline, byte tag)
  {
    cache[cacheline]= new block();
    cache[cacheline].tag = tag;
    cache[cacheline].f[0] = M[(0xFFFC&address)];
    cache[cacheline].f[1] = M[(0xFFFC&address)+1];
    cache[cacheline].f[2] = M[(0xFFFC&address)+2];
    cache[cacheline].f[3] = M[(0xFFFC&address)+3];

  }

  // mAccessPrint
  // prints information about memory accesses
  public static void mAccessPrint(short address, short blocknum, 
                                    short cacheline, short line, byte tag)
  {
    // AR value we're referencing
    out.println("Memory Access:\nAR = "+(0xFFFF&address)+" block number = "
                  +(0xFFFF&blocknum)+" cache line number = "+(0xFFFF&cacheline)
                  +" cache line = "+line+" tag = "+tag);
  }
  // load
  // loads the provided program into memory
  public static void load(Scanner in)
  {

    int i =0;
    String code;
    while(in.hasNext())
    {
      code = in.next();
      writeMemory((short)i,(byte)(0xFF&Short.valueOf(code, 16)));
      i++;
    }
  }

  // regStat
  // Prints the values in the registers for use after each operation
  public static void regStat()
  {
    // if the instruction represents an 8 bit function, print the values
    // in 8-bit format
    if((0xFF&IR) < 5 || ((0xFF&IR)>7 && (0xFF&IR) < 16)||
         ((0xFF&IR)>17 && (0xFF&IR) <23))
    {
      out.print("Current Data: AC = "); out.print((byte)AC);
      out.print(", R = "); out.print((byte)R);
    } else
    {
      // otherwise print it in 16-bit
      out.print("Current Data: AC = "); out.print(AC);
      out.print(", R = "); out.print(R);
    }
    out.print(", ZCVN = ");
    for(int i = 0; i<4; i++)
    {
      if(flag[i])
      {
        out.print("1");
      } else
      {
        out.print("0");
      }
    }
    out.print(", AR = "); out.print((int)0xFFFF&AR);
    out.print(", PC = "); out.print((int)0xFFFF&PC);
    out.print(", DR = "); out.print((int)0xFF&DR);
    out.println();
    
    return;
  }

  // printzcvn
  // printzcvn prints teh values of ZCVN as neeed by routines
  public static void printzcvn()
  {
    out.print(" ZCVN = ");
    for(int i = 0; i<4; i++)
    {
      if(flag[i])
      {
        out.print("1");
      } else
      {
        out.print("0");
      }
    }
    out.println();
  }

  // fetch
  // Fetch executes all the subphases required in the fetch cycle
  public static void fetch()
  {
    // fetch1
    AR = PC;
    out.println("fetch1: AR = "+((int)0xFFFF&AR)+" and PC = "
      +((int)0xFFFF&PC));
    // fetch2
    PC++;
    DR = readMemory(AR);
    out.println("fetch2: DR = "+((short)0xFF&DR)+" and PC = "
      +((int)0xFFFF&PC));

    //fetch3
    AR = PC;
    IR = DR;
    out.println("fetch3: IR = "+((short)0xFF&IR)+" and AR = "
      +((int)0xFFFF&AR));

  }

  // decode
  // Decode interprets the opcode and performs the operation based on it
  public static boolean decode()
  {
    
    switch((short)0xFF&IR)
    {
      case 0: noop();
              break;
      case 1: ldac();
              break;
      case 2: stac();
              break;
      case 3: mvac();
              break;
      case 4: movr();
              break;
      case 5: jump();
              break;
      case 6: jmpz();
              break;
      case 7: jpnz();
              break;
      case 8: add();
              break;
      case 9: sub();
              break;
      case 10: inac();
              break;
      case 11: clac();
              break;
      case 12: and();
              break;
      case 13: or();
              break;
      case 14: xor();
              break;
      case 15: not();
              break;
      case 16: jmpc();
              break;
      case 17: jv();
              break;
      case 18: rl();
              break;
      case 19: rr();
              break;
      case 20: lsl();
              break;
      case 21: lsr();
              break;
      case 22: mvi();
              break;
      case 23: jn();
              break;
      case 129: xldac();
              break;
      case 130: xstac();
              break;
      case 131: xmvac();
              break;
      case 132: xmovr();
              break;
      case 136: xadd();
              break;
      case 137: xsub();
              break;
      case 138: xinac();
              break;
      case 139: xclac();
              break;
      case 140: xand();
              break;
      case 141: x_or();
              break;
      case 142: xxor();
              break;
      case 143: xnot();
              break;
      case 146: xrl();
              break;
      case 147: xrr();
              break;
      case 148: xlsl();
              break;
      case 149: xlsr();
              break;
      case 150: xmvi();
              break;
      case 255: halt();
              return false;
      default:
      out.println("Invalid opcode! "+((short)0xFF&IR));
      System.exit(1);
    }

    return true;

  }

  // noop
  // no operation, do nothing
  public static void noop()
  {
    out.println("no op instruction");
  }
  
  // ldac 8 bit
  // ldac places the value in the given address into the accumulator
  public static void ldac()
  {
    
    out.println("ldac instruction");
    // LDAC1 read the high order half of the address into DR
    DR = readMemory(AR);
    PC++;
    AR++;
    out.println("ldac1 AR = "+((int)0xFFFF&AR)+" PC = "
      +((int)0xFFFF&PC)+" DR = "+((int)0xFF&DR));

    //LDAC2 save the contents of DR
    TR=DR;
    DR=readMemory(AR);
    PC++;
    out.println("ldac2 AR = "+((int)0xFFFF&AR)+" PC = "
      +((int)0xFFFF&PC)
      +" DR = "+((int)0xFF&DR)+" TR = "+((int)0xFF&TR));

    // LDAC3 form the address AR from TR and DR
    AR = (short)(((0xFF&TR)*256) + (0xFF&DR));
    out.println("ldac3 AR = "+ ((int)0xFFFF&AR));

    // LDAC4 read in the byte from memory
    DR = readMemory(AR);
    out.println("ldac4 DR = "+ ((int)0xFF&DR));

    // LDAC5 place DR in the accumulator

    // clear out the lowest 8 bits
    AC -= (0xFF&AC);
    // add the value to AC
    AC += 0xFF&DR;
    out.println("ldac5 AC = "+ (byte)(0xFF&AC));
    
  }
  
  // stac
  // stac stores the accumulator in the given address
  public static void stac()
  {
    out.println("stac instruction");
    // STAC1 read the high order half of the address into DR
    DR = readMemory(AR);
    PC++;
    AR++;
    out.println("stac1 AR = "+((int)0xFFFF&AR)+" PC = "
      +((int)0xFFFF&PC)
      +" DR = "+((int)0xFF&DR));

    //STAC2 save the contents of DR
    TR=DR;
    DR=readMemory(AR);
    PC++;
    out.println("stac2 AR = "+((int)0xFFFF&AR)+" PC = "
      +((int)0xFFFF&PC)
      +" DR = "+((int)0xFF&DR)+" TR = "+((int)0xFF&TR));

    // STAC3 form the address AR from TR and DR
  
    AR = (short)(((0xFF&TR)*256) + (0xFF&DR));
    out.println("stac3 AR = "+ ((int)0xFFFF&AR));

    // STAC4 put the value in the accumulator into DR
    DR = (byte)(0xFF&AC);
    out.println("stac4 DR = "+ ((byte)0xFF&DR));

    // STAC5 put the value in DR into the desired memory location
    writeMemory(AR,DR);
    out.println("stac5 M[AR] = "+((byte)0xFF&readMemory(AR)));
  }
  
  // mvac
  // mvac places the value in AC into the R register
  public static void mvac()
  {
    out.println("mvac instruction");
    // MVAC1 put value in AC into the R register

    // clear out the bottom byte of R
    R -= (0xFF&R);
    // add in the bottom byte of AC
    R += 0xFF&AC;
    out.println("mvac1 R = "+(byte)(0xFF&R));
  }

  // movr
  // movr puts the value in the R register in the accumulator
  public static void movr()
  {
    out.println("movr instruction");
    // MOVR1 puts the value in the R register into the accumulator
    // clear out the bottom byte of R
    AC -= (0xFF&AC);
    // add in the bottom byte of AC
    AC += (0xFF&R);
    out.println("movr1 AC = "+(byte)(0xFF&AC));
  }
  
  // jump
  // jump sets the program counter equal to the address specified
  public static void jump()
  {
    out.println("jump instruction");
    jumper("jump");

  } 
  // jumper
  // jumper carries out the jump instruction for the conditional jumps
  public static void jumper(String op)
  {
    // JUMP1 read the high order half of the address into DR
    DR = readMemory(AR);
    PC++;
    AR++;
    out.println( op +"1 AR = "+((int)0xFFFF&AR)+" PC = "
      +((int)0xFFFF&PC)
      +" DR = "+((int)0xFF&DR));

    // JUMP2 save the contents of DR
    TR=DR;
    DR=readMemory(AR);
    PC++;
    out.println(op+"2 AR = "+((int)0xFFFF&AR)+" PC = "
      +((int)0xFFFF&PC)
      +" DR = "+((int)0xFF&DR)+" TR = "+((int)0xFF&TR));

    // JUMP3 form the address from TR and DR and place it in PC
  
    PC = (short)(((0xFF&TR)*256) + (0xFF&DR));
    out.println(op+"3 PC = "+ ((int)0xFFFF&AR));

  }
  // jmpz
  // jmpz jumps to the given address if the z flag is 1
  public static void jmpz()
  {
    out.println("jmpz instruction");
    if(flag[0])
    {
      jumper("jmpz");
    }else
    {
      // increment past the uneeded address
      PC+=2;
      out.print("jmpz1 PC = "+((int)0xFFFF&PC));
      printzcvn();
    }
  }

  // jpnz
  // jpnz jumps to the given address if the z flag is 0
  public static void jpnz()
  {
    out.println("jpnz instruction");
    if(!flag[0])
    {
      jumper("jpnz");
    }else
    {
      // incrememnt past the uneeded address
      PC+=2;
      out.print("jpnz1 PC = "+((int)0xFFFF&PC));
      printzcvn();
    }
  } 

  // add
  // add adds the value in R with AC, and places the value in AC
  public static void add()
  {
    out.println("add instruction");
    // ADD1 adds R into AC, and sets the flags as appropriate
    add1();

    out.print("add1 AC = "+(byte)(0xFF&AC)+" R = "+(byte)(0xFF&R));
    printzcvn();
  } 

  // add1
  // for use by add and sub for clean code
  public static void add1()
  {
    setFlagsAS();

    AC += -(byte)(0xFF&AC)+ ((byte)(0xFF&AC)) + (byte)(0xFF&R);
  }
  
  public static void sub()
  {
    out.println("sub instruction");
    // SUB1 complements R, and calls the add() routine
    R = (short)-R;
    add1();
    // set R back to its original value
    R = (short)-R;

    out.print("sub1 AC = "+(byte)(0xFF&AC));
    printzcvn();
  }

  // setFlagsAS
  // setFlagsAS sets the flags for the add and subtract routines
  public static void setFlagsAS()
  {
    // if the result is 0, set zero flag to true
    if((byte)(0xFF&AC) + (byte)(0xFF&R) == 0)
    {
      flag[0] = true;
    } else
    {
      flag[0] = false;
    }
    // "it represents the result too large in the case of 
    // add or a borrow in the case of subtract."
    
    // start by setting the value false
    flag[1] = false;
    // if 2 negative numbers, always a carry
    if((byte)(0xFF&AC) < 0 && (byte)(0xFF&R) < 0)
    {
      flag[1] = true;
    }
    // otherwise, if one positive and one negative number,
    // if the magnitude of the positive number is >=
    // the magnitude of the negative number, it's a carry
    else if((byte)(0xFF&AC) < 0 && (byte)(0xFF&R) > 0)
    {
      if((byte)(0xFF&R) >= (byte)-(0xFF&AC))
      {
        flag[1] = true;
      }
    } else if((byte)(0xFF&AC)> 0 && (byte)(0xFF&R) < 0)
    {
      if((byte)(0xFF&AC) >= (byte)-(0xFF&R))
      {
        flag[1] = true;
      }
    }
    // if the flag hasn't changed, they are two positive numbers
    // and that never results in a carry

    // "the addition of two positive numbers results in a 
    // negative number."

    // start with the value reset
    flag[2] = false;
    // if AC and R are positive and wind up with a 
    // negative, overflow
    if((byte)(0xFF&AC) > 0 && (byte)(0xFF&R) > 0)
    {
      if(((byte)((byte)(0xFF&AC) + (byte)(0xFF&R))) < 0)
      {
        flag[2] = true;  
      }
    } 
    // if AC and R are both negative and wind up with
    // positive, overflow
    else if((byte)(0xFF&AC) < 0 && (byte)(0xFF&R) < 0)
    {
      if(((byte)((byte)(0xFF&AC) + (byte)(0xFF&R))) > 0)
      {
        flag[2] = true;  
      }
    }
    // if the value ends up negative, set the negative flag
    if(((byte)((byte)(0xFF&AC) + (byte)(0xFF&R))) <0)
    {
      flag[3] = true;
    } else
    {
      flag[3] = false;
    }
  }

  // inac
  // inac increments the accumulator
  public static void inac()
  {
    out.println("inac instruction");
    // INAC1 increment the value in AC by 1, setting/resetting flags
    // as appropriate
    if(((byte)(AC) < 0) && ((byte)(AC+1) >=0))
    {
      flag[1] = true;
    }else{
      flag[1] = false;
    }
    if((byte)(AC) == 127)
    {
      flag[2] = true;
    }else{
      flag[2] = false;
    }
    if((byte)(AC) == -1)
    {
      AC-=0xFF&AC;
    } else{
      AC++;
    }
    // set other flags
    if((byte)AC == 0)
    {
      flag[0] = true;
    } else{
      flag[0] = false;
    }
    if((byte)AC < 0)
    {
      flag[3] = true;
    }else{
      flag[3] = false;
    }
    out.print("inac1 AC = "+(byte)(0xFF&AC));
    printzcvn();
  } 
  
  // clac
  // clac sets the accumulator to 0, sets the Z flag to true,
  // and sets the other flags to false
  public static void clac()
  {
    out.println("clac instruction");
    // CLAC1 set the value in the accumulator to be 0, 
    // set the flags to the appropriate value
    AC -= 0xFF&AC;
    out.print("clac1 AC = "+(byte)(0xFF&AC));
    flag[0] = true;
    flag[1] = false;
    flag[2] = false;
    flag[3] = false;
    printzcvn();
  }
  
  // and
  // and bitwise ands AC and R, then stores the result in AC
  // sets or resets ZN
  public static void and()
  {
    out.println("and instruction");
    // AND1 bitwise ands the two registers, stores in AC
    // set/resets ZN
    AC += -(0xFF&AC) +((0xFF&AC) & (0xFF&R));
    out.print("and1 AC = " + (byte)(0xFF&AC));

    if((byte)(0xFF&AC) == 0)
    {
      flag[0] = true;
    } else{
      flag[0] = false;
    }
    if((byte)(0xFF&AC) < 0)
    {
      flag[3] = true;
    } else{
      flag[3] = false;
    }
    printzcvn();
  }
  
  // or
  // performs bitwise or on AC and R, then stores the result
  // in AC, and sets or resets ZN
  public static void or()
  {
    out.println("or operation");
    // OR1 bitwise or the two registers, store result in AC
    // set/reset ZN
    AC += -(0xFF&AC) +((0xFF&AC) | (0xFF&R));
    out.print("or1 AC = " + (byte)(0xFF&AC));

    if((byte)(0xFF&AC) == 0)
    {
      flag[0] = true;
    } else{
      flag[0] = false;
    }
    if((byte)(0xFF&AC) < 0)
    {
      flag[3] = true;
    } else{
      flag[3] = false;
    }
    printzcvn();
  }
  
  // xor
  // performs bitwise xor on AC and R, then stores the result
  // in AC, and sets or resets ZN 
  public static void xor()
  {
    out.println("xor operation");
    // XOR1 bitwise xor the two registers, store result in AC
    // set/reset ZN
    AC += -(0xFF&AC) +((0xFF&AC) ^ (0xFF&R));
    out.print("xor1 AC = " + (byte)(0xFF&AC));

    if((byte)(0xFF&AC) == 0)
    {
      flag[0] = true;
    } else{
      flag[0] = false;
    }
    if((byte)(0xFF&AC) < 0)
    {
      flag[3] = true;
    } else{
      flag[3] = false;
    }
    printzcvn();
  }
  
  // not
  // performs bitwise not on AC, then stores the result
  // in AC, and sets or resets ZN 
  public static void not()
  {
    out.println("not operation");
    // NOT1 bitwise not AC, store result in AC, set/reset ZN
    AC += -(0xFF&AC) +((0xFF&AC) ^ 0xFF);
    out.print("not1 AC = " + (byte)(0xFF&AC));

    if((byte)(0xFF&AC) == 0)
    {
      flag[0] = true;
    } else{
      flag[0] = false;
    }
    if((byte)(0xFF&AC) < 0)
    {
      flag[3] = true;
    } else{
      flag[3] = false;
    }
    printzcvn();
  }
  
  // jmpc
  // jmpc jumps to the given address if c is 1
  public static void jmpc()
  {
    out.println("jmpc instruction");
    if(flag[1])
    {
      jumper("jmpc");
    }else
    {
      // increment past the uneeded address
      PC+=2;
      out.print("jmpc1 PC = "+((int)0xFFFF&PC));
      printzcvn();
    }
  }
  
  // jv
  // jv jumps to the given address if v is 1
  public static void jv()
  {
    out.println("jv instruction");
    if(flag[2])
    {
      jumper("jv");
    }else
    {
      // increment past the uneeded address
      PC+=2;
      out.print("jv1 PC = "+((int)0xFFFF&PC));
      printzcvn();
    }
  }
  
  // setFlagsRotS
  // setFlagsRotS sets the flags for the shift and rotate routines
  public static void setFlagsRotS()
  {
    // set zero flag
    if((byte)AC == 0)
    {
      flag[0] = true;
    } else
    {
      flag[0] = false;
    }
    // carry flag already dictated by the individual routine
    
    // overflow flag already dictated by the individual routine
    // set negative flag
    if((byte)AC < 0)
    {
      flag[3] = true;
    } else
    {
      flag[3] = false;
    } 
  }

  // rl
  // rl rotates AC left one bit, sets/resets all flags
  public static void rl()
  {
    out.println("rl instruction");
    // RL1 rotates AC left one bit, sets/resets the flags
    // if AC is negative, carry bit will be true
    if((byte)AC < 0)
    {
      flag[1] = true;
    } else
    {
      flag[1] = false;
    }
    // setting overflow flag
    // if it changes sign, overflow
    if((byte)(0xFF&AC)>0 && (byte)((0xFF&AC)<<1) <0)
    {
      flag[2] = true;
    } else if((byte)(0xFF&AC) < 0 && (byte)((0xFF&AC)<<1) >0)
    {
      flag[2] = true;
    } else
    {
      flag[2] = false;
    }
    AC += -(0xFF&AC) +(0xFF&AC << 1);
    // if there is a carried bit, set the LSB to 1
    if(flag[1])
    {
      AC++;
    }
    // set the remaining flags
    setFlagsRotS();

    out.print("rl1 AC = "+(byte)(0xFF&AC));
    printzcvn();
  }
  
  // rr
  // rr rotates AC right one bit, sets/resets all flags
  public static void rr()
  {
    out.println("rr instruction");
    // RR1 rotates AC right one bit, sets/resets all flags
    // need to test it with a temporary variable containing
    // the converted value
    if((byte)AC < 0)
    {
      if(((byte)AC * -1)%2 == 1)
      {
        flag[1] = true;
      } else
      {
        flag[1] = false;
      }  
    }else if((byte)AC%2 == 1)
    {
      flag[1] = true;
    } else
    {
      flag[1] = false;
    }
    int conv;
    conv = (byte)AC & 0xFF;
    
    conv>>>=1;
    if(flag[1])
    {
      conv+=128;
    }
    // setting overflow flag
    // if it changes sign, overflow
    if((byte)(0xFF&AC)>0 && (byte)conv <0)
    {
      flag[2] = true;
    } else if((byte)(0xFF&AC) < 0 && (byte)conv >0)
    {
      flag[2] = true;
    } else
    {
      flag[2] = false;
    }

    AC += -(0xFF&AC)+ conv;
    // if there is a carried bit, set the MSBflags to 1
    
    // set the remaining flags
    setFlagsRotS();

    out.print("rr1 AC = "+(byte)(0xFF&AC));
    printzcvn();
  }
  
  // lsl
  // lsl logical shifts AC left one bit, sets/resets all flags
  public static void lsl()
  {
    out.println("lsl instruction");
    // LSL1 shifts AC left one bit, sets/resets the flags
    // if AC is negative, carry bit will be true
    if((byte)AC < 0)
    {
      flag[1] = true;
    } else
    {
      flag[1] = false;
    }
    // setting overflow flag
    // if it changes sign, overflow
    if((byte)(0xFF&AC)>0 && (byte)((0xFF&AC)<<1) <0)
    {
      flag[2] = true;
    } else if((byte)(0xFF&AC) < 0 && (byte)((0xFF&AC)<<1) >0)
    {
      flag[2] = true;
    } else
    {
      flag[2] = false;
    }
    AC += -(0xFF&AC) +(0xFF&AC << 1);

    // set the flags
    setFlagsRotS();
    out.print("lsl1 AC = "+(byte)(0xFF&AC));
    printzcvn();
  }
  
  // lsr
  // lsr logical shifts AC right one bit, sets/resets all flags
  public static void lsr()
  {
    out.println("lsr instruction");
    // LSR1 shifts AC right one bit, sets/resets all flags
    if((byte)AC < 0)
    {
      if(((byte)AC * -1)%2 == 1)
      {
        flag[1] = true;
      } else
      {
        flag[1] = false;
      }  
    }else if((byte)AC%2 == 1)
    {
      flag[1] = true;
    } else
    {
      flag[1] = false;
    }
    int conv;
    conv = AC & 0xFF;
    conv>>>=1;
    // setting overflow flag
    // if it changes sign, overflow
    if((byte)(0xFF&AC)>0 && (byte)conv <0)
    {
      flag[2] = true;
    } else if((byte)(0xFF&AC) < 0 && (byte)conv >0)
    {
      flag[2] = true;
    } else
    {
      flag[2] = false;
    }
    AC += -(0xFF&AC)+ conv;

    // set the flags
    setFlagsRotS();
    out.print("lsr1 AC = "+(byte)(0xFF&AC));
    printzcvn();
  }
  
  // mvi
  // mvi loads the accumulator with the 8 bits following
  // the instruction
  public static void mvi()
  {
    out.println("mvi instruction");
    // MVI1 read the bits into DR
    DR = readMemory(AR);
    PC++;
    out.println("mvi1 DR = "+((int)0xFF&DR)+" PC = "
      +((int)0xFFFF&PC)+" AR = "+((int)0xFFFF&AR));

    // MVI2 place the bits into AC
    AC -=(0xFF&AC);
    AC += (short)(0xFF&DR);
    out.println("mvi2 AC = "+(byte)(0xFF&AC));

    /*AC = 0x01FF;
    DR = (byte)0xFF;
    out.println(DR);
    out.println(0xFF&DR);
    out.println((short)(DR));
    out.println((short)(0xFF&DR));
    out.println(AC);
    out.println((byte)(AC));
    out.println((byte)(0xFF&AC));
    out.println(0xFF&AC);
    -1
    255
    -1
    255
    511
    -1
    -1
    255

  */

  }
  
  // jn
  // jn jumps to to the given address if n is 1
  public static void jn()
  {
    out.println("jn instruction");
    if(flag[3])
    {
      jumper("jn");
    }else
    {
      // increment past the uneeded address
      PC+=2;
      out.print("jn1 PC = "+((int)0xFFFF&PC));
      printzcvn();
    }
  }
  
  public static void xldac()
  {
    out.println("xldac instruction");
    // XLDAC1 read the high order half of the address into DR
    DR = readMemory(AR);
    PC++;
    AR++;
    out.println("xldac1 AR = "+((int)0xFFFF&AR)+" PC = "
      +((int)0xFFFF&PC)+" DR = "+((int)0xFF&DR));

    // XLDAC2 save the contents of DR
    TR=DR;
    DR=readMemory(AR);
    PC++;
    out.println("xldac2 AR = "+((int)0xFFFF&AR)+" PC = "
      +((int)0xFFFF&PC)
      +" DR = "+((int)0xFF&DR)+" TR = "+((int)0xFF&TR));

    // XLDAC3 form the address AR from TR and DR
    AR = (short)(((0xFF&TR)*256) + (0xFF&DR));
    out.println("xldac3 AR = "+ ((int)0xFFFF&AR));

    // XLDAC4 read in the byte from memory
    DR = readMemory(AR);
    out.println("xldac4 DR = "+ ((short)0xFF&DR));

    // XLDAC5 place DR in the accumulator as the higher order AC
    //    read in the next byte of memory, and place that in the DR
    AC = (short)(DR * 256);

    AR++;
    DR = readMemory(AR);

    out.println("xldac5 AC = "+ AC+" DR = "+DR);
    // XLDAC6 add the lower order value to AC
    AC += 0xFF&DR;

    out.println("xldac6 AC = "+AC);
  }
  public static void xstac()
  {
    out.println("xstac instruction");
    // XSTAC1 read the high order half of the address into DR
    DR = readMemory(AR);
    PC++;
    AR++;
    out.println("xstac1 AR = "+((int)0xFFFF&AR)+" PC = "
      +((int)0xFFFF&PC)
      +" DR = "+((int)0xFF&DR));

    // XSTAC2 save the contents of DR
    TR=DR;
    DR=readMemory(AR);
    PC++;
    out.println("xstac2 AR = "+((int)0xFFFF&AR)+" PC = "
      +((int)0xFFFF&PC)
      +" DR = "+((int)0xFF&DR)+" TR = "+((int)0xFF&TR));

    // XSTAC3 form the address AR from TR and DR
  
    AR = (short)(((0xFF&TR)*256) + (0xFF&DR));
    out.println("xstac3 AR = "+ ((int)0xFFFF&AR));

    // XSTAC4 put the high order value in the accumulator into DR
    DR = (byte)((AC-((0xFF&AC)))/256);
    out.println("xstac4 DR = "+ ((int)0xFF&DR));

    // XSTAC5 put the value in DR into the desired memory location,
    //    set up DR to store the low order value of AC
    writeMemory(AR, DR);
    DR = (byte)(0xFF&AC);
    out.println("xstac5 M[AR] = "+((int)0xFF&readMemory(AR))+" DR = "+(int)DR);

    // XSTAC6 put DR in the next address location
    writeMemory(AR, DR);
    out.println("xstac6 M[AR] = "+((int)0xFF&readMemory(AR)));
  }
  
  public static void xmvac()
  {
    out.println("xmvac instruction");
    // XMVAC1 moves AC to R
    // clear out the bottom short of R
    R -= (0xFFFF&R);
    // add in the bottom short of AC
    R += (0xFFFF&AC);
    out.println("xmvac1 R = "+R);
  }
  public static void xmovr()
  {
    out.println("xmovr instruction");
    // XMVAC1 moves R to AC
    // clear out the bottom short of AC
    AC -= (0xFFFF&AC);
    // add in the bottom short of R
    AC += (0xFFFF&R);
    out.println("xmovr1 AC = "+AC);
  }
  public static void xadd()
  {
    out.println("xadd instruction");
    // XADD1 adds R into AC, and sets the flags as appropriate
    xadd1();

    out.print("xadd1 AC = "+AC+" R = "+R);
    printzcvn();
  }
  public static void xadd1()
  {
    setFlagsXAS();

    AC += R;
  }
  public static void xsub()
  {
    out.println("xsub instruction");
    // XSUB1 complements R, and calls the xadd1() routine
    R = (short)-R;
    xadd1();
    // set R back to its original value
    R = (short)-R;

    out.print("sub1 AC = "+AC);
    printzcvn();
  }
  public static void setFlagsXAS()
  {
    // if the result is 0, set zero flag to true
    if(AC + R == 0)
    {
      flag[0] = true;
    } else
    {
      flag[0] = false;
    }
    // "it represents the result too large in the case of 
    // add or a borrow in the case of subtract."
    
    // start by setting the value false
    flag[1] = false;
    // if 2 negative numbers, always a carry
    if(AC < 0 && R < 0)
    {
      flag[1] = true;
    }
    // otherwise, if one positive and one negative number,
    // if the magnitude of the positive number is >=
    // the magnitude of the negative number, it's a carry
    else if(AC < 0 && R > 0)
    {
      if(R >= -AC)
      {
        flag[1] = true;
      }
    } else if(AC> 0 && R < 0)
    {
      if(AC >= -R)
      {
        flag[1] = true;
      }
    }
    // if the flag hasn't changed, they are two positive numbers
    // and that never results in a carry

    // "the addition of two positive numbers results in a 
    // negative number."

    // start with the value reset
    flag[2] = false;
    // if AC and R are positive and wind up with a 
    // negative, overflow
    if(AC > 0 && R > 0)
    {
      if(((short)(AC + R)) < 0)
      {
        flag[2] = true;  
      }
    } 
    // if AC and R are both negative and wind up with
    // positive, overflow
    else if(AC < 0 && R < 0)
    {
      if(((short)(AC + R)) > 0)
      {
        flag[2] = true;  
      }
    }
    // if the value ends up negative, set the negative flag
    if(((short)(AC + R)) <0)
    {
      flag[3] = true;
    } else
    {
      flag[3] = false;
    }
  }
  public static void xinac()
  {
    out.println("xinac instruction");
    // XINAC1 increment the value in AC by 1, setting/resetting flags
    // as appropriate
    if((AC < 0) && (AC+1 >=0))
    {
      flag[1] = true;
    }else{
      flag[1] = false;
    }
    if(AC> 0 && (short)(AC+1)<0)
    {
      flag[2] = true;
    }else{
      flag[2] = false;
    }
    AC++;
    // set other flags
    if(AC == 0)
    {
      flag[0] = true;
    } else{
      flag[0] = false;
    }
    if(AC < 0)
    {
      flag[3] = true;
    }else{
      flag[3] = false;
    }
    out.print("xinac1 AC = "+AC);
    printzcvn();
  }
  public static void xclac()
  {
    out.println("xclac instruction");
    // XCLAC1 set the value in the accumulator to be 0, 
    // set the flags to the appropriate value
    AC -= 0xFFFF&AC;
    out.print("xclac1 AC = "+AC);
    flag[0] = true;
    flag[1] = false;
    flag[2] = false;
    flag[3] = false;
    printzcvn();
  }
  public static void xand()
  {
    out.println("xand instruction");
    // XAND1 bitwise ands the two registers, stores in AC
    // set/resets ZN
    AC += -(0xFFFF&AC) +((0xFFFF&AC) & (0xFFFF&R));
    out.print("xand1 AC = " + AC);

    if((short)(0xFFFF&AC) == 0)
    {
      flag[0] = true;
    } else{
      flag[0] = false;
    }
    if((short)(0xFFFF&AC) < 0)
    {
      flag[3] = true;
    } else{
      flag[3] = false;
    }
    printzcvn();
  }
  public static void x_or()
  {
    out.println("x_or instruction");
    // X_OR1 bitwise ors the two registers, stores in AC
    // set/resets ZN
    AC += -(0xFFFF&AC) +((0xFFFF&AC) | (0xFFFF&R));
    out.print("x_or1 AC = " + AC);

    if((short)(0xFFFF&AC) == 0)
    {
      flag[0] = true;
    } else{
      flag[0] = false;
    }
    if((short)(0xFFFF&AC) < 0)
    {
      flag[3] = true;
    } else{
      flag[3] = false;
    }
    printzcvn();
  }
  public static void xxor()
  {
    out.println("xxor instruction");
    // XXOR1 bitwise xors the two registers, stores in AC
    // set/resets ZN
    AC += -(0xFFFF&AC) +((0xFFFF&AC) ^ (0xFFFF&R));
    out.print("xxor1 AC = " + AC);

    if((short)(0xFFFF&AC) == 0)
    {
      flag[0] = true;
    } else{
      flag[0] = false;
    }
    if((short)(0xFFFF&AC) < 0)
    {
      flag[3] = true;
    } else{
      flag[3] = false;
    }
    printzcvn();
  }
  public static void xnot()
  {
    out.println("xnot instruction");
    // XNOT1 bitwise nots AC, stores in AC
    // set/resets ZN
    AC += -(0xFFFF&AC) +((0xFFFF&AC) ^ 0xFFFF);
    out.print("xnot1 AC = " + AC);

    if((short)(0xFFFF&AC) == 0)
    {
      flag[0] = true;
    } else{
      flag[0] = false;
    }
    if((short)(0xFFFF&AC) < 0)
    {
      flag[3] = true;
    } else{
      flag[3] = false;
    }
    printzcvn();
  }
  public static void xrl()
  {
    out.println("xrl instruction");
    // XRL1 rotates AC left one bit, sets/resets the flags
    // if AC is negative, carry bit will be true
    if((short)AC < 0)
    {
      flag[1] = true;
    } else
    {
      flag[1] = false;
    }
    // setting overflow flag
    // if it changes sign, overflow
    if((short)(0xFFFF&AC)>0 && (short)((0xFFFF&AC)<<1) <0)
    {
      flag[2] = true;
    } else if((short)(0xFFFF&AC) < 0 && (short)((0xFFFF&AC)<<1) >0)
    {
      flag[2] = true;
    } else
    {
      flag[2] = false;
    }
    AC += -(0xFFFF&AC) +(0xFFFF&AC << 1);
    // if there is a carried bit, set the LSB to 1
    if(flag[1])
    {
      AC++;
    }
    // set the remaining flags
    setFlagsXRotS();

    out.print("xrl1 AC = "+AC);
    printzcvn();
  }
  public static void xrr()
  {
    out.println("xrr instruction");
    // XRR1 rotates AC right one bit, sets/resets all flags
    // need to test it with a temporary variable containing
    // the converted value
    if((short)AC < 0)
    {
      if(((short)AC * -1)%2 == 1)
      {
        flag[1] = true;
      } else
      {
        flag[1] = false;
      }  
    }else if((short)AC%2 == 1)
    {
      flag[1] = true;
    } else
    {
      flag[1] = false;
    }
    int conv;
    conv = (short)AC & 0xFFFF;
    conv>>>=1;
    if(flag[1])
    {
      conv= conv|0x8000;
    }
    // setting overflow flag
    // if it changes sign, overflow
    if((short)(0xFFFF&AC)>0 && (short)conv <0)
    {
      flag[2] = true;
    } else if((short)(0xFFFF&AC) < 0 && (short)conv >0)
    {
      flag[2] = true;
    } else
    {
      flag[2] = false;
    }
    AC += -(0xFFFF&AC)+ conv;
    
    // set the remaining flags
    setFlagsXRotS();
    out.print("xrr1 AC = "+AC);
    printzcvn();
  }
  public static void xlsl()
  {
    out.println("xlsl instruction");
    // XLSL1 shifts AC left one bit, sets/resets the flags
    // if AC is negative, carry bit will be true
    if((short)AC < 0)
    {
      flag[1] = true;
    } else
    {
      flag[1] = false;
    }
    // setting overflow flag
    // if it changes sign, overflow
    if((short)(0xFFFF&AC)>0 && (short)((0xFFFF&AC)<<1) <0)
    {
      flag[2] = true;
    } else if((short)(0xFFFF&AC) < 0 && (short)((0xFFFF&AC)<<1) >0)
    {
      flag[2] = true;
    } else
    {
      flag[2] = false;
    }
    AC += -(0xFFFF&AC) +(0xFFFF&AC << 1);

    out.println(0xFFFF&AC);
    // set the flags
    setFlagsXRotS();
    out.print("xlsl1 AC = "+AC);
    printzcvn();
  }
  public static void xlsr()
  {
    out.println("xlsr instruction");
    // LSR1 shifts AC right one bit, sets/resets all flags
    if((short)AC < 0)
    {
      if(((short)AC * -1)%2 == 1)
      {
        flag[1] = true;
      } else
      {
        flag[1] = false;
      }  
    }else if((short)AC%2 == 1)
    {
      flag[1] = true;
    } else
    {
      flag[1] = false;
    }
    int conv;
    conv = AC & 0xFFFF;
    conv>>>=1;
    // setting overflow flag
    // if it changes sign, overflow
    if((short)(0xFFFF&AC)>0 && (short)conv <0)
    {
      flag[2] = true;
    } else if((short)(0xFFFF&AC) < 0 && (short)conv >0)
    {
      flag[2] = true;
    } else
    {
      flag[2] = false;
    }
    AC += -(0xFFFF&AC)+ conv;

    // set the flags
    setFlagsXRotS();
    out.print("xlsr1 AC = "+AC);
    printzcvn();
  }
  public static void setFlagsXRotS()
  {
    // set zero flag
    if((short)AC == 0)
    {
      flag[0] = true;
    } else
    {
      flag[0] = false;
    }
    // carry flag already dictated by the individual routine
    
    // overflow handled by the individual routine
    // set negative flag
    if((short)AC < 0)
    {
      flag[3] = true;
    } else
    {
      flag[3] = false;
    } 

  }
  public static void xmvi()
  {
    out.println("xmvi instruction");
    // XMVI1 read the higher order byte into DR
    DR = readMemory(AR);
    PC++;
    out.println("xmvi1 DR = "+((int)0xFF&DR)+" PC = "
      +((int)0xFFFF&PC)+" AR = "+((int)0xFFFF&AR));

    // XMVI2 place the bits into AC, read the lower order byte in
    AC = (short)(DR*256);
    AR++;
    PC++;
    DR = readMemory(AR);

    out.println("xmvi2 AC = "+AC+" DR = "+DR);
    // XMVI3 place the lower order byte in AC
    AC+=((int)0xFF&DR);
    out.println("xmvi3 AC = "+AC);
  }
  // halt
  // ends the program in decode
  public static void halt()
  {
    out.println("halt instruction");
  }
}

