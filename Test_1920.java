import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Test_1920 {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine()); // n개의 점수
		st = new StringTokenizer(br.readLine());
		int[] a = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] nums = new int[m];
		for(int i = 0; i < m; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(a);
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < m; i++) {
			if(Arrays.binarySearch(a, nums[i]) >= 0)
				result.append(1);
			else
				result.append(0);
			result.append("\n");
		}
		System.out.println(result.toString());
	}
}
