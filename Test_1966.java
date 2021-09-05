import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1966 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int[][] num = new int[n][2];
			for(int i = 0; i < n; i++) {
				num[i][0] = i;
				num[i][1] = Integer.parseInt(st.nextToken());
			}
			int[][] rank = new int[n][3];
			Arrays.sort(num, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[1] == o2[1])
						return Integer.compare(o1[0], o2[0]);
					return Integer.compare(o2[1], o1[1]);
				}
			});
			int beforeIdx = 0;
			for(int i = 0; i < n; i++) {
				int idx = num[i][0];
				rank[idx][0] = idx; // 번호
				rank[idx][1] = num[i][1]; // 중요도
				rank[idx][2] = (i + 1);
				// 중요도 같은 경우
				if(i != 0 && rank[beforeIdx][1] == rank[idx][1]) {
					rank[idx][2] = rank[beforeIdx][2];
				}
				beforeIdx = idx;
			}
			Deque<Printer> dq = new ArrayDeque<Printer>();
			for(int i = 0; i < n; i++) {
				dq.add(new Printer(rank[i][0], rank[i][1], rank[i][2]));
			}
			
			int cnt = 1;
			while(!dq.isEmpty()) {
				Printer now = dq.poll();
				if(now.rank > cnt) {
					dq.add(now);
				} else {
					if(m == now.num)
						break;
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}
	
	public static class Printer{
		int important;
		int num;
		int rank;
		public Printer(int num, int important, int rank) {
			this.num = num;
			this.important = important;
			this.rank = rank;
		}
	}
}
