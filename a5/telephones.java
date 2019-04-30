import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class telephones {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()) {
			int n =sc.nextInt();
			int m= sc.nextInt();
			if(n==0) {
				break;
			}
			int[][] callConnections= new int[n][2];
			//ArrayList<Integer> callConnections = new ArrayList<Integer>();
			//collect information about the telephone calls
			for(int i=0; i<n;i++) {
				//we don't actually need src and destination
				int src= sc.nextInt();
				int dest= sc.nextInt();
				
				int start=sc.nextInt();
				int duration=sc.nextInt();
				callConnections[i]= new int[] {start, start+duration};
			}
			
			//collect the information of the time intervals the police are interested in 
			for(int i=0;i<m;i++) {
				int numCalls=0;
				int targetStart=sc.nextInt();
				int dur=sc.nextInt();
				//check for how many calls fall within the interval
				for(int j=0;j<callConnections.length;j++) {
					if(isWithinInterval(targetStart,targetStart+dur,callConnections[j][0],callConnections[j][1])) {
						numCalls++;
					}
				}
				System.out.println(numCalls);
			}
		}
		
	}
	public static boolean isWithinInterval(int startTarget,int endTarget, int start, int end) {
		//if it starts earlier than the startTarget, check if if the end time is after the start time(min interlap=1sec)
		if(start<startTarget && end>startTarget){
			return true;
		}
		//if it starts later than the startTarget, check if the start time is before the endTarget
		if(start>=startTarget && start<endTarget) {
			return true;
		}
		return false;
	}
}
