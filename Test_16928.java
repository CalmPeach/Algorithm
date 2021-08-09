import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Test_16928 {	
	
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt(); // 사다리의 수
		int M = scan.nextInt(); // 뱀의 수 
		
		HashMap<Integer, Integer> info = new HashMap<Integer, Integer>(); // 사다리와 뱀 정보
		
		for(int i = 0; i < N + M; i++) {
			int v1 = scan.nextInt();
			int v2 = scan.nextInt();
			info.put(v1, v2);
		}
		
		System.out.println(solution(info));
	}
	
	static int solution(HashMap<Integer, Integer> info) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		int[] visit = new int[101];
		visit[1] = 1;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(now == 100) break;
			
			for(int i = 1; i <= 6; i++) {
				int next = now + i;
				// 사다리 혹은 뱀이 있니?
				Integer changeNext = info.get(next);
				if(changeNext != null) next = changeNext;
				
				if(next >= 1 && next <= 100 && visit[next] == 0) {
					visit[next] = visit[now] + 1;
					q.add(next);
				}
			}
		}

		return visit[100] - 1;
	}
}
