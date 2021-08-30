import java.io.*;
import java.util.*;
 
public class Test_2839 {
	
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	
    	int n = scan.nextInt();
    	System.out.println(solution(n));
    }

    public static int solution(int n) {
    	int maxValue = n / 5; 
    	int value = n;
    	while(maxValue != -1) { // n = 6: 5 * 1 + 3 * ? -> 5 * 0 + 3 * ? 5에 곱해지는 수를 점점 줄여가며 가능한 조합을 모두 해본다
    		value = n - maxValue * 5;
    		if(value % 3 == 0)
    			break;
    		maxValue--;
    	}
    	
    	if(maxValue == -1 && value % 3 != 0)
    		return -1;
    	return maxValue + value / 3;
    }
}

/*
// DP 사용
import java.io.*;
import java.util.*;

public class Test_1463 {
	
	static int n;
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[3] = 1;
		if(n >= 5) {
			dp[5] = 1;
		}
		
		int result = dfs(n);
		System.out.println(result == Integer.MAX_VALUE? -1: result);
	}
	
	public static int dfs(int n) {
		if(n < 3)
			return Integer.MAX_VALUE;
		if(dp[n] != Integer.MAX_VALUE)
			return dp[n];
		
		int r1 = dfs(n - 3);
		int r2 = dfs(n - 5);
		if(r1 != Integer.MAX_VALUE || r2 != Integer.MAX_VALUE)
			dp[n] = Math.min(r1, r2) + 1;
		
		return dp[n];
	}
	
}
*/