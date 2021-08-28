import java.io.*;
import java.util.*;
 
public class Test_7570 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int[] nums = new int[n]; // 줄서있는 순서
    	for(int i = 0; i < n; i++)
    		nums[i] = Integer.parseInt(st.nextToken());
    	
    	int[] cnt = new int[n + 1]; // idx번의 부분수열 길이
    	int max = -1;
    	for(int i = 0; i < n; i++) {
    		cnt[nums[i]] = cnt[nums[i] - 1] + 1;
    		if(max < cnt[nums[i]])
    			max = cnt[nums[i]];
    	}
    	System.out.println(n - max);
    }
 
}   