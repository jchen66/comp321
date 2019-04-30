import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class sofas {
	public static void main(String[] args) throws IOException {
		int nStyles=36;
		int nColors=36;
		
		Scanner sc = new Scanner(System.in);
		//number of test scenarios
		int n = sc.nextInt();
		while(n>0) {
			//Scenario n has m sofas avail
			//m<=100
			int m= sc.nextInt();
			sc.nextLine();

			int[][] sofaAvail=new int[nStyles+1][nColors+1];
			//have an array of sofa styles + numbers 
			int[] sofaStyles=new int[nStyles+1];
			int[] sofaColors= new int[nColors+1];
			//gather all the info for each sofa
			for(int i=0; i<m;i++) {
				String tmp=sc.nextLine();
				String[] avail=tmp.split(" ");
				// notice column and row 0 are all empty
				int style= Integer.parseInt(avail[0]);
				int color= Integer.parseInt(avail[1]);
				
				//update matrix and the arrays
				sofaAvail[style][color]=color;
				sofaStyles[style]=sofaStyles[style]+1;
				sofaColors[color]=sofaColors[color]+1;
			}
			//Find cliques
			//the max k of m sofas is 
			int maxK=findMaxK(m);
			int cliqueK=1;		//minK is 1 , as there is at least 1 sofa of 1 color  (as m>0)
	
			//if maxK is 1 (i.e there are less than 4 sofas avail) --> no need to compute directly print out "1"
			//else: do algorithm
			if(maxK==1) {
				System.out.println(cliqueK);
			}
			else {
				/*
				 * So this code that I used does not work, 
				 * 		it finds the sofa style with the most colors and gets the min value of possible intersection with other sofa styles
				 * 					(by comparing the arrays)
				 * 
				 * The correct approach would have been to create a graph using a adjacency matrix
				 * 			and finding the maximum clique (vertex being the different colors and the subsets with different edges connecting the vertex as the different sofa styles)
				 * 				^using intersection sets
				 * 
				 * */
				
				
				//find row with most colors 
				int mostColor=0; //row index
				for(int i=0;i<sofaColors.length;i++) {
					if(mostColor<sofaColors[i]) {
						mostColor=i;
					}
				}
				//get the sofa style with the most available color
				int[] maxSofa= sofaAvail[mostColor];
				int minK=maxK;
				//check for max array that matches our maxSofa 
				for(int i=0;i<sofaAvail.length;i++) {
					if(i==mostColor) {
						continue;
					}
					int cmp= iDegree(sofaAvail[mostColor],sofaAvail[i]);
					if(cmp<minK && cmp>1) {
						minK=cmp;
					}
					
				}
				cliqueK=minK;
//				// starting from maxK
//				for(int k=2;k<=maxK;k++) {
//					boolean isPossible=false;
//					//if for k it's not possible for have k sofas of k colors --> then it's not possible to find for k+1
//					// then break out of forloop
//					if(!isPossible) {
//						break;
//					}
//				}
				
				System.out.println(cliqueK);
			}
			n--;
			
			
		}
	}
	//return how many intersections between the two arrays
	public static int iDegree(int[] a, int[]b){
		int[] intersect=Arrays.stream(a).distinct().filter(x -> Arrays.stream(b).anyMatch(y -> y == x)).toArray();
		return intersect.length;
    }
	
	//returns the intersection values
	public static int[] intersection(int[] a, int[]b) {
		return Arrays.stream(a).distinct().filter(x -> Arrays.stream(b).anyMatch(y -> y == x)).toArray();
		
	}
	
	/*
	 * returns styles(in number from 1 to 36) that are in color c*/
	public static int[] findEquivColumn(boolean [][] m,int c) {
		List<Integer> styles = new ArrayList<Integer>();
		for(int r=0;r<m.length;r++) {
			//if row r has the column (i.e m[r][c] == true)
			if(m[r][c]) {
				styles.add(r);
			}
		}
		return styles.stream().mapToInt(i -> i).toArray();
	}
	
	/*Find the elements in a row that are the same*/
	public static int[] findSameElRow(int[]a,int[]b) {
		List<Integer> intersection = new ArrayList<Integer>();
		for(int i=0; i<a.length;i++) {
			if(a[i]==b[i]) {
				intersection.add(i);
			}
		}
		return intersection.stream().mapToInt(i -> i).toArray();
	}

	/*Find max k given the number of sofas*/
	public static int findMaxK(int m) {
		return (int) Math.floor(Math.sqrt(m));
	}
	
	/*Get max element in a array
	 * Returns [index of max element, max value]*/
	public static int[] largest(int[] arr){ 
        // Initialize maximum element 
        int[] max = new int[2];
        max[0]=0;  //index
        max[1]=arr[0];	//value
       
        // Traverse array elements from second and 
        // compare every element with current max   
        for (int i = 1; i < arr.length; i++) 
            if (arr[i] > max[1]) {
            	max[1] = arr[i];  //value
            	max[0]=i;		//index
            }
                
       
        return max; 
    } 
}
