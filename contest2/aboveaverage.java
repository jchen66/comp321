import java.util.Scanner;

public class aboveaverage {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int c=sc.nextInt();
		for(int i=0; i<c;i++) {
			int n=sc.nextInt();
			int[] students= new int[n];
			int total=0;
			//store student data
			for(int j=0; j<n;j++) {
				
				int tmp= sc.nextInt();
				students[j]=tmp;
				total += tmp;
			}
			double avg= total/(double)n;
			int numStudent=0;
			for(int j=0; j<n;j++) {
				if((double)students[j]>avg) {
					numStudent++;
				}
			}
			System.out.println(String.format("%.3f",(numStudent/(double)n)*100)+"%");
		}
	}

}
