package vbasket;

import java.util.Scanner;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class VBasket {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int sumTmp=0;
		int num=0;
		int sum=0;
		Set<Integer> indexes = new HashSet<>();
		int[] veggies= new int [n];
		//scan the next n integers
		//store the next n integers in veggies[]
		//store the indexes to get the power set
		for(int i=0; i<n;i++) {
			num=sc.nextInt();
			veggies[i]= num; 
			indexes.add(i);
		}
		
		//find power set of the indexes
		Set<Set<Integer>> ans = powerSet(indexes);
		//add the sum 
		for (Set<Integer> intSet : ans) {
			int weight = 0;
			//check for empty {}
			if (intSet.isEmpty()) {
				continue;
			}
			//add the sum for all the indexes
			for (Integer w : intSet) {
				weight += veggies[w];
			}
			if (weight >= 200) {
				sum += weight;
			}
		}
		System.out.println(sum);
	}
	
	public static <T> Set<Set<T>> powerSet(Set<T> indexes) {
	    Set<Set<T>> powersets = new HashSet<Set<T>>();
	    //return empty set if there are no indexes
	    //shouldn't happen --> since n>=1
	    if (indexes.isEmpty()) {
	        powersets.add(new HashSet<T>());
	        return powersets;
	    }
	    List<T> list = new ArrayList<T>(indexes);
	    //start from the first element
	    T base = list.get(0);
	    System.out.println("base: "+base);
	    //get sublists(by applying powersets)
	    //use sets to avoid duplicates
	    Set<T> rest = new HashSet<T>(list.subList(1, list.size())); 
	    
	    // 
	    for (Set<T> subset :powerSet(rest)) {
//	    	System.out.println("subset: "+subset);
	        Set<T> tmpSet = new HashSet<T>();
	        tmpSet.add(base);
	        tmpSet.addAll(subset);
	        powersets.add(tmpSet);
	        powersets.add(subset);
	    }       
	    return powersets;
	}  
	
}
