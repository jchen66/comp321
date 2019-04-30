import java.util.Scanner;

public class oddbinom {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n =sc.nextLong(); // value of n for the Odd Binomial
		//int numOddBinCoeff=0;
		long numOddBinCoeff= getOdd(n);
		//he number of odd entries in row N of Pascal's Triangle is 2 raised to the number of 1's in the binary expansion of N.
		//run too slow --> O(n) ; need constant running time --> find pattern
//		for(long i=0;i<n;i++) {
//			//convert row number to binary expression
//			long num1=Long.bitCount(i);
//			long oddInRow= (1<<num1); //exponent 2
//			
//			//System.out.println(oddInRow);
//			numOddBinCoeff+=oddInRow;
//			//System.out.println(" "+(i+1)+" " + binString+" : "+oddInRow+" = "+numOddBinCoeff);
//		}
		System.out.println(numOddBinCoeff);
	} 
	// use Sierpinski triangle
	/*
	 * f:=proc(n) option remember;
	 * https://oeis.org/A006046
		if n <= 1 then n elif n mod 2 = 0 then 3*f(n/2)
		else 2*f((n-1)/2)+f((n+1)/2); fi; end;	
	 * */
	public static long getOdd(long n) {
		//System.out.println(n);
		//base cases for first and 2nd row (add 1s to the extremities)
		if(n<2) {
			//.out.println("base case");
			return n;
		}
		//https://www.usma.edu/math/AY2008%20Problems/POTW-0802-12-Solution.pdf
		//recursion on [3 * getOdd ( row n-1) ] --> divide by 2
		// a recurrence relation for the number of zeros in a Sierpinski triangle with 2n rows
		// recursion?
		long a_n1=3*getOdd(n>>1); //shift right (divide by 2)
		//if odd: 
		// 		--> return 3*a_n1 + 
		if(n%2!=0) {
			//System.out.println("	n%2: "+ (1L <<(Long.bitCount(n-1))));
			//q_n1 + 2^n-1
			return a_n1 + (1L <<(Long.bitCount(n-1))); //3* a(row - 1) + //shifts bit pattern to the left
		}
		//else even
		return a_n1;
	}
}
