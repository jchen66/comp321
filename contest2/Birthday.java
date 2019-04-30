import java.util.Scanner;

public class Birthday {
	public static boolean check(int[] arr) {
		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1) ans++;
		}
		if (ans > 1) return true;
		else return false;
	}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextInt()) {
        	int a = sc.nextInt();
        	int b = sc.nextInt();

        	if (a == 0 && b == 0) return;

        	int[][] grid = new int[a][a];

        	for (int i = 0; i < b; i++) {
        		int x = sc.nextInt();
        		int y = sc.nextInt();
        		grid[x][y] = 1;
        		grid[y][x] = 1;
        	}

        	boolean result = true;
        	for (int j = 0; j < a; j++) {
        		if (check(grid[j]) == false) result = false;
        	}
        	if (result == false) System.out.println("Yes");
        	else System.out.println("No");
        }
    }

}