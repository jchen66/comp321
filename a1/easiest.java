import java.util.Scanner;

public class TheEasiestProblemIsThisOne {
	
	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		
		// get input n 
		int n= sc.nextInt();
		
		
		//if input from scanner= 0 --> end of test cases
		while (n != 0) {
			
			int nSum= digitSum(n);
			//scan from smallest number --> we want to minimize p
			for (int p = 11; p <= Integer.MAX_VALUE; p++) {
				if (digitSum(p*n) == nSum) {
					System.out.println(p);
					break;
				}
			}
			n = sc.nextInt();
		}
		sc.close();

	}
	
	/*Takes in an integer
	 * Returns the sum of all its digits
	 * */
	private static int digitSum(int num) {
		
		int ans= 0;
		while (num > 0) {
			ans= ans + num % 10;
			num= num / 10;
		}
		return ans;
	}

}