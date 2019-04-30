import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class exactchange2 {
	public static void main(String[] args) {
		int maxPrice=10000;
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int numCases=sc.nextInt();
		
		//scan the next numCases
		for(int currCase=0; currCase<numCases;currCase++) {
			
			//use Dynamic Programming 
			int[] dp_money = new int[maxPrice+1];
			Set<Integer> optSoln= new HashSet<>();
			
//			for(int i=0;i<dp_money.length;i++) {
//				dp_money[i]=10001;         //NOPE doesn't work --> need
//			}
//			dp_money[0]=1;
			//price(in cents) for currCase
			int price=sc.nextInt();
			int numBills=sc.nextInt();
			int[] wallet=new int[numBills];
			//store the bills in our array
			for(int i=0;  i<numBills;i++) {
				wallet[i]=sc.nextInt();
			}
			//assume that the inputs are in ascending order?
			//sort the array
			Arrays.sort(wallet);
			
//			System.out.println("sorted!");
			//DP Programming Bottom Up
			for(int i=numBills-1;i>=0;i--) {
				//minimize # bills --> try to use largest bill/coins first
				int currMoney=wallet[i];
				//start with number of bills --> 1
				dp_money[currMoney]=1; //keep track of how many numbers of that money(coin/bill) we need
				
				
				Set<Integer> tmpCash=new HashSet<>();
				tmpCash.add(currMoney);
				//add currMoney to optSoln set if it does not exceed the price with the opt Soln
				for(int m:optSoln) {
					int sum= currMoney+m;
					//first condition: the sum of the money shouldn't not exceed maxPrice
					//as no item has price higher than that
//					System.out.println(Arrays.toString(dp_money));
					if(sum<=maxPrice) {
						//if num at the sum place in the dp array is 0 --> assign the #of bills in the prev case+1
						//track the sum
//						if(dp_money[sum]>dp_money[m]+1){
						if(dp_money[sum]==0) {
							dp_money[sum]=dp_money[m]+1;
							tmpCash.add(sum);
						}
						//if the sum has been reached before by a previous combination of bills/coins
						//but the current uses less bills --> assign it
						else if(dp_money[sum]>(dp_money[m]+1)) {
							dp_money[sum]=dp_money[m]+1;
						}
					}
				}
				for(int myCash:tmpCash) {
					optSoln.add(myCash); //since sets remove duplicates
				}
			}
//			boolean isOpt=false; // for test reasons
			//since the sum of all the coins must at least be th eprice itself --> start by looking at pos price
			for(int i=price;i<maxPrice+1;i++) {
				//since we made dp_money starting from largest bills --> 
				//first element not 0--> is the minimized optimal soln
				if(dp_money[i]!=0) {
					//System.out.println("minimized opt solution!");
					String ans=""+i+" "+(dp_money[i]);
					System.out.println(ans);
//					isOpt=true;
					//minimize
					break;
				}
			}
//			if(!isOpt) {
//				System.out.println("impossible");
//			}
//			System.out.println("done");
		} 
	}
}