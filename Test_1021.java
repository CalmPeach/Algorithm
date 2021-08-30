import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
 
public class Test_1021 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] nums = new int[m];
		for(int i = 0; i < m; i++)
			nums[i] = Integer.parseInt(st.nextToken()) - 1;
		
		int size = n;
		int cnt = 0;
		for(int i = 0; i < m; i++) {
			int s = nums[i]; // 시작 위치와의 차이
			int e = size - nums[i]; // 마지막 위치와의 차이
			if(s <= e) { // 시작 위치가 더 가깝다면 왼쪽으로 이동
				for(int j = i + 1; j < m; j++) {
					nums[j] -= (s + 1);
					if(nums[j] < 0)
						nums[j] = size + nums[j];
				}
				cnt += s;
			} else if(s > e){ // 마지막 위치가 더 가깝다면 오른쪽으로 이동
				for(int j = i + 1; j < m; j++) {
					nums[j] += (e - 1);
					if(nums[j] >= size)
						nums[j] = nums[j] % size;
				}
				cnt += e;
			}
			size--;
		}
		System.out.println(cnt);
	}
}
