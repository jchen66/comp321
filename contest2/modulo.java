
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class modulo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n=10;
		int num_mod=0;
		Set<Integer> mod_num = new HashSet<Integer>(); 
		for(int i=0; i<n;i++) {
			int tmp =sc.nextInt();
			int tmp_n=(tmp % 42);
			if(!mod_num.contains(tmp_n)) {
				num_mod++;
				mod_num.add(tmp_n);
			}
		}
		System.out.println(num_mod);
	}
}
