import java.io.*;
import java.util.*;

public class Test_1781 {

	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine()); // 숙제의 개수
		int[][] problem = new int[n][2]; // 데드라인, 컵라면 수
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			problem[i][0] = Integer.parseInt(st.nextToken());
			problem[i][1] = Integer.parseInt(st.nextToken());
			cnt += problem[i][1];
		}
		
		// 데드라인이 빠른 순으로 데드라인이 같다면 컵라면의 수가 많은 순으로 
		Arrays.sort(problem, (o1, o2) -> {
			return Integer.compare(o1[0], o2[0]);
		});
		
		int idx = 0; // 문제의 idx
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // 컵라면 수가 작은 순으로 
		for(int i = 1; i <= n; i++) {
			while(idx < n && problem[idx][0] <= i) {
				pq.add(problem[idx][1]);
				idx++;
			}
			
			while(pq.size() > i) {
				cnt -= pq.poll();
			}
		}
		
		System.out.println(cnt);
	}
}