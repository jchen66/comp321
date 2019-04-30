import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class narrowartgallery {
	public static int n;
	public static HashMap<String, Integer> dp=new HashMap<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n =sc.nextInt();
		int k = sc.nextInt();
		int orig_k=k;
		int[][] gallery= new int[n][2];
		int opt_count=0;
//		Set<Integer[]> dp= new HashSet<>();
		
		
		//populate gallery array
		for(int i=0; i<n;i++) {
			gallery[i][0]=sc.nextInt();
			gallery[i][1]=sc.nextInt();
		}
		sc.nextInt();
		sc.nextInt();
		int count=0;
		
		if (k<=n){
			count += getOptimalPlan(0,-1,gallery,k);
		}
		System.out.println(count);
		
	}
	
	public static int getOptimalPlan(int room, int isPrevClosed,int[][] array_dp, int k) {
		int maxVal=0;
		//isClosed: -1 --> not closed
		//			0 --> closed on row 0
		// 			1 --> closed on row 1
		//Integer[] tmp = {room,isPrevClosed,k};
		String tmp=""+room+"."+isPrevClosed+"."+k;
		//if we already have the value in memory --> do not have to calculate it again
		if (dp.containsKey(tmp)){
			return dp.get(tmp);
		}
		// if there are still galleries(rows) to close
		else if((k<n-room) && (room<n)) {
			//if its not closed
			if(isPrevClosed==-1) {
				//predict for future row -->for if row 0 is closed 
				//predict for future row -->for if row 1 is closed 
				//predict for future row -->for if none of the rows are closed 
				int row0=array_dp[room][0]+getOptimalPlan(room+1, 0,array_dp,k-1);
				int row1=array_dp[room][1]+getOptimalPlan(room+1,1,array_dp,k-1);
				int noRow=array_dp[room][0]+array_dp[room][1]+getOptimalPlan(room+1,-1,array_dp,k);
				int currMax= Math.max(noRow, Math.max(row0, row1));
				dp.put(tmp,currMax);
//				System.out.println(currMax);
				return currMax;
			}
			// if row 0 was closed
			if(isPrevClosed ==0) {
				//predict for future row -->for if row 0 is closed 
				//predict for future row -->for if row 1 is closed 
				//predict for future row -->for if none of the rows are closed 
				int row0=array_dp[room][0]+getOptimalPlan(room+1, 0,array_dp,k-1);
				int noRow=array_dp[room][0]+array_dp[room][1]+getOptimalPlan(room+1,-1,array_dp,k);
				int currMax= Math.max(row0, noRow);
				dp.put(tmp,currMax);
//				System.out.println(currMax);
				return currMax;
			}
			//if row 1 was closed
			if(isPrevClosed==1) {
				//predict for future row -->for if row 0 is closed 
				//predict for future row -->for if row 1 is closed 
				//predict for future row -->for if none of the rows are closed 
				int row1=array_dp[room][1]+getOptimalPlan(room+1,1,array_dp,k-1);
				int noRow=array_dp[room][0]+array_dp[room][1]+getOptimalPlan(room+1,-1,array_dp,k);
				int currMax= Math.max(noRow, row1);
				dp.put(tmp,currMax);
//				System.out.println(currMax);
				return currMax;
			}
			
		}
		//if the num of rows to close is equal to the number of rows left
		else if(k==n-room && room<n) {
			//if its not closed
			if(isPrevClosed==-1) {
				//predict for future row -->for if row 0 is closed 
				//predict for future row -->for if row 1 is closed 
				//predict for future row -->for if none of the rows are closed 
				int row0=array_dp[room][0]+getOptimalPlan(room+1, 0,array_dp,k-1);
				int row1=array_dp[room][1]+getOptimalPlan(room+1,1,array_dp,k-1);
				int currMax= Math.max(row0, row1);
				dp.put(tmp,currMax);
//				System.out.println(currMax);
				return currMax;
			}
			// if row 0 was closed
			if(isPrevClosed ==0) {
				//predict for future row -->for if row 0 is closed 
				//predict for future row -->for if row 1 is closed 
				//predict for future row -->for if none of the rows are closed 
//				int row0=array_dp[room][0]+getOptimalPlan(room+1, 0,array_dp,k-1);
//				int row1=array_dp[room][1]+array_dp[room][0]+getOptimalPlan(room+1,-1,array_dp,k-1);
				int currMax= array_dp[room][0]+getOptimalPlan(room+1, 0,array_dp,k-1);
				dp.put(tmp,currMax);
//				System.out.println(currMax);
				return currMax;
			}
			//if row 1 was closed
			if(isPrevClosed==1) {
				//predict for future row -->for if row 0 is closed 
				//predict for future row -->for if row 1 is closed 
				//predict for future row -->for if none of the rows are closed 
//				int row1=array_dp[room][1]+getOptimalPlan(room+1,1,array_dp,k-1);
//				int noRow=array_dp[room][0]+array_dp[room][1]+getOptimalPlan(room+1,-1,array_dp,k);
				int currMax= array_dp[room][1]+getOptimalPlan(room+1,1,array_dp,k-1);
				dp.put(tmp,currMax);
//				System.out.println(currMax);
				return currMax;
			}
		}
		
		return maxVal;
		
		//opt_count += getMaxLayout(n,gallery,0,-1,k);
									//N,v,r,unclosed,k
	}
}
