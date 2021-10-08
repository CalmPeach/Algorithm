import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Test_1205 {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // n개의 점수
		int score = Integer.parseInt(st.nextToken()); // 송유진의 새로운 점수
		int p = Integer.parseInt(st.nextToken());
		
		if(n != 0) {
			st = new StringTokenizer(br.readLine());
		}
		int[] scores = new int[n]; // 정수
		
		for (int i = 0; i < n; i++)
			scores[i] = Integer.parseInt(st.nextToken());
		
		int minIdx = 0;
		for(minIdx = 0; minIdx < n; minIdx++) {
			if(scores[minIdx] < score)
				break;
		}
		
		int maxIdx = minIdx - 1;
		while(maxIdx >= 0 && scores[maxIdx] <= score)
			maxIdx--;
		
		int rank = maxIdx + 1;
		int cnt = 0;
		if(minIdx - maxIdx != 1) {
			cnt = minIdx - maxIdx - 1;
		} 
		
		if(rank + cnt < p) {
			System.out.println(rank + 1);
		} else {
			System.out.println(-1);
		}
	}
}
