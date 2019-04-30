import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class allaboutthatbase {
	public static void main(String[] args) throws IOException {
		//possible bases
		char[] bases = new char[] { '1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0'};
		//max base possible is 10+26(alphabet)
		int b=36;
		
		//set up scanner
		//first input from user is n (the # of following expr to evaluate)
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		//parse through all expressions to evaluate
		while (n> 0) {
			//retrieve the expressions and separate in form of x op y = z
			String[] expr = sc.nextLine().split(" ");
			String x = expr[0];
			String op = expr[1];
			String y = expr[2];
			String z = expr[4];
			
			//System.out.println("Expression : "+x+op+y+"="+z);
			
			//we know that the base type is at least 1
			//asciiVal=max digit    i.e: max Base possible... if max digit is 9 --> then max base is 10
			int startBase = 1;
			char asciiVal = Character.MIN_VALUE;
			boolean hasZero = false;
			
			String[] strArray= new String[] {x,y,z};
			// go through all the numbers x,y,z
			//check to max bases or 0s
			for (String s : strArray)
				for (char c : s.toCharArray()) {
					if (c>asciiVal)
						asciiVal = c;
					if (c == '0' && !hasZero)
						hasZero = true;
				}
			
			//get the minimal base to start with 
			if (hasZero || asciiVal != '1' ) {
				startBase=getMinBase(asciiVal);
			}
			
			//append all valid bases that the expression is valid for in ascending order
			//start with minimum valid bases
			StringBuilder validBases = new StringBuilder();
			for (int currBase = startBase; currBase <= b; currBase++) {
				//if currBase is 1--> have to check the length of the str instead of the changing into decimal values
				if (currBase == 1) {
					boolean isBaseOne=isExprBaseOne(x,y,z,op);
					if(isBaseOne) {
						validBases.append("1");
					}
					
				} 
				else {
					if (currBase == 0) {
						currBase = b;
					}

					int dec=10;
					//all the valid bases
					ArrayList<Character> currBases = new ArrayList<>(currBase);
					if (currBase == 1) {
						currBases.add('1');
					}
					//if its base 2-10 ---> just get the value of it 
					else if (currBase >= 2 && currBase <=dec) {
						for (int i= 0;i <currBase; i++) {
							currBases.add(String.valueOf(i).charAt(0));
						}
					}
					//if its base 11-36, change the alphabetical value to its corresponding decimal values
					//i.e.: a--> 11, b -->12, c-->13....
					else{
						for (int i =0; i <dec; i++) {
							currBases.add(String.valueOf(i).charAt(0));
						}
						for (int i =0; i <currBase-dec; i++) {
							currBases.add((char)('a'+i));
						}
					}
					
					//change the values of the numbers into decimal numbers
					int x_dec = base10(x, currBases);
					int y_dec = base10(y, currBases);
					int z_dec = base10(z, currBases);
					
					//if the expression is valid in base 10--> add the base to the list
					if(isExprBase(x_dec,y_dec,z_dec,op)) {
						validBases.append(bases[currBase-1]);
					}
				}
			}
			
			//if there are no valid bases --> print "Invalid"
			if(validBases.length()!=0) {
				System.out.println(validBases);
			}
			else {
				System.out.println("invalid");
			}
			
			//go to next line(expression)
			n--;
		}
		sc.close();
	}
	/*
	 *Check if the expression given is valid in decimal*/
	public static boolean isExprBase(int x, int y,int z, String op) {
		boolean isbase=false;
		switch(op) {
		case "+":
			isbase= (x+y==z);
			break;
		case "-":
			isbase= (x-y==z);
			break;
		case "*":
			isbase=(x*y==z);
			break;
		default:
			//default case --> division
			isbase=(z*y==x);
			break;
			
		}
		return isbase;
	}
	/*
	 * Change number into base 10
	 * Use exponents*/
	public static int base10(String d, ArrayList<Character> bDigits) {
		int b10 = 0;
		int dl=d.length();
		for (int i = 0; i < dl; i++)
			b10 += bDigits.indexOf(d.charAt(i))*Math.pow(bDigits.size(), dl-i-1);
		return b10;
	}
	
	/*
	 * For operations in base 1 --> use the length of the str 
	 * to check if the expression given is valid in unary*/
	public static boolean isExprBaseOne(String x_str, String y_str,String z_str, String op) {
		boolean isbase=false;
		int x=x_str.length();
		int y=y_str.length();
		int z=z_str.length();
		switch(op) {
		case "+":
			isbase= (x+y==z);
			break;
		case "-":
			isbase= (x-y==z);
			break;
		case "*":
			isbase=(x*y==z);
			break;
		default:
			//default case --> division
			isbase=(z*y==x);
			break;
			
		}
		return isbase;		
	}
	/*
	 * Get minimum Base*/
	public static int getMinBase(int num) {
		int minBase=1;
		//if it is a digit(i.e not of base 11 and +)
		//min base = digits+1
		if (Character.isDigit(num)) {
			int base = Character.getNumericValue(num) + 1;
			if(base>1) {
				minBase=base;
			}
			else {
				minBase=1;
			}
		}
		//if the minBase is more than 11
		else {
			minBase = num-'a'+11;
		}
		//System.out.println("minBase: "+minBase);
		return minBase;
		
	}
}