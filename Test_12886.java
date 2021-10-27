import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.print.attribute.HashAttributeSet;

public class Test_12886 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] stones = new int[3];
		int sum = 0;
		for(int i = 0; i < 3; i++) {
			stones[i] = Integer.parseInt(st.nextToken());
			sum += stones[i];
		}
		
		if(sum % 3 != 0)
			System.out.println(0);
		else
			System.out.println(bfs(stones));
	}
	
	public static int bfs(int[] stones) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(stones);
		Set<String> visit = new HashSet<String>();
		int ans = 0;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			if(now[0] == now[1] && now[1] == now[2]) {
				ans = 1;
				break;
			}
			
			for(int i = 0; i < 3; i++) {
				for(int j = i + 1; j < 3; j++) {
					if(now[i] == now[j])
						continue;
					int x = now[i] < now[j]? i: j;
					int y = now[i] < now[j]? j: i;
					int[] next = Arrays.copyOf(now, 3);
					next[x] += now[x];
					next[y] -= now[x];
					String nextKey = "" + next[x] + " " + next[y];
					if(visit.contains(nextKey))
						continue;
					visit.add(nextKey);
					q.add(next);
				}
			}
		}
		
		return ans;
	}
	
}