import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 수열의 크기
		
		int[] a = new int[n]; // 수열
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] LIS1 = new int[n]; // 왼쪽부터 시작하여 i를 끝으로 하는 최장길이
		int[] LIS2 = new int[n]; // 오른쪽부터 시작하여 i를 끝으로 하는 최장길이
		for(int i = 0; i < n; i++) {
			LIS1[i] = 1;
			for(int j = 0; j < i; j++) {
				if(a[j] < a[i] && LIS1[j] >= LIS1[i]) {
					LIS1[i] = LIS1[j] + 1;
				}
			}
		}
		
		for(int i = n - 1; i >= 0; i--) {
			LIS2[i] = 1;
			for(int j = n - 1; j > i; j--) {
				if(a[j] < a[i] && LIS2[j] >= LIS2[i]) {
					LIS2[i] = LIS2[j] + 1;
				}
			}
		}
		
		int max = 0;
		for(int i = 0; i < n; i++) {
			int sum = LIS1[i] + LIS2[i] - 1; // -1을 하는 이유 -> 두개의 최장길이를 더하면 i가 두번 둘어간다 그러므로 하나 제거
			max = max < sum? sum: max;
		}
		System.out.println(max);
	}
}