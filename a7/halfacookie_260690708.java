import java.util.Scanner;

public class halfacookie {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			double r=sc.nextDouble();
			double x=sc.nextDouble();
			double y=sc.nextDouble();
			
			//System.out.println("YUS");
			//System.out.println(r);
			//check if outside of cookie
			double dist = Math.sqrt((Math.pow(x,2) + Math.pow(y,2))) ;
			if(dist>r) {
				System.out.println("miss");
			}
			else {
				//calculate entire cookie surface--> pi*r^2
				double cookie_area= Math.PI *Math.pow(r,2);
				//calculate angle
				double angle= 2*Math.acos(dist/r);
				//calculate piecewise area
				double piece1= Math.pow(r,2)*(angle - Math.sin(angle))/2;
				double piece2= cookie_area-piece1;
				//format 6 decimal places
				System.out.println(String.format("%.6f",piece2)+" "+String.format("%.6f",piece1));
						
			}
		}
	}
}
