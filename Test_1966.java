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
			int n = Integer.parseInt(st.nextToken()); // n개의 문서
			int m = Integer.parseInt(st.nextToken()); // m번째 문서
			st = new StringTokenizer(br.readLine());
			
			Queue<int[]> q = new LinkedList<int[]>(); // 문서를 담는 Queue
			int[] rank = new int[n]; // 문서의 중요도
			for(int i = 0; i < n; i++) {
				rank[i] = Integer.parseInt(st.nextToken());
				q.add(new int[] {i, rank[i]});
			}
			
			Arrays.sort(rank);
			int rIdx = n - 1; // 문서중 가장 높은 중요도 
		
			while(!q.isEmpty()) {
				int[] now = q.poll(); // 현재 문서
				if(now[1] != rank[rIdx]) {
					q.add(now);
				} else {
					if(now[0] == m) // m 문서를 인쇄함
						break;
					rIdx--;
				}
			}
			
			System.out.println(n - rIdx);
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
