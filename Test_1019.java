import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1019 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int start = 1;
		int point = 1;
		int[] ans = new int[10];
		
		while(start <= n) {
			while(n % 10 != 9 && start <= n) {
				calc(n, point, ans);
				n--;
			}
			
			if(start > n)
				break;
			
			while(start % 10 != 0 && start <= n) {
				calc(start, point, ans);
				start++;
			}
			
			start /= 10;
			n /= 10;
			for(int i = 0; i < 10; i++)
				ans[i] += (n - start + 1) * point;
			
			point *= 10;
		}
		
		for(int i = 0; i < 10; i++)
			System.out.print(ans[i] + " ");
	}
	
	public static void calc(int n, int point, int[] ans) {
		while(n / 10 != 0) {
			ans[n % 10] += point;
			n /= 10;
		}
		ans[n % 10] += point;
	}
}