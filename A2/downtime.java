package Downtime;


import java.util.Scanner;


public class Downtime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		//get n and k
		//init the n following timestamps
		int n = sc.nextInt();
		int maxRequest= sc.nextInt();
		int[] timeStamps = new int[n];
		//collect the time stamps (sorted in chronological order already)
		for(int i = 0; i < n; i++){
			timeStamps[i] = sc.nextInt();
		}
	
		int numServer = 1;
		int cur = 0;
		int newReq = 0;
		for (int i=0; i < n; i++) {
			cur++;
			
			//while time > next timestamp 
			while(timeStamps[i] >= timeStamps[newReq]+1000) {
				newReq++;
				cur--;
			}
			if(cur>numServer) {
				numServer=cur;
			}
		}
		
		System.out.println((int)Math.ceil((double) numServer/maxRequest));
	}

}
