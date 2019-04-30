import java.util.Scanner;
import java.util.Arrays;

public class busnumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] bus = new int[n];
        for (int i = 0; i < n; i++) {
            bus[i] = sc.nextInt();
        }
        Arrays.sort(bus); 
        
        int count = 0;
        int skip = 0;

        while(count < n) {
            if (skip == 0) {
                if (count + 2 < n && bus[count+1] == bus[count] + 1 && bus[count+2] == bus[count] +2) {
                    System.out.print(bus[count] + "-");
                    skip = 1;
                    count=count+2;
                } 
                else {
                    System.out.print(bus[count] + " ");
                    count++;
                }
            }
            else {
                if (count + 1 < n) {
                    if (bus[count+1] != bus[count] + 1) {
                        System.out.print(bus[count] + " ");
                        skip = 0;
                    }
                }
                else {
                    System.out.print(bus[count]);
                }
                count++;
            }
        }
        System.out.println();
    }

}