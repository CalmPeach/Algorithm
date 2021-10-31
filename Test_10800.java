import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Test_10800 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine()); // 공의 개수
		
		int[][] ball = new int[n][3];
		int[] c = new int[n + 1]; // 해당 색에 해당하는 공들의 합
		int total = 0; // 모든 공들의 합
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			ball[i][0] = i; // 공 번호
			ball[i][1] = Integer.parseInt(st.nextToken()); // 공 색
			ball[i][2] = Integer.parseInt(st.nextToken()); // 공 크기
			c[ball[i][1]] += ball[i][2];
			total += ball[i][2];
		}
		
		// 공 크기를 기준으로 내림차순
		Arrays.sort(ball, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o2[2], o1[2]);
			}
		});
		
		int[] ans = new int[n]; // 답
		for(int i = 0; i < n; i++) {
			total -= ball[i][2];
			c[ball[i][1]] -= ball[i][2];
			
			// 동일한 크기를 가진 공들 처리
			int tmp = i + 1;
			int tmpTotal = total;
			int tmpColor = c[ball[i][1]];
			while(tmp < n && ball[i][2] == ball[tmp][2]) { // i번째 공과 크기가 동일하다면
				tmpTotal -= ball[tmp][2];
				if(ball[i][1] == ball[tmp][1]) // i번째 공과 색이 동일하다면
					tmpColor -= ball[tmp][2];
				tmp++;
			}
			
			ans[ball[i][0]] = tmpTotal - tmpColor; // i번째 공보다 크기가 작은 공들의 합 - i번째 공보다 크기가 작으면서 색이 동일한 공들의 합
		}
		
		StringBuilder rs = new StringBuilder();
		for(int i = 0; i < n; i++) {
			rs.append(ans[i] + "\n");
		}
		System.out.println(rs.toString());
	}
	
}