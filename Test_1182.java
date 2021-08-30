import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1182 {
	
	static int cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= n; i++) {
			solution(arr, new int[i], i, s);
		}
		System.out.println(cnt);
	}
	
	public static void solution(int[] items, int[] bucket, int k, int m) {
		if(k == 0) {
			int sum = 0;
			for(int i = 0; i < bucket.length - k; i++) {
				sum += items[bucket[i]];
			}
			if(sum == m)
				cnt++;
			return;
		}
		
		int lastIndex = bucket.length - k - 1;
		for(int i = 0; i < items.length; i++) {
			if(bucket.length == k) {
				bucket[0] = i;
				solution(items, bucket, k - 1, m);
			} else if(bucket[lastIndex] < i) {
				bucket[lastIndex + 1] = i;
				solution(items, bucket, k - 1, m);
			}
		}
	}
}