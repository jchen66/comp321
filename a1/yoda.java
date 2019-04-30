package Yoda;

import java.util.Scanner;

public class Yoda {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// get the two numbers
		String s1= sc.next();
		String s2= sc.next();
		
		String a1="";
		String a2="";
		
		//check for equal length "strings/integers"
		while(s1.length() != s2.length()) {
			//add 0 in front of s2
			if(s1.length()>s2.length()) {
				s2= "0"+ s2;
			}
			//add 0 in front of s1
			else {
				s1= "0"+s1;
			}
			
		}
		
		//Start comparing now that the strings are of equal length
		for(int i=0; i<s1.length();i++) {
			
			int n1= Integer.parseInt(s1.substring(i, i+1));
			int n2= Integer.parseInt(s2.substring(i, i+1));
			
			
			//delete smallest digit from its string
			//if n1<n2, add n2 to a2
			if(n1<n2) {
				a2= a2+n2;
			}
			//if n2<n1, add n1 to a1
			else if(n2<n1) {
				a1=a1+n1;
			}
			//if n1=n2, add n1 to a1; n2 to a2
			else {
				a1=a1+n1;
				a2=a2+n2;
			}
		}
		
		//if one of the strings is ""(aka empty) --> b/c all of its digits are < than all of the digit of the other string
		//print "YODA"
		if(a1=="") {
			System.out.println("YODA");
		}else {
			System.out.println(Integer.parseInt(a1));
		}
		
		if(a2=="") {
			System.out.println("YODA");
		}else {
			System.out.println(Integer.parseInt(a2));
		}
		sc.close();
	}
}
