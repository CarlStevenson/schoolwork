// rscpu4.java
// Relatively Simple CPU
// Program to simulate a cpu in order to demonstrate the inner functionality
// of a cpu
// Written by: Carl Stevenson
// 3/16/15
// rscpu4 adds and, or, xor and not instructions
// *************************************************************************


import java.io.*;
import java.util.*;

public class rscpu4
{
	// Registers to be used in the CPU
	// double the neccesarry size due to needed bits and nonexistant 
	// unsigned data types in Java

	// 8 bit accumulator
	public static byte AC;
	// 8 bit general purpose register
	public static byte R;
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
			M[i] = (byte)(0xFF&Short.valueOf(code, 16));
			i++;
		}
	}

	// regStat
	// Prints the values in the registers for use after each operation
	public static void regStat()
	{
		out.print("Current Data: AC = "); out.print(AC);
		out.print(", R = "); out.print(R);
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
		DR = M[0xFFFF&AR];
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
			case 255: halt();
							return false;
			default:
			out.println("Invalid opcode!");
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
	
	// ldac
	// ldac places the value in the given address into the accumulator
	public static void ldac()
	{
		
		out.println("ldac instruction");
		// LDAC1 read the high order half of the address into DR
		DR = M[0xFFFF&AR];
		PC++;
		AR++;
		out.println("ldac1 AR = "+((int)0xFFFF&AR)+" PC = "
			+((int)0xFFFF&PC)+" DR = "+((int)0xFF&DR));

		//LDAC2 save the contents of DR
		TR=DR;
		DR=M[0xFFFF&AR];
		PC++;
		out.println("ldac2 AR = "+((int)0xFFFF&AR)+" PC = "
			+((int)0xFFFF&PC)
			+" DR = "+((int)0xFF&DR)+" TR = "+((int)0xFF&TR));

		// LDAC3 form the address AR from TR and DR
		AR = (short)(((0xFF&TR)*16) + (0xFF&DR));
		out.println("ldac3 AR = "+ ((int)0xFFFF&AR));

		// LDAC4 read in the byte from memory
		DR = M[0xFFFF&AR];
		out.println("ldac4 DR = "+ ((int)0xFF&DR));

		// LDAC5 place DR in the accumulator
		AC = DR;
		out.println("ldac5 AC = "+ AC);
		
	}
	
	// stac
	// stac stores the accumulator in the given address
	public static void stac()
	{
		out.println("stac instruction");
		// STAC1 read the high order half of the address into DR
		DR = M[0xFFFF&AR];
		PC++;
		AR++;
		out.println("stac1 AR = "+((int)0xFFFF&AR)+" PC = "
			+((int)0xFFFF&PC)
			+" DR = "+((int)0xFF&DR));

		//STAC2 save the contents of DR
		TR=DR;
		DR=M[0xFFFF&AR];
		PC++;
		out.println("stac2 AR = "+((int)0xFFFF&AR)+" PC = "
			+((int)0xFFFF&PC)
			+" DR = "+((int)0xFF&DR)+" TR = "+((int)0xFF&TR));

		// STAC3 form the address AR from TR and DR
	
		AR = (short)(((0xFF&TR)*16) + (0xFF&DR));
		out.println("stac3 AR = "+ ((int)0xFFFF&AR));

		// STAC4 put the value in the accumulator into DR
		DR = AC;
		out.println("stac4 DR = "+ ((int)0xFF&DR));

		// STAC5 put the value in DR into the desired memory location
		M[0xFFFF&AR] = DR;
		out.println("stac5 M[AR] = "+((int)0xFF&M[0xFFFF&AR]));
	}
	
	// mvac
	// mvac places the value in AC into the R register
	public static void mvac()
	{
		out.println("mvac instruction");
		// MVAC1 put value in AC into the R register
		R = AC;
		out.println("mvac1 R = "+R);
	}

	// movr
	// movr puts the value in the R register in the accumulator
	public static void movr()
	{
		out.println("movr instruction");
		// MOVR1 puts the value in the R register into the accumulator
		AC = R;
		out.println("movr1 AC = "+AC);
	}
	
	public static void jump()
	{
		out.println("jump");
	} 

	public static void jmpz()
	{
		out.println("jmpz");
	}

	public static void jpnz()
	{
		out.println("jpnz");
	} 

	public static void add()
	{
		out.println("add");
	}
	
	public static void sub()
	{
		out.println("sub");
	}

	// inac
	// inac increments the accumulator
	public static void inac()
	{
		out.println("inac instruction");
		// INAC1 increment the value in AC by 1, setting/resetting flags
		// as appropriate
		if((AC < 0) && (AC+1 >=0))
		{
			flag[1] = true;
		}else{
			flag[1] = false;
		}
		if(AC == 127)
		{
			AC = -128;
			flag[2] = true;
		}else{
			AC++;
			flag[2] = false;
		}
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
		out.print("inac1 AC = "+AC);
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
	
	// clac
	// clac sets the accumulator to 0, sets the Z flag to true,
	// and sets the other flags to false
	public static void clac()
	{
		out.println("clac instruction");
		// CLAC1 set the value in the accumulator to be 0, 
		// set the flags to the appropriate value
		AC = 0;
		out.print("clac1 AC = "+AC);
		flag[0] = true;
		flag[1] = false;
		flag[2] = false;
		flag[3] = false;
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
	
	// and
	// and bitwise ands AC and R, then stores the result in AC
	// sets or resets ZN
	public static void and()
	{
		out.println("and instruction");
		// AND1 bitwise ands the two registers, stores in AC
		// set/resets ZN
		AC = (byte)(AC & R);
		out.print("and1 AC = " + AC);

		if(AC == 0)
		{
			flag[0] = true;
		} else{
			flag[0] = false;
		}
		if(AC < 0)
		{
			flag[3] = true;
		} else{
			flag[3] = false;
		}
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
	
	// or
	// performs bitwise or on AC and R, then stores the result
	// in AC, and sets or resets ZN
	public static void or()
	{
		out.println("or operation");
    // OR1 bitwise or the two registers, store result in AC
    // set/reset ZN
		AC = (byte)(AC | R);
		out.print("or1 AC = " + AC);

		if(AC == 0)
		{
			flag[0] = true;
		} else{
			flag[0] = false;
		}
		if(AC < 0)
		{
			flag[3] = true;
		} else{
			flag[3] = false;
		}
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
	
  // xor
  // performs bitwise xor on AC and R, then stores the result
  // in AC, and sets or resets ZN 
	public static void xor()
	{
		out.println("xor operation");
    // XOR1 bitwise xor the two registers, store result in AC
    // set/reset ZN
    AC = (byte)(AC ^ R);  // bitwise xor = ^
    out.print("xor1 AC = " + AC);

    if(AC == 0)
    {
      flag[0] = true;
    } else{
      flag[0] = false;
    }
    if(AC < 0)
    {
      flag[3] = true;
    } else{
      flag[3] = false;
    }
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
	
  // not
  // performs bitwise not on AC, then stores the result
  // in AC, and sets or resets ZN 
	public static void not()
	{
		out.println("not operation");
    // NOT1 bitwise not AC, store result in AC, set/reset ZN
    AC = (byte)(AC ^ -1); // AC xor 11111111 
    out.print("not1 AC = " + AC);

    if(AC == 0)
    {
      flag[0] = true;
    } else{
      flag[0] = false;
    }
    if(AC < 0)
    {
      flag[3] = true;
    } else{
      flag[3] = false;
    }
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
	
	public static void jmpc()
	{
		out.println("jmpc");
	}
	
	public static void jv()
	{
		out.println("jv");
	}
	
	public static void rl()
	{
		out.println("rl");
	}
	
	public static void rr()
	{
		out.println("rr");
	}
	
	public static void lsl()
	{
		out.println("lsl");
	}
	
	public static void lsr()
	{
		out.println("lsr");
	}
	
	// mvi
	// mvi loads the accumulator with the 8 bits following
	// the instruction
	public static void mvi()
	{
		out.println("mvi instruction");
		// MVI1 read the bits into DR
		DR = M[0xFFFF&AR];
		PC++;
		out.println("mvi1 DR = "+((int)0xFF&DR)+" PC = "
			+((int)0xFFFF&PC)+" AR = "+((int)0xFFFF&AR));

		// MVI2 place the bits into AC
		AC = DR;
		out.println("mvi2 AC = "+AC);


	}
	
	public static void jn()
	{
		out.println("jn");
	}
	
	// halt
	// ends the program in decode
	public static void halt()
	{
		out.println("halt instruction");
	}


}